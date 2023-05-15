package com.rustdv.marketplace.dto.auth;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@Builder
public class SellerRegistrationDto {

    @Email
    String email;

    @NotBlank
    @Size(min = 8)
    String password;

    @NotBlank
    @Size(min = 11, max = 11)
    String phoneNumber;

    @NotBlank
    String ownershipForm;

}
