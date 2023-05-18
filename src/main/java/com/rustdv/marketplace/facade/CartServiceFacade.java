package com.rustdv.marketplace.facade;



import java.util.List;

public interface CartServiceFacade<R, C> {

    R addGoods(C object);

    R buyGoods(C object);

    List<R> findAllByBuyerId(Long buyerId);
}
