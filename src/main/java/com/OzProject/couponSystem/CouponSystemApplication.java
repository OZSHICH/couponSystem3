package com.OzProject.couponSystem;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.scheduling.annotation.*;

@SpringBootApplication
@EnableScheduling
public class CouponSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponSystemApplication.class, args);
    }

}
