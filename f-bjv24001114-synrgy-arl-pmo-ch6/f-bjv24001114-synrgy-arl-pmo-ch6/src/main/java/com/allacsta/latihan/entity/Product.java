package com.allacsta.latihan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue()
    public UUID id;

    @Column(name = "product_name", columnDefinition = "TEXT")
    public String product_name;

    @Column(name = "price")
    public double price;

    @OneToOne(targetEntity = Merchant.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
//    private Merchant merchant;
}
