package com.learn1.jwt1.Exception;

import com.learn1.jwt1.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobelExceptonHandler {

    public static final String USERNAME_OR_PASSWORD_INCORRECT = "username or password incorrect";
    public static final String NO_PERMISION = "No permision";

    @ExceptionHandler(MethodArgumentNotValidException.class)
   public ApiResponse<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<ObjectError> allErrors = ex.getAllErrors();
        List<String> finalerrors = new ArrayList();
        for(ObjectError error : allErrors){
             finalerrors.add(error.getDefaultMessage());
        }
 return new ApiResponse<>(finalerrors, HttpStatus.BAD_REQUEST.value());

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiResponse handleResourceNotFound(ResourceNotFoundException ex){
        String message = ex.getMessage();
        return  new ApiResponse(Arrays.asList(message),HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(CustomException.class)
    public ApiResponse<Object> handle(CustomException ex){
        String error = ex.getMessage();
        return new ApiResponse<>(Arrays.asList(error),HttpStatus.UNAUTHORIZED.value());
    }

     @ExceptionHandler(AccessDeniedException.class)
     @ResponseStatus(HttpStatus.FORBIDDEN)
      public ApiResponse<Object> handle(AccessDeniedException ex){
         //String errorMessage = ex.getMessage();
         return new ApiResponse<>(Arrays.asList(NO_PERMISION),HttpStatus.FORBIDDEN.value());
     }


     @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
     @ResponseStatus(HttpStatus.UNAUTHORIZED)
     public ApiResponse<Object> handleAuthenticationException(Exception ex){
        return new ApiResponse<>(Arrays.asList(USERNAME_OR_PASSWORD_INCORRECT),HttpStatus.UNAUTHORIZED.value());
     }
}
