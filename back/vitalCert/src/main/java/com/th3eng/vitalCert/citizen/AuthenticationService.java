package com.th3eng.vitalCert.citizen;

import com.th3eng.vitalCert.config.JwtService;
import com.th3eng.vitalCert.utils.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final CitizenRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        if (request.getSsn() == null || request.getSsn().isEmpty()) {
            return AuthenticationResponse.builder()
                    .message("SSN is required").build();
        } else if (request.getPassword() == null || request.getPassword().isEmpty()) {
            return AuthenticationResponse.builder()
                    .message("Password is required").build();
        } else if (request.getFirstName() == null || request.getFirstName().isEmpty()) {
            return AuthenticationResponse.builder()
                    .message("First name is required").build();
        } else if (request.getLastName() == null || request.getLastName().isEmpty()) {
            return AuthenticationResponse.builder()
                    .message("Last name is required").build();
        } else if (repository.findBySsn(request.getSsn()).isPresent()) {
            return AuthenticationResponse.builder()
                    .message("Citizen already exists").build();
        } else if (request.getSsn().length() != 14) {
            return AuthenticationResponse.builder()
                    .message("SSN must be 14 characters").build();
        } else if (request.getPassword().length() < 8) {
            return AuthenticationResponse.builder()
                    .message("Password must be at least 8 characters").build();
        }

        Citizen citizen = Citizen.builder()
                .ssn(request.getSsn())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(Role.USER)
                .build();
        repository.save(citizen);
        var jwtToken = jwtService.generateToken(citizen);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .message("Citizen registered successfully")
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getSsn(), request.getPassword()));
        var user = repository.findBySsn(request.getSsn()).orElseThrow(
                () -> new RuntimeException("Citizen not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .message("Citizen logged in successfully")
                .build();
    }
}
