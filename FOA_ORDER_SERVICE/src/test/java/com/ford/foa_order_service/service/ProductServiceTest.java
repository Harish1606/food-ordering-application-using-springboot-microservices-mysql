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

        //when
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJha2FzaCIsImV4cCI6MTYzNzg2OTMxMiwiaWF0IjoxNjM3ODMzMzEyfQ.U4rtaJxh-X7mJkvU22ngJBlCwto2bA4vnXtHrvdSj3A";

        //then
        assertNotNull(productService.addProduct(product,token));
    }

    @Test
    void shouldThrowExceptionInAddProductIfHotelIdNotExistsAndTokenIsValid() {
        //given
        Product product = new Product(0,"Chicken Fried Rice",150,"made with hygiened ingredients",12);

        //when
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJha2FzaCIsImV4cCI6MTYzNzg2OTMxMiwiaWF0IjoxNjM3ODMzMzEyfQ.U4rtaJxh-X7mJkvU22ngJBlCwto2bA4vnXtHrvdSj3A";

        //then
        Exception exception = assertThrows(Exception.class, () -> productService.addProduct(product,token));
    }

    @Test
    void shouldThrowExceptionInAddProductIfHotelIdExistsAndTokenIsExpired() {
        //given
        Product product = new Product(0,"Chicken Noodles",150,"made with hygiened ingredients",5);

        //when
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIYXJpc2giLCJleHAiOjE2Mzc2MTc2MjAsImlhdCI6MTYzNzU4MTYyMH0.4MbKnHLf_hCF9GqI_8VomCGrcuTAjDWzEzCwftZiQ5g";

        //then
        Exception exception = assertThrows(Exception.class, () -> productService.addProduct(product,token));
    }

    @SneakyThrows
    @Test
    void shouldReturnProductsByHotelIdIfHotelIdExistsAndTokenIsValid(){
        //given
        Integer hotelId = 5;

        //when
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJha2FzaCIsImV4cCI6MTYzNzg2OTMxMiwiaWF0IjoxNjM3ODMzMzEyfQ.U4rtaJxh-X7mJkvU22ngJBlCwto2bA4vnXtHrvdSj3A";

        //then
        assertNotNull(productService.getProductsByHotelId(hotelId,token));
    }

    @SneakyThrows
    @Test
    void shouldReturnEmptyListIfHotelIdNotExistsAndTokenIsValid(){
        //given
        Integer hotelId = 12;
        List<Product> productList = new ArrayList<Product>();

        //when
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJha2FzaCIsImV4cCI6MTYzNzg2OTMxMiwiaWF0IjoxNjM3ODMzMzEyfQ.U4rtaJxh-X7mJkvU22ngJBlCwto2bA4vnXtHrvdSj3A";

        //then
        assertEquals(productList,productService.getProductsByHotelId(hotelId,token));
    }

    @Test
    void shouldThrowExceptionInGetProductsByHotelIdIfHotelIdExistsAndTokenIsExpired(){
        //given
        Integer hotelId = 5;

        //when
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIYXJpc2giLCJleHAiOjE2Mzc2MTc2MjAsImlhdCI6MTYzNzU4MTYyMH0.4MbKnHLf_hCF9GqI_8VomCGrcuTAjDWzEzCwftZiQ5g";

        //then
        Exception exception = assertThrows(Exception.class, () -> productService.getProductsByHotelId(hotelId,token));
    }

    @SneakyThrows
    @Test
    void shouldReturnProductByIdIfProductIdExistsAndTokenIsValid(){
        //given
        Integer productId = 11;

        //when
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJha2FzaCIsImV4cCI6MTYzNzg2OTMxMiwiaWF0IjoxNjM3ODMzMzEyfQ.U4rtaJxh-X7mJkvU22ngJBlCwto2bA4vnXtHrvdSj3A";

        //then
        assertNotNull(productService.getProductById(productId,token));
    }

    @Test
    void shouldThrowExceptionInGetProductByIdIfProductIdNotExistsAndTokenIsValid(){
        //given
        Integer productId = 25;

        //when
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJha2FzaCIsImV4cCI6MTYzNzg2OTMxMiwiaWF0IjoxNjM3ODMzMzEyfQ.U4rtaJxh-X7mJkvU22ngJBlCwto2bA4vnXtHrvdSj3A";

        //then
        Exception exception = assertThrows(Exception.class, () -> productService.getProductById(productId,token));
    }

    @Test
    void shouldThrowExceptionInGetProductByIdIfProductIdExistsAndTokenIsExpired(){
        //given
        Integer productId = 11;

        //when
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIYXJpc2giLCJleHAiOjE2Mzc2MTc2MjAsImlhdCI6MTYzNzU4MTYyMH0.4MbKnHLf_hCF9GqI_8VomCGrcuTAjDWzEzCwftZiQ5g";

        //then
        Exception exception = assertThrows(Exception.class, () -> productService.getProductById(productId,token));
    }

}