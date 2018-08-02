package com.integration.boot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
    @ExceptionHandler(MultipartException.class)
    public Map<String,Object> handleError1(MultipartException e) {
        Map <String,Object> result = new HashMap<>();
        result.put("data", "error");
        return result;
    }
}