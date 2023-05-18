package com.rustdv.marketplace.service;

import com.rustdv.marketplace.entity.Goods;
import com.rustdv.marketplace.exception.NoSuchElementException;
import com.rustdv.marketplace.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoodsServiceImpl implements GoodsService {

    private final ShopRepository shopRepository;


    @Transactional
    @Override
    public Goods addGoods(Long shopId, Goods goods) {

        var shop = shopRepository.findById(shopId).orElseThrow(() ->
        {
            throw new NoSuchElementException("there is no such shop by this id");
        });


        shop.addGoods(goods);
        shopRepository.flush();

        return goods;

    }

}
