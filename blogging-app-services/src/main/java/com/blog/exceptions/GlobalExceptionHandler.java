package com.blog.exceptions;

import com.blog.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> notFoundExceptionHandler(ResourceNotFoundException ex) {
        return new ResponseEntity<ApiResponse>(new ApiResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HashMap<String, String>> BadRequestExceptionHandler(MethodArgumentNotValidException ex) {
        var resp = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            var field = ((FieldError)error).getField();
            var value = error.getDefaultMessage();
            resp.put(field, value);
        });
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
}
