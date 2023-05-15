package com.rustdv.marketplace.service;

import com.rustdv.marketplace.dto.createupdate.CreateUpdateGoodsDto;
import com.rustdv.marketplace.dto.createupdate.CreateUpdateShopDto;
import com.rustdv.marketplace.dto.read.ReadGoodsDto;
import com.rustdv.marketplace.dto.read.ReadShopDto;
import com.rustdv.marketplace.exception.NoSuchElementException;
import com.rustdv.marketplace.exception.NotUniqueNameException;
import com.rustdv.marketplace.mapper.CreateUpdateGoodsDtoMapper;
import com.rustdv.marketplace.mapper.CreateUpdateShopDtoMapper;
import com.rustdv.marketplace.mapper.ReadGoodsDtoMapper;
import com.rustdv.marketplace.mapper.ReadShopDtoMapper;
import com.rustdv.marketplace.repository.SellerRepository;
import com.rustdv.marketplace.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    private final SellerRepository sellerRepository;

    private final CreateUpdateShopDtoMapper createUpdateShopDtoMapper;

    private final ReadShopDtoMapper readShopDtoMapper;

    private final CreateUpdateGoodsDtoMapper createUpdateGoodsDtoMapper;
    private final ReadGoodsDtoMapper readGoodsDtoMapper;

    @Transactional
    public ReadShopDto create(Long sellerId, CreateUpdateShopDto createUpdateShopDto) {

        var maybeShop = shopRepository.findByName(createUpdateShopDto.getName());
        maybeShop.ifPresent(
                shop -> {
                    throw new NotUniqueNameException("shop with such name already exists");
                }
        );
        var shopToSave = createUpdateShopDtoMapper.map(createUpdateShopDto);

        var seller = sellerRepository.findById(sellerId);
        seller.ifPresent(
                seller1 -> {
                    seller1.addShop(shopToSave);
                    sellerRepository.flush();
                }
        );


        return readShopDtoMapper.map(shopToSave);
    }

    @Transactional
    public ReadGoodsDto addGoods(Long shopId, CreateUpdateGoodsDto createUpdateGoodsDto) {

        var shop = shopRepository.findById(shopId).orElseThrow(() ->
        {
            throw new NoSuchElementException("there is no such shop by this id");
        });

        var goodsToSave = createUpdateGoodsDtoMapper.map(createUpdateGoodsDto);

        shop.addGoods(goodsToSave);
        shopRepository.flush();

        return readGoodsDtoMapper.map(goodsToSave);

    }
}
