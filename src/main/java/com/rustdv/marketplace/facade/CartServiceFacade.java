package com.rustdv.marketplace.facade;

import com.rustdv.marketplace.dto.createupdate.CreateUpdateCartDto;
import com.rustdv.marketplace.dto.read.ReadCartDto;
import com.rustdv.marketplace.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartServiceFacade {

    private final CartService cartService;

    public ReadCartDto addGoods(CreateUpdateCartDto createUpdateCartDto) {
        return cartService.addGoodsToCart(createUpdateCartDto);
    }

    public ReadCartDto buyGoods(CreateUpdateCartDto createUpdateCartDto) {

        return cartService.buyGoods(createUpdateCartDto);
    }

    public List<ReadCartDto> findAll(Long buyerId) {

        return cartService.findAllByBuyerId(buyerId);
    }
}
