package com.rustdv.marketplace.service;

import com.rustdv.marketplace.dto.createupdate.CreateUpdateCartDto;
import com.rustdv.marketplace.dto.read.ReadCartDto;
import com.rustdv.marketplace.entity.embeddable.PurchaseStatus;
import com.rustdv.marketplace.exception.NoSuchElementException;
import com.rustdv.marketplace.mapper.CreateUpdateCartDtoMapper;
import com.rustdv.marketplace.mapper.ReadBuyerDtoMapper;
import com.rustdv.marketplace.mapper.ReadCartDtoMapper;
import com.rustdv.marketplace.mapper.ReadGoodsDtoMapper;
import com.rustdv.marketplace.repository.BuyerRepository;
import com.rustdv.marketplace.repository.CartRepository;
import com.rustdv.marketplace.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {


    private final CartRepository cartRepository;

    private final BuyerRepository buyerRepository;

    private final GoodsRepository goodsRepository;

    private final CreateUpdateCartDtoMapper createUpdateCartDtoMapper;

    private final ReadGoodsDtoMapper readGoodsDtoMapper;

    private final ReadBuyerDtoMapper readBuyerDtoMapper;

    private final ReadCartDtoMapper readCartDtoMapper;

    public List<ReadCartDto> findAllByBuyerId(Long id) {

        return cartRepository.findAllByBuyerId(id)
                .stream()
                .filter(
                        cart -> cart.getPurchaseStatus() == PurchaseStatus.ADDED_TO_CART
                )
                .map(readCartDtoMapper::map)
                .toList();
    }


    @Transactional
    public ReadCartDto addGoodsToCart(CreateUpdateCartDto createUpdateCartDto) {
        var buyer = buyerRepository.findById(Long.parseLong(createUpdateCartDto.getBuyerId())).orElseThrow(() -> {
            throw new NoSuchElementException("there is no such user with this id");
        });


        var goods = goodsRepository.findById(Long.parseLong(createUpdateCartDto.getGoodsId())).orElseThrow(() -> {
                    throw new NoSuchElementException("there is no such goods with this id");
                }

        );


        var cartToSave = createUpdateCartDtoMapper.map(createUpdateCartDto);
        cartToSave.addGoods(goods);
        cartToSave.addBuyer(buyer);
        cartRepository.saveAndFlush(cartToSave);
        goods.setAmount(goods.getAmount() - Integer.parseInt(createUpdateCartDto.getAmount()));
        goodsRepository.flush();


        return ReadCartDto.builder()
                .id(cartToSave.getId())
                .amount(cartToSave.getAmount())
                .readBuyerDto(readBuyerDtoMapper.map(buyer))
                .readGoodsDto(readGoodsDtoMapper.map(goods))
                .build();


    }

    @Transactional
    public ReadCartDto buyGoods(CreateUpdateCartDto createUpdateCartDto) {

        var buyerId = Long.parseLong(createUpdateCartDto.getBuyerId());
        var goodsId = Long.parseLong(createUpdateCartDto.getGoodsId());
        var maybeCart = cartRepository.findByBuyerIdAndGoodsId(buyerId, goodsId);

        return maybeCart.map(
                cart -> {
                    cart.setPurchaseStatus(PurchaseStatus.BOUGHT);
                    cartRepository.flush();
                    return ReadCartDto.builder()
                            .id(cart.getId())
                            .amount(cart.getAmount())
                            .readGoodsDto(readGoodsDtoMapper.map(cart.getGoods()))
                            .readBuyerDto(readBuyerDtoMapper.map(cart.getBuyer()))
                            .build();
                }
        ).orElseThrow(() -> {
            throw new NoSuchElementException("there is no cart with such buyerId and goodsId");
        });


    }
}
