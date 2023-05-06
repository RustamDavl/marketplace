package com.rustdv.marketplace.unit.mapper;

import com.rustdv.marketplace.entity.Buyer;
import com.rustdv.marketplace.entity.embeddable.Address;
import com.rustdv.marketplace.entity.embeddable.Gender;
import com.rustdv.marketplace.mapper.ReadBuyerDtoMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class ReadBuyerDtoMapperTest {

    private ReadBuyerDtoMapper readBuyerDtoMapper;

    @BeforeEach
    void setUp() {
        readBuyerDtoMapper = new ReadBuyerDtoMapper();
    }
    @Test
    void map() {
        var address = Address.builder()
                .city("Kazan")
                .street("Test street")
                .houseNumber("16k1")
                .build();
        Buyer buyer = Buyer.builder()
                .email("test@gmail.com")
                .password("strong")
                .address(address)
                .birthDate(LocalDate.of(2001, 1, 29))
                .gender(Gender.MALE)
                .phoneNumber("89179209061")
                .registerAt(LocalDateTime.now())
                .build();

        var actualResult = readBuyerDtoMapper.map(buyer);

        assertThat(actualResult.getEmail()).isEqualTo(buyer.getEmail());
        assertThat(actualResult.getPhoneNumber()).isEqualTo(buyer.getPhoneNumber());
        assertThat(actualResult.getCity()).isEqualTo(buyer.getAddress().getCity());
        assertThat(actualResult.getStreet()).isEqualTo(buyer.getAddress().getStreet());
        assertThat(actualResult.getHouseNumber()).isEqualTo(buyer.getAddress().getHouseNumber());
        assertThat(actualResult.getEmail()).isEqualTo(buyer.getEmail());
        assertThat(actualResult.getBirthDate()).isEqualTo(buyer.getBirthDate().toString());
    }
}