package com.rustdv.marketplace.dto.auth;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SellerLoginDto {

    String email;
    String password;
}
