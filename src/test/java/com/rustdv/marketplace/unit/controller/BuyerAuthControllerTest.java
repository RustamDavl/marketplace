package com.rustdv.marketplace.unit.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.rustdv.marketplace.controller.BuyerAuthController;
import com.rustdv.marketplace.dto.auth.BuyerRegistrationDto;
import com.rustdv.marketplace.exception.handler.ControllerExceptionHandler;
import com.rustdv.marketplace.mapper.BuyerRegistrationDtoMapper;
import com.rustdv.marketplace.mapper.ReadBuyerDtoMapper;
import com.rustdv.marketplace.repository.BuyerRepository;
import com.rustdv.marketplace.service.BuyerService;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class BuyerAuthControllerTest {


    BuyerService buyerService;
    @Mock
    private BuyerAuthController buyerAuthController;

    @InjectMocks
    private ControllerExceptionHandler controllerExceptionHandler;

    private ObjectMapper objectMapper;

    private ReadBuyerDtoMapper readBuyerDtoMapper;

    private BuyerRegistrationDtoMapper buyerRegistrationDtoMapper;

    @BeforeEach
    void setUp() {
        readBuyerDtoMapper = new ReadBuyerDtoMapper();
        buyerRegistrationDtoMapper = new BuyerRegistrationDtoMapper();
        objectMapper = new ObjectMapper();
        RestAssuredMockMvc.standaloneSetup(buyerAuthController, controllerExceptionHandler);


       
    }

    @Test
    void registerShouldPass() throws JsonProcessingException {

        String jsonRequestForm = "{\"email\": \"test@gmail.com\", \"password\":\"strong\", \"phoneNumber\":\"89179209061\"," +
                "\"city\": \"Kazan\",\"street\": \"street\",\"houseNumber\": \"16k1\"," +
                "\"gender\": \"мужчина\", \"birthDate\": \"2001-01-29\"}";

        BuyerRegistrationDto buyerRegistrationRequest = new BuyerRegistrationDto(
                "test@gmail.com",
                "strong",
                "89179209061",
                "Kazan",
                "street",
                "16k1",
                "мужчина",
                LocalDate.parse("2001-01-29"));


        var buyer = buyerRegistrationDtoMapper.map(buyerRegistrationRequest);
        buyer.setRegisterAt(LocalDateTime.now());
        var readBuyerDto = readBuyerDtoMapper.map(buyer);
        doReturn(readBuyerDto).when(buyerAuthController).register(buyerRegistrationRequest);

        var actualResult = buyerAuthController.register(buyerRegistrationRequest);



        RestAssuredMockMvc
                .given()
                .contentType("application/json")
                .body(jsonRequestForm)
                .when()
                .post("/api/v1/buyer")
                .then()
                .status(HttpStatus.CREATED)
                .contentType(ContentType.JSON);


    }
}