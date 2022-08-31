package com.OzProject.couponSystem.dto;

import com.OzProject.couponSystem.security.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReqDto {

    private String email;
    private String password;
    private ClientType clientType;


}
