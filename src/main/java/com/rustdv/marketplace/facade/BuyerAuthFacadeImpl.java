package com.rustdv.marketplace.facade;

import com.rustdv.marketplace.dto.auth.BuyerLoginDto;
import com.rustdv.marketplace.dto.auth.BuyerRegistrationDto;
import com.rustdv.marketplace.dto.read.ReadBuyerDto;
import com.rustdv.marketplace.entity.Buyer;
import com.rustdv.marketplace.mapper.BuyerRegistrationDtoMapper;
import com.rustdv.marketplace.mapper.ReadBuyerDtoMapper;
import com.rustdv.marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuyerAuthFacadeImpl implements BuyerAuthFacade {
    private final UserService<Buyer> buyerServiceImpl;

    private final BuyerRegistrationDtoMapper buyerRegistrationDtoMapper;

    private final ReadBuyerDtoMapper readBuyerDtoMapper;


    @Override
    public ReadBuyerDto signUp(BuyerRegistrationDto object) {


        return readBuyerDtoMapper.map(buyerServiceImpl.register(buyerRegistrationDtoMapper.map(object)));
    }


    @Override
    public ReadBuyerDto signIn(BuyerLoginDto buyerLoginDto) {

        return readBuyerDtoMapper.map(buyerServiceImpl.findByEmailAndPassword(
                buyerLoginDto.getEmail(),
                buyerLoginDto.getPassword()
        ));
    }

}
