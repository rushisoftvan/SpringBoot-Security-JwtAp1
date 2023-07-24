package com.learn1.jwt1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String refreshToken;
    private Instant expiryDate;

    @OneToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private UserEntity user;

}
