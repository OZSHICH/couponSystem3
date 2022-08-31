package com.OzProject.couponSystem.dto;

import lombok.*;


@Data
@AllArgsConstructor
public class CompanyPayloadDto {

    private String name;
    private String email;
    private String password;
}
