package com.rustdv.marketplace.service;

import com.rustdv.marketplace.entity.Shop;

public interface ShopService {

    Shop create (Long sellerId, Shop shop);


}
