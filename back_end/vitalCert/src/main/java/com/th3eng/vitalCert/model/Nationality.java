package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table
@Builder
public class Nationality {
    @Id
    @SequenceGenerator(
            name = "nationality_sequence",
            sequenceName = "nationality_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "nationality_sequence"
    )
    private int id;
    private String nationalityName;

    public Nationality(int id, String nationalityName) {
        this.id = id;
        this.nationalityName = nationalityName;
    }

    public Nationality(String nationalityName) {
        this.nationalityName = nationalityName;
    }

    public Nationality() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNationalityName() {
        return nationalityName;
    }

    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }
}
