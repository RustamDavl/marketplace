package com.rustdv.marketplace.dto.createupdate;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;


@Builder
@Value
public class CreateUpdateShopDto {

    @NotBlank
    String name;
    @NotBlank
    String goodsCategory;

}
