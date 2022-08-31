package com.OzProject.couponSystem.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class CustomerPayloadDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
