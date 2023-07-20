package com.learn1.jwt1.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ApiResponse<T> {
    private T data;

    private Integer statusCode;

    List<String> errors;

    public ApiResponse(T data,Integer statusCode){
        this.data=data;
        this.statusCode=statusCode;
    }
    public ApiResponse( List<String> errors,Integer statusCode)
    {
        this.errors=errors;
        this.statusCode=statusCode;
    }



}
