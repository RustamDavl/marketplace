package com.rustdv.marketplace.mapper;

import com.rustdv.marketplace.dto.auth.BuyerRegistrationDto;
import com.rustdv.marketplace.entity.Buyer;
import com.rustdv.marketplace.entity.embeddable.Address;
import com.rustdv.marketplace.entity.embeddable.Gender;
import org.springframework.stereotype.Component;

@Component
public class BuyerRegistrationDtoMapper implements Mapper<BuyerRegistrationDto, Buyer> {
    @Override
    public Buyer map(BuyerRegistrationDto from) {

        return Buyer.builder()
                .email(from.getEmail())
                .password(from.getPassword())
                .address(Address.builder()
                        .city(from.getCity())
                        .street(from.getStreet())
                        .houseNumber(from.getHouseNumber())
                        .build())
                .phoneNumber(from.getPhoneNumber())
                .gender(Gender.getGender(from.getGender()))
                .birthDate(from.getBirthDate())
                .build();

    }
}
