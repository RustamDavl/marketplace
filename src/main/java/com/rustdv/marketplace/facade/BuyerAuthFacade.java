package com.rustdv.marketplace.facade;

import com.rustdv.marketplace.dto.auth.BuyerLoginDto;
import com.rustdv.marketplace.dto.auth.BuyerRegistrationDto;
import com.rustdv.marketplace.dto.read.ReadBuyerDto;

public interface BuyerAuthFacade {

    ReadBuyerDto signUp(BuyerRegistrationDto buyerRegistrationDto);

    ReadBuyerDto signIn(BuyerLoginDto buyerLoginDto);
}
