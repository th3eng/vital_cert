package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table
@Builder
public class MilitaryCondition {
    @Id
    @SequenceGenerator(
            name = "militaryCondition_sequence",
            sequenceName = "militaryCondition_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "militaryCondition_sequence"
    )

    private int id;
    private String militaryCondition;

    public MilitaryCondition(String militaryCondition) {
        this.militaryCondition = militaryCondition;
    }

    public MilitaryCondition(int id, String militaryCondition) {
        this.id = id;
        this.militaryCondition = militaryCondition;
    }

    public MilitaryCondition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMilitaryCondition() {
        return militaryCondition;
    }

    public void setMilitaryCondition(String militaryCondition) {
        this.militaryCondition = militaryCondition;
    }
}
