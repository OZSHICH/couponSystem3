package com.OzProject.couponSystem.exception;

import lombok.*;

@Data
public class CouponSecurityException extends Exception {

    private SecurityMessage securityMessage;

    public CouponSecurityException(SecurityMessage securityMessages) {
        super(securityMessages.getMessage());
        this.securityMessage = securityMessages;

    }
}
