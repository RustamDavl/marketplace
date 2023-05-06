package com.rustdv.marketplace.integration.service;

import com.rustdv.marketplace.dto.auth.BuyerRegistrationDto;
import com.rustdv.marketplace.exception.UserAlreadyExistsException;
import com.rustdv.marketplace.integration.IntegrationTestBase;
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

        var actualResult = buyerService.register(buyerRegistrationRequest);

        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getEmail()).isEqualTo(buyerRegistrationRequest.getEmail());
        assertThat(actualResult.getPhoneNumber()).isEqualTo(buyerRegistrationRequest.getPhoneNumber());
        assertThat(actualResult.getCity()).isEqualTo(buyerRegistrationRequest.getCity());
        assertThat(actualResult.getStreet()).isEqualTo(buyerRegistrationRequest.getStreet());
        assertThat(actualResult.getHouseNumber()).isEqualTo(buyerRegistrationRequest.getHouseNumber());
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


        org.junit.jupiter.api.Assertions.assertThrows(UserAlreadyExistsException.class, () -> buyerService.register(buyerRegistrationRequest));

    }
}