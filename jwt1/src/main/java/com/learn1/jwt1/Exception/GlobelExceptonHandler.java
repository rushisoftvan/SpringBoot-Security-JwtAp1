package com.learn1.jwt1.Exception;

import com.learn1.jwt1.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobelExceptonHandler {

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

}
