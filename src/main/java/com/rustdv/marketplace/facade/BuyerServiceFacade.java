package com.rustdv.marketplace.facade;

import com.rustdv.marketplace.dto.auth.BuyerLoginDto;
import com.rustdv.marketplace.dto.auth.BuyerRegistrationDto;
import com.rustdv.marketplace.dto.read.ReadBuyerDto;
import com.rustdv.marketplace.mapper.BuyerRegistrationDtoMapper;
import com.rustdv.marketplace.mapper.ReadBuyerDtoMapper;
import com.rustdv.marketplace.service.BuyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuyerServiceFacade {
    private final BuyerService buyerService;

    private final BuyerRegistrationDtoMapper buyerRegistrationDtoMapper;

    private final ReadBuyerDtoMapper readBuyerDtoMapper;


    public ReadBuyerDto signUp(BuyerRegistrationDto object) {


        return readBuyerDtoMapper.map(buyerService.register(buyerRegistrationDtoMapper.map(object)));
    }


    public ReadBuyerDto signIn(BuyerLoginDto buyerLoginDto) {

        return readBuyerDtoMapper.map(buyerService.findByEmailAndPassword(
                buyerLoginDto.getEmail(),
                buyerLoginDto.getPassword()
        ));
    }
}
