package com.rustdv.marketplace.integration.repository;

import com.rustdv.marketplace.entity.Goods;
import com.rustdv.marketplace.entity.Seller;
import com.rustdv.marketplace.entity.Shop;
import com.rustdv.marketplace.entity.embeddable.Address;
import com.rustdv.marketplace.entity.embeddable.GoodsCategory;
import com.rustdv.marketplace.entity.embeddable.OwnershipForm;
import com.rustdv.marketplace.integration.IntegrationTestBase;
import com.rustdv.marketplace.repository.GoodsRepository;
import com.rustdv.marketplace.repository.SellerRepository;
import com.rustdv.marketplace.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@Sql(
        value = "classpath:sql/data.sql",
        config = @SqlConfig(encoding = "utf-8")
)
public class GoodsRepositoryIT extends IntegrationTestBase {

    private final GoodsRepository goodsRepository;
    private final ShopRepository shopRepository;

    private List<Goods> goods;

    private Shop shop;

    private Seller seller;

    @BeforeEach
    void setUp() {

        goods = new ArrayList<>();
        goods.add(Goods.builder()
                .name("Very good item")
                .amount(10)
                .price(BigDecimal.valueOf(1456))
                .build());

        goods.add(Goods.builder()
                .name("sport item")
                .amount(5)
                .price(BigDecimal.valueOf(2000))
                .build());

    }

    @Test
    void save() {
        var actualShop = shopRepository.findById(1L);
        actualShop.ifPresent(
                shop1 -> {
                    goods.forEach(
                            shop1::addGoods
                    );
                    shopRepository.flush();
                }
        );

        var actualGoods = goodsRepository.findAll();
        assertThat(actualGoods).hasSize(2);
        assertThat(actualGoods.get(0).getId()).isNotNull();
        assertThat(actualGoods.get(1).getId()).isNotNull();

    }
}
