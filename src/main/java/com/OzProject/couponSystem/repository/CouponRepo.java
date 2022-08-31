package com.OzProject.couponSystem.repository;

import com.OzProject.couponSystem.beans.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import javax.transaction.*;
import java.sql.Date;
import java.util.*;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Integer> {

    boolean existsByTitleAndCompanyId(String title, int companyId);

    List<Coupon> findAllByCategory(Category category);

    @Query(value = "SELECT * FROM `couponSystem1`.coupons WHERE company_id = ? AND title = ? ;", nativeQuery = true)
    Coupon getCouponByCompanyIdAndTitle(int companyId, String title);

    @Query(value = "select id from couponSystem1.coupons where current_date() > end_date;", nativeQuery = true)
    List<Integer> findAllExpiredCoupon();

    List<Coupon> findByPriceLessThan(double price);

    @Transactional
    @Modifying
    @Query(value = "delete from couponSystem1.coupons where current_date() > end_date;", nativeQuery = true)
    void deleteExpiredCoupon();

    @Query(value = "SELECT * FROM couponSystem1.coupons where id not in(select coupon_id from customers_coupons where customer_id = ?);", nativeQuery = true)
    List<Coupon> getAllAvailableCoupons(int customerId);

    List<Coupon> findByIdAndEndDateBefore(int couponId, Date date);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM couponSystem1.customers_coupons where coupon_id = ?", nativeQuery = true)
    void deleteCustomerCouponPurchases(int couponId);

    List<Coupon> findByCompanyIdAndPriceLessThanEqual(int companyId, double maxPrice);

    @Query(value = "SELECT * FROM couponsystem1.coupons where company_id = ?;", nativeQuery = true)
    List<Coupon> getAllCouponsByCompanyId(int companyId);

    @Query(value = "delete from couponsystem1.customers_coupons where coupon_id = ?;", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteCustomersCoupons(int couponId);

    List<Coupon> findByCompanyIdAndCategory(int companyId, Category category);

}
