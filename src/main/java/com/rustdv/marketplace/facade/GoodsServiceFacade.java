package com.rustdv.marketplace.facade;

public interface GoodsServiceFacade <R, C> {

    R addGoods(Long shopId, C object);

}
