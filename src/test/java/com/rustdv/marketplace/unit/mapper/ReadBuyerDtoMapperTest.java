package com.rustdv.marketplace.unit.mapper;

import com.rustdv.marketplace.dto.read.ReadBuyerDto;
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
        var expectedResult = ReadBuyerDto.builder()
                .email("test@gmail.com")
                .city("Kazan")
                .street("Test street")
                .houseNumber("16k1")
                .gender("мужчина")
                .birthDate(LocalDate.of(2001, 1, 29))
                .phoneNumber("89179209061")
                .registerAt(buyer.getRegisterAt().toString())
                .build();

        var actualResult = readBuyerDtoMapper.map(buyer);

        assertThat(actualResult.getEmail()).isEqualTo(expectedResult.getEmail());
        assertThat(actualResult.getPhoneNumber()).isEqualTo(expectedResult.getPhoneNumber());
        assertThat(actualResult.getCity()).isEqualTo(expectedResult.getCity());
        assertThat(actualResult.getStreet()).isEqualTo(expectedResult.getStreet());
        assertThat(actualResult.getHouseNumber()).isEqualTo(expectedResult.getHouseNumber());
        assertThat(actualResult.getEmail()).isEqualTo(expectedResult.getEmail());
        assertThat(actualResult.getBirthDate()).isEqualTo(expectedResult.getBirthDate().toString());
        assertThat(actualResult.getGender()).isEqualTo(expectedResult.getGender());
    }
}