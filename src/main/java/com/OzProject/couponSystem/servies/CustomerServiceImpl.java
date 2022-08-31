package com.OzProject.couponSystem.servies;

import com.OzProject.couponSystem.beans.*;
import com.OzProject.couponSystem.dto.*;
import com.OzProject.couponSystem.exception.*;
import com.OzProject.couponSystem.mapper.*;
import com.OzProject.couponSystem.security.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends ClientService implements CustomerService {

    private final TokenManager tokenManager;
    private final CustomerMapper customerMapper;
    private final CouponMapper couponMapper;

    @Override
    public void register(CustomerDto registerReqDto) throws CouponSecurityException {
        if (customerRepo.existsByEmailAndPassword(registerReqDto.getEmail(), registerReqDto.getPassword())) {
            throw new CouponSecurityException(SecurityMessage.EMAIL_OR_PASSWORD_INCORRECT);
        }
        System.out.println(customerMapper.toBean(registerReqDto));
        customerRepo.save(customerMapper.toBean(registerReqDto));
    }

//    @Override
//    public boolean login(String email, String password) throws CustomException {
//        if (!customerRepo.existsByEmailAndPassword(email, password)) {
//            throw new CustomException(ErrorMessage.CUSTOMER_NOT_EXIST);
//        }
//        Customer customer = customerRepo.findByEmail(email);
//        System.out.println("*********Customer Number " + customer.getId() + " Has Logged in" + " ********");
//        return true;
//    }


    @Override
    public CouponDto purchaseCoupon(int customerId, int couponId, UUID token) throws CouponSecurityException, CustomException {

        Coupon coupon = (couponRepo.findById(couponId).orElseThrow(() -> new CustomException(ErrorMessage.CUSTOMER_NOT_EXIST)));
        //Coupon coupon = (couponRepo.findById(couponId).orElseThrow(() -> new CustomException(ErrorMessage.CUSTOMER_NOT_EXIST)));

        if (tokenManager.getUserId(token) != customerId) {
            throw new CouponSecurityException(SecurityMessage.INVALID_TOKEN);
        }

        if (coupon.getAmount() < 1) {
            throw new CustomException(ErrorMessage.COUPON_UNAVAILABLE);
        }

        if (customerRepo.purchaseCouponIsExist(customerId, coupon.getId()) > 0) {
            throw new CustomException(ErrorMessage.COUPON_ALREADY_EXIST);
        }

//        if (!customerRepo.isCouponExpired(coupon.getId())) {
//            throw new CustomException(ErrorMessage.COUPON_EXPIRED);
//        }

        customerRepo.addCouponPurchase(customerId, coupon.getId());
        coupon.setAmount(coupon.getAmount() - 1);
        return couponMapper.toDto(couponRepo.save(coupon));
    }

    @Override
    public Set<Coupon> getCustomerCoupons(int customerId) throws CustomException {
        return customerRepo.findById(customerId).orElseThrow(() -> new CustomException(ErrorMessage.CUSTOMER_NOT_EXIST)).getCoupons();
    }

    @Override
    public Set<Coupon> getCustomerCouponsByCategory(int customerId, Category category, UUID token) throws CustomException {
        Set<Coupon> coupons = customerRepo.findById(customerId).orElseThrow(() -> new CustomException(ErrorMessage.CUSTOMER_NOT_EXIST)).getCoupons();

        for (Coupon coupon : coupons) {
            if (coupon.getCategory() == category)
                coupons.add(coupon);

        }

        return coupons;
    }

    @Override
    public Set<Coupon> getCustomerCouponsByPrice(int customerId, double maxPrice, UUID token) throws CustomException {
        Set<Coupon> coupons = customerRepo.findById(customerId).orElseThrow(() -> new CustomException(ErrorMessage.CUSTOMER_NOT_EXIST)).getCoupons();
        for (Coupon coupon : coupons) {
            if (coupon.getPrice() > 0)
                coupons.add(coupon);
        }
        return coupons;
    }


    @Override
    public Customer getCustomerDetails(int customerId, UUID uuid) throws CouponSecurityException, CustomException {
        if (tokenManager.getUserId(uuid) != customerId) {
            throw new CouponSecurityException(SecurityMessage.INVALID_TOKEN);
        }
        ;
        return customerRepo.findById(customerId).orElseThrow(() -> new CustomException(ErrorMessage.CUSTOMER_NOT_EXIST));

    }

    @Override
    public List<Coupon> getAllCouponsAvailableForPurchase(int customerID, UUID token) {
        return couponRepo.getAllAvailableCoupons(customerID);
    }


    @Override
    public List<Coupon> getAllCustomerCoupons(int customerId) {
        return couponRepo.findAll();

    }

}
