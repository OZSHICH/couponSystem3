package com.OzProject.couponSystem.dto;

import com.OzProject.couponSystem.security.*;
import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
public class LoginResDto {

    private int id;
    private String name;
    private String email;
    private UUID token;
    private ClientType clientType;
}
