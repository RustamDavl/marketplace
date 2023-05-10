package com.rustdv.marketplace.controller;

import com.rustdv.marketplace.dto.auth.BuyerRegistrationDto;
import com.rustdv.marketplace.dto.auth.SellerRegistrationDto;
import com.rustdv.marketplace.dto.createupdate.CreateUpdateGoodsDto;
import com.rustdv.marketplace.dto.read.ReadGoodsDto;
import com.rustdv.marketplace.dto.read.ReadSellerDto;
import com.rustdv.marketplace.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seller")
@RequiredArgsConstructor
public class SellerAuthController {

    private final SellerService sellerService;

    @PostMapping("/registration")
    public ResponseEntity<ReadSellerDto> register(@RequestBody SellerRegistrationDto sellerRegistrationDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(sellerService.register(sellerRegistrationDto));
    }



    @PostMapping("/{id}/goods")
    public ResponseEntity<ReadGoodsDto> addGoods(@PathVariable("id") Long id, CreateUpdateGoodsDto createUpdateGoodsDto) {

        return null;
    }


}
