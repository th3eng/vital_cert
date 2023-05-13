package com.th3eng.vitalCert.controller;

import com.th3eng.vitalCert.service.CitizenAuthenticationService;
import com.th3eng.vitalCert.dto.AuthenticateRequest;
import com.th3eng.vitalCert.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/citizen")
@RequiredArgsConstructor
public class CitizenController {

    private final CitizenAuthenticationService service;

    //register citizen
    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return service.register(request);
    }

    //login citizen
    @PostMapping("/auth/authenticate")
    public ResponseEntity<?> login(@RequestBody AuthenticateRequest request) {
        return service.authenticate(request);
    }
}
