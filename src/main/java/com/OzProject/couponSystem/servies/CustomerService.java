package com.OzProject.couponSystem.servies;

import com.OzProject.couponSystem.beans.*;
import com.OzProject.couponSystem.dto.*;
import com.OzProject.couponSystem.exception.*;

import java.util.*;

public interface CustomerService {

    CouponDto purchaseCoupon(int customerId, int couponId, UUID token) throws CouponSecurityException, CustomException;

    void register(CustomerDto registerReqDto) throws CouponSecurityException;

    Set<Coupon> getCustomerCoupons(int customerId) throws CustomException;

    Set<Coupon> getCustomerCouponsByCategory(int customerId, Category category, UUID token) throws CustomException;

    Set<Coupon> getCustomerCouponsByPrice(int customerId, double maxPrice, UUID token) throws CustomException;

    Customer getCustomerDetails(int customerId, UUID token) throws CouponSecurityException, CustomException;

    List<Coupon> getAllCouponsAvailableForPurchase(int customerId, UUID token);

    List<Coupon> getAllCustomerCoupons(int customerId);

}
