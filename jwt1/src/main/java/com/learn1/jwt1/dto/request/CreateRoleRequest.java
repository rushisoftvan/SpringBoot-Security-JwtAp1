package com.learn1.jwt1.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CreateRoleRequest {

    @NotEmpty(message = "name should not be null or empty")
    private String name;
}
