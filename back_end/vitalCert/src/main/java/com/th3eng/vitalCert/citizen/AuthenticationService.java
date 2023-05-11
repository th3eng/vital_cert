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

        if (request.getSsn().trim().isEmpty()) {
            return AuthenticationResponse.builder()
                    .message("SSN is required").build();
        } else if (request.getPassword().trim().isEmpty()) {
            return AuthenticationResponse.builder()
                    .message("Password is required").build();
        } else if (request.getSsn().length() != 14) {
            return AuthenticationResponse.builder()
                    .message("SSN must be 14 characters").build();
        } else if (repository.findBySsn(request.getSsn()).isEmpty()) {
            return AuthenticationResponse.builder()
                    .message("Citizen not exists").build();
        } else if (request.getPassword().length() < 8) {
            return AuthenticationResponse.builder()
                    .message("Password must be at least 8 characters").build();
        }


        var optional = repository.findBySsn(request.getSsn());
        if (optional.isPresent()) {
            //if password is already exist in database return error message
            if (!optional.get().getPassword().isEmpty()) {
                return AuthenticationResponse.builder()
                        .message("this user already exists").build();
            }

            // if password is not exist in database set the new password
            var citizen = optional.get();
            citizen.setPassword(passwordEncoder.encode(request.getPassword()));
            repository.save(citizen);
        }

        var citizen = Citizen.builder()
                .ssn(request.getSsn())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        var jwtToken = jwtService.generateToken(citizen);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .message("Citizen registered successfully")
                .build();
    }

    //login
    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getSsn(), request.getPassword()));

        var user = repository.findBySsn(request.getSsn()).orElseThrow(
                () -> new RuntimeException("Citizen not found"));

        if(user.getPassword().isEmpty()){
            return AuthenticationResponse.builder()
                    .message("Citizen not exists").build();
        }

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            return AuthenticationResponse.builder()
                    .message("Wrong password").build();
        }

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .message("Citizen logged in successfully")
                .build();
    }
}