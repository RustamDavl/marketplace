package com.rustdv.marketplace.service;

import com.rustdv.marketplace.entity.Buyer;
import com.rustdv.marketplace.exception.NoUserWithSuchCredentialsException;
import com.rustdv.marketplace.exception.UserAlreadyExistsException;
import com.rustdv.marketplace.repository.BuyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BuyerServiceImpl implements UserService<Buyer, Long> {

    private final BuyerRepository buyerRepository;

    @Transactional
    @Override
    public Buyer register(Buyer registrationBuyer) {

        var maybeBuyer = buyerRepository.findByEmail(registrationBuyer.getEmail());

        maybeBuyer.ifPresent(
                buyer -> {
                    throw new UserAlreadyExistsException("user with such email already exists");
                }
        );

        buyerRepository.save(registrationBuyer);
        buyerRepository.flush();

        return registrationBuyer;

    }

    @Override
    public Buyer findByEmailAndPassword(String email, String password) {

        var maybeBuyer = buyerRepository.findByEmailAndPassword(email, password);

        return maybeBuyer.orElseThrow(() -> {
                    throw new NoUserWithSuchCredentialsException("email or password are incorrect");
                }
        );


    }
}
