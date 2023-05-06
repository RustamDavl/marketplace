package com.rustdv.marketplace.unit.mapper;

import com.rustdv.marketplace.dto.auth.BuyerRegistrationDto;
import com.rustdv.marketplace.dto.auth.SellerRegistrationDto;
import com.rustdv.marketplace.entity.Buyer;
import com.rustdv.marketplace.entity.Seller;
import com.rustdv.marketplace.entity.embeddable.Address;
import com.rustdv.marketplace.entity.embeddable.OrganizationType;
import com.rustdv.marketplace.mapper.SellerRegistrationDtoMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SellerRegistrationDtoMapperTest {

    private Seller expectedSeller;

    private SellerRegistrationDto registrationSellerDtoRequest;

    private SellerRegistrationDtoMapper sellerRegistrationDtoMapper;


    @BeforeEach
    void setUp() {
        sellerRegistrationDtoMapper = new SellerRegistrationDtoMapper();

        expectedSeller = Seller.builder()
                .email("easton12345@gmail.com")
                .password("strong")
                .address(Address.builder()
                        .city("Kazan")
                        .street("street")
                        .houseNumber("16k1")
                        .build())
                .inn("123456789012")
                .magazineName("some name")
                .phoneNumber("89179308071")
                .organizationType(OrganizationType.IP)
                .build();

        registrationSellerDtoRequest = SellerRegistrationDto.builder()
                .email("easton12345@gmail.com")
                .password("strong")
                .city("Kazan")
                .street("street")
                .houseNumber("16k1")
                .inn("123456789012")
                .magazineName("some name")
                .phoneNumber("89179308071")
                .organizationType("ИП")
                .build();


    }

    @Test
    void map() {
        Seller actualResult = sellerRegistrationDtoMapper.map(registrationSellerDtoRequest);
        assertThat(actualResult.getOrganizationType()).isEqualTo(expectedSeller.getOrganizationType());
        assertThat(actualResult.getEmail()).isEqualTo(expectedSeller.getEmail());
        assertThat(actualResult.getPassword()).isEqualTo(expectedSeller.getPassword());
        assertThat(actualResult.getPhoneNumber()).isEqualTo(expectedSeller.getPhoneNumber());
        assertThat(actualResult.getAddress().getCity()).isEqualTo(expectedSeller.getAddress().getCity());


    }
}