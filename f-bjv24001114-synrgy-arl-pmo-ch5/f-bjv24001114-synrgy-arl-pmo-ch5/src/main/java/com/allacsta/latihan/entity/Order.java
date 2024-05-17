package com.allacsta.latihan.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "`order`")
public class Order implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue()
    public UUID id;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date order_time;

    @Column(name = "destination_address", columnDefinition = "TEXT")
    public String destination_address;

    @Column(name = "completed")
    public Boolean completed;

    @OneToOne(targetEntity = Users.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;


}
