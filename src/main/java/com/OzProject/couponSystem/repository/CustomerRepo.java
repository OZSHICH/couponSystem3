package com.OzProject.couponSystem.repository;

import com.OzProject.couponSystem.beans.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import javax.transaction.*;
import java.util.*;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {


    boolean existsByEmailAndPassword(String email, String password);

    Optional<Customer> findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    Customer findByEmail(String email);

    Customer findTop1ByEmail(String email);

    @Query(value = "SELECT exists(SELECT * FROM `couponSystem1`.customers_coupons where customer_id = ? and coupon_id = ?) as res;", nativeQuery = true)
    int purchaseCouponIsExist(int customerId, int couponId);

    @Query(value = "select exists (SELECT * FROM couponSystem1.coupons where id = ? and current_date() > end_date) as res;", nativeQuery = true)
    boolean isCouponExpired(int id);

    @Query(value = "SELECT * FROM `couponSystem1`.coupons WHERE company_id = ? AND title = ? ;", nativeQuery = true)
    int getCouponByCompanyIdAndTitle(int companyId, String title);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `couponSystem1`.`customers_coupons` (`customer_id`, `coupon_id`) VALUES (?,?);", nativeQuery = true)
    void addCouponPurchase(int customerId, int couponId);

    @Query(value = "SELECT * FROM couponSystem1.customers_coupons join couponSystem1.coupons on customers_coupons.coupon_id = coupon.id where customers_coupons.customer_id = ? and price < ?;", nativeQuery = true)
    List<Coupon> getAllPurchaseCouponsByPrice(int customerId, double price);

    @Query(value = "SELECT * from coupons where id in (select coupon_id from customers_coupons where customer_id = ?);;", nativeQuery = true)
    List<Coupon> getAllPurchaseCouponsByCustomerId(int customerId);

    @Modifying
    @Transactional
    @Query(value = "DELETE from customers_coupons where coupons_id = ?", nativeQuery = true)
    void deleteCustomerPurchaseCoupon(int couponID);

}
