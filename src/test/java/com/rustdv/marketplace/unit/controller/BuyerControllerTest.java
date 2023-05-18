package com.rustdv.marketplace.unit.controller;

import com.rustdv.marketplace.controller.BuyerController;
import com.rustdv.marketplace.dto.auth.BuyerRegistrationDto;
import com.rustdv.marketplace.dto.read.ReadBuyerDto;
import com.rustdv.marketplace.facade.BuyerAuthFacade;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;

@WebMvcTest(BuyerController.class)
class BuyerControllerTest {

    @MockBean
    private BuyerAuthFacade buyerServiceFacadeImpl;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void registerShouldPass() {

        String jsonRequest = "{ \"email\": \"test@gmail.com\"," +
                "\"password\": \"strongPassw\"," +
                "\"phoneNumber\": \"89179209061\"," +
                "\"city\": \"Kazan\"," +
                "\"street\": \"street\"," +
                "\"houseNumber\": \"16k1\"," +
                "\"gender\": \"male\"," +
                "\"birthDate\": \"2001-01-29\" }";

        doReturn(ReadBuyerDto.builder()
                .id(10L)
                .phoneNumber("89179209061")
                .city("Kazan")
                .email("test@gmail.com")
                .build())
                .when(buyerServiceFacadeImpl)
                .signUp(new BuyerRegistrationDto(
                        "test@gmail.com",
                        "strongPassw",
                        "89179209061",
                        "Kazan",
                        "street",
                        "16k1",
                        "male",
                        LocalDate.of(2001, 1, 29))
                );

        RestAssuredMockMvc
                .given()
                .contentType("application/json")
                .body(jsonRequest)
                .when()
                .post("/api/v1/buyer/signup")
                .then()
                .status(HttpStatus.CREATED)
                .contentType(ContentType.JSON);


    }

    @Test
    @DisplayName("fail test because of short length of password")
    void registerShouldFail() {

        String jsonRequest = "{\"email\": \"test@gmail.com\", \"password\":\"short\", \"phoneNumber\":\"89179209061\"," +
                "\"city\": \"Kazan\",\"street\": \"street\",\"houseNumber\": \"16k1\"," +
                "\"gender\": \"мужчина\", \"birthDate\": \"2001-01-29\"}";

        RestAssuredMockMvc
                .given()
                .contentType("application/json")
                .body(jsonRequest)
                .when()
                .post("/api/v1/buyer/signup")
                .then()
                .status(HttpStatus.BAD_REQUEST);
    }
}