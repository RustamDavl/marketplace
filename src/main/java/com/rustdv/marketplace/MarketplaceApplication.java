package com.rustdv.marketplace;

import lombok.Cleanup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketplaceApplication {

    public static void main(String[] args) {

       @Cleanup var context = SpringApplication.run(MarketplaceApplication.class, args);
    }

}
