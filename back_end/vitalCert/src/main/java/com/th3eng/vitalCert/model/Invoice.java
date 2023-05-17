package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table
@Builder
public class Invoice {
    @Id
    @SequenceGenerator(
            name = "invoice_sequence",
            sequenceName = "invoice_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "invoice_sequence"
    )
    private Long id;
    private Long ssn;
    private Long certificateNumber;
    private String numberOfCopies;
    private String priceId;

    public Invoice(Long ssn, Long certificateNumber, String numberOfCopies, String priceId) {
        this.ssn = ssn;
        this.certificateNumber = certificateNumber;
        this.numberOfCopies = numberOfCopies;
        this.priceId = priceId;
    }

    public Invoice() {
    }

    public Invoice(Long id, Long ssn, Long certificateNumber, String numberOfCopies, String priceId) {
        this.id = id;
        this.ssn = ssn;
        this.certificateNumber = certificateNumber;
        this.numberOfCopies = numberOfCopies;
        this.priceId = priceId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSsn() {
        return ssn;
    }

    public void setSsn(Long ssn) {
        this.ssn = ssn;
    }

    public Long getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(Long certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(String numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }
}
