package com.rustdv.marketplace.integration.service;

import com.rustdv.marketplace.dto.auth.SellerRegistrationDto;
import com.rustdv.marketplace.entity.Seller;
import com.rustdv.marketplace.entity.embeddable.Address;
import com.rustdv.marketplace.entity.embeddable.OrganizationType;
import com.rustdv.marketplace.exception.UserAlreadyExistsException;
import com.rustdv.marketplace.integration.IntegrationTestBase;
import com.rustdv.marketplace.repository.SellerRepository;
import com.rustdv.marketplace.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.assertj.core.api.Assertions.assertThat;


@RequiredArgsConstructor
@Sql(
        value = "classpath:sql/data.sql",
        config = @SqlConfig(encoding = "utf-8")
)
public class SellerServiceIT extends IntegrationTestBase {

    private final SellerService sellerService;


    @Test
    void registerShouldPass() {
        var registrationSellerDtoRequest = SellerRegistrationDto.builder()
                .email("easton@gmail.com")
                .password("strong")
                .city("Kazan")
                .street("street")
                .houseNumber("16k1")
                .inn("123456789012")
                .magazineName("some name")
                .phoneNumber("89179308071")
                .organizationType("ИП")
                .build();
        var actualResult = sellerService.register(registrationSellerDtoRequest);

        assertThat(actualResult.getEmail()).isEqualTo(registrationSellerDtoRequest.getEmail());
        assertThat(actualResult.getMagazineName()).isEqualTo(registrationSellerDtoRequest.getMagazineName());



    }

    @Test
    void registerShouldFail() {

        var registrationSellerDtoRequest = SellerRegistrationDto.builder()
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

        org.junit.jupiter.api.Assertions.assertThrows(UserAlreadyExistsException.class, () -> sellerService.register(registrationSellerDtoRequest));
    }
}
