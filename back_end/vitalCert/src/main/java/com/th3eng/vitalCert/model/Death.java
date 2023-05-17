package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Date;

@Entity
@Table
@Builder
public class Death {
    @Id
    @SequenceGenerator(
            name = "death_sequence",
            sequenceName = "death_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "death_sequence"
    )

    private int id;
    private String ssn;
    private int officeNumber;
    private Date RegistrationDate;
    private String deathDate;
    private String reason;
    private String deathPlace;
    private String certificateNumber;

    public Death(String ssn, int officeNumber, Date registrationDate, String deathDate, String reason, String deathPlace, String certificateNumber) {
        this.ssn = ssn;
        this.officeNumber = officeNumber;
        RegistrationDate = registrationDate;
        this.deathDate = deathDate;
        this.reason = reason;
        this.deathPlace = deathPlace;
        this.certificateNumber = certificateNumber;
    }

    public Death(int id, String ssn, int officeNumber, Date registrationDate, String deathDate, String reason, String deathPlace, String certificateNumber) {
        this.id = id;
        this.ssn = ssn;
        this.officeNumber = officeNumber;
        RegistrationDate = registrationDate;
        this.deathDate = deathDate;
        this.reason = reason;
        this.deathPlace = deathPlace;
        this.certificateNumber = certificateNumber;
    }

    public Death() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public int getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(int officeNumber) {
        this.officeNumber = officeNumber;
    }

    public Date getRegistrationDate() {
        return RegistrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        RegistrationDate = registrationDate;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDeathPlace() {
        return deathPlace;
    }

    public void setDeathPlace(String deathPlace) {
        this.deathPlace = deathPlace;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }
}
