package com.ford.foa_order_service.service;

import com.ford.foa_order_service.model.Product;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    String valid_token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoYXJpcyIsImV4cCI6MTYzODIyMTc1MSwiaWF0IjoxNjM4MTg1NzUxfQ.jweSBxUVYsi_YRFWNm2u8QTEXHvuaBrmOAD85jzdKQA";

    String expired_token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIYXJpc2giLCJleHAiOjE2Mzc2MTc2MjAsImlhdCI6MTYzNzU4MTYyMH0.4MbKnHLf_hCF9GqI_8VomCGrcuTAjDWzEzCwftZiQ5g";

    @Autowired
    private ProductService productService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @SneakyThrows
    @Test
    void shouldAddProductIfHotelIdExistsAndTokenIsValid() {
        //given
        Product product = new Product(0,"Chicken Fried Rice",150,"made with hygiened ingredients",5);

        //then
        assertNotNull(productService.addProduct(product,valid_token));
    }

    @Test
    void shouldThrowExceptionInAddProductIfHotelIdNotExistsAndTokenIsValid() {
        //given
        Product product = new Product(0,"Chicken Fried Rice",150,"made with hygiened ingredients",12);

        //then
        Exception exception = assertThrows(Exception.class, () -> productService.addProduct(product,valid_token));
    }

    @Test
    void shouldThrowExceptionInAddProductIfHotelIdExistsAndTokenIsExpired() {
        //given
        Product product = new Product(0,"Chicken Noodles",150,"made with hygiened ingredients",5);

        //then
        Exception exception = assertThrows(Exception.class, () -> productService.addProduct(product,expired_token));
    }

    @SneakyThrows
    @Test
    void shouldReturnProductsByHotelIdIfHotelIdExistsAndTokenIsValid(){
        //given
        Integer hotelId = 5;

        //then
        assertNotNull(productService.getProductsByHotelId(hotelId,valid_token));
    }

    @SneakyThrows
    @Test
    void shouldReturnEmptyListIfHotelIdNotExistsAndTokenIsValid(){
        //given
        Integer hotelId = 12;
        List<Product> productList = new ArrayList<Product>();

        //then
        assertEquals(productList,productService.getProductsByHotelId(hotelId,valid_token));
    }

    @Test
    void shouldThrowExceptionInGetProductsByHotelIdIfHotelIdExistsAndTokenIsExpired(){
        //given
        Integer hotelId = 5;

        //then
        Exception exception = assertThrows(Exception.class, () -> productService.getProductsByHotelId(hotelId,expired_token));
    }

    @SneakyThrows
    @Test
    void shouldReturnProductByIdIfProductIdExistsAndTokenIsValid(){
        //given
        Integer productId = 11;

        //then
        assertNotNull(productService.getProductById(productId,valid_token));
    }

    @Test
    void shouldThrowExceptionInGetProductByIdIfProductIdNotExistsAndTokenIsValid(){
        //given
        Integer productId = 25;

        //then
        Exception exception = assertThrows(Exception.class, () -> productService.getProductById(productId,valid_token));
    }

    @Test
    void shouldThrowExceptionInGetProductByIdIfProductIdExistsAndTokenIsExpired(){
        //given
        Integer productId = 11;

        //then
        Exception exception = assertThrows(Exception.class, () -> productService.getProductById(productId,expired_token));
    }

}