package com.rustdv.marketplace.integration.repository;

import com.rustdv.marketplace.entity.*;
import com.rustdv.marketplace.entity.embeddable.Address;
import com.rustdv.marketplace.entity.embeddable.Gender;
import com.rustdv.marketplace.entity.embeddable.GoodsCategory;
import com.rustdv.marketplace.entity.embeddable.OwnershipForm;
import com.rustdv.marketplace.integration.IntegrationTestBase;
import com.rustdv.marketplace.repository.*;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@Sql(
        value = "classpath:sql/data.sql",
        config = @SqlConfig(encoding = "utf-8")
)
public class CartRepositoryIT extends IntegrationTestBase {

    private final CartRepository cartRepository;
    private final BuyerRepository buyerRepository;

    private final ShopRepository shopRepository;

    private Goods goods;

    private Cart cart;

    private Shop shop;


    @BeforeEach
    void setUp() {


        goods = Goods.builder()
                .name("Very good item")
                .amount(10)
                .price(BigDecimal.valueOf(1456))
                .build();

        cart = Cart.builder()
                .amount(10L)
                .build();


    }


    @Test
    void save() {
        shop = shopRepository.findById(1L).get();
        shop.addGoods(goods);
        shopRepository.flush();
        var buyer = buyerRepository.findById(1L);

        cart.addBuyer(buyer.get());
        cart.addGoods(goods);

        cartRepository.saveAndFlush(cart);

        var actualCart = cartRepository.findById(cart.getId());

        assertThat(actualCart).isPresent();
        assertThat(actualCart.get().getId()).isNotNull();
        assertThat(actualCart.get().getBuyer().getId()).isNotNull();
        assertThat(actualCart.get().getGoods().getId()).isNotNull();
        assertThat(actualCart.get().getAmount()).isEqualTo(10L);
        assertThat(actualCart.get().getGoods()).isEqualTo(goods);
        assertThat(actualCart.get().getBuyer()).isEqualTo(buyer.get());


    }
}
