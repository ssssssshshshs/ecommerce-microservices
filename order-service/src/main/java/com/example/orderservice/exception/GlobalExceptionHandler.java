package com.example.orderservice.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String, String>>
    handleOrderNotFoundException(
            OrderNotFoundException ex) {

        Map<String, String> error =
                new HashMap<>();

        error.put("message", ex.getMessage());

        return new ResponseEntity<>(
                error,
                HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(InvalidOrderException.class)
    public ResponseEntity<String> handleInvalidOrderException(
            InvalidOrderException ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }
    
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
          .getFieldErrors()
          .forEach(error ->
                  errors.put(error.getField(),
                             error.getDefaultMessage()));

        return new ResponseEntity<>(
                errors,
                HttpStatus.BAD_REQUEST);
    }
}