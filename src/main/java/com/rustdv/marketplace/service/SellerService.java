package com.rustdv.marketplace.service;

import com.rustdv.marketplace.dto.auth.SellerRegistrationDto;
import com.rustdv.marketplace.dto.createupdate.CreateUpdateGoodsDto;
import com.rustdv.marketplace.dto.read.ReadGoodsDto;
import com.rustdv.marketplace.dto.read.ReadSellerDto;
import com.rustdv.marketplace.exception.UserAlreadyExistsException;
import com.rustdv.marketplace.mapper.CreateUpdateGoodsDtoMapper;
import com.rustdv.marketplace.mapper.ReadGoodsDtoMapper;
import com.rustdv.marketplace.mapper.ReadSellerDtoMapper;
import com.rustdv.marketplace.mapper.SellerRegistrationDtoMapper;
import com.rustdv.marketplace.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    private final SellerRegistrationDtoMapper sellerRegistrationDtoMapper;

    private final ReadSellerDtoMapper readSellerDtoMapper;

    private final CreateUpdateGoodsDtoMapper createUpdateGoodsDtoMapper;

    private final ReadGoodsDtoMapper readGoodsDtoMapper;

    public ReadSellerDto register(SellerRegistrationDto sellerRegistrationDto) {

        var maybeSeller = sellerRepository.findByEmail(sellerRegistrationDto.getEmail());
        maybeSeller.ifPresent(
                seller -> {
                    throw new UserAlreadyExistsException("Seller with such email already exists");
                }
        );
        var sellerToSave = sellerRegistrationDtoMapper.map(sellerRegistrationDto);

        sellerRepository.save(sellerToSave);

        return readSellerDtoMapper.map(sellerToSave);
    }



}
