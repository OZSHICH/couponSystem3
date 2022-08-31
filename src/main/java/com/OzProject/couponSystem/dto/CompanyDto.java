package com.OzProject.couponSystem.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {


    private int companyId;
    private String name;
    private String password;
    private String email;


    public CompanyDto(CompanyPayloadDto companyPayloadDto) {
        this.name = companyPayloadDto.getName();
        this.email = companyPayloadDto.getEmail();
        this.password = companyPayloadDto.getPassword();

    }

}
