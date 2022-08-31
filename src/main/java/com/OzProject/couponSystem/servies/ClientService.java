package com.OzProject.couponSystem.servies;

import com.OzProject.couponSystem.repository.*;
import com.OzProject.couponSystem.security.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public abstract class ClientService {
    @Autowired
    protected CompanyRepo companyRepo;
    @Autowired
    protected CouponRepo couponRepo;
    @Autowired
    protected CustomerRepo customerRepo;
    @Autowired
    protected TokenManager tokenManager;

}
