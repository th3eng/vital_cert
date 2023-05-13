package com.th3eng.vitalCert.service;

import com.th3eng.vitalCert.repository.EmployeeRepository;
import com.th3eng.vitalCert.utils.JwtService;
import com.th3eng.vitalCert.dto.AuthenticateRequest;
import com.th3eng.vitalCert.dto.ResetRequest;
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
public class EmployeeAuthenticationService {
    private final EmployeeRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

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

        //implement the user
        var optional = repository.findBySsn(request.getSsn());

        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Invalid username/password supplied"
            ));
        }
        var user = optional.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "success", false,
                    "message", "Invalid username/password supplied"
            ));
        }

        var token = jwtService.generateToken(user);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Logged in successfully",
                "token", token
        ));
    }

    public ResponseEntity<?> reset(ResetRequest request) {
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

        //implement the user
        var optional = repository.findBySsn(request.getSsn());

        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Invalid username/password supplied"
            ));
        }
        var user = optional.get();

        if (request.getPassword().isEmpty() || request.getNewPassword().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Password is empty"
            ));
        }

        if (!request.getPassword().equals(request.getNewPassword())) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Password doesn't match"
            ));
        }

        //save the new password in database
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        repository.save(user);

        var token = jwtService.generateToken(user);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Password changed successfully",
                "token", token
        ));
    }
}
