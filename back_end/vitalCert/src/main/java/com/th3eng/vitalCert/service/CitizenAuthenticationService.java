package com.th3eng.vitalCert.service;

import com.th3eng.vitalCert.model.Citizen;
import com.th3eng.vitalCert.repository.CitizenRepository;
import com.th3eng.vitalCert.utils.JwtService;
import com.th3eng.vitalCert.dto.AuthenticateRequest;
import com.th3eng.vitalCert.dto.RegisterRequest;
import com.th3eng.vitalCert.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CitizenAuthenticationService {
    private final CitizenRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> register(RegisterRequest request) {

        if (request.getSsn().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "SSN is required"
            ));
        } else if (request.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Password is required"
            ));
        } else if (request.getSsn().length() != 14) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "SSN must be 14 characters"
            ));
        } else if (repository.findBySsn(request.getSsn()).isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Citizen not exists"
            ));
        } else if (request.getPassword().length() < 8) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Password must be at least 8 characters"
            ));
        }

        var optional = repository.findBySsn(request.getSsn());
        if (optional.isPresent()) {
            //if password is already exist in database return error message
            if (!optional.get().getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "message", "Citizen account already exists"
                ));
            }
        }

        var citizen = Citizen.builder()
                .ssn(request.getSsn())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        var jwtToken = jwtService.generateToken(citizen);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Citizen registered successfully",
                "token", jwtToken
        ));
    }

    //login
    public ResponseEntity<?> authenticate(AuthenticateRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getSsn(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Invalid username/password supplied"
            ));
        }

        var user = repository.findBySsn(request.getSsn()).orElseThrow(
                () -> new RuntimeException("Citizen not found"));

        if(user.getPassword().isEmpty()){
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Invalid username/password supplied"
            ));
        }

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "success", false,
                    "message", "Invalid username/password supplied"
            ));
        }

        var jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Logged in successfully",
                "token", jwtToken
        ));
    }
}