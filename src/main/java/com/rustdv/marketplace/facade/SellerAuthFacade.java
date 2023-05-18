package com.rustdv.marketplace.facade;

import com.rustdv.marketplace.dto.auth.SellerLoginDto;
import com.rustdv.marketplace.dto.auth.SellerRegistrationDto;
import com.rustdv.marketplace.dto.read.ReadSellerDto;

public interface SellerAuthFacade {

    ReadSellerDto signUp(SellerRegistrationDto sellerRegistrationDto);

    ReadSellerDto signIn(SellerLoginDto sellerLoginDto);
}
