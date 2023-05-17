package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table
@Builder
public class HealthCenter {

    @Id
    @SequenceGenerator(
            name = "healthCenter_sequence",
            sequenceName = "healthCenter_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "healthCenter_sequence"
    )

    private int id;
    private int governorateId;
    private String name;
    private String address;

    public HealthCenter(
            int id,
            int governorateId,
            String name,
            String address) {
        this.id = id;
        this.governorateId = governorateId;
        this.name = name;
        this.address = address;
    }

    public HealthCenter(
            int governorateId,
            String name,
            String address) {
        this.governorateId = governorateId;
        this.name = name;
        this.address = address;
    }

    public HealthCenter() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGovernorateId() {
        return governorateId;
    }

    public void setGovernorateId(int governorateId) {
        this.governorateId = governorateId;
    }

    public String getHealthCenterName() {
        return name;
    }

    public void setHealthCenterName(String name) {
        this.name = name;
    }

    public String getHealthCenterAddress() {
        return address;
    }

    public void setHealthCenterAddress(String address) {
        this.address = address;
    }
}
