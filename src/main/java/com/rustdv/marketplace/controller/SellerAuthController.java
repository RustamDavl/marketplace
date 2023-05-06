package com.rustdv.marketplace.controller;

import com.rustdv.marketplace.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seller")
@RequiredArgsConstructor
public class SellerAuthController {

    private final SellerService sellerService;


}
