package com.rustdv.marketplace.service;

import com.rustdv.marketplace.entity.Seller;
import com.rustdv.marketplace.exception.NoUserWithSuchCredentialsException;
import com.rustdv.marketplace.exception.UserAlreadyExistsException;
import com.rustdv.marketplace.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SellerServiceImpl implements UserService<Seller> {

    private final SellerRepository sellerRepository;

    @Transactional
    @Override
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

    @Override
    public Seller findByEmailAndPassword(String email, String password) {

        var maybeSeller = sellerRepository.findByEmailAndPassword(email, password);

        return maybeSeller.orElseThrow(() -> {
                    throw new NoUserWithSuchCredentialsException("email or password are incorrect");
                }
        );
    }



}
