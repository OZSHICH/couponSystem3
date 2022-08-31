package com.OzProject.couponSystem.exception;

import lombok.*;

@Getter
public enum ErrorMessage {
    COMPANY_ALREADY_EXIST("Company Already Exists"),
    COMPANY_NOT_EXIST("Company Not Exists"),
    COUPON_ALREADY_EXIST("Coupon Already Exists"),
    COUPON_NOT_EXIST("Coupon Not Exists"),
    CUSTOMER_ALREADY_EXIST("Customer Already Exists"),
    CUSTOMER_NOT_EXIST("Customer Not Exists"),
    COUPON_UNAVAILABLE("Coupon Not Qualified For Purchased Or Out Of Stock"),
    COUPON_EXPIRED("Delete Expired Coupons");

    private String Message;

    ErrorMessage(String message) {
        Message = message;
    }
}
