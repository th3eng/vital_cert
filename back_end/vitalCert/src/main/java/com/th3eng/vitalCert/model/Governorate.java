package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table
@Builder
public class Governorate {
    @Id
    @SequenceGenerator(
            name = "governorate_sequence",
            sequenceName = "governorate_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "governorate_sequence"
    )

    private int id;
    private String governorateName;

    public Governorate(int id, String governorate) {
        this.id = id;
        this.governorateName = governorate;
    }

    public Governorate(String governorate) {
        this.governorateName = governorate;
    }

    public Governorate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGovernorate() {
        return governorateName;
    }

    public void setGovernorate(String governorate) {
        this.governorateName = governorate;
    }
}
