package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table
@Builder
public class DivorceType {

    @Id
    @SequenceGenerator(
            name = "divorce_type_sequence",
            sequenceName = "divorce_type_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "divorce_type_sequence"
    )

    private int id;
    private String divorceType;

    public DivorceType(String divorceType) {
        this.divorceType = divorceType;
    }

    public DivorceType(int id, String divorceType) {
        this.id = id;
        this.divorceType = divorceType;
    }

    public DivorceType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDivorceType() {
        return divorceType;
    }

    public void setDivorceType(String divorceType) {
        this.divorceType = divorceType;
    }
}
