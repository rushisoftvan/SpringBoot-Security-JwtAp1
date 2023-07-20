package com.learn1.jwt1.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class UserEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FIRST_NAME")
    private String firstname;

    @Column(name = "LAST_NAME")
    private String lastname;


    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "REGISTRATION_DATE_TIME")
    private LocalDateTime registrationDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    private RoleEntity role;

    @PrePersist
    public void beforeInsert(){
        this.registrationDateTime = LocalDateTime.now();
    }

}
