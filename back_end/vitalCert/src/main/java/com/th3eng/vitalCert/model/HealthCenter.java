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
    private String healthCenterName;
    private String healthCenterAddress;

    public HealthCenter(
            int id,
            int governorateId,
            String healthCenterName,
            String healthCenterAddress) {
        this.id = id;
        this.governorateId = governorateId;
        this.healthCenterName = healthCenterName;
        this.healthCenterAddress = healthCenterAddress;
    }

    public HealthCenter(
            int governorateId,
            String healthCenterName,
            String healthCenterAddress) {
        this.governorateId = governorateId;
        this.healthCenterName = healthCenterName;
        this.healthCenterAddress = healthCenterAddress;
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
        return healthCenterName;
    }

    public void setHealthCenterName(String healthCenterName) {
        this.healthCenterName = healthCenterName;
    }

    public String getHealthCenterAddress() {
        return healthCenterAddress;
    }

    public void setHealthCenterAddress(String healthCenterAddress) {
        this.healthCenterAddress = healthCenterAddress;
    }
}
