package com.rustdv.marketplace.facade;

import com.rustdv.marketplace.dto.auth.SellerLoginDto;
import com.rustdv.marketplace.dto.auth.SellerRegistrationDto;
import com.rustdv.marketplace.dto.read.ReadSellerDto;
import com.rustdv.marketplace.mapper.*;
import com.rustdv.marketplace.service.SellerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SellerAuthFacadeImpl implements SellerAuthFacade {

    private final SellerServiceImpl sellerService;


    private final SellerRegistrationDtoMapper sellerRegistrationDtoMapper;

    private final ReadSellerDtoMapper readSellerDtoMapper;


    @Override
    public ReadSellerDto signUp(SellerRegistrationDto sellerRegistrationDto) {

        return readSellerDtoMapper.map(sellerService.register(sellerRegistrationDtoMapper.map(sellerRegistrationDto)));
    }

    @Override
    public ReadSellerDto signIn(SellerLoginDto sellerLoginDto) {

        return readSellerDtoMapper
                .map(sellerService.findByEmailAndPassword(sellerLoginDto.getEmail(), sellerLoginDto.getPassword()));
    }


}
