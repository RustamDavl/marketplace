package com.rustdv.marketplace.dto.read;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReadGoodsDto {

    Long id;
    String name;
    String price;
    String amount;
    ReadShopDto readShopDto;
}
