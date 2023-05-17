package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table
@Builder
public class CriminalRecord {
    @Id
    @SequenceGenerator(
            name = "criminal_record_sequence",
            sequenceName = "criminal_record_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "criminal_record_sequence"
    )

    private int id;
    private String employeeSsn;
    private String citizenSsn;
    private byte[] citizenPicture;
    private String producedTo;
    private String result;
    private int officeNumber;
    private int certificateNumber;

    public CriminalRecord(String employeeSsn, String citizenSsn, byte[] citizenPicture, String producedTo, String result, int officeNumber, int certificateNumber) {
        this.employeeSsn = employeeSsn;
        this.citizenSsn = citizenSsn;
        this.citizenPicture = citizenPicture;
        this.producedTo = producedTo;
        this.result = result;
        this.officeNumber = officeNumber;
        this.certificateNumber = certificateNumber;
    }

    public CriminalRecord(int id, String employeeSsn, String citizenSsn, byte[] citizenPicture, String producedTo, String result, int officeNumber, int certificateNumber) {
        this.id = id;
        this.employeeSsn = employeeSsn;
        this.citizenSsn = citizenSsn;
        this.citizenPicture = citizenPicture;
        this.producedTo = producedTo;
        this.result = result;
        this.officeNumber = officeNumber;
        this.certificateNumber = certificateNumber;
    }

    public CriminalRecord() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeSsn() {
        return employeeSsn;
    }

    public void setEmployeeSsn(String employeeSsn) {
        this.employeeSsn = employeeSsn;
    }

    public String getCitizenSsn() {
        return citizenSsn;
    }

    public void setCitizenSsn(String citizenSsn) {
        this.citizenSsn = citizenSsn;
    }

    public byte[] getCitizenPicture() {
        return citizenPicture;
    }

    public void setCitizenPicture(byte[] citizenPicture) {
        this.citizenPicture = citizenPicture;
    }

    public String getProducedTo() {
        return producedTo;
    }

    public void setProducedTo(String producedTo) {
        this.producedTo = producedTo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(int officeNumber) {
        this.officeNumber = officeNumber;
    }

    public int getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(int certificateNumber) {
        this.certificateNumber = certificateNumber;
    }
}
