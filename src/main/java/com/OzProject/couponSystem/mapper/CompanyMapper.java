package com.OzProject.couponSystem.mapper;

import com.OzProject.couponSystem.beans.*;
import com.OzProject.couponSystem.dto.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Component
public class CompanyMapper implements Mapper<Company, CompanyDto> {

    @Override
    public Company toBean(CompanyDto companyDto) {
        return Company.builder()
                .id(companyDto.getCompanyId())
                .email(companyDto.getEmail())
                .password(companyDto.getPassword())
                .name(companyDto.getName())
                .build();
    }

    @Override
    public CompanyDto toDto(Company company) {
        return CompanyDto.builder()
                .companyId(company.getId())
                .email(company.getEmail())
                .password(company.getPassword())
                .name(company.getName())
                .build();
    }

    @Override
    public List<Company> toBeanList(List<CompanyDto> companyDtos) {
        return companyDtos.stream().map(this::toBean).collect(Collectors.toList());
    }

    @Override
    public List<CompanyDto> toDtoList(List<Company> companies) {
        return companies.stream().map(this::toDto).collect(Collectors.toList());
    }
}
