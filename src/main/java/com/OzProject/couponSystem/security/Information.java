package com.OzProject.couponSystem.security;

import lombok.*;

import java.time.*;

@Data
@AllArgsConstructor

public class Information {
    private int id;
    private String name;
    private String email;
    private ClientType clientType;
    private LocalDateTime time = LocalDateTime.now();

    public Information(int id, String name, String email, ClientType clientType) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.clientType = clientType;
    }
}
