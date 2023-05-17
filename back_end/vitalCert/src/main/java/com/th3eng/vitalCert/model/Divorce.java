package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Date;

@Entity
@Table
@Builder
public class Divorce {

    @Id
    @SequenceGenerator(
            name = "divorce_sequence",
            sequenceName = "divorce_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "divorce_sequence"
    )

    private int id;
    private String wifeSsn;
    private String husbandSsn;
    private Date divorceDate;
    private Date registrationDate;
    private String authority;
    private int divorceTypeId;
    private int certificateNumber;

    public Divorce(String wifeSsn, String husbandSsn, Date divorceDate, Date registrationDate, String authority, int divorceTypeId, int certificateNumber) {
        this.wifeSsn = wifeSsn;
        this.husbandSsn = husbandSsn;
        this.divorceDate = divorceDate;
        this.registrationDate = registrationDate;
        this.authority = authority;
        this.divorceTypeId = divorceTypeId;
        this.certificateNumber = certificateNumber;
    }

    public Divorce(int id, String wifeSsn, String husbandSsn, Date divorceDate, Date registrationDate, String authority, int divorceTypeId, int certificateNumber) {
        this.id = id;
        this.wifeSsn = wifeSsn;
        this.husbandSsn = husbandSsn;
        this.divorceDate = divorceDate;
        this.registrationDate = registrationDate;
        this.authority = authority;
        this.divorceTypeId = divorceTypeId;
        this.certificateNumber = certificateNumber;
    }

    public Divorce() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWifeSsn() {
        return wifeSsn;
    }

    public void setWifeSsn(String wifeSsn) {
        this.wifeSsn = wifeSsn;
    }

    public String getHusbandSsn() {
        return husbandSsn;
    }

    public void setHusbandSsn(String husbandSsn) {
        this.husbandSsn = husbandSsn;
    }

    public Date getDivorceDate() {
        return divorceDate;
    }

    public void setDivorceDate(Date divorceDate) {
        this.divorceDate = divorceDate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public int getDivorceTypeId() {
        return divorceTypeId;
    }

    public void setDivorceTypeId(int divorceTypeId) {
        this.divorceTypeId = divorceTypeId;
    }

    public int getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(int certificateNumber) {
        this.certificateNumber = certificateNumber;
    }
}
