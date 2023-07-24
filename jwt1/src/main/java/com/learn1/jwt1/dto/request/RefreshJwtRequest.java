package com.learn1.jwt1.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshJwtRequest {
    private String refreshToken;
}
