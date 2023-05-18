package com.rustdv.marketplace.unit.controller;

import com.rustdv.marketplace.controller.SellerController;
import com.rustdv.marketplace.dto.auth.SellerRegistrationDto;
import com.rustdv.marketplace.dto.createupdate.CreateUpdateGoodsDto;
import com.rustdv.marketplace.dto.createupdate.CreateUpdateShopDto;
import com.rustdv.marketplace.dto.read.ReadGoodsDto;
import com.rustdv.marketplace.dto.read.ReadSellerDto;
import com.rustdv.marketplace.dto.read.ReadShopDto;
import com.rustdv.marketplace.facade.GoodsServiceFacade;
import com.rustdv.marketplace.facade.SellerAuthFacade;
import com.rustdv.marketplace.facade.ShopServiceFacade;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;

@WebMvcTest(SellerController.class)
public class SellerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShopServiceFacade<ReadShopDto, CreateUpdateShopDto> shopServiceFacadeImpl;

    @MockBean
    private GoodsServiceFacade<ReadGoodsDto, CreateUpdateGoodsDto> goodsServiceFacadeImpl;

    @MockBean
    private SellerAuthFacade sellerServiceFacadeImpl;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void signUp() {

        String jsonRequest = "{\"email\": \"someemail@gmail.com\", \"password\":\"password777\"," +
                "\"phoneNumber\":\"89179308077\", \"ownershipForm\":\"ИП\"}";


        doReturn(ReadSellerDto
                .builder()
                .id(1L)
                .email("someemail@gmail.com")
                .build())
                .when(sellerServiceFacadeImpl)
                .signUp(SellerRegistrationDto
                        .builder()
                        .email("someemail@gmail.com")
                        .password("password777")
                        .phoneNumber("89179308077")
                        .ownershipForm("ИП")
                        .build());

        RestAssuredMockMvc
                .given()
                .contentType("application/json")
                .body(jsonRequest)
                .when()
                .post("api/v1/seller/signup")
                .then()
                .status(HttpStatus.CREATED)
                .contentType(ContentType.JSON)
                .body("id", is(1))
                .body("email", is("someemail@gmail.com"));
    }

    @Test
    void createShop() {

        String jsonRequest = "{\"name\": \"shopName\", \"goodsCategory\":\"музыка\"}";

        doReturn(ReadShopDto
                .builder()
                .id(1L)
                .goodsCategory("музыка")
                .readSellerDto(
                        ReadSellerDto.builder()
                                .id(1L)
                                .build())
                .name("crazy shop")
                .build())
                .when(shopServiceFacadeImpl)
                .createShop(1L,
                        CreateUpdateShopDto.builder()
                                .name("shopName")
                                .goodsCategory("музыка")
                                .build());
        RestAssuredMockMvc
                .given()
                .contentType("application/json")
                .body(jsonRequest)
                .when()
                .post("api/v1/seller/{id}/shop", 1L)
                .then()
                .status(HttpStatus.CREATED)
                .contentType(ContentType.JSON)
                .body("id", is(1))
                .body("goodsCategory", is("музыка"));


    }

    @Test
    void addGoods() {

        String jsonRequest = "{\"name\": \"guitar\", \"price\":\"20000\"," +
                "\"amount\":\"20\"}";

        doReturn(ReadGoodsDto
                .builder()
                .id(10L)
                .amount("20")
                .name("guitar")
                .price("20000")
                .readShopDto(
                        ReadShopDto.builder()
                                .id(10L)
                                .name("crazy shop")
                                .build())
                .build())
                .when(goodsServiceFacadeImpl)
                .addGoods(1L, CreateUpdateGoodsDto.builder()
                        .name("guitar")
                        .price("20000")
                        .amount("20")
                        .build());

        RestAssuredMockMvc
                .given()
                .contentType("application/json")
                .body(jsonRequest)
                .when()
                .post("api/v1/seller/shop/{shopId}/goods", 1L)
                .then()
                .status(HttpStatus.CREATED)
                .contentType(ContentType.JSON)
                .body("id", is(10))
                .body("name", is("guitar"))
                .body("price", is("20000"))
                .body("readShopDto.id", is(10))
                .body("readShopDto.name", is("crazy shop"));

    }


}
