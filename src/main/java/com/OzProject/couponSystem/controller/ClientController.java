package com.OzProject.couponSystem.controller;

import com.OzProject.couponSystem.dto.*;
import com.OzProject.couponSystem.exception.*;
import com.OzProject.couponSystem.servies.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@RestController
@Service
public abstract class ClientController {

    @Autowired
    protected AdminService adminService;
    @Autowired
    protected CompanyService companyService;
    @Autowired
    protected CustomerService customerService;

    public abstract LoginResDto login(LoginReqDto loginReqDto) throws CouponSecurityException, CustomException;

}
