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
@RequestMapping("api/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

    private final CustomerService customerService;
    private final TokenManager tokenManager;
    private final CouponMapper couponMapper;


    @PostMapping("/purchase/{couponId}")
    CouponDto purchaseCoupon(@RequestHeader("Authorization") UUID token, @PathVariable int couponId) throws CustomException, CouponSecurityException {
        int customerId = tokenManager.getUserId(token);
        return customerService.purchaseCoupon(customerId, couponId, token);
    }

    @GetMapping("coupons/{customerId}")
    Set<Coupon> getCustomerCoupons(@RequestHeader("Authorization") UUID token) throws CouponSecurityException, CustomException {
        int customerId = tokenManager.getUserId(token);
        return customerService.getCustomerCoupons(customerId);
    }

    @GetMapping("coupons/{customerId}/{category}")
    Set<Coupon> getCustomerCouponsByCategory(@RequestHeader("Authorization") UUID token, @PathVariable Category category) throws CouponSecurityException, CustomException {
        int customerId = tokenManager.getUserId(token);
        return customerService.getCustomerCouponsByCategory(customerId, category, token);
    }

    @GetMapping("coupons/{customerId}/{maxPrice}")
    Set<Coupon> getCustomerCouponsByPrice(@RequestHeader("Authorization") UUID token, @RequestParam double maxPrice) throws CouponSecurityException, CustomException {
        int customerId = tokenManager.getUserId(token);
        return customerService.getCustomerCouponsByPrice(customerId, maxPrice, token);
    }

    @GetMapping("coupons")
    List<CouponDto> getAllCoupons(@RequestHeader("Authorization") UUID token) throws CouponSecurityException {
        int customerId = tokenManager.getUserId(token);
        return couponMapper.toDtoList(customerService.getAllCustomerCoupons(customerId));
    }

    @GetMapping("/purchase/{customerId}")
    List<Coupon> getAllAvailableCoupons(@RequestHeader("Authorization") UUID token, @PathVariable int customerId) {
        return customerService.getAllCouponsAvailableForPurchase(customerId, token);
    }

    @GetMapping("/customerDetails")
    Customer getCustomerDetails(@RequestHeader("Authorization") UUID token) throws CouponSecurityException, CustomException {
        int customerId = tokenManager.getUserId(token);
        return customerService.getCustomerDetails(customerId, token);
    }


}
