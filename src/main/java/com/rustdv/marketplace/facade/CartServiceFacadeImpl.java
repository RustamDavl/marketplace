package com.rustdv.marketplace.facade;

import com.rustdv.marketplace.dto.createupdate.CreateUpdateCartDto;
import com.rustdv.marketplace.dto.read.ReadCartDto;
import com.rustdv.marketplace.mapper.CreateUpdateCartDtoMapper;
import com.rustdv.marketplace.mapper.ReadCartDtoMapper;
import com.rustdv.marketplace.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartServiceFacadeImpl implements CartServiceFacade {

    private final CartService cartServiceImpl;

    private final CreateUpdateCartDtoMapper createUpdateCartDtoMapper;

    private final ReadCartDtoMapper readCartDtoMapper;


    @Override
    public ReadCartDto addGoods(CreateUpdateCartDto createUpdateCartDto) {
        return readCartDtoMapper.map(cartServiceImpl.addGoodsToCart(createUpdateCartDtoMapper.map(createUpdateCartDto)));
    }

    @Override
    public ReadCartDto buyGoods(CreateUpdateCartDto createUpdateCartDto) {

        return readCartDtoMapper.map(cartServiceImpl.buyGoods(createUpdateCartDtoMapper.map(createUpdateCartDto)));
    }

    @Override
    public List<ReadCartDto> findAllByBuyerId(Long buyerId) {

        return cartServiceImpl.findAllByBuyerId(buyerId)
                .stream()
                .map(readCartDtoMapper::map)
                .toList();
    }
}
