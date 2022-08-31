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
public class AdminServiceImpl extends ClientService implements AdminService {

    private final CompanyMapper companyMapper;
    private final CustomerMapper customerMapper;

    @Override
    public void addCompany(Company company, UUID token) throws CustomException {
        if (companyRepo.existsByEmail(company.getEmail()) || companyRepo.existsByName(company.getName())) {
            throw new CustomException(ErrorMessage.COMPANY_ALREADY_EXIST);
        }
        companyRepo.save(company);
    }

    @Override
    public void addCompanies(List<Company> companies) throws CustomException {
        for (Company company : companies) {
            if (companyRepo.existsByName(company.getName()) || (companyRepo.existsByEmail(company.getEmail()))) {
                throw new CustomException(ErrorMessage.COMPANY_ALREADY_EXIST);
            }
        }
        companyRepo.saveAllAndFlush(companies);
    }


    @Override
    public CompanyDto updateCompany(int companyId, CompanyDto companyDto, UUID token) throws CustomException {
        if (!companyRepo.existsById(companyId)) {
            throw new CustomException(ErrorMessage.COMPANY_NOT_EXIST);
        }

        companyDto.setCompanyId(companyId);
        Company company = companyMapper.toBean(companyDto);

        return companyMapper.toDto(companyRepo.saveAndFlush(company));
    }

    @Override
    public void deleteCompany(int companyId, UUID token) throws CustomException {
        if (!companyRepo.existsById(companyId)) {
            throw new CustomException(ErrorMessage.COMPANY_NOT_EXIST);
        }

        companyRepo.deleteById(companyId);
    }

    @Override
    public List<Company> getAllCompanies(UUID token) {

        return companyRepo.findAll();
    }


    @Override
    public Company getOneCompany(int id, UUID token) throws CustomException {

        return companyRepo.findById(id).orElseThrow(() -> new CustomException(ErrorMessage.COMPANY_NOT_EXIST));
    }

    @Override
    public void addCustomer(Customer customer, UUID token) throws CustomException {
        if (customerRepo.existsByEmail(customer.getEmail())) {
            throw new CustomException(ErrorMessage.CUSTOMER_ALREADY_EXIST);
        }
        customerRepo.save(customer);
    }

    @Override
    public void addCustomers(List<Customer> customers, UUID token) throws CustomException {
        for (Customer customer : customers) {
            if (customerRepo.existsByEmail(customer.getEmail())) {
                throw new CustomException(ErrorMessage.CUSTOMER_ALREADY_EXIST);
            }
        }
        customerRepo.saveAllAndFlush(customers);
    }

    @Override
    public CustomerDto updateCustomer(int customerId, CustomerDto customerDto, UUID token) throws CustomException, CouponSecurityException {
        if (!tokenManager.isAdminTokenValid(token)) {
            throw new CouponSecurityException(SecurityMessage.INVALID_TOKEN);
        }
        if (!customerRepo.existsById(customerId)) {
            throw new CustomException(ErrorMessage.CUSTOMER_NOT_EXIST);
        }
        customerDto.setCustomerId(customerId);
        Customer customer = customerMapper.toBean(customerDto);

        return customerMapper.toDto(customerRepo.saveAndFlush(customer));

    }

    @Override
    public void deleteCustomer(int customerId, UUID token) throws CustomException {
        if (!customerRepo.existsById(customerId)) {
            throw new CustomException(ErrorMessage.CUSTOMER_NOT_EXIST);
        }
        customerRepo.deleteById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers(UUID token) {
        return customerRepo.findAll();
    }

    @Override
    public Customer getOneCustomer(int customerId, UUID token) throws CustomException {

        return customerRepo.findById(customerId).orElseThrow(() -> new CustomException(ErrorMessage.CUSTOMER_NOT_EXIST));

    }

}
