package com.OzProject.couponSystem.beans;

import com.OzProject.couponSystem.dto.*;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "companies")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, length = 40, updatable = false)
    private String name;
    private String password;
    @Column(unique = true, length = 40)
    private String email;


    @ToString.Exclude
    @Singular

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "company")
    private List<Coupon> coupons = new ArrayList<>();


    public Company(CompanyPayloadDto companyPayloadDto) {
        this.name = companyPayloadDto.getName();
        this.email = companyPayloadDto.getEmail();
        this.password = companyPayloadDto.getPassword();
    }

}



