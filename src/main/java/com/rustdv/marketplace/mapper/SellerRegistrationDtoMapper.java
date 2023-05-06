package com.rustdv.marketplace.mapper;

import com.rustdv.marketplace.dto.auth.SellerRegistrationDto;
import com.rustdv.marketplace.entity.Seller;
import com.rustdv.marketplace.entity.embeddable.Address;
import com.rustdv.marketplace.entity.embeddable.OrganizationType;
import org.springframework.stereotype.Component;

@Component
public class SellerRegistrationDtoMapper implements Mapper<SellerRegistrationDto, Seller> {
    @Override
    public Seller map(SellerRegistrationDto from) {

        return Seller.builder()
                .email(from.getEmail())
                .password(from.getPassword())
                .inn(from.getInn())
                .address(Address.builder()
                        .city(from.getCity())
                        .street(from.getStreet())
                        .houseNumber(from.getHouseNumber())
                        .build())
                .organizationType(OrganizationType.getOrganizationType(from.getOrganizationType()))
                .magazineName(from.getMagazineName())
                .phoneNumber(from.getPhoneNumber())
                .build();
    }
}
