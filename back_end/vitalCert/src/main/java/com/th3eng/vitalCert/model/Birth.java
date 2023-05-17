package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Date;

@Entity
@Table
@Builder
public class Birth {

    @Id
    @SequenceGenerator(
            name = "birth_sequence",
            sequenceName = "birth_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "birth_sequence"
    )

    private int id;
    private Date registrationDate;
    private String ssn;
    private int healthCenterId;
    private int officeNumber;
    private int CertificateNumber;

    public Birth(int id, Date registrationDate, String ssn, int healthCenterId, int officeNumber, int CertificateNumber) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.ssn = ssn;
        this.healthCenterId = healthCenterId;
        this.officeNumber = officeNumber;
        this.CertificateNumber = CertificateNumber;
    }

    public Birth(Date registrationDate, String ssn, int healthCenterId, int officeNumber, int CertificateNumber) {
        this.registrationDate = registrationDate;
        this.ssn = ssn;
        this.healthCenterId = healthCenterId;
        this.officeNumber = officeNumber;
        this.CertificateNumber = CertificateNumber;
    }

    public Birth() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public int getHealthCenterId() {
        return healthCenterId;
    }

    public void setHealthCenterId(int healthCenterId) {
        this.healthCenterId = healthCenterId;
    }

    public int getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(int officeNumber) {
        this.officeNumber = officeNumber;
    }

    public int getCertificateNumber() {
        return CertificateNumber;
    }

    public void setCertificateNumber(int certificateNumber) {
        CertificateNumber = certificateNumber;
    }
}
