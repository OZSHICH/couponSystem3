package com.OzProject.couponSystem.controller;

import com.OzProject.couponSystem.beans.*;
import com.OzProject.couponSystem.dto.*;
import com.OzProject.couponSystem.exception.*;
import com.OzProject.couponSystem.security.*;
import com.OzProject.couponSystem.servies.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/companies")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CompanyController {


    private final CompanyService companyService;
    private final TokenManager tokenManager;


    @PostMapping("/addCoupon/{companyId}")
    CouponDto addCoupon(@RequestHeader("Authorization") UUID token, @RequestBody CouponDto couponDto) throws CustomException, CouponSecurityException {
        int companyId = tokenManager.getUserId(token);
        return companyService.addCoupon(companyId, couponDto, token);
    }

    @PutMapping("/update/{couponId}")
    CouponDto updateCoupon(@RequestHeader("Authorization") UUID token, @PathVariable int couponId, @RequestBody CouponDto couponDto) throws CustomException, CouponSecurityException {
        int companyId = tokenManager.getUserId(token);
        return companyService.updateCoupon(couponId, couponDto, token);
    }

    @DeleteMapping("/delete/{id}")
    void deleteCoupon(@RequestHeader("Authorization") UUID token, @PathVariable int id) throws CouponSecurityException, CustomException {
        companyService.deleteCoupon(id, token);
    }

    @GetMapping("/coupons/{Id}")
    List<Coupon> getCompanyCoupons(@RequestHeader("Authorization") UUID token) throws CouponSecurityException {
        int companyId = tokenManager.getUserId(token);
        return companyService.getAllCompanyCoupons(companyId, token);
    }

    @GetMapping("/coupons/category/{category}")
    List<Coupon> getCompanyCouponsByCategory(@RequestHeader("Authorization") UUID token, @PathVariable Category category) throws CouponSecurityException {
        int companyId = tokenManager.getUserId(token);
        return companyService.getAllCompanyCouponsByCategory(companyId, category, token);
    }

    @GetMapping("/{companyId}/{maxPrice}")
    List<Coupon> getCompanyCoupons(@RequestHeader("Authorization") UUID token, @PathVariable int companyId, @RequestParam int maxPrice) {
        return companyService.getAllCompanyCouponsByPrice(companyId, maxPrice, token);
    }

    @GetMapping("/details")
    public Company getDetails(@RequestHeader("Authorization") UUID token) throws CouponSecurityException, CustomException {
        int companyId = tokenManager.getUserId(token);
        return companyService.getCompanyDetails(companyId, token);
    }

}

