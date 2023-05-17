package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table
@Builder
public class PhoneNumber {
    @Id
    @SequenceGenerator(
            name = "phone_number_sequence",
            sequenceName = "phone_number_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "phone_number_sequence"
    )

    private int id;
    private String phoneNumber;
    private String ssn;

    public PhoneNumber(String phoneNumber, String ssn) {
        this.phoneNumber = phoneNumber;
        this.ssn = ssn;
    }

    public PhoneNumber(int id, String phoneNumber, String ssn) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.ssn = ssn;
    }

    public PhoneNumber() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
