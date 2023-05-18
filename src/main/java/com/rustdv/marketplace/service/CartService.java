package com.rustdv.marketplace.service;

import com.rustdv.marketplace.entity.Cart;

import java.util.List;

public interface CartService {

    List<Cart> findAllByBuyerId(Long id);

    Cart addGoodsToCart(Cart cart);

    Cart buyGoods(Cart cart);


}
