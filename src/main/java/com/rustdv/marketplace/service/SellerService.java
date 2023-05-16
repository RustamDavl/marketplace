package com.rustdv.marketplace.service;

import com.rustdv.marketplace.dto.auth.SellerRegistrationDto;
import com.rustdv.marketplace.dto.createupdate.CreateUpdateGoodsDto;
import com.rustdv.marketplace.dto.read.ReadGoodsDto;
import com.rustdv.marketplace.dto.read.ReadSellerDto;
import com.rustdv.marketplace.entity.Seller;
import com.rustdv.marketplace.exception.NoSuchElementException;
import com.rustdv.marketplace.exception.NoUserWithSuchCredentialsException;
import com.rustdv.marketplace.exception.UserAlreadyExistsException;
import com.rustdv.marketplace.mapper.CreateUpdateGoodsDtoMapper;
import com.rustdv.marketplace.mapper.ReadGoodsDtoMapper;
import com.rustdv.marketplace.mapper.ReadSellerDtoMapper;
import com.rustdv.marketplace.mapper.SellerRegistrationDtoMapper;
import com.rustdv.marketplace.repository.GoodsRepository;
import com.rustdv.marketplace.repository.SellerRepository;
import com.rustdv.marketplace.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;



    @Transactional
    public Seller register(Seller registrationSeller) {

        var maybeSeller = sellerRepository.findByEmail(registrationSeller.getEmail());
        maybeSeller.ifPresent(
                seller -> {
                    throw new UserAlreadyExistsException("Seller with such email already exists");
                }
        );

        sellerRepository.save(registrationSeller);

        return registrationSeller;
    }

    public Seller findByEmailAndPassword(String email, String password) {

        var maybeSeller = sellerRepository.findByEmailAndPassword(email, password);

        return maybeSeller.orElseThrow(() -> {
                    throw new NoUserWithSuchCredentialsException("email or password are incorrect");
                }
        );
    }



}
