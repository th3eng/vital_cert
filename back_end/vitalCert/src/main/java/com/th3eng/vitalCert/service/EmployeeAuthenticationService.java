package com.th3eng.vitalCert.service;

import com.th3eng.vitalCert.dto.EmployeeRegisterRequest;
import com.th3eng.vitalCert.model.Citizen;
import com.th3eng.vitalCert.model.Employee;
import com.th3eng.vitalCert.model.Role;
import com.th3eng.vitalCert.model.User;
import com.th3eng.vitalCert.repository.EmployeeRepository;
import com.th3eng.vitalCert.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> authenticate(AuthenticateRequest request) {
        System.out.println("request = " + request.getSsn());
        System.out.println("request = " + request.getPassword());
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getSsn(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Invalid username/password supplied 1"
            ));
        }

        var user = repository.findBySsn(request.getSsn()).orElseThrow(
                () -> new RuntimeException("Employee not found"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "success", false,
                    "message", "Invalid username/password supplied 2"
            ));
        }

        var jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Logged in successfully",
                "token", jwtToken
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

    public ResponseEntity<?> register(EmployeeRegisterRequest request) {
        var optional = repository.findBySsn(request.getSsn());
        if (optional.isPresent()) {
            //if password is already exist in database return error message
            if (!optional.get().getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "message", "Employee account already exists"
                ));
            }
        }

        var employee = Employee.builder()
                .ssn(request.getSsn())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.EMPLOYEE)
                .build();

        repository.save(employee);
        var user = User.builder()
                .ssn(request.getSsn())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.EMPLOYEE)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(employee);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Employee registered successfully",
                "token", jwtToken
        ));
    }
}
