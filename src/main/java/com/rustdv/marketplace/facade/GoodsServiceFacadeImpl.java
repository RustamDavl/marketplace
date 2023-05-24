package com.rustdv.marketplace.facade;

import com.rustdv.marketplace.dto.createupdate.CreateUpdateGoodsDto;
import com.rustdv.marketplace.dto.read.ReadGoodsDto;
import com.rustdv.marketplace.mapper.CreateUpdateGoodsDtoMapper;
import com.rustdv.marketplace.mapper.ReadGoodsDtoMapper;
import com.rustdv.marketplace.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoodsServiceFacadeImpl implements GoodsServiceFacade {

    private final ReadGoodsDtoMapper readGoodsDtoMapper;

    private final CreateUpdateGoodsDtoMapper createUpdateGoodsDtoMapper;

    private final GoodsService goodsServiceImpl;


    @Override
    public ReadGoodsDto addGoods(Long shopId, CreateUpdateGoodsDto createUpdateGoodsDto) {

        return readGoodsDtoMapper.map(goodsServiceImpl.addGoods(shopId, createUpdateGoodsDtoMapper.map(createUpdateGoodsDto)));
    }
}
