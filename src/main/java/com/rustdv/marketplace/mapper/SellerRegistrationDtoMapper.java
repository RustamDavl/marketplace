package com.rustdv.marketplace.mapper;

import com.rustdv.marketplace.dto.auth.SellerRegistrationDto;
import com.rustdv.marketplace.entity.Seller;
import com.rustdv.marketplace.entity.embeddable.OwnershipForm;
import org.springframework.stereotype.Component;

@Component
public class SellerRegistrationDtoMapper implements Mapper<SellerRegistrationDto, Seller> {
    @Override
    public Seller map(SellerRegistrationDto from) {

        return Seller.builder()
                .email(from.getEmail())
                .password(from.getPassword())
                .ownershipForm(OwnershipForm.getOwnershipForm(from.getOwnershipForm()))
                .phoneNumber(from.getPhoneNumber())
                .build();
    }
}
