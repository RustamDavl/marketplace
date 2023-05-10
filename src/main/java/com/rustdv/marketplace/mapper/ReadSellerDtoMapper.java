package com.rustdv.marketplace.mapper;

import com.rustdv.marketplace.dto.read.ReadSellerDto;
import com.rustdv.marketplace.entity.Seller;
import org.springframework.stereotype.Component;

@Component
public class ReadSellerDtoMapper implements Mapper<Seller, ReadSellerDto> {

    @Override
    public ReadSellerDto map(Seller from) {

        return ReadSellerDto.builder()
                .id(from.getId())
                .email(from.getEmail())
                .registerAt(from.getRegisterAt())
                .ownershipForm(from.getOwnershipForm().toString())
                .build();
    }
}
