package com.OzProject.couponSystem.mapper;

import com.OzProject.couponSystem.beans.*;
import com.OzProject.couponSystem.dto.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class CustomerMapper implements Mapper<Customer, CustomerDto> {
    @Autowired
    private DateMapper dateMapper;

    @Override
    public Customer toBean(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getCustomerId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .password(customerDto.getPassword())
                .build();
    }

    @Override
    public CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .build();
    }

    @Override
    public List<Customer> toBeanList(List<CustomerDto> customerDto) {
        return null;
    }

    @Override
    public List<CustomerDto> toDtoList(List<Customer> customers) {
        return null;
    }
}
