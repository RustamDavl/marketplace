package com.rustdv.marketplace.facade;


public interface ShopServiceFacade<R, C> {

    R createShop(Long sellerId, C createUpdateShopDto);
}
