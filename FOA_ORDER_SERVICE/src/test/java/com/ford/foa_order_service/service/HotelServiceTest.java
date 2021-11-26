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

        //when
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJha2FzaCIsImV4cCI6MTYzNzg2OTMxMiwiaWF0IjoxNjM3ODMzMzEyfQ.U4rtaJxh-X7mJkvU22ngJBlCwto2bA4vnXtHrvdSj3A";

        //then
        assertNotNull(hotelService.addHotel(hotel,token));
    }

    @Test
    void shouldThrowExceptionInAddHotelIfTokenIsExpired() {
        //given
        Hotel hotel = new Hotel(0,"Fisherman's fare","8984092093","Annanagar chennai");

        //when
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIYXJpc2giLCJleHAiOjE2Mzc2MTc2MjAsImlhdCI6MTYzNzU4MTYyMH0.4MbKnHLf_hCF9GqI_8VomCGrcuTAjDWzEzCwftZiQ5g";

        //then
        Exception exception = assertThrows(Exception.class, () -> hotelService.addHotel(hotel,token));
    }

    @Test
    @SneakyThrows
    void shouldReturnAllHotelsIfTokenIsValid() {
        //given
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJha2FzaCIsImV4cCI6MTYzNzg2OTMxMiwiaWF0IjoxNjM3ODMzMzEyfQ.U4rtaJxh-X7mJkvU22ngJBlCwto2bA4vnXtHrvdSj3A";

        //then
        assertNotNull(hotelService.getAllHotels(token));
    }

    @Test
    void shouldThrowExceptionInGetAllHotelsIfTokenIsExpired() {
        //given
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIYXJpc2giLCJleHAiOjE2Mzc2MTc2MjAsImlhdCI6MTYzNzU4MTYyMH0.4MbKnHLf_hCF9GqI_8VomCGrcuTAjDWzEzCwftZiQ5g";

        //then
        Exception exception = assertThrows(Exception.class, () -> hotelService.getAllHotels(token));
    }
}