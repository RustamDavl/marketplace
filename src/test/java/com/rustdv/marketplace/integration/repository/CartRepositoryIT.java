package com.rustdv.marketplace.integration.repository;

import com.rustdv.marketplace.entity.Buyer;
import com.rustdv.marketplace.entity.Cart;
import com.rustdv.marketplace.entity.Goods;
import com.rustdv.marketplace.entity.Seller;
import com.rustdv.marketplace.entity.embeddable.Address;
import com.rustdv.marketplace.entity.embeddable.Gender;
import com.rustdv.marketplace.entity.embeddable.GoodsCategory;
import com.rustdv.marketplace.entity.embeddable.OrganizationType;
import com.rustdv.marketplace.integration.IntegrationTestBase;
import com.rustdv.marketplace.repository.BuyerRepository;
import com.rustdv.marketplace.repository.CartRepository;
import com.rustdv.marketplace.repository.GoodsRepository;
import com.rustdv.marketplace.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class CartRepositoryIT extends IntegrationTestBase {

    private final CartRepository cartRepository;

    private final GoodsRepository goodsRepository;
    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;

    private Goods goods;

    private Seller seller;

    private Buyer buyer;


    @BeforeEach
    void setUp() {
        goods = Goods.builder()
                .name("Very good item")
                .amount(10)
                .goodsCategory(GoodsCategory.ADULT)
                .price(BigDecimal.valueOf(1456))
                .build();

        Address sellerAddress = Address.builder()
                .city("Leninogorsk")
                .street("Krupskoy")
                .houseNumber("5")
                .build();

        seller = Seller.builder()
                .email("seller@gmail.com")
                .password("seller")
                .phoneNumber("89174509090")
                .address(sellerAddress)
                .inn("123456789012")
                .magazineName("CoolName")
                .organizationType(OrganizationType.IP)
                .build();

        var buyerAddress = Address.builder()
                .city("Kazan")
                .street("Test street")
                .houseNumber("16k1")
                .build();

        buyer = Buyer.builder()
                .email("easton@gmail.com")
                .password("strong")
                .phoneNumber("89179209061")
                .address(buyerAddress)
                .gender(Gender.MALE)
                .birthDate(LocalDate.of(2001, 1, 29))
                .build();

        seller.addGoods(goods);
        buyerRepository.save(buyer);
        sellerRepository.save(seller);


    }


    @Test
    void save() {

        Cart cart = Cart.builder()
                .amount(10L)
                .build();

        cart.addBuyer(buyer);
        cart.addGoods(goods);

        cartRepository.save(cart);

        var actualCart = cartRepository.findById(cart.getId());

        assertThat(actualCart).isPresent();
        assertThat(actualCart.get().getBuyer()).isEqualTo(buyer);
        assertThat(actualCart.get().getGoods()).isEqualTo(goods);
        assertThat(actualCart.get().getAmount()).isEqualTo(10L);









    }
}
