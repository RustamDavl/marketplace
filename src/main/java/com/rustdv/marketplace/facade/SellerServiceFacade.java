package com.rustdv.marketplace.facade;

import com.rustdv.marketplace.dto.auth.SellerLoginDto;
import com.rustdv.marketplace.dto.auth.SellerRegistrationDto;
import com.rustdv.marketplace.dto.createupdate.CreateUpdateGoodsDto;
import com.rustdv.marketplace.dto.createupdate.CreateUpdateShopDto;
import com.rustdv.marketplace.dto.read.ReadGoodsDto;
import com.rustdv.marketplace.dto.read.ReadSellerDto;
import com.rustdv.marketplace.dto.read.ReadShopDto;
import com.rustdv.marketplace.service.SellerService;
import com.rustdv.marketplace.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SellerServiceFacade {

    private final SellerService sellerService;

    private final ShopService shopService;

    public ReadSellerDto signUp(SellerRegistrationDto sellerRegistrationDto) {

        return sellerService.register(sellerRegistrationDto);
    }

    public ReadSellerDto signIn(SellerLoginDto sellerLoginDto) {

        return sellerService.findByEmailAndPassword(sellerLoginDto.getEmail(), sellerLoginDto.getPassword());
    }

    public ReadShopDto createShop(Long id, CreateUpdateShopDto createUpdateShopDto) {

        return shopService.create(id, createUpdateShopDto);

    }

    public ReadGoodsDto addGoods(Long shopId, CreateUpdateGoodsDto createUpdateGoodsDto) {

        return shopService.addGoods(shopId, createUpdateGoodsDto);
    }
}
