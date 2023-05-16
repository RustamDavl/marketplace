package com.rustdv.marketplace.mapper;

import com.rustdv.marketplace.dto.createupdate.CreateUpdateCartDto;
import com.rustdv.marketplace.entity.Buyer;
import com.rustdv.marketplace.entity.Cart;
import com.rustdv.marketplace.entity.Goods;
import com.rustdv.marketplace.entity.embeddable.PurchaseStatus;
import org.springframework.stereotype.Component;

@Component
public class CreateUpdateCartDtoMapper implements Mapper<CreateUpdateCartDto, Cart> {
    @Override
    public Cart map(CreateUpdateCartDto from) {

        return Cart.builder()
                .buyer(
                        Buyer.builder()
                                .id(Long.parseLong(from.getBuyerId()))
                                .build()
                )
                .goods(
                        Goods.builder()
                                .id(Long.parseLong(from.getGoodsId()))
                                .build()
                )

                .amount(Long.parseLong(from.getAmount()))
                .purchaseStatus(PurchaseStatus.ADDED_TO_CART)
                .build();

    }
}
