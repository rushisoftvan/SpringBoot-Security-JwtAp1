package com.learn1.jwt1.Exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
public class AuthenticationEntryPointJwt implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("<<<<<<<<< AuthenticationEntryPointJwt");
        //log.info("Authentication exception info ::" , authException);
        //handlerExceptionResolver.resolveException(request, response, null, authException);
        log.info("AuthenticationEntryPointJwt >>>>>>");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized User!");
    }
}
