package com.rustdv.marketplace.service;

import com.rustdv.marketplace.entity.Shop;
import com.rustdv.marketplace.exception.NotUniqueNameException;
import com.rustdv.marketplace.repository.SellerRepository;
import com.rustdv.marketplace.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    private final SellerRepository sellerRepository;


    @Transactional
    @Override
    public Shop create(Long sellerId, Shop shop) {

        var maybeShop = shopRepository.findByName(shop.getName());
        maybeShop.ifPresent(
                shop1 -> {
                    throw new NotUniqueNameException("shop with such name already exists");
                }
        );

        var seller = sellerRepository.findById(sellerId);
        seller.ifPresent(
                seller1 -> {
                    seller1.addShop(shop);
                    sellerRepository.flush();
                }
        );


        return shop;
    }

}
