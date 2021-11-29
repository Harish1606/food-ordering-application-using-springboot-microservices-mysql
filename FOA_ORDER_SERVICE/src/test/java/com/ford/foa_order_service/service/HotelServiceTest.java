package com.ford.foa_order_service.service;

import com.ford.foa_order_service.model.Hotel;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HotelServiceTest {

    String valid_token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoYXJpcyIsImV4cCI6MTYzODIyMTc1MSwiaWF0IjoxNjM4MTg1NzUxfQ.jweSBxUVYsi_YRFWNm2u8QTEXHvuaBrmOAD85jzdKQA";

    String expired_token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIYXJpc2giLCJleHAiOjE2Mzc2MTc2MjAsImlhdCI6MTYzNzU4MTYyMH0.4MbKnHLf_hCF9GqI_8VomCGrcuTAjDWzEzCwftZiQ5g";

    @Autowired
    private HotelService hotelService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @SneakyThrows
    void shouldAddHotelIfTokenIsValid() {
        //given
        Hotel hotel = new Hotel(0,"Fisherman's fare","8984092093","Annanagar chennai");

        //then
        assertNotNull(hotelService.addHotel(hotel,valid_token));
    }

    @Test
    void shouldThrowExceptionInAddHotelIfTokenIsExpired() {
        //given
        Hotel hotel = new Hotel(0,"Fisherman's fare","8984092093","Annanagar chennai");

        //then
        Exception exception = assertThrows(Exception.class, () -> hotelService.addHotel(hotel,expired_token));
    }

    @Test
    @SneakyThrows
    void shouldReturnAllHotelsIfTokenIsValid() {
        //then
        assertNotNull(hotelService.getAllHotels(valid_token));
    }

    @Test
    void shouldThrowExceptionInGetAllHotelsIfTokenIsExpired() {
        //then
        Exception exception = assertThrows(Exception.class, () -> hotelService.getAllHotels(expired_token));
    }
}