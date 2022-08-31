package com.OzProject.couponSystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterReqDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
