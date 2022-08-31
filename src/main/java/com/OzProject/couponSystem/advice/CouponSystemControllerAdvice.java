package com.OzProject.couponSystem.advice;

import com.OzProject.couponSystem.exception.*;
import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestControllerAdvice
public class CouponSystemControllerAdvice {


    @ExceptionHandler(value = {CustomException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails errorHelper(Exception e) {
        return new ErrorDetails("An error occurred", e.getMessage());
    }


    @ExceptionHandler(value = CouponSecurityException.class)
    public ResponseEntity<?> handleSecException(CouponSecurityException e) {
        ErrorDetails errorDetails = new ErrorDetails("An error occurred", e.getSecurityMessage().getMessage());
        return new ResponseEntity<>(errorDetails, e.getSecurityMessage().getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
