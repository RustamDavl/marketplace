package com.rustdv.marketplace.dto.createupdate;

import lombok.*;

@Builder
@Value
public class CreateUpdateGoodsDto {
    String name;
    String goodsCategory;
    String price;
    String amount;
}
