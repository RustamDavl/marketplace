package com.rustdv.marketplace.dto.createupdate;

import com.rustdv.marketplace.dto.read.ReadGoodsDto;
import com.rustdv.marketplace.dto.read.ReadSellerDto;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
@Value
public class CreateUpdateShopDto {

    @NotBlank
    String name;
    @NotBlank
    String goodsCategory;

}
