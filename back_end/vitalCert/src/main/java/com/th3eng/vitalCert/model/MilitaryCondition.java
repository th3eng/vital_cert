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
    private String militaryConditionName;

    public MilitaryCondition(String militaryConditionName) {
        this.militaryConditionName = militaryConditionName;
    }

    public MilitaryCondition(int id, String militaryConditionName) {
        this.id = id;
        this.militaryConditionName = militaryConditionName;
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
        return militaryConditionName;
    }

    public void setMilitaryCondition(String militaryConditionName) {
        this.militaryConditionName = militaryConditionName;
    }
}
