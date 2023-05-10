package com.rustdv.marketplace.dto.read;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class ReadShopDto {

    Long id;
    String name;
    String goodsCategory;
    ReadSellerDto readSellerDto;
}
