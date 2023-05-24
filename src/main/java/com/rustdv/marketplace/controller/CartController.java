package com.rustdv.marketplace.controller;

import com.rustdv.marketplace.dto.createupdate.CreateUpdateCartDto;
import com.rustdv.marketplace.dto.read.ReadCartDto;
import com.rustdv.marketplace.facade.CartServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartServiceFacade cartServiceFacadeImpl;

    @PostMapping
    public ResponseEntity<ReadCartDto> addGoods(@RequestBody @Valid
                                                      CreateUpdateCartDto createUpdateCartDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(cartServiceFacadeImpl.addGoods(createUpdateCartDto));

    }

    @PatchMapping
    public ResponseEntity<ReadCartDto> buyGoods(
            @RequestBody @Valid CreateUpdateCartDto createUpdateCartDto
    ) {

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(cartServiceFacadeImpl.buyGoods(createUpdateCartDto));

    }

    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<List<ReadCartDto>> findAll(@PathVariable("buyerId") Long buyerId) {


        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(cartServiceFacadeImpl.findAllByBuyerId(buyerId));
    }


}
