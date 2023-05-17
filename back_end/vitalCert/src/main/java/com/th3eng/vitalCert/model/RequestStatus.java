package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table
@Builder
public class RequestStatus {
    @Id
    @SequenceGenerator(
            name = "request_status_sequence",
            sequenceName = "request_status_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "request_status_sequence"
    )

    private int id;
    private String status;

    public RequestStatus(String status) {
        this.status = status;
    }

    public RequestStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public RequestStatus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
