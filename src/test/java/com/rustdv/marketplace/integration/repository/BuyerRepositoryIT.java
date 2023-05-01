package com.rustdv.marketplace.integration.repository;

import com.rustdv.marketplace.entity.Buyer;
import com.rustdv.marketplace.entity.embeddable.Address;
import com.rustdv.marketplace.entity.embeddable.Sex;
import com.rustdv.marketplace.integration.IntegrationTestBase;
import com.rustdv.marketplace.repository.BuyerRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class BuyerRepositoryIT extends IntegrationTestBase {

    private final BuyerRepository buyerRepository;

    @Test
    void save() {
        var address = Address.builder()
                .city("Kazan")
                .street("Test street")
                .houseNumber("16k1")
                .build();

        Buyer buyer = Buyer.builder()
                .email("test@gmail.com")
                .password("strong")
                .phoneNumber("89179209061")
                .address(address)
                .sex(Sex.MALE)
                .birthDate(LocalDate.of(2001, 1,29))
                .build();
        buyerRepository.save(buyer);
        var actualResult = buyerRepository.findById(buyer.getId());

        assertThat(actualResult).isPresent();
        assertThat(actualResult.get().getEmail()).isEqualTo(buyer.getEmail());
        assertThat(actualResult.get().getPassword()).isEqualTo(buyer.getPassword());
        assertThat(actualResult.get().getPhoneNumber()).isEqualTo(buyer.getPhoneNumber());
        assertThat(actualResult.get().getSex().name()).isEqualTo(buyer.getSex().name());


    }
}
