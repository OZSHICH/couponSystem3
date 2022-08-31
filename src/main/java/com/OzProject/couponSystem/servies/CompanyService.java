package com.OzProject.couponSystem.servies;

import com.OzProject.couponSystem.beans.*;
import com.OzProject.couponSystem.dto.*;
import com.OzProject.couponSystem.exception.*;

import java.util.*;

public interface CompanyService {

    CouponDto addCoupon(int companyId, CouponDto couponDto, UUID token) throws CustomException, CouponSecurityException;

    CouponDto updateCoupon(int couponId, CouponDto couponDto, UUID token) throws CustomException, CouponSecurityException;

    void deleteCoupon(int couponId, UUID token) throws CustomException;

    List<Coupon> getAllCompanyCoupons(int companyId, UUID token);

    List<Coupon> getAllCompanyCouponsByCategory(int companyId, Category category, UUID token);

    List<Coupon> getAllCompanyCouponsByPrice(int companyId, double maxPrice, UUID token);

    Company getCompanyDetails(int companyID, UUID token) throws CustomException;

}
