package com.rustdv.marketplace.dto.createupdate;

import com.rustdv.marketplace.dto.read.ReadGoodsDto;
import com.rustdv.marketplace.dto.read.ReadSellerDto;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class CreateUpdateShopDto {

    String name;
    String goodsCategory;

}
