package com.rustdv.marketplace.service;

import com.rustdv.marketplace.dto.auth.BuyerRegistrationDto;
import com.rustdv.marketplace.dto.createupdate.CreateUpdateCartDto;
import com.rustdv.marketplace.dto.read.ReadBuyerDto;
import com.rustdv.marketplace.dto.read.ReadCartDto;
import com.rustdv.marketplace.dto.read.ReadGoodsDto;
import com.rustdv.marketplace.exception.NoSuchElementException;
import com.rustdv.marketplace.exception.NoUserWithSuchCredentialsException;
import com.rustdv.marketplace.exception.UserAlreadyExistsException;
import com.rustdv.marketplace.mapper.BuyerRegistrationDtoMapper;
import com.rustdv.marketplace.mapper.CreateUpdateCartDtoMapper;
import com.rustdv.marketplace.mapper.ReadBuyerDtoMapper;
import com.rustdv.marketplace.mapper.ReadGoodsDtoMapper;
import com.rustdv.marketplace.repository.BuyerRepository;
import com.rustdv.marketplace.repository.CartRepository;
import com.rustdv.marketplace.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BuyerService {

    private final BuyerRepository buyerRepository;

    private final BuyerRegistrationDtoMapper buyerRegistrationDtoMapper;

    private final ReadBuyerDtoMapper readBuyerDtoMapper;


    @Transactional
    public ReadBuyerDto register(BuyerRegistrationDto registrationDto) {

        var maybeBuyer = buyerRepository.findByEmail(registrationDto.getEmail());

        maybeBuyer.ifPresent(
                buyer -> {
                    throw new UserAlreadyExistsException("user with such email already exists");
                }
        );

        var buyerToSave = buyerRegistrationDtoMapper.map(registrationDto);
        buyerRepository.save(buyerToSave);

        return readBuyerDtoMapper.map(buyerToSave);

    }

    public ReadBuyerDto findByEmailAndPassword(String email, String password) {

        var maybeBuyer = buyerRepository.findByEmailAndPassword(email, password);

        return maybeBuyer.map(
                readBuyerDtoMapper::map
        ).orElseThrow(() -> {
                    throw new NoUserWithSuchCredentialsException("email or password are incorrect");
                }
        );


    }



    public Optional<ReadBuyerDto> findById(Long id) {

        var maybeBuyer = buyerRepository.findById(id);

        return maybeBuyer.map(
                readBuyerDtoMapper::map
        );


    }
}
