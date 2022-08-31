package com.OzProject.couponSystem.servies;

import com.OzProject.couponSystem.beans.*;
import com.OzProject.couponSystem.dto.*;
import com.OzProject.couponSystem.exception.*;

import java.util.*;

public interface AdminService {

    void addCompany(Company company, UUID token) throws CustomException;

    void addCompanies(List<Company> companies) throws CustomException;

    CompanyDto updateCompany(int companyId, CompanyDto companyDto, UUID token) throws CustomException;

    void deleteCompany(int companyId, UUID token) throws CustomException;

    List<Company> getAllCompanies(UUID token);

    Company getOneCompany(int id, UUID token) throws CustomException;

    void addCustomer(Customer customer, UUID token) throws CustomException;

    void addCustomers(List<Customer> customers, UUID token) throws CustomException;

    CustomerDto updateCustomer(int customerId, CustomerDto customerDto, UUID token) throws CustomException, CouponSecurityException;

    void deleteCustomer(int customerId, UUID token) throws CustomException;

    List<Customer> getAllCustomers(UUID token);

    Customer getOneCustomer(int customerId, UUID token) throws CustomException;
}
