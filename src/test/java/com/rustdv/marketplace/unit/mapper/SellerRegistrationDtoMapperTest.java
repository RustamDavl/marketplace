package com.rustdv.marketplace.unit.mapper;

import com.rustdv.marketplace.dto.auth.SellerRegistrationDto;
import com.rustdv.marketplace.entity.Seller;
import com.rustdv.marketplace.entity.embeddable.Address;
import com.rustdv.marketplace.entity.embeddable.OwnershipForm;
import com.rustdv.marketplace.mapper.SellerRegistrationDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
                .phoneNumber("89179308071")
                .ownershipForm(OwnershipForm.IP)
                .build();

        registrationSellerDtoRequest = SellerRegistrationDto.builder()
                .email("easton12345@gmail.com")
                .password("strong")
                .phoneNumber("89179308071")
                .ownershipForm("ИП")
                .build();


    }

    @Test
    void map() {
        Seller actualResult = sellerRegistrationDtoMapper.map(registrationSellerDtoRequest);
        assertThat(actualResult.getOwnershipForm()).isEqualTo(expectedSeller.getOwnershipForm());
        assertThat(actualResult.getEmail()).isEqualTo(expectedSeller.getEmail());
        assertThat(actualResult.getPassword()).isEqualTo(expectedSeller.getPassword());
        assertThat(actualResult.getPhoneNumber()).isEqualTo(expectedSeller.getPhoneNumber());

    }
}