package com.th3eng.vitalCert.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Date;

@Entity
@Table
@Builder
public class Price {
    @Id
    @SequenceGenerator(
            name = "price_sequence",
            sequenceName = "price_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "price_sequence"
    )

    private int id;
    private String price;
    private Date date;

    public Price(String price, Date date) {
        this.price = price;
        this.date = date;
    }

    public Price(int id, String price, Date date) {
        this.id = id;
        this.price = price;
        this.date = date;
    }

    public Price() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
