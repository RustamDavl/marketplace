package com.rustdv.marketplace.dto.read;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReadGoodsDto {

    Long id;
    String name;
    String goodsCategory;
    String price;
    String amount;
    ReadSellerDto readSellerDto;
}
