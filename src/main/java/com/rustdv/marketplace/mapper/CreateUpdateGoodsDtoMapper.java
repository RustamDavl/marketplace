package com.rustdv.marketplace.mapper;

import com.rustdv.marketplace.dto.createupdate.CreateUpdateGoodsDto;
import com.rustdv.marketplace.entity.Goods;
import com.rustdv.marketplace.entity.embeddable.GoodsCategory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CreateUpdateGoodsDtoMapper implements Mapper<CreateUpdateGoodsDto, Goods> {
    @Override
    public Goods map(CreateUpdateGoodsDto from) {

        return Goods.builder()
                .name(from.getName())
                .amount(Integer.valueOf(from.getAmount()))
                .price(BigDecimal.valueOf(Long.parseLong(from.getPrice())))
                .build();

    }
}