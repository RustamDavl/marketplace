package com.rustdv.marketplace.controller;

import com.rustdv.marketplace.dto.auth.BuyerRegistrationDto;
import com.rustdv.marketplace.dto.read.ReadBuyerDto;
import com.rustdv.marketplace.service.BuyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/buyer")
@RestController
public class BuyerAuthController {

    private final BuyerService buyerService;


    @PostMapping()
    public ResponseEntity<ReadBuyerDto> register(@RequestBody BuyerRegistrationDto buyerRegistrationDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(buyerService.register(buyerRegistrationDto));




    }
}
