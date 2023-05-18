package com.rustdv.marketplace.service;

import com.rustdv.marketplace.entity.Cart;
import com.rustdv.marketplace.entity.embeddable.PurchaseStatus;
import com.rustdv.marketplace.exception.NoSuchElementException;
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
public class CartServiceImpl implements CartService {


    private final CartRepository cartRepository;

    private final BuyerRepository buyerRepository;

    private final GoodsRepository goodsRepository;


    @Override
    public List<Cart> findAllByBuyerId(Long id) {

        return cartRepository.findAllByBuyerId(id)
                .stream()
                .filter(
                        cart -> cart.getPurchaseStatus() == PurchaseStatus.ADDED_TO_CART
                )
                .toList();
    }


    @Transactional
    @Override
    public Cart addGoodsToCart(Cart cart) {
        var buyer = buyerRepository.findById(cart.getBuyer().getId()).orElseThrow(() -> {
            throw new NoSuchElementException("there is no such user with this id");
        });


        var goods = goodsRepository.findById(cart.getGoods().getId()).orElseThrow(() -> {
                    throw new NoSuchElementException("there is no such goods with this id");
                }

        );

        cart.addGoods(goods);
        cart.addBuyer(buyer);
        cartRepository.saveAndFlush(cart);
        goods.setAmount((int) (goods.getAmount() - cart.getAmount()));
        goodsRepository.flush();

        return cart;


    }

    @Transactional
    @Override
    public Cart buyGoods(Cart cart) {

        var buyerId = cart.getBuyer().getId();
        var goodsId = cart.getGoods().getId();
        var maybeCart = cartRepository.findByBuyerIdAndGoodsId(buyerId, goodsId);

        maybeCart.ifPresentOrElse(
                cart1 -> {
                    cart1.setPurchaseStatus(PurchaseStatus.BOUGHT);
                    cartRepository.flush();
                },
                () -> {
                    throw new NoSuchElementException("there is no cart with such buyerId and goodsId");
                }
        );

        return maybeCart.get();


    }
}
