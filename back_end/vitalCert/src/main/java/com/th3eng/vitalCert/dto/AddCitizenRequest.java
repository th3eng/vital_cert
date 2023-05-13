package com.th3eng.vitalCert.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCitizenRequest {

    private Long id;
    private String ssn;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthDate;
    private String place_of_birth;
    private String address;
    private String father_ssn;
    private String mother_ssn;
    private byte[] fingerprint;
    private String password;
    private int gender_id;
    private int nationality_id;
    private int religion_id;
    private int social_status_id;
    private int military_condition_id;

    public AddCitizenRequest(
            String ssn,
            String firstName,
            String middleName,
            String lastName,
            Date birthDate,
            String place_of_birth,
            String address,
            String father_ssn,
            String mother_ssn,
            byte[] fingerprint,
            String password,
            int gender_id,
            int nationality_id,
            int religion_id,
            int social_status_id,
            int military_condition_id) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.place_of_birth = place_of_birth;
        this.address = address;
        this.father_ssn = father_ssn;
        this.mother_ssn = mother_ssn;
        this.fingerprint = fingerprint;
        this.password = password;
        this.gender_id = gender_id;
        this.nationality_id = nationality_id;
        this.religion_id = religion_id;
        this.social_status_id = social_status_id;
        this.military_condition_id = military_condition_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFather_ssn() {
        return father_ssn;
    }

    public void setFather_ssn(String father_ssn) {
        this.father_ssn = father_ssn;
    }

    public String getMother_ssn() {
        return mother_ssn;
    }

    public void setMother_ssn(String mother_ssn) {
        this.mother_ssn = mother_ssn;
    }

    public byte[] getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(byte[] fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender_id() {
        return gender_id;
    }

    public void setGender_id(int gender_id) {
        this.gender_id = gender_id;
    }

    public int getNationality_id() {
        return nationality_id;
    }

    public void setNationality_id(int nationality_id) {
        this.nationality_id = nationality_id;
    }

    public int getReligion_id() {
        return religion_id;
    }

    public void setReligion_id(int religion_id) {
        this.religion_id = religion_id;
    }

    public int getSocial_status_id() {
        return social_status_id;
    }

    public void setSocial_status_id(int social_status_id) {
        this.social_status_id = social_status_id;
    }

    public int getMilitary_condition_id() {
        return military_condition_id;
    }

    public void setMilitary_condition_id(int military_condition_id) {
        this.military_condition_id = military_condition_id;
    }
}
