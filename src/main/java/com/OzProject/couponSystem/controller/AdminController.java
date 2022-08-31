package com.OzProject.couponSystem.controller;


import com.OzProject.couponSystem.beans.*;
import com.OzProject.couponSystem.dto.*;
import com.OzProject.couponSystem.exception.*;
import com.OzProject.couponSystem.mapper.*;
import com.OzProject.couponSystem.security.*;
import com.OzProject.couponSystem.servies.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {

    private final AdminService adminService;
    private final CompanyMapper companyMapper;
    private final TokenManager tokenManager;

    @PostMapping("/addCompany")
    public void addCompany(@RequestHeader("Authorization") UUID token, @RequestBody CompanyPayloadDto companyPayloadDto) throws CustomException {
        adminService.addCompany(new Company(companyPayloadDto), token);
    }

    @PutMapping("/update/{companyId}")
    CompanyDto updateCompany(@RequestHeader("Authorization") UUID token, @PathVariable int companyId, @RequestBody CompanyPayloadDto companyPayloadDto) throws CustomException {
        return adminService.updateCompany(companyId, new CompanyDto(companyPayloadDto), token);

    }

    @DeleteMapping("/companies/{companyId}")
    void deleteCompany(@RequestHeader("Authorization") UUID token, @PathVariable int companyId) throws CustomException {
        adminService.deleteCompany(companyId, token);
    }

    @GetMapping("/companies")
    List<Company> getAllCompanies(@RequestHeader("Authorization") UUID token) {
        return adminService.getAllCompanies(token);
    }

    @GetMapping("/company/{companyId}")
    Company getOneCompany(@RequestHeader("Authorization") UUID token, @PathVariable int companyId) throws CustomException {
        return adminService.getOneCompany(companyId, token);
    }

    @PostMapping("/addCustomer")
    void addCustomer(@RequestHeader("Authorization") UUID token, @RequestBody Customer customer) throws CustomException {
        adminService.addCustomer(customer, token);
    }

    @PutMapping("/customer/{customerId}")
    CustomerDto updateCustomer(@RequestHeader("Authorization") UUID token, @PathVariable int customerId, @RequestBody CustomerPayloadDto customerPayloadDto) throws CustomException, CouponSecurityException {
        return adminService.updateCustomer(customerId, new CustomerDto(customerPayloadDto), token);
    }

    @DeleteMapping("/customer/{customerId}")
    void deleteCustomer(@RequestHeader("Authorization") UUID token, @PathVariable int customerId) throws CustomException {
        adminService.deleteCustomer(customerId, token);
    }

    @GetMapping("/customers")
    List<Customer> getAllCustomers(@RequestHeader("Authorization") UUID token) throws CouponSecurityException {
        return adminService.getAllCustomers(token);
    }

    @GetMapping("/customer/{customerId}")
    Customer getOneCustomer(@RequestHeader("Authorization") UUID token, @PathVariable int customerId) throws CustomException {
        return adminService.getOneCustomer(customerId, token);
    }

}
