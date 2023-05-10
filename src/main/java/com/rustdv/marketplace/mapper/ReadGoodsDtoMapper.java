package com.rustdv.marketplace.mapper;

import com.rustdv.marketplace.dto.read.ReadGoodsDto;
import com.rustdv.marketplace.entity.Goods;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReadGoodsDtoMapper implements Mapper<Goods, ReadGoodsDto>{
    private final ReadShopDtoMapper readShopDtoMapper;

    @Override
    public ReadGoodsDto map(Goods from) {
        return ReadGoodsDto.builder()
                .id(from.getId())
                .name(from.getName())
                .amount(String.valueOf(from.getAmount()))
                .price(from.getPrice().toString())
                .readShopDto(readShopDtoMapper.map(from.getShop()))
                .build();
    }
}
