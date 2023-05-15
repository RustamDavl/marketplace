package com.rustdv.marketplace.integration.service;

import com.rustdv.marketplace.dto.auth.SellerRegistrationDto;
import com.rustdv.marketplace.dto.createupdate.CreateUpdateGoodsDto;
import com.rustdv.marketplace.exception.UserAlreadyExistsException;
import com.rustdv.marketplace.integration.IntegrationTestBase;
import com.rustdv.marketplace.repository.SellerRepository;
import com.rustdv.marketplace.service.SellerService;
import lombok.RequiredArgsConstructor;
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
                .phoneNumber("89179308071")
                .ownershipForm("ИП")
                .build();
        var actualResult = sellerService.register(registrationSellerDtoRequest);

        assertThat(actualResult.getId()).isNotNull();
        assertThat(actualResult.getEmail()).isEqualTo(registrationSellerDtoRequest.getEmail());
        assertThat(actualResult.getOwnershipForm()).isEqualTo(registrationSellerDtoRequest.getOwnershipForm());


    }

    @Test
    void registerShouldFail() {

        var registrationSellerDtoRequest = SellerRegistrationDto.builder()
                .email("easton12345@gmail.com")
                .password("strong")
                .phoneNumber("89179308071")
                .ownershipForm("ИП")
                .build();

        org.junit.jupiter.api.Assertions.assertThrows(UserAlreadyExistsException.class, () -> sellerService.register(registrationSellerDtoRequest));
    }


}
