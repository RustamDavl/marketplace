package com.rustdv.marketplace.mapper;

import com.rustdv.marketplace.dto.read.ReadShopDto;
import com.rustdv.marketplace.entity.Shop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReadShopDtoMapper implements Mapper<Shop, ReadShopDto> {

    private final ReadSellerDtoMapper readSellerDtoMapper;

    @Override
    public ReadShopDto map(Shop from) {
        return ReadShopDto.builder()
                .id(from.getId())
                .goodsCategory(from.getGoodsCategory().toString())
                .readSellerDto(readSellerDtoMapper.map(from.getSeller()))
                .name(from.getName())
                .build();
    }
}
