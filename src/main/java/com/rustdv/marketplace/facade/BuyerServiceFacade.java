package com.rustdv.marketplace.facade;

import com.rustdv.marketplace.dto.auth.BuyerLoginDto;
import com.rustdv.marketplace.dto.auth.BuyerRegistrationDto;
import com.rustdv.marketplace.dto.read.ReadBuyerDto;
import com.rustdv.marketplace.service.BuyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuyerServiceFacade {
    private final BuyerService buyerService;

    public ReadBuyerDto signUp(BuyerRegistrationDto object) {

        return buyerService.register(object);
    }


    public ReadBuyerDto signIn(BuyerLoginDto buyerLoginDto) {

        return buyerService.findByEmailAndPassword(
                buyerLoginDto.getEmail(),
                buyerLoginDto.getPassword()
        );
    }
}
