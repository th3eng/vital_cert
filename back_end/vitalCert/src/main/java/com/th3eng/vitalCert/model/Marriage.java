package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Date;

@Entity
@Table
@Builder
public class Marriage {
    @Id
    @SequenceGenerator(
            name = "marriage_sequence",
            sequenceName = "marriage_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "marriage_sequence"
    )

    private int id;
    private Long certificateNumber;
    private int officeId;
    private Long husbandSsn;
    private Long wifeSsn;
    private Date marriageDate;
    private String marriagePlace;

    public Marriage(Long certificateNumber, int officeId, Long husbandSsn, Long wifeSsn, Date marriageDate, String marriagePlace) {
        this.certificateNumber = certificateNumber;
        this.officeId = officeId;
        this.husbandSsn = husbandSsn;
        this.wifeSsn = wifeSsn;
        this.marriageDate = marriageDate;
        this.marriagePlace = marriagePlace;
    }

    public Marriage() {
    }

    public Marriage(
            int id,
            Long certificateNumber,
            int officeId,
            Long husbandSsn,
            Long wifeSsn,
            Date marriageDate,
            String marriagePlace) {
        this.id = id;
        this.certificateNumber = certificateNumber;
        this.officeId = officeId;
        this.husbandSsn = husbandSsn;
        this.wifeSsn = wifeSsn;
        this.marriageDate = marriageDate;
        this.marriagePlace = marriagePlace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(Long certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public Long getHusbandSsn() {
        return husbandSsn;
    }

    public void setHusbandSsn(Long husbandSsn) {
        this.husbandSsn = husbandSsn;
    }

    public Long getWifeSsn() {
        return wifeSsn;
    }

    public void setWifeSsn(Long wifeSsn) {
        this.wifeSsn = wifeSsn;
    }

    public Date getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(Date marriageDate) {
        this.marriageDate = marriageDate;
    }

    public String getMarriagePlace() {
        return marriagePlace;
    }

    public void setMarriagePlace(String marriagePlace) {
        this.marriagePlace = marriagePlace;
    }
}
