package com.OzProject.couponSystem.beans;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.sql.*;


@Entity
@Table(name = "coupons")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    private Company company;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String title;

    private String description;

    //    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;
    

}
