package com.rustdv.marketplace.facade;

import com.rustdv.marketplace.dto.auth.SellerLoginDto;
import com.rustdv.marketplace.dto.auth.SellerRegistrationDto;
import com.rustdv.marketplace.dto.createupdate.CreateUpdateGoodsDto;
import com.rustdv.marketplace.dto.createupdate.CreateUpdateShopDto;
import com.rustdv.marketplace.dto.read.ReadGoodsDto;
import com.rustdv.marketplace.dto.read.ReadSellerDto;
import com.rustdv.marketplace.dto.read.ReadShopDto;
import com.rustdv.marketplace.mapper.*;
import com.rustdv.marketplace.service.SellerService;
import com.rustdv.marketplace.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SellerServiceFacade {

    private final SellerService sellerService;

    private final ShopService shopService;

    private final SellerRegistrationDtoMapper sellerRegistrationDtoMapper;

    private final ReadSellerDtoMapper readSellerDtoMapper;

    private final ReadGoodsDtoMapper readGoodsDtoMapper;

    private final ReadShopDtoMapper readShopDtoMapper;

    private final CreateUpdateShopDtoMapper createUpdateShopDtoMapper;

    private final CreateUpdateGoodsDtoMapper createUpdateGoodsDtoMapper;


    public ReadSellerDto signUp(SellerRegistrationDto sellerRegistrationDto) {

        return readSellerDtoMapper.map(sellerService.register(sellerRegistrationDtoMapper.map(sellerRegistrationDto)));
    }

    public ReadSellerDto signIn(SellerLoginDto sellerLoginDto) {

        return readSellerDtoMapper
                .map(sellerService.findByEmailAndPassword(sellerLoginDto.getEmail(), sellerLoginDto.getPassword()));
    }

    public ReadShopDto createShop(Long id, CreateUpdateShopDto createUpdateShopDto) {

        return readShopDtoMapper.map(shopService.create(id, createUpdateShopDtoMapper.map(createUpdateShopDto)));

    }

    public ReadGoodsDto addGoods(Long shopId, CreateUpdateGoodsDto createUpdateGoodsDto) {

        return readGoodsDtoMapper.map(shopService.addGoods(shopId, createUpdateGoodsDtoMapper.map(createUpdateGoodsDto)));
    }
}
