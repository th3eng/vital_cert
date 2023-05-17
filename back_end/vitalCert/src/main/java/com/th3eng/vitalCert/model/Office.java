package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table
@Builder
public class Office {
    @Id
    @SequenceGenerator(
            name = "office_sequence",
            sequenceName = "office_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "office_sequence"
    )

    private int id;
    private String address;

    public Office(String address) {
        this.address = address;
    }

    public Office(int id, String address) {
        this.id = id;
        this.address = address;
    }

    public Office() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOfficeAddress() {
        return address;
    }

    public void setOfficeAddress(String address) {
        this.address = address;
    }
}
