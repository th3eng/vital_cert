package com.th3eng.vitalCert.citizen;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/citizen")
@RequiredArgsConstructor
public class CitizenController {

    private final AuthenticationService service;

    //register citizen
    @PostMapping("/auth/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }

    //login citizen
    @PostMapping("/auth/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticateRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }

    //demo
    @GetMapping("/sayHello")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello");
    }
}
