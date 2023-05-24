package com.rustdv.marketplace.facade;


import com.rustdv.marketplace.dto.createupdate.CreateUpdateShopDto;
import com.rustdv.marketplace.dto.read.ReadShopDto;

public interface ShopServiceFacade {

    ReadShopDto createShop(Long sellerId, CreateUpdateShopDto createUpdateShopDto);
}
