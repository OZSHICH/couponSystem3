package com.OzProject.couponSystem.advice;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorDetails {

    private String key;
    private String value;

    public ErrorDetails(String value) {
        this.value = value;
    }
}
