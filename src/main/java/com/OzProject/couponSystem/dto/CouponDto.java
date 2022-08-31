package com.OzProject.couponSystem.dto;

import lombok.*;

import java.sql.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponDto {

    private int id;
    private int companyId;
    private String category;
    private String title;
    private String description;

    private Date startDate;

    //    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private Date endDate;
    private int amount;
    private double price;
    private String image;

    public CouponDto(CouponPayloadDto couponPayloadDto) {
        this.title = couponPayloadDto.getTitle();
        this.category = couponPayloadDto.getCategory();
        this.description = couponPayloadDto.getDescription();
        this.startDate = couponPayloadDto.getStartDate();
        this.endDate = couponPayloadDto.getEndDate();
        this.amount = couponPayloadDto.getAmount();
        this.price = couponPayloadDto.getPrice();
        this.image = couponPayloadDto.getImage();
    }
}
