package com.allacsta.latihan.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class Users implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue()
    public UUID id;

    @Column(name = "username")
    public String username;

    @Column(name = "email_address")
    public String email_address;

    @Column(name = "password")
    public String password;

}
