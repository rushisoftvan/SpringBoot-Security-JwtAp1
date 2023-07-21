package com.learn1.jwt1.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserLoginRequest {

    @Email(regexp = ".+[@].+[\\.].+",message = "please enter valid email")
    private String email;
    @NotEmpty(message = "password should not be null or empty")
    private String password;
}
