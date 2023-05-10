package com.rustdv.marketplace.dto.auth;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SellerRegistrationDto {

    String email;
    String password;
    String phoneNumber;
    String ownershipForm;

}
