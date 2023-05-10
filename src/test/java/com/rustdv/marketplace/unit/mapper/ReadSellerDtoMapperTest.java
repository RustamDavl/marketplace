package com.rustdv.marketplace.unit.mapper;

import com.rustdv.marketplace.dto.read.ReadSellerDto;
import com.rustdv.marketplace.entity.Seller;
import com.rustdv.marketplace.entity.embeddable.Address;
import com.rustdv.marketplace.entity.embeddable.OwnershipForm;
import com.rustdv.marketplace.mapper.ReadSellerDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ReadSellerDtoMapperTest {

    private Seller seller;
    private ReadSellerDto expectedResult;

    private ReadSellerDtoMapper readSellerDtoMapper;

    @BeforeEach
    void setUp() {

        readSellerDtoMapper = new ReadSellerDtoMapper();
        seller = Seller.builder()
                .id(10L)
                .email("easton12345@gmail.com")
                .password("strong")
                .phoneNumber("89179308071")
                .ownershipForm(OwnershipForm.IP)
                .registerAt(LocalDateTime.now())
                .build();

        expectedResult = ReadSellerDto.builder()
                .id(10L)
                .email("easton12345@gmail.com")
                .magazineName("name")
                .registerAt(seller.getRegisterAt())
                .build();
    }

    @Test
    void map() {
        ReadSellerDto actualSeller = readSellerDtoMapper.map(seller);
        assertThat(actualSeller.getEmail()).isEqualTo(expectedResult.getEmail());
        assertThat(actualSeller.getMagazineName()).isEqualTo(expectedResult.getMagazineName());
        assertThat(actualSeller.getId()).isEqualTo(expectedResult.getId());
        assertThat(actualSeller.getRegisterAt()).isEqualTo(expectedResult.getRegisterAt());


    }
}