package com.rustdv.marketplace.integration.repository;


import com.rustdv.marketplace.entity.Seller;
import com.rustdv.marketplace.entity.embeddable.Address;
import com.rustdv.marketplace.entity.embeddable.OwnershipForm;
import com.rustdv.marketplace.integration.IntegrationTestBase;
import com.rustdv.marketplace.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class SellerRepositoryIT extends IntegrationTestBase {

    private final SellerRepository sellerRepository;

    @Test
    void save() {

        Seller seller = Seller.builder()
                .email("test@gmail.com")
                .password("strong")
                .phoneNumber("89179209061")
                .ownershipForm(OwnershipForm.IP)
                .build();
        sellerRepository.save(seller);
        var actualResult = sellerRepository.findById(seller.getId());

        assertThat(actualResult).isPresent();
        assertThat(actualResult.get().getEmail()).isEqualTo(seller.getEmail());
        assertThat(actualResult.get().getPassword()).isEqualTo(seller.getPassword());
        assertThat(actualResult.get().getPhoneNumber()).isEqualTo(seller.getPhoneNumber());
        assertThat(actualResult.get().getOwnershipForm().name()).isEqualTo(seller.getOwnershipForm().name());

    }
}
