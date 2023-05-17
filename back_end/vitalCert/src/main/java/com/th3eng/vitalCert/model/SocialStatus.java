package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table
@Builder
public class SocialStatus {
    @Id
    @SequenceGenerator(
            name = "social_status_sequence",
            sequenceName = "social_status_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "social_status_sequence"
    )

    private int id;
    private String status;

    public SocialStatus(String status) {
        this.status = status;
    }

    public SocialStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public SocialStatus() {
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
