package com.OzProject.couponSystem.beans;

import com.OzProject.couponSystem.dto.*;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "customers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})

    @JoinTable(
            name = "customers_coupons",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "coupon_id"))
    @Singular
    private Set<Coupon> coupons = new HashSet<>();

    public Customer(CustomerPayloadDto customerPayloadDto) {
        this.firstName = customerPayloadDto.getFirstName();
        this.lastName = customerPayloadDto.getLastName();
        this.email = customerPayloadDto.getEmail();
        this.password = customerPayloadDto.getPassword();
    }
}
