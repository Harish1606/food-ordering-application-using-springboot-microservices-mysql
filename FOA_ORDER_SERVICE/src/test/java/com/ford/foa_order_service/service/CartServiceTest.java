package com.ford.foa_order_service.service;

import com.ford.foa_order_service.model.Cart;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartServiceTest {

    String valid_token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoYXJpcyIsImV4cCI6MTYzODIyMTc1MSwiaWF0IjoxNjM4MTg1NzUxfQ.jweSBxUVYsi_YRFWNm2u8QTEXHvuaBrmOAD85jzdKQA";

    String expired_token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIYXJpc2giLCJleHAiOjE2Mzc2MTc2MjAsImlhdCI6MTYzNzU4MTYyMH0.4MbKnHLf_hCF9GqI_8VomCGrcuTAjDWzEzCwftZiQ5g";

    @Autowired
    private CartService cartService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @SneakyThrows
    @Test
    void shouldAddProductToCartIfTokenIsValid() {
        //given
        Cart cart = new Cart(0,26,5,11,"Parotta",40,2);

        //then
        assertNotNull(cartService.addProductToCart(cart,valid_token));
    }

    @Test
    void shouldThrowExceptionInAddProductToCartIfTokenIsExpired(){
        //given
        Cart cart = new Cart(0,26,5,11,"Parotta",40,2);

        //then
        Exception exception = assertThrows(Exception.class, () -> cartService.addProductToCart(cart,expired_token));
    }

    @SneakyThrows
    @Test
    void shouldReturnCartDetailsByUserIdIfTokenIsValid() {
        //then
        assertNotNull(cartService.getCartDetailsByUserId(valid_token));
    }

    @Test
    void shouldThrowExceptionInGetCartDetailsByUserIdIfTokenIsExpired() {
        //then
        Exception exception = assertThrows(Exception.class, () -> cartService.getCartDetailsByUserId(expired_token));
    }

    @SneakyThrows
    @Test
    void shouldDeleteCartByIdIfCartIdExistsAndTokenIsValid() {
        //given
        Integer cartId = 40;

        //then
        assertNotNull(cartService.deleteCartById(cartId,valid_token));
    }

    @Test
    void shouldThrowExceptionInDeleteCartByIdIfCartIdNotExistsAndTokenIsValid(){
        //given
        Integer cartId = 0;

        //then
        Exception exception = assertThrows(Exception.class, () -> cartService.deleteCartById(cartId,valid_token));
    }

    @Test
    void shouldThrowExceptionInDeleteCartByIdIfCartIdExistsAndTokenIsExpired(){
        //given
        Integer cartId =36;

        //then
        Exception exception = assertThrows(Exception.class, () -> cartService.deleteCartById(cartId,expired_token));
    }
}