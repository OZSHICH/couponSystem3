package com.OzProject.couponSystem.mapper;

import org.springframework.stereotype.*;

import java.sql.*;
import java.time.*;

@Component
public class DateMapper {
    public Timestamp toTimestamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    public LocalDateTime toLocalDateTime(Timestamp timestamp) {

        return timestamp.toLocalDateTime();
    }

}
