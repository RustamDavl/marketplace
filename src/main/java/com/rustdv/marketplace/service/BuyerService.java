package com.rustdv.marketplace.service;

import com.rustdv.marketplace.dto.auth.BuyerRegistrationDto;
import com.rustdv.marketplace.dto.read.ReadBuyerDto;
import com.rustdv.marketplace.exception.UserAlreadyExistsException;
import com.rustdv.marketplace.mapper.BuyerRegistrationDtoMapper;
import com.rustdv.marketplace.mapper.ReadBuyerDtoMapper;
import com.rustdv.marketplace.repository.BuyerRepository;
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


    public Optional<ReadBuyerDto> findById(Long id) {

        var maybeBuyer = buyerRepository.findById(id);

        return maybeBuyer.map(
                readBuyerDtoMapper::map
        );


    }
}
