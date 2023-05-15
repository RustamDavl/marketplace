package com.rustdv.marketplace.dto.read;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReadCartDto {

    Long id;
    Long amount;
    ReadBuyerDto readBuyerDto;
    ReadGoodsDto readGoodsDto;


}
