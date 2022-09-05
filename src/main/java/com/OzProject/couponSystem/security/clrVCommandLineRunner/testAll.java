package com.OzProject.couponSystem.security.clrVCommandLineRunner;

import com.OzProject.couponSystem.beans.*;
import com.OzProject.couponSystem.repository.*;
import com.OzProject.couponSystem.security.*;
import lombok.*;
import org.springframework.boot.*;
import org.springframework.core.annotation.*;
import org.springframework.stereotype.*;

import java.sql.Date;
import java.time.*;
import java.util.*;

@Service
@RequiredArgsConstructor
@Order(1)
public class testAll implements CommandLineRunner {

    Date date = Date.valueOf(LocalDate.now().plusDays(10));
    Date endDate = Date.valueOf(LocalDate.now().plusDays(17));
    Date expiredDate = Date.valueOf(LocalDate.now().minusDays(2));
//Date.valueOf(LocalDate.now().minusDays(7)\plusDays(7))


    private final CompanyRepo companyRepo;
    private final CouponRepo couponRepo;
    private final CustomerRepo customerRepo;
    private final LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {

        Company c1 = Company
                .builder()
                .name("Coca Cola")
                .email("CocaCola@gmail.com")
                .password("2035757")
                .build();

        Company c2 = Company
                .builder()
                .name("Frito-Lay's")
                .email("Frito-Lay's@gmail.com")
                .password("2046922")
                .build();

        Company c3 = Company
                .builder()
                .name("Xiaomi")
                .email("Xiaomi@gmail.com")
                .password("578865")
                .build();

        Company c4 = Company
                .builder()
                .name("Lays")
                .email("Lays@gmail.com")
                .password("2434675")
                .build();

        Company c5 = Company
                .builder()
                .name("ACE")
                .email("Ace@gmail.com")
                .password("2342354")
                .build();


        Coupon co1 = Coupon.builder()
                .company(c1)
                .category(Category.Electricity)
                .title("Xiaomi Poco")
                .description("15% off")
                .startDate(date)
                //.startDate(Timestamp.valueOf(LocalDateTime.now()))
                .endDate(endDate)
                .amount(1)
                .price(2)
                .image("Image")
                .build();

        Coupon co2 = Coupon.builder()
                .company(c2)
                .category(Category.Food)
                .title("Doritos")
                .description("10% off")
                //.startDate(Timestamp.valueOf(LocalDateTime.now()))
                .startDate(date)
                //.endDate(Timestamp.valueOf(LocalDateTime.now().plusDays(5)))
                .endDate(endDate)
                .amount(4)
                .price(4)
                .image("Image")
                .build();

        Coupon co3 = Coupon.builder()
                .company(c1)
                .category(Category.Food)
                .title("Chips")
                .description("10% off")
                //.startDate(Timestamp.valueOf(LocalDateTime.now()))
                .startDate(date)
                //.endDate(Timestamp.valueOf(LocalDateTime.now().plusDays(5)))
                .endDate(endDate)
                .amount(2)
                .price(3)
                .image("image")
                .build();

        Coupon co4 = Coupon.builder()
                .company(c1)
                .category(Category.Electricity)
                .title("PS5")
                .description("5% off")
                //.startDate(Timestamp.valueOf(LocalDateTime.now()))
                .startDate(date)
                //.endDate(Timestamp.valueOf(LocalDateTime.now().plusDays(5)))
                .endDate(endDate)
                .amount(3)
                .price(3)
                .image("Image")
                .build();

        Coupon co5 = Coupon.builder()
                .company(c1)
                .category(Category.Electricity)
                .title("Cola 24")
                .description("10% off")
                //.startDate(Timestamp.valueOf(LocalDateTime.now()))
                .startDate(date)
                //.endDate(Timestamp.valueOf(LocalDateTime.now().plusDays(5)))
                .endDate(endDate)
                .amount(5)
                .price(2.5)
                .image("Image")
                .build();

        Customer cu1 = Customer.builder()
                .firstName("Tony")
                .lastName("Vespa")
                .email("TonyV@gmail.com")
                .password("5467798")
                .build();

        Customer cu2 = Customer.builder()
                .firstName("Papa")
                .lastName("Johns")
                .email("PapaJ@gmail.com")
                .password("56376453")
                .build();

        Customer cu3 = Customer.builder()
                .firstName("Johnnie")
                .lastName("Walker")
                .email("JohnnieW@gmail.com")
                .password("7846474")
                .build();

        Customer cu4 = Customer.builder()
                .firstName("Tomer")
                .lastName("Adams")
                .email("Tomer@gmail.com")
                .password("1234")
                .build();

        Customer cu5 = Customer.builder()
                .firstName("David")
                .lastName("Austin")
                .email("DavidA@gmail.com")
                .password("3567652")
                .build();

        companyRepo.saveAll(Arrays.asList(c1, c2, c3, c4, c5));
        customerRepo.saveAll(Arrays.asList(cu1, cu2, cu3, cu4, cu5));
        couponRepo.saveAll(Arrays.asList(co1, co2, co3, co4, co5));


//        System.out.println("       " + "*********AdminLogin********");
//
//        AdminService adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
//        System.out.println("*********InsertedCompanies/Customers/Coupons********");
//
//        adminService.addCompanies(Arrays.asList(c1, c2, c3, c4, c5));
//        System.out.println(Art.COMPANIES);
//        System.out.println("*********addCompaniesToTheSystem********");
//        companyRepo.findAll().forEach(System.out::println);
//
//        adminService.addCustomers(Arrays.asList(cu1, cu2, cu3, cu4, cu5));
//        System.out.println(Art.CUSTOMER);
//        System.out.println("*********addCustomersToTheSystem********");
//        customerRepo.findAll().forEach(System.out::println);
//
//        //couponRepo.saveAll(Arrays.asList(co1, co2, co3, co4));
//        System.out.println(Art.COUPONS);
//        System.out.println("*********addCouponsToTheSystem********");
//        couponRepo.findAll().forEach(System.out::println);
//
//        System.out.println("        " + "*********ACTIONS********");
//        System.out.println("*********updateCompanyAdminService********");
//        c2.setName("rtyu");
//        adminService.updateCompany(c2.getId(), c2);
//        companyRepo.findAll().forEach(System.out::println);
//
//        System.out.println("*********deleteCompanyAdminService********");
//        adminService.deleteCompany(c5.getId());
//        companyRepo.findAll().forEach(System.out::println);
//
//        System.out.println("*********getAllCompaniesAdminService********");
//        adminService.getAllCompanies();
//        companyRepo.findAll().forEach(System.out::println);
//
//        System.out.println("*********getOneCompanyAdminService********");
//        System.out.println(adminService.getOneCompany(c2.getId()));
//
//        System.out.println("*********updateCustomerAdminService********");
//        cu2.setFirstName("dfhjdgh");
//        adminService.updateCustomer(cu2.getId(), cu2);
//        customerRepo.findAll().forEach(System.out::println);
//
//        System.out.println("*********deleteCustomerAdminService********");
//        adminService.deleteCustomer(cu3.getId());
//        customerRepo.findAll().forEach(System.out::println);
//
//        System.out.println("*********getAllCustomersAdminService********");
//        adminService.getAllCustomers();
//        customerRepo.findAll().forEach(System.out::println);
//
//        System.out.println("*********getOneCustomerAdminService********");
//        System.out.println(adminService.getOneCustomer(cu2.getId()));
//
//        System.out.println("       " + "*********CompanyLogin********");
//        CompanyService companyService1 = (CompanyService) loginManager.login("Frito-Lay's@gmail.com", "204692206", ClientType.COMPANY);
//
//        System.out.println("*********addCouponsToCompany********");
//
//        companyService1.addCoupon(c2.getId(), co2);
//        companyService1.addCoupon(c2.getId(), co3);
//        companyService1.addCoupon(c2.getId(), co5);
//
//        companyService1.getAllCoupons().forEach(System.out::println);
//
//        System.out.println("*********updateCouponCompanyService********");
//        co3.setAmount(6);
//        companyService1.updateCoupon(c2.getId(), co3.getId(), co3);
//        companyService1.getAllCoupons().forEach(System.out::println);
//
//        System.out.println("*********getAllCouponsByCategory********");
//        companyService1.getAllCouponsByCategory(c2.getId(), Category.Food).forEach(System.out::println);
//
//        System.out.println("*********getAllCouponsByMaxPrice********");
//        companyService1.getAllCouponsByPrice(c2.getId(), 3).forEach(System.out::println);
//
//        System.out.println("*********getCompanyDetails********");
//        System.out.println(companyService1.getOneCompany(c2.getId()));
//
//        System.out.println("       " + "*********CompanyLogin********");
//        CompanyService companyService2 = (CompanyService) loginManager.login("Lays@gmail.com", "243467543", ClientType.COMPANY);
//        System.out.println("*********addCouponsToCompany********");
//
//        companyService2.addCoupon(c4.getId(), co1);
//        companyService2.addCoupon(c4.getId(), co4);
//        companyService2.addCoupon(c4.getId(), co5);
//        companyService2.getAllCoupons().forEach(System.out::println);
//
//        System.out.println("*********deleteCouponCompanyService********");
//
//        companyService2.deleteCoupon(c4.getId(), co4.getId());
//        companyService2.getAllCoupons().forEach(System.out::println);
//
//
//        System.out.println("       " + "*********CustomerLogin********");
//        CustomerService customerService = (CustomerService) loginManager.login("Tomer@gmail.com", "1234", ClientType.CUSTOMER);
//
//        System.out.println("*********CustomerPurchaseCoupon********");
////        customerService.purchaseCoupon(co2);
////        System.out.println("2");
//        customerService.purchaseCoupon(co3);
//        System.out.println("3");
////        try {
////            customerService.purchaseCoupon(co2);
////        } catch (CustomException e) {
////            System.out.println(e.getMessage());
////        }
//        //customerRepo.findAll().forEach(System.out::println);
//
//        System.out.println("*********getAllPurchaseCouponByCustomerId********");
//        customerService.getAllPurchaseCouponsByCustomerId(cu4.getId()).forEach(System.out::println);
//
//        System.out.println("*********getAllPurchaseCouponByCategory********");
//        customerService.getAllPurchaseCouponsByCategory(Category.Food).forEach(System.out::println);
//
//        System.out.println("*********getAllPurchaseCouponByPrice********");
//        customerService.getAllPurchaseCouponsByPrice(cu4.getId(), 5.5).forEach(System.out::println);
//
//        System.out.println("*********getCustomerDetails********");
//        System.out.println(customerService.getCustomerDetails(cu4.getId()));
//
//
        System.out.println("*********ListExpiredCoupons********");
        couponRepo.findAllExpiredCoupon().forEach(System.out::println);
//        System.out.println(customerService.getCustomerDetails(cu1.getId()));
//        System.out.println("Asdasds");
    }


}
