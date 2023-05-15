package com.rustdv.marketplace.dto.auth;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@Builder
public class SellerLoginDto {

    @Email
    String email;

    @NotBlank
    @Size(min = 8)
    String password;
}
