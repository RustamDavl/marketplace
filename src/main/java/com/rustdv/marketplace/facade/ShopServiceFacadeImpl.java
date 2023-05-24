package com.rustdv.marketplace.facade;

import com.rustdv.marketplace.dto.createupdate.CreateUpdateShopDto;
import com.rustdv.marketplace.dto.read.ReadShopDto;
import com.rustdv.marketplace.mapper.CreateUpdateShopDtoMapper;
import com.rustdv.marketplace.mapper.ReadShopDtoMapper;
import com.rustdv.marketplace.service.ShopServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShopServiceFacadeImpl implements ShopServiceFacade {

    private final ReadShopDtoMapper readShopDtoMapper;

    private final CreateUpdateShopDtoMapper createUpdateShopDtoMapper;

    private final ShopServiceImpl shopServiceImpl;

    @Override
    public ReadShopDto createShop(Long id, CreateUpdateShopDto createUpdateShopDto) {

        return readShopDtoMapper.map(shopServiceImpl.create(id, createUpdateShopDtoMapper.map(createUpdateShopDto)));

    }
}
