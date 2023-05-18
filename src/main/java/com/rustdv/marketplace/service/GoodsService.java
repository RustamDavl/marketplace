package com.rustdv.marketplace.service;

import com.rustdv.marketplace.entity.Goods;


public interface GoodsService {

    Goods addGoods(Long shopId, Goods goods);
}
