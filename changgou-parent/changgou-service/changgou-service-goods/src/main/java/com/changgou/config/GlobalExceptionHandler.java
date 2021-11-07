package com.changgou.config;

import entity.Result;
import exception.commonException.ResourceNotFoundException;
import exception.commonException.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handler(ResourceNotFoundException e) {
        e.printStackTrace();
        return new Result(false, e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handler(ValidationException e) {
        e.printStackTrace();
        return new Result(false, e.getErrorCode(), e.getMessage());
    }
}
