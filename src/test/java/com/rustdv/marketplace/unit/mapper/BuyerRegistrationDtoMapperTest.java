package com.rustdv.marketplace.unit.mapper;

import com.rustdv.marketplace.dto.auth.BuyerRegistrationDto;
import com.rustdv.marketplace.entity.Buyer;
import com.rustdv.marketplace.entity.embeddable.Address;
import com.rustdv.marketplace.entity.embeddable.Gender;
import com.rustdv.marketplace.mapper.BuyerRegistrationDtoMapper;
import com.rustdv.marketplace.mapper.ReadBuyerDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class BuyerRegistrationDtoMapperTest {

    private BuyerRegistrationDtoMapper buyerRegistrationDtoMapper;

    @BeforeEach
    void setUp() {
        buyerRegistrationDtoMapper = new BuyerRegistrationDtoMapper();
    }

    @Test
    void map() {
        var address = Address.builder()
                .city("Kazan")
                .street("street")
                .houseNumber("16k1")
                .build();

        BuyerRegistrationDto buyerRegistrationDtoFromRequest = new BuyerRegistrationDto(
                "test@gmail.com",
                "strong",
                "89179209061",
                "Kazan",
                "street",
                "16k1",
                "мужчина",
                LocalDate.parse("2001-01-29"));


        Buyer expectedResult = Buyer.builder()
                .email("test@gmail.com")
                .password("strong")
                .address(address)
                .birthDate(LocalDate.of(2001, 1, 29))
                .gender(Gender.MALE)
                .phoneNumber("89179209061")
                .build();

        var actualResult = buyerRegistrationDtoMapper.map(buyerRegistrationDtoFromRequest);


        assertThat(actualResult.getEmail()).isEqualTo(expectedResult.getEmail());


        assertThat(actualResult.getEmail()).isEqualTo(expectedResult.getEmail());
        assertThat(actualResult.getPhoneNumber()).isEqualTo(expectedResult.getPhoneNumber());
        assertThat(actualResult.getAddress()).isEqualTo(expectedResult.getAddress());
        assertThat(actualResult.getEmail()).isEqualTo(expectedResult.getEmail());
        assertThat(actualResult.getBirthDate()).isEqualTo(expectedResult.getBirthDate().toString());

    }
}
