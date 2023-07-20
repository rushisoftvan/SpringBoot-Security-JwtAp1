package com.learn1.jwt1.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {

    private  String firstname;

    private String lastname;

    private String email;

    private String password;

    private Integer roleId;
}
