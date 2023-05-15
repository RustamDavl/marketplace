package com.rustdv.marketplace.controller;

import com.rustdv.marketplace.dto.auth.BuyerLoginDto;
import com.rustdv.marketplace.dto.auth.BuyerRegistrationDto;
import com.rustdv.marketplace.dto.createupdate.CreateUpdateCartDto;
import com.rustdv.marketplace.dto.read.ReadBuyerDto;
import com.rustdv.marketplace.dto.read.ReadCartDto;
import com.rustdv.marketplace.dto.read.ReadGoodsDto;
import com.rustdv.marketplace.facade.BuyerServiceFacade;
import com.rustdv.marketplace.service.BuyerService;
import com.rustdv.marketplace.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/buyer")
@RestController
public class BuyerController {

    private final BuyerServiceFacade buyerServiceFacade;

    @PostMapping("/signup")
    public ResponseEntity<ReadBuyerDto> signUp(@RequestBody @Valid BuyerRegistrationDto buyerRegistrationDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(buyerServiceFacade.signUp(buyerRegistrationDto));


    }

    @PostMapping("/signin")
    public ResponseEntity<ReadBuyerDto> signIn(@RequestBody @Valid BuyerLoginDto buyerLoginDto) {

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(buyerServiceFacade.signIn(buyerLoginDto));
    }


}
