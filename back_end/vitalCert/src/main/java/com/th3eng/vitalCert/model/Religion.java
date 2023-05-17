package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table
@Builder
public class Religion {
    @Id
    @SequenceGenerator(
            name = "religion_sequence",
            sequenceName = "religion_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "religion_sequence"
    )

    private int id;
    private String religion;

    public Religion(String religion) {
        this.religion = religion;
    }

    public Religion(int id, String religion) {
        this.id = id;
        this.religion = religion;
    }

    public Religion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }
}
