package com.th3eng.vitalCert.controller;

import com.th3eng.vitalCert.dto.AddCitizenRequest;
import com.th3eng.vitalCert.dto.AuthenticateRequest;
import com.th3eng.vitalCert.dto.EmployeeRegisterRequest;
import com.th3eng.vitalCert.dto.ResetRequest;
import com.th3eng.vitalCert.service.EmployeeAuthenticationService;
import com.th3eng.vitalCert.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;
    private final EmployeeAuthenticationService authenticateService;

    //login employee
    @PostMapping("/auth/authenticate2")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticateRequest request) {
        return authenticateService.authenticate(request);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody EmployeeRegisterRequest request) {
        return authenticateService.register(request);
    }

    //reset password
    @PostMapping("/auth/reset")
    public ResponseEntity<?> reset(@RequestBody ResetRequest request){
        return authenticateService.reset(request);
    }

    //create citizen
    @PostMapping("/createCitizen")
    public ResponseEntity<?> createCitizen(@RequestBody AddCitizenRequest newCitizen){
        return service.createCitizen(newCitizen);
    }

}
