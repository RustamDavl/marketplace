package com.rustdv.marketplace.unit.controller;

import com.rustdv.marketplace.controller.CartController;
import com.rustdv.marketplace.dto.createupdate.CreateUpdateCartDto;
import com.rustdv.marketplace.dto.read.ReadBuyerDto;
import com.rustdv.marketplace.dto.read.ReadCartDto;
import com.rustdv.marketplace.dto.read.ReadGoodsDto;
import com.rustdv.marketplace.facade.CartServiceFacade;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartServiceFacade cartServiceFacadeImpl;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }


    @Test
    void addGoods() {

        String jsonRequest = "{\"buyerId\": \"1\", \"goodsId\":\"1\", \"amount\":\"4\"}";

        doReturn(ReadCartDto
                .builder()
                .readBuyerDto(ReadBuyerDto
                        .builder()
                        .id(1L)
                        .build())
                .readGoodsDto(ReadGoodsDto
                        .builder()
                        .id(1L)
                        .build())
                .amount(20L)
                .build())
                .when(cartServiceFacadeImpl).addGoods(CreateUpdateCartDto
                        .builder()
                        .goodsId("1")
                        .buyerId("1")
                        .amount("4")
                        .build());

        RestAssuredMockMvc
                .given()
                .contentType("application/json")
                .body(jsonRequest)
                .when()
                .post("/api/v1/cart")
                .then()
                .status(HttpStatus.CREATED)
                .contentType(ContentType.JSON)
                .body("amount", is(20))
                .body("readGoodsDto.id", is(1))
                .body("readBuyerDto.id", is(1));
    }

    @Test
    void findAll() {

        var readGoodsDto1 = ReadCartDto.builder()
                .id(10L)
                .readBuyerDto(ReadBuyerDto.builder().build())
                .readGoodsDto(ReadGoodsDto.builder().build())
                .amount(10L)
                .build();
        var readGoodsDto2 = ReadCartDto.builder()
                .id(11L)
                .readBuyerDto(ReadBuyerDto.builder().build())
                .readGoodsDto(ReadGoodsDto.builder().build())
                .amount(5L)
                .build();

        doReturn(List.of(readGoodsDto1, readGoodsDto2))
                .when(cartServiceFacadeImpl).findAllByBuyerId(1L);

        RestAssuredMockMvc
                .given()
                .contentType("application/json")
                .when()
                .get("/api/v1/cart/buyer/{buyerId}", 1L)
                .then()
                .status(HttpStatus.OK)
                .contentType(ContentType.JSON)
                .body("$.size()", is(2))
                .body("[0].id", is(10));
    }
}
