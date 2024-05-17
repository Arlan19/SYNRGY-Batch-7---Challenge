package com.allacsta.latihan.entity;

import com.allacsta.latihan.entity.abstractdate.AbstractDate;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "orderdetail")
public class OrderDetail implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue()
    public UUID id;

    @Column(name = "quantity")
    public int quantity;

    @Column(name = "total_price")
    public double total_price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
}
