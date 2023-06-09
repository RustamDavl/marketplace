package com.rustdv.marketplace.dto.createupdate;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Value
public class CreateUpdateGoodsDto {

    @NotBlank
    String name;
    @NotBlank
    String price;
    @NotBlank
    String amount;
}
