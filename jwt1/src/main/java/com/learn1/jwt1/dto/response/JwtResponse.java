package com.learn1.jwt1.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

    private String JwtToken;

    private String refershToken;
}
