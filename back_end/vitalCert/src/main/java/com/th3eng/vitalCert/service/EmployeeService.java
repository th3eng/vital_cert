package com.th3eng.vitalCert.service;

import com.th3eng.vitalCert.model.Citizen;
import com.th3eng.vitalCert.model.Role;
import com.th3eng.vitalCert.repository.*;
import com.th3eng.vitalCert.dto.AddCitizenRequest;
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
    private final GenderRepository genderRepository;
    private final NationalityRepository nationalityRepository;
    private final SocialStatusRepository socialStatusRepository;
    private final MilitaryConditionRepository militaryConditionRepository;

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
        } else if (newCitizen.getGender_id()>2 || newCitizen.getGender_id()<1 || genderRepository.findById(newCitizen.getGender_id()).isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Gender is required"));
        } else if (newCitizen.getNationality_id()<1 || nationalityRepository.findById(newCitizen.getNationality_id()).isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Nationality is required"));
        } else if (newCitizen.getSocial_status_id()<1 || socialStatusRepository.findById(newCitizen.getSocial_status_id()).isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Social status is required"));
        } else if (newCitizen.getMilitary_condition_id()<1 || militaryConditionRepository.findById(newCitizen.getMilitary_condition_id()).isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Military condition is required"));
        }

        //add the new citizen to database
        var citizen = Citizen.builder()
                .ssn(newCitizen.getSsn())
                .firstName(newCitizen.getFirstName())
                .middleName(newCitizen.getMiddleName())
                .lastName(newCitizen.getLastName())
                .birthDate(newCitizen.getBirthDate())
                .place_of_birth(newCitizen.getPlace_of_birth())
                .address(newCitizen.getAddress())
                .father_ssn(newCitizen.getFather_ssn())
                .mother_ssn(newCitizen.getMother_ssn())
                .gender_id(newCitizen.getGender_id())
                .nationality_id(newCitizen.getNationality_id())
                .religion_id(newCitizen.getReligion_id())
                .social_status_id(newCitizen.getSocial_status_id())
                .military_condition_id(newCitizen.getMilitary_condition_id())
                .role(Role.USER)
                .build();
        citizenRepository.save(citizen);

        return ResponseEntity.ok(Map.of("success", true, "message", "Citizen added successfully"));





    }
}
