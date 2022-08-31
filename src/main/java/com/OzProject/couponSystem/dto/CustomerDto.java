package com.OzProject.couponSystem.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public CustomerDto(CustomerPayloadDto customerPayloadDto) {
        this.firstName = customerPayloadDto.getFirstName();
        this.lastName = customerPayloadDto.getLastName();
        this.email = customerPayloadDto.getEmail();
        this.password = customerPayloadDto.getPassword();
    }
}
