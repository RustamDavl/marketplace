package com.rustdv.marketplace.mapper;

import com.rustdv.marketplace.dto.read.ReadBuyerDto;
import com.rustdv.marketplace.entity.Buyer;
import org.springframework.stereotype.Component;

@Component
public class ReadBuyerDtoMapper implements Mapper<Buyer, ReadBuyerDto> {
    @Override
    public ReadBuyerDto map(Buyer from) {

        return ReadBuyerDto.builder()
                .id(from.getId())
                .email(from.getEmail())
                .birthDate(from.getBirthDate())
                .city(from.getAddress().getCity())
                .street(from.getAddress().getStreet())
                .houseNumber(from.getAddress().getHouseNumber())
                .gender(from.getGender().toString())
                .registerAt(from.getRegisterAt().toString())
                .phoneNumber(from.getPhoneNumber())
                .build();
    }
}
