package com.th3eng.vitalCert.service;

import com.th3eng.vitalCert.repository.CitizenRepository;
import com.th3eng.vitalCert.dto.AddCitizenRequest;
import com.th3eng.vitalCert.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CitizenRepository citizenRepository;

    //    create new citizen
    public ResponseEntity<?> createCitizen(AddCitizenRequest newCitizen) {
        //check if all data filled
        if (newCitizen.getSsn().trim().length() != 14 || !newCitizen.getSsn().trim().matches("[0-9]+")) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "SSN is required and must be 14 characters"));
        } else if (newCitizen.getFirstName().trim().isEmpty() ||
                newCitizen.getFirstName().trim().matches(".*\\d.*") ||
                newCitizen.getFirstName().trim().matches(".*[!@#$%^&*()_+=|<>?{}\\[\\]~-].*")) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("success", false, "message", "First name is required and must not contain numbers or special characters"));
        } else if (newCitizen.getMiddleName().trim().isEmpty() ||
                newCitizen.getMiddleName().trim().matches(".*\\d.*") ||
                newCitizen.getMiddleName().trim().matches(".*[!@#$%^&*()_+=|<>?{}\\[\\]~-].*")) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("success", false, "message", "Middle name is required and must not contain numbers or special characters"));
        } else if (newCitizen.getLastName().trim().isEmpty() ||
                newCitizen.getLastName().trim().matches(".*\\d.*") ||
                newCitizen.getLastName().trim().matches(".*[!@#$%^&*()_+=|<>?{}\\[\\]~-].*")) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("success", false, "message", "Last name is required and must not contain numbers or special characters"));
        } else if (newCitizen.getBirthDate().after(Date.from(new Date().toInstant()))) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Birth date must be in past"));
        } else if (newCitizen.getPlace_of_birth().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Place of birth is required"));
        } else if (newCitizen.getAddress().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Address is required"));
        } else if (newCitizen.getFather_ssn().trim().length() != 14 || !newCitizen.getFather_ssn().trim().matches("[0-9]+")) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Father's SSN is required and must be 14 characters"));
        } else if (newCitizen.getMother_ssn().trim().length() != 14 || !newCitizen.getMother_ssn().trim().matches("[0-9]+")) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Mother's SSN is required and must be 14 characters"));
        } else if (newCitizen.getGender_id()>2 || newCitizen.getGender_id()<1){
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Gender is required"));
        }

        //TODO: not completed yet

        return ResponseEntity.ok(Map.of("success", true, "message", "Citizen created successfully"));


    }
}
