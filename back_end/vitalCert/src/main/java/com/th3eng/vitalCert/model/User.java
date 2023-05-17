package com.th3eng.vitalCert.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ssn; // Social Security Number
    private String password;
    private Role role; // "EMPLOYEE", "CITIZEN", etc.

    public User() {

    }

    public User(String ssn, String password, Role role) {
        this.ssn = ssn;
        this.password = password;
        this.role = role;
    }

    public User(Long id, String ssn, String password, Role role) {
        this.id = id;
        this.ssn = ssn;
        this.password = password;
        this.role = role;
    }
}
