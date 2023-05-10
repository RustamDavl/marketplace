package com.rustdv.marketplace.integration.repository;


import com.rustdv.marketplace.entity.Seller;
import com.rustdv.marketplace.entity.Shop;
import com.rustdv.marketplace.entity.embeddable.GoodsCategory;
import com.rustdv.marketplace.entity.embeddable.OwnershipForm;
import com.rustdv.marketplace.integration.IntegrationTestBase;
import com.rustdv.marketplace.repository.SellerRepository;
import com.rustdv.marketplace.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class ShopRepositoryIT extends IntegrationTestBase {

    private final ShopRepository shopRepository;
    private final SellerRepository sellerRepository;
    private Seller seller;
    private Shop shop;

    @BeforeEach
    void setUp() {
        seller = Seller.builder()
                .email("test@gmail.com")
                .password("strong")
                .phoneNumber("89179209061")
                .ownershipForm(OwnershipForm.IP)
                .build();
        shop = Shop.builder()
                .name("my shop")
                .goodsCategory(GoodsCategory.MUSIC)
                .build();
    }

    @Test
    void save() {
        sellerRepository.save(seller);
        var actualSeller = sellerRepository.findById(seller.getId());
        actualSeller.ifPresent(
                seller1 -> {
                    seller1.addShop(shop);
                    sellerRepository.flush();
                }
        );

        var actualShop = shopRepository.findById(shop.getId());
        assertThat(actualShop).isPresent();
        assertThat(actualShop.get().getId()).isNotNull();
        assertThat(actualShop.get().getName()).isEqualTo("my shop");
        assertThat(actualShop.get().getGoodsCategory()).isEqualTo(GoodsCategory.MUSIC);
    }

}
