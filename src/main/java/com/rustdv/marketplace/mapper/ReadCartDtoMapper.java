package com.rustdv.marketplace.mapper;


import com.rustdv.marketplace.dto.read.ReadCartDto;
import com.rustdv.marketplace.entity.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReadCartDtoMapper implements Mapper<Cart, ReadCartDto> {
    private final ReadBuyerDtoMapper readBuyerDtoMapper;

    private final ReadGoodsDtoMapper readGoodsDtoMapper;

    @Override
    public ReadCartDto map(Cart from) {
        return ReadCartDto.builder()
                .id(from.getId())
                .readBuyerDto(readBuyerDtoMapper.map(from.getBuyer()))
                .readGoodsDto(readGoodsDtoMapper.map(from.getGoods()))
                .amount(from.getAmount())
                .build();
    }
}
