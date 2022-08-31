package com.OzProject.couponSystem.security;

import com.OzProject.couponSystem.exception.*;
import com.OzProject.couponSystem.repository.*;
import com.OzProject.couponSystem.servies.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LoginManager {

    private final CompanyServiceImpl companyService;
    private final CustomerServiceImpl customerService;
    private final AdminServiceImpl adminService;
    private final CustomerRepo customerRepo;
    private final CompanyRepo companyRepo;


    public ClientService login(String email, String password, ClientType type) throws CouponSecurityException {
        switch (type) {
            case ADMINISTRATOR:
                if (!Objects.equals(email, "admin@admin.com") && !Objects.equals(password, "admin")) {
                    throw new CouponSecurityException(SecurityMessage.EMAIL_OR_PASSWORD_INCORRECT);
                }
                return adminService;
            case COMPANY:
                if (!companyRepo.existsByEmailAndPassword(email, password)) {
                    throw new CouponSecurityException(SecurityMessage.EMAIL_OR_PASSWORD_INCORRECT);
                }
                return companyService;
            case CUSTOMER:
                if (!customerRepo.existsByEmailAndPassword(email, password)) {
                    throw new CouponSecurityException(SecurityMessage.EMAIL_OR_PASSWORD_INCORRECT);
                }
                return customerService;

        }
        throw new CouponSecurityException(SecurityMessage.EMAIL_OR_PASSWORD_INCORRECT);
    }

}
