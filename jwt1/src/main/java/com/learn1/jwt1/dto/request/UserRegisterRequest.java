package com.learn1.jwt1.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRegisterRequest {

    @NotEmpty(message = "firstname should not be null or empty")
    private  String firstname;

    @NotEmpty(message = "lastname should not be null or empty")
    private String lastname;

    @Email(regexp = ".+[@].+[\\.].+",message = "please enter valid email")
    private String email;

    @NotEmpty(message = "please enter password")
    private String password;

    @NotNull
    private Integer roleId;
}
