package com.OzProject.couponSystem.mapper;

import com.OzProject.couponSystem.beans.*;
import com.OzProject.couponSystem.dto.*;
import com.OzProject.couponSystem.repository.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Component
@RequiredArgsConstructor
public class CouponMapper implements Mapper<Coupon, CouponDto> {

    private final CompanyRepo companyRepo;

    private final DateMapper dateMapper;


    @SneakyThrows
    @Override
    public Coupon toBean(CouponDto couponDto) {
        return Coupon.builder()
                .title(couponDto.getTitle())
                .category(Category.valueOf(couponDto.getCategory()))
                .company(companyRepo.getById(couponDto.getCompanyId()))
                .amount(couponDto.getAmount())
                .price(couponDto.getPrice())
                .description(couponDto.getDescription())
                .endDate(couponDto.getEndDate())
                .startDate(couponDto.getStartDate())
                .image(couponDto.getImage())
                .id(couponDto.getId())
                .build();

    }

    @Override
    public CouponDto toDto(Coupon coupon) {
        return CouponDto.builder()
                .companyId(coupon.getCompany().getId())
                .amount(coupon.getAmount())
                .category(coupon.getCategory().toString())
                .id(coupon.getId())
                .description(coupon.getDescription())
                .endDate(coupon.getEndDate())
                .startDate(coupon.getStartDate())
                .price(coupon.getPrice())
                .title(coupon.getTitle())
                .image(coupon.getImage())
                .build();

    }

    @Override
    public List<Coupon> toBeanList(List<CouponDto> couponDtos) {
        return couponDtos.stream().map(this::toBean).collect(Collectors.toList());
    }

    @Override
    public List<CouponDto> toDtoList(List<Coupon> coupons) {
        return coupons.stream().map(this::toDto).collect(Collectors.toList());
    }
}
