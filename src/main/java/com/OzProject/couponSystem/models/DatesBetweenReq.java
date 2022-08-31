package com.OzProject.couponSystem.models;

import lombok.*;

import java.sql.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatesBetweenReq {

    private Date start;
    private Date end;
}
