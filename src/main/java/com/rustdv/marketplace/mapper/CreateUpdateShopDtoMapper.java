package com.rustdv.marketplace.mapper;

import com.rustdv.marketplace.dto.createupdate.CreateUpdateShopDto;
import com.rustdv.marketplace.entity.Shop;
import com.rustdv.marketplace.entity.embeddable.GoodsCategory;
import org.springframework.stereotype.Component;

@Component
public class CreateUpdateShopDtoMapper implements Mapper<CreateUpdateShopDto, Shop>{
    @Override
    public Shop map(CreateUpdateShopDto from) {

        return Shop.builder()
                .name(from.getName())
                .goodsCategory(GoodsCategory.goodsCategory(from.getGoodsCategory()))
                .build();
    }
}
