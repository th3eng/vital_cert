package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table
@Builder
public class OfficePhone {
    @Id
    @SequenceGenerator(
            name = "office_phone_sequence",
            sequenceName = "office_phone_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "office_phone_sequence"
    )

    private int id;
    private String phoneNumber;
    private int officeId;

    public OfficePhone(String phoneNumber, int officeId) {
        this.phoneNumber = phoneNumber;
        this.officeId = officeId;
    }

    public OfficePhone(int id, String phoneNumber, int officeId) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.officeId = officeId;
    }

    public OfficePhone() {
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

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }
}
