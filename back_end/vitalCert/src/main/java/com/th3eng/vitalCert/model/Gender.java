package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table
@Builder
public class Gender {
    @Id
    @SequenceGenerator(
            name = "gender_sequence",
            sequenceName = "gender_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "gender_sequence"
    )

    private int id;
    private String genderName;

    public Gender(String genderName) {
        this.genderName = genderName;
    }

    public Gender(int id, String genderName) {
        this.id = id;
        this.genderName = genderName;
    }

    public Gender() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getGender() {
        return genderName;
    }

    public void setGender(String genderName) {
        this.genderName = genderName;
    }
}
