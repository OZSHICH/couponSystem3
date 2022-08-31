package com.OzProject.couponSystem.exception;

import lombok.*;
import org.springframework.http.*;

@Getter
public enum SecurityMessage {

    EMAIL_ALREADY_EXIST("Email Already Exist", HttpStatus.CONFLICT),
    EMAIL_OR_PASSWORD_INCORRECT("Email Or Password Incorrect", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN("Invalid Token Please Login Again", HttpStatus.UNAUTHORIZED),
    WRONG_TYPE_OF_CLIENT("Invalid Client Type", HttpStatus.UNAUTHORIZED);

    private String message;
    private HttpStatus status;

    SecurityMessage(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
