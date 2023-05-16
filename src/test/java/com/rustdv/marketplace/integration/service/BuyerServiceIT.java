package com.rustdv.marketplace.integration.service;

import com.rustdv.marketplace.dto.auth.BuyerRegistrationDto;
import com.rustdv.marketplace.exception.UserAlreadyExistsException;
import com.rustdv.marketplace.integration.IntegrationTestBase;
import com.rustdv.marketplace.mapper.BuyerRegistrationDtoMapper;
import com.rustdv.marketplace.repository.BuyerRepository;
import com.rustdv.marketplace.service.BuyerService;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
@Sql(
        value = "classpath:sql/data.sql",
        config = @SqlConfig(encoding = "utf-8")
)
class BuyerServiceIT extends IntegrationTestBase {

    private final BuyerService buyerService;

    private final BuyerRegistrationDtoMapper buyerRegistrationDtoMapper;


    @Test
    void registerShouldPass() {
        BuyerRegistrationDto buyerRegistrationRequest = new BuyerRegistrationDto(
                "test@gmail.com",
                "strong",
                "89179209061",
                "Kazan",
                "street",
                "16k1",
                "мужчина",
                LocalDate.parse("2001-01-29"));
        var buyer = buyerRegistrationDtoMapper.map(buyerRegistrationRequest);

        var actualResult = buyerService.register(buyer);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getEmail()).isEqualTo(buyerRegistrationRequest.getEmail());
        assertThat(actualResult.getPhoneNumber()).isEqualTo(buyerRegistrationRequest.getPhoneNumber());
        assertThat(actualResult.getAddress().getCity()).isEqualTo(buyerRegistrationRequest.getCity());
        assertThat(actualResult.getAddress().getStreet()).isEqualTo(buyerRegistrationRequest.getStreet());
        assertThat(actualResult.getAddress().getHouseNumber()).isEqualTo(buyerRegistrationRequest.getHouseNumber());
        assertThat(actualResult.getBirthDate()).isEqualTo(buyerRegistrationRequest.getBirthDate());
    }

    @Test
    void registerShouldFail() {

        BuyerRegistrationDto buyerRegistrationRequest = new BuyerRegistrationDto(
                "easton12345@gmail.com",
                "strong",
                "89179209061",
                "Kazan",
                "street",
                "16k1",
                "мужчина",
                LocalDate.parse("2001-01-29"));
        var buyer = buyerRegistrationDtoMapper.map(buyerRegistrationRequest);


        org.junit.jupiter.api.Assertions.assertThrows(UserAlreadyExistsException.class, () -> buyerService.register(buyer));

    }
}