package com.rustdv.marketplace.facade;

import com.rustdv.marketplace.dto.createupdate.CreateUpdateCartDto;
import com.rustdv.marketplace.dto.read.ReadCartDto;
import com.rustdv.marketplace.mapper.CreateUpdateCartDtoMapper;
import com.rustdv.marketplace.mapper.ReadBuyerDtoMapper;
import com.rustdv.marketplace.mapper.ReadCartDtoMapper;
import com.rustdv.marketplace.mapper.ReadGoodsDtoMapper;
import com.rustdv.marketplace.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartServiceFacade {

    private final CartService cartService;

    private final CreateUpdateCartDtoMapper createUpdateCartDtoMapper;


    private final ReadCartDtoMapper readCartDtoMapper;

    public ReadCartDto addGoods(CreateUpdateCartDto createUpdateCartDto) {
        return readCartDtoMapper.map(cartService.addGoodsToCart(createUpdateCartDtoMapper.map(createUpdateCartDto)));
    }

    public ReadCartDto buyGoods(CreateUpdateCartDto createUpdateCartDto) {

        return readCartDtoMapper.map(cartService.buyGoods(createUpdateCartDtoMapper.map(createUpdateCartDto)));
    }

    public List<ReadCartDto> findAll(Long buyerId) {

        return cartService.findAllByBuyerId(buyerId)
                .stream()
                .map(readCartDtoMapper::map)
                .toList();
    }
}
