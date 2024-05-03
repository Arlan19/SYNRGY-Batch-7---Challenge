package com.allacsta.latihan.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "merchant")
public class Merchant {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "merchant_name")
    public String merchant_name;

    @Column(name = "merchant_location")
    public String merchant_location;

    @Column(name = "open")
    public Boolean open;
}
