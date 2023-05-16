package com.rustdv.marketplace.unit.controller;

import com.rustdv.marketplace.controller.BuyerController;
import com.rustdv.marketplace.dto.auth.BuyerRegistrationDto;
import com.rustdv.marketplace.dto.read.ReadBuyerDto;
import com.rustdv.marketplace.exception.handler.ControllerExceptionHandler;
import com.rustdv.marketplace.facade.BuyerServiceFacade;
import com.rustdv.marketplace.mapper.BuyerRegistrationDtoMapper;
import com.rustdv.marketplace.mapper.ReadBuyerDtoMapper;
import com.rustdv.marketplace.service.BuyerService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class BuyerControllerTest {


    @Mock
    BuyerServiceFacade buyerServiceFacade;
    @InjectMocks
    private BuyerController buyerController;
    private ObjectMapper objectMapper;
    private ReadBuyerDtoMapper readBuyerDtoMapper;
    private BuyerRegistrationDtoMapper buyerRegistrationDtoMapper;

    @BeforeEach
    void setUp() {
        readBuyerDtoMapper = new ReadBuyerDtoMapper();
        buyerRegistrationDtoMapper = new BuyerRegistrationDtoMapper();
        objectMapper = new ObjectMapper();
        RestAssuredMockMvc.standaloneSetup(buyerController);


    }

    @Test
    void registerShouldPass() throws JsonProcessingException {

        BuyerRegistrationDto buyerRegistrationRequest = new BuyerRegistrationDto(
                "test@gmail.com",
                "strong",
                "89179209061",
                "Kazan",
                "street",
                "16k1",
                "мужчина",
                LocalDate.parse("2001-01-29"));


        String jsonRequest = "{\"email\": \"test@gmail.com\", \"password\":\"strongPassw\", \"phoneNumber\":\"89179209061\"," +
                "\"city\": \"Kazan\",\"street\": \"street\",\"houseNumber\": \"16k1\"," +
                "\"gender\": \"мужчина\", \"birthDate\": \"2001-01-29\"}";


        var buyer = buyerRegistrationDtoMapper.map(buyerRegistrationRequest);
        buyer.setRegisterAt(LocalDateTime.now());
        var readBuyerDto = readBuyerDtoMapper.map(buyer);
        doReturn(readBuyerDto).when(buyerServiceFacade).signUp(buyerRegistrationRequest);

        var actualResult = buyerController.signUp(buyerRegistrationRequest);


        RestAssuredMockMvc
                .given()
                .contentType("application/json")
                .body(jsonRequest)
                .when()
                .post("/api/v1/buyer/signup")
                .then()
                .status(HttpStatus.CREATED)
                .contentType(ContentType.JSON)
                .body(is(actualResult));


    }
}