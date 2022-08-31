package com.OzProject.couponSystem.dto;

import lombok.*;

import java.sql.*;

@Data
@AllArgsConstructor
public class CouponPayloadDto {


    private String title;
    private String category;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;
}
