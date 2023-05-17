package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Date;

@Entity
@Table
@Builder

public class Request {
    @Id
    @SequenceGenerator(
            name = "request_sequence",
            sequenceName = "request_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "request_sequence"
    )

    private Long id;
    private String ssn;
    private Date requestDate;
    private int requestStatusId;
    private int certificateNumber;
    private Long invoiceId;

    public Request(String ssn, Date requestDate, int requestStatusId, int certificateNumber, Long invoiceId) {
        this.ssn = ssn;
        this.requestDate = requestDate;
        this.requestStatusId = requestStatusId;
        this.certificateNumber = certificateNumber;
        this.invoiceId = invoiceId;
    }

    public Request() {
    }

    public Request(Long id, String ssn, Date requestDate, int requestStatusId, int certificateNumber, Long invoiceId) {
        this.id = id;
        this.ssn = ssn;
        this.requestDate = requestDate;
        this.requestStatusId = requestStatusId;
        this.certificateNumber = certificateNumber;
        this.invoiceId = invoiceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public int getRequestStatusId() {
        return requestStatusId;
    }

    public void setRequestStatusId(int requestStatusId) {
        this.requestStatusId = requestStatusId;
    }

    public int getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(int certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }
}
