package com.rustdv.marketplace.dto.auth;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BuyerLoginDto {

    String email;
    String password;
}
