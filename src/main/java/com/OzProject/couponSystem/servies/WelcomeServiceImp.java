package com.OzProject.couponSystem.servies;


import com.OzProject.couponSystem.beans.*;
import com.OzProject.couponSystem.exception.*;
import com.OzProject.couponSystem.repository.*;
import com.OzProject.couponSystem.security.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor

public class WelcomeServiceImp implements WelcomeService {
    private final String email = "admin@admin.com";
    private final String password = "admin";

    private final TokenManager tokenManager;
    private final CustomerRepo customerRepo;
    private final CompanyRepo companyRepo;

    @Override
    public UUID login(String email, String password, ClientType clientType) throws CouponSecurityException {

        switch (clientType) {
            case CUSTOMER:
                Customer customer = customerRepo.findByEmailAndPassword(email, password).orElseThrow(() -> new CouponSecurityException(SecurityMessage.EMAIL_OR_PASSWORD_INCORRECT));

                return tokenManager.addToken(new Information(customer.getId(), customer.getFirstName(), email, ClientType.CUSTOMER));
            case COMPANY:
                Company company = companyRepo.findByEmailAndPassword(email, password).orElseThrow(() -> new CouponSecurityException(SecurityMessage.EMAIL_OR_PASSWORD_INCORRECT));

                return tokenManager.addToken(new Information(company.getId(), company.getName(), email, ClientType.COMPANY));
            case ADMINISTRATOR:

                if (!Objects.equals(email, this.email) || !Objects.equals(password, this.password)) {

                    throw new CouponSecurityException(SecurityMessage.EMAIL_OR_PASSWORD_INCORRECT);
                }

                return tokenManager.addToken(new Information(0, "admin", email, ClientType.ADMINISTRATOR));


        }
        throw new CouponSecurityException(SecurityMessage.EMAIL_OR_PASSWORD_INCORRECT);
    }

    @Override
    public void logOut(UUID uuid) {
        tokenManager.deleteByToken(uuid);

    }


}
