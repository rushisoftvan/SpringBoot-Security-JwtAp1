package com.learn1.jwt1.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;



    @Configuration
    @OpenAPIDefinition(info = @Info(title = "SpringBoot JWT Token  API", version = "v1"))
    @SecurityScheme(
            name = "jwtBearerAuth",
            type = SecuritySchemeType.HTTP,
            bearerFormat = "JWT",
            scheme = "bearer"
    )
    public class SwaggerConfig {
    }

