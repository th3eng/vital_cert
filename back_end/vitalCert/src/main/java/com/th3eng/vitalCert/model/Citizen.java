package com.th3eng.vitalCert.model;

import com.th3eng.vitalCert.model.Role;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Builder
public class Citizen implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "citizen_sequence",
            sequenceName = "citizen_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "citizen_sequence"
    )
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

    public Citizen(
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
            int military_condition_id,
            Role role) {
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
        this.role = role;
    }

    public Citizen(
            Long id,
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
            int military_condition_id,
            Role role) {
        this.id = id;
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
        this.role = role;
    }

    public Citizen() {

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

    public byte[] getFinger_print() {
        return fingerprint;
    }

    public void setFinger_print(byte[] fingerprint) {
        this.fingerprint = fingerprint;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return ssn;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
