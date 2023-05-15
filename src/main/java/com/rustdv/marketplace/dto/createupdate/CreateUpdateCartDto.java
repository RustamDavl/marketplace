package com.rustdv.marketplace.dto.createupdate;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Builder
@Value
public class CreateUpdateCartDto {

    @NotBlank
    String buyerId;
    @NotBlank
    String goodsId;
    @NotBlank
    String amount;
}
