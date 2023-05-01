package com.rustdv.marketplace.integration.repository;

import com.rustdv.marketplace.entity.Goods;
import com.rustdv.marketplace.entity.Seller;
import com.rustdv.marketplace.entity.embeddable.Address;
import com.rustdv.marketplace.entity.embeddable.GoodsCategory;
import com.rustdv.marketplace.entity.embeddable.OrganizationType;
import com.rustdv.marketplace.integration.IntegrationTestBase;
import com.rustdv.marketplace.repository.GoodsRepository;
import com.rustdv.marketplace.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class GoodsRepositoryIT extends IntegrationTestBase {

    private final GoodsRepository goodsRepository;
    private final SellerRepository sellerRepository;

    private Goods goods;

    private Seller seller;

    @BeforeEach
    void setUp() {
        goods = Goods.builder()
                .name("Very good item")
                .amount(10)
                .goodsCategory(GoodsCategory.ADULT)
                .price(BigDecimal.valueOf(1456))
                .build();

        Address address = Address.builder()
                .city("Kazan")
                .street("Test street")
                .houseNumber("16k1")
                .build();

        seller = Seller.builder()
                .email("test@gmail.com")
                .password("strong")
                .phoneNumber("89179209061")
                .address(address)
                .inn("123456789012")
                .magazineName("CoolName")
                .organizationType(OrganizationType.IP)
                .build();

    }

    @Test
    void save() {

        seller.addGoods(goods);

        sellerRepository.save(seller);

        var actualSeller = sellerRepository.findById(seller.getId());

        var actualGoods = goodsRepository.findById(goods.getId());
        assertThat(actualGoods).isPresent();
        assertThat(actualGoods.get()).isEqualTo(goods);
        assertThat(actualSeller).isPresent();
        assertThat(actualSeller.get().getEmail()).isEqualTo(seller.getEmail());
        assertThat(actualSeller.get().getPassword()).isEqualTo(seller.getPassword());
        assertThat(actualSeller.get().getPhoneNumber()).isEqualTo(seller.getPhoneNumber());
        assertThat(actualSeller.get().getOrganizationType().name()).isEqualTo(seller.getOrganizationType().name());
        assertThat(actualSeller.get().getGoods().get(0)).isEqualTo(goods);




    }
}
