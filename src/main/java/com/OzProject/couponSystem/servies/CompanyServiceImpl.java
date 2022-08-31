package com.OzProject.couponSystem.servies;

import com.OzProject.couponSystem.beans.*;
import com.OzProject.couponSystem.dto.*;
import com.OzProject.couponSystem.exception.*;
import com.OzProject.couponSystem.mapper.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl extends ClientService implements CompanyService {

    private final CouponMapper couponMapper;

    @Override
    public CouponDto addCoupon(int companyId, CouponDto couponDto, UUID token) throws CouponSecurityException, CustomException {
        if (tokenManager.getUserId(token) != companyId) {
            throw new CouponSecurityException(SecurityMessage.INVALID_TOKEN);

        }
        if (null != couponRepo.getCouponByCompanyIdAndTitle(companyId, couponDto.getTitle())) {
            throw new CustomException(ErrorMessage.COUPON_ALREADY_EXIST);
        }

        Coupon coupon = couponMapper.toBean(couponDto);

        Company company = companyRepo.getById(companyId);

        coupon.setCompany(company);

        couponDto = couponMapper.toDto(couponRepo.saveAndFlush(coupon));
        return couponDto;

    }

    @Override
    public CouponDto updateCoupon(int couponId, CouponDto couponDto, UUID token) throws CouponSecurityException, CustomException {

        int companyId = tokenManager.getUserId(token);
        if (tokenManager.getUserId(token) != companyId) {
            throw new CouponSecurityException(SecurityMessage.INVALID_TOKEN);
        }
        Coupon couponsBefore = couponRepo.findById(couponId).orElseThrow(() ->
                new CustomException(ErrorMessage.COUPON_NOT_EXIST));

        if (couponsBefore.getId() != couponDto.getId() && couponsBefore.getCompany().getId() != couponDto.getCompanyId()) {
            throw new CustomException(ErrorMessage.COUPON_NOT_EXIST);
        }
        couponDto.setCompanyId(companyId);
        Coupon coupon = couponMapper.toBean(couponDto);
        return couponMapper.toDto(couponRepo.saveAndFlush(coupon));
    }

    @Override
    public void deleteCoupon(int couponId, UUID token) throws CustomException {
        Coupon coupon = couponRepo.findById(couponId).orElseThrow(() -> new CustomException(ErrorMessage.COUPON_NOT_EXIST));
        couponRepo.delete(coupon);
        couponRepo.deleteCustomerCouponPurchases(couponId);
    }

    @Override
    public List<Coupon> getAllCompanyCoupons(int companyId, UUID token) {
        return couponRepo.getAllCouponsByCompanyId(companyId);
    }

    @Override
    public List<Coupon> getAllCompanyCouponsByCategory(int companyId, Category category, UUID token) {
//        List<Coupon> coupons = new ArrayList<>();
//        List<Coupon> couponsId = couponRepo.getAllCouponsByCompanyId(companyId);
//
//        for (Coupon coupon : couponsId) {
//            if (couponRepo.getById(coupon.getId()).getCategory() == category)
//                coupons.add(coupon);
//
//        }
//        return coupons;

        return couponRepo.findByCompanyIdAndCategory(companyId, category);
    }

    @Override
    public List<Coupon> getAllCompanyCouponsByPrice(int companyId, double maxPrice, UUID token) {
        return couponRepo.findByCompanyIdAndPriceLessThanEqual(companyId, maxPrice);

    }

    @Override
    public Company getCompanyDetails(int companyId, UUID token) throws CustomException {
        return companyRepo.findById(companyId).orElseThrow(() -> new CustomException(ErrorMessage.COMPANY_NOT_EXIST));
    }

}




