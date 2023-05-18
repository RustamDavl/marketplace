package com.rustdv.marketplace.controller;

import com.rustdv.marketplace.dto.auth.BuyerLoginDto;
import com.rustdv.marketplace.dto.auth.BuyerRegistrationDto;
import com.rustdv.marketplace.dto.read.ReadBuyerDto;
import com.rustdv.marketplace.facade.BuyerAuthFacade;
import com.rustdv.marketplace.facade.BuyerAuthFacadeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RequestMapping("/api/v1/buyer")
@RestController
public class BuyerController {

    private final BuyerAuthFacade buyerServiceFacadeImpl;

    @PostMapping("/signup")
    public ResponseEntity<ReadBuyerDto> signUp(@RequestBody @Valid BuyerRegistrationDto buyerRegistrationDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(buyerServiceFacadeImpl.signUp(buyerRegistrationDto));


    }

    @PostMapping("/signin")
    public ResponseEntity<ReadBuyerDto> signIn(@RequestBody @Valid BuyerLoginDto buyerLoginDto) {

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(buyerServiceFacadeImpl.signIn(buyerLoginDto));
    }


}
