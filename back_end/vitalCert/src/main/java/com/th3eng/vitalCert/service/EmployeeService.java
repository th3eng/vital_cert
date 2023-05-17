package com.th3eng.vitalCert.service;

import com.th3eng.vitalCert.dto.AuthenticateRequest;
import com.th3eng.vitalCert.model.Citizen;
import com.th3eng.vitalCert.model.Role;
import com.th3eng.vitalCert.repository.*;
import com.th3eng.vitalCert.dto.AddCitizenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CitizenRepository citizenRepository;
    private final GenderRepository genderRepository;
    private final NationalityRepository nationalityRepository;
    private final SocialStatusRepository socialStatusRepository;
    private final MilitaryConditionRepository militaryConditionRepository;
    private final AuthenticationManager authenticationManager;


    //    create new citizen
    public ResponseEntity<?> createCitizen(AddCitizenRequest newCitizen) {
        //check if the ssn is already exists
        var optional = citizenRepository.findBySsn(newCitizen.getSsn());
        if (optional.isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "SSN already exists"));
        }

        //check if all data filled

        try {
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
            } else if (newCitizen.getGender_id() > 2 || newCitizen.getGender_id() < 1 || genderRepository.findById(newCitizen.getGender_id()).isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Gender is required"));
            } else if (newCitizen.getNationality_id() < 1 || nationalityRepository.findById(newCitizen.getNationality_id()).isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Nationality is required"));
            } else if (newCitizen.getSocial_status_id() < 1 || socialStatusRepository.findById(newCitizen.getSocial_status_id()).isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Social status is required"));
            } else if (newCitizen.getMilitary_condition_id() < 1 || militaryConditionRepository.findById(newCitizen.getMilitary_condition_id()).isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Military condition is required"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "All data is required"));

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
                .password("")
                .role(Role.USER)
                .build();
        citizenRepository.save(citizen);
        return ResponseEntity.ok(Map.of("success", true, "message", "Citizen added successfully"));
    }

    //edit citizen data
    public ResponseEntity<?> updateCitizen(AddCitizenRequest request) {
        //if ssn not found
        if (request.getSsn().isEmpty() || request.getSsn().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "SSN is required"));
        }

        // check the new if not null and if different from the old one, then update it
        var citizen = citizenRepository.findBySsn(request.getSsn());
        if (citizen.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Citizen not found"));
        }

        if (request.getFirstName() != null && !request.getFirstName().equals(citizen.get().getFirstName())) {
            citizen.get().setFirstName(request.getFirstName());
        }
        if (request.getMiddleName() != null && !request.getMiddleName().equals(citizen.get().getMiddleName())) {
            citizen.get().setMiddleName(request.getMiddleName());
        }

        if (request.getLastName() != null && !request.getLastName().equals(citizen.get().getLastName())) {
            citizen.get().setLastName(request.getLastName());
        }

        if (request.getBirthDate() != null && !request.getBirthDate().equals(citizen.get().getBirthDate())) {
            citizen.get().setBirthDate(request.getBirthDate());
        }

        if (request.getPlace_of_birth() != null && !request.getPlace_of_birth().equals(citizen.get().getPlace_of_birth())) {
            citizen.get().setPlace_of_birth(request.getPlace_of_birth());
        }

        if (request.getAddress() != null && !request.getAddress().equals(citizen.get().getAddress())) {
            citizen.get().setAddress(request.getAddress());
        }

        if (request.getFather_ssn() != null && !request.getFather_ssn().equals(citizen.get().getFather_ssn())) {
            citizen.get().setFather_ssn(request.getFather_ssn());
        }

        if (request.getMother_ssn() != null && !request.getMother_ssn().equals(citizen.get().getMother_ssn())) {
            citizen.get().setMother_ssn(request.getMother_ssn());
        }

        if (request.getGender_id() != 0 && request.getGender_id() != citizen.get().getGender_id()) {
            citizen.get().setGender_id(request.getGender_id());
        }

        if (request.getNationality_id() != 0 && request.getNationality_id() != citizen.get().getNationality_id()) {
            citizen.get().setNationality_id(request.getNationality_id());
        }

        if (request.getReligion_id() != 0 && request.getReligion_id() != citizen.get().getReligion_id()) {
            citizen.get().setReligion_id(request.getReligion_id());
        }

        if (request.getSocial_status_id() != 0 && request.getSocial_status_id() != citizen.get().getSocial_status_id()) {
            citizen.get().setSocial_status_id(request.getSocial_status_id());
        }

        if (request.getMilitary_condition_id() != 0 && request.getMilitary_condition_id() != citizen.get().getMilitary_condition_id()) {
            citizen.get().setMilitary_condition_id(request.getMilitary_condition_id());
        }

        citizenRepository.save(citizen.get());
        return ResponseEntity.ok(Map.of("success", true, "message", "Citizen data updated successfully"));


    }

    //get all citizens
    public ResponseEntity<?> getAllCitizens() {
        return ResponseEntity.ok(citizenRepository.findAll());
    }

    //get citizen by ssn
    public ResponseEntity<?> getCitizenBySsn(AuthenticateRequest request) {
        //if ssn in request is empty
        try{
        if (request.getSsn().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "SSN is required"));
        }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "SSN is required"));
        }

        var citizen = citizenRepository.findBySsn(request.getSsn());
        if (citizen.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Citizen not found"));
        }
        return ResponseEntity.ok(citizen.get());
    }
}
