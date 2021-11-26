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

        //when
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJha2FzaCIsImV4cCI6MTYzNzg2OTMxMiwiaWF0IjoxNjM3ODMzMzEyfQ.U4rtaJxh-X7mJkvU22ngJBlCwto2bA4vnXtHrvdSj3A";

        //then
        assertNotNull(cartService.addProductToCart(cart,token));
    }

    @Test
    void shouldThrowExceptionInAddProductToCartIfTokenIsExpired(){
        //given
        Cart cart = new Cart(0,26,5,11,"Parotta",40,2);

        //when
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIYXJpc2giLCJleHAiOjE2Mzc2MTc2MjAsImlhdCI6MTYzNzU4MTYyMH0.4MbKnHLf_hCF9GqI_8VomCGrcuTAjDWzEzCwftZiQ5g";

        //then
        Exception exception = assertThrows(Exception.class, () -> cartService.addProductToCart(cart,token));
    }

    @SneakyThrows
    @Test
    void shouldReturnCartDetailsByUserIdIfTokenIsValid() {
        //given
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJha2FzaCIsImV4cCI6MTYzNzg2OTMxMiwiaWF0IjoxNjM3ODMzMzEyfQ.U4rtaJxh-X7mJkvU22ngJBlCwto2bA4vnXtHrvdSj3A";

        //then
        assertNotNull(cartService.getCartDetailsByUserId(token));
    }

    @Test
    void shouldThrowExceptionInGetCartDetailsByUserIdIfTokenIsExpired() {
        //given
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIYXJpc2giLCJleHAiOjE2Mzc2MTc2MjAsImlhdCI6MTYzNzU4MTYyMH0.4MbKnHLf_hCF9GqI_8VomCGrcuTAjDWzEzCwftZiQ5g";

        //then
        Exception exception = assertThrows(Exception.class, () -> cartService.getCartDetailsByUserId(token));
    }

    @SneakyThrows
    @Test
    void shouldDeleteCartByIdIfCartIdExistsAndTokenIsValid() {
        //given
        Integer cartId = 40;

        //when
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJha2FzaCIsImV4cCI6MTYzNzg2OTMxMiwiaWF0IjoxNjM3ODMzMzEyfQ.U4rtaJxh-X7mJkvU22ngJBlCwto2bA4vnXtHrvdSj3A";

        //then
        assertNotNull(cartService.deleteCartById(cartId,token));
    }

    @Test
    void shouldThrowExceptionInDeleteCartByIdIfCartIdNotExistsAndTokenIsValid(){
        //given
        Integer cartId = 0;

        //when
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJha2FzaCIsImV4cCI6MTYzNzg2OTMxMiwiaWF0IjoxNjM3ODMzMzEyfQ.U4rtaJxh-X7mJkvU22ngJBlCwto2bA4vnXtHrvdSj3A";

        //then
        Exception exception = assertThrows(Exception.class, () -> cartService.deleteCartById(cartId,token));
    }

    @Test
    void shouldThrowExceptionInDeleteCartByIdIfCartIdExistsAndTokenIsExpired(){
        //given
        Integer cartId =36;

        //when
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIYXJpc2giLCJleHAiOjE2Mzc2MTc2MjAsImlhdCI6MTYzNzU4MTYyMH0.4MbKnHLf_hCF9GqI_8VomCGrcuTAjDWzEzCwftZiQ5g";

        //then
        Exception exception = assertThrows(Exception.class, () -> cartService.deleteCartById(cartId,token));
    }
}