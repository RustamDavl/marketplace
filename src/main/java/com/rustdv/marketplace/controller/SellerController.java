package com.rustdv.marketplace.controller;

import com.rustdv.marketplace.dto.auth.SellerLoginDto;
import com.rustdv.marketplace.dto.auth.SellerRegistrationDto;
import com.rustdv.marketplace.dto.createupdate.CreateUpdateGoodsDto;
import com.rustdv.marketplace.dto.createupdate.CreateUpdateShopDto;
import com.rustdv.marketplace.dto.read.ReadGoodsDto;
import com.rustdv.marketplace.dto.read.ReadSellerDto;
import com.rustdv.marketplace.dto.read.ReadShopDto;
import com.rustdv.marketplace.facade.SellerServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerServiceFacade sellerServiceFacade;

    @PostMapping("/signup")
    public ResponseEntity<ReadSellerDto> signUp(@RequestBody @Valid SellerRegistrationDto sellerRegistrationDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(sellerServiceFacade.signUp(sellerRegistrationDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<ReadSellerDto> signIn(@RequestBody @Valid SellerLoginDto sellerLoginDto) {

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(sellerServiceFacade.signIn(sellerLoginDto));
    }

    @PostMapping("/{id}/shop")
    public ResponseEntity<ReadShopDto> create(
            @PathVariable("id") Long id,
            @RequestBody @Valid CreateUpdateShopDto createUpdateShopDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(sellerServiceFacade.createShop(id, createUpdateShopDto));
    }


    @PostMapping("/shop/{shopId}/goods")
    public ResponseEntity<ReadGoodsDto> addGoods(
            @PathVariable("shopId") Long shopId,
            @RequestBody @Valid CreateUpdateGoodsDto createUpdateGoodsDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(sellerServiceFacade.addGoods(shopId, createUpdateGoodsDto));
    }


}
