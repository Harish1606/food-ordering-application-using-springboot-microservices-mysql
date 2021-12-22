package com.ford.foa_order_service.controller;

import com.ford.foa_order_service.model.Hotel;
import com.ford.foa_order_service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @PostMapping("/addHotel")
    public Hotel addHotel(@RequestBody Hotel hotel) throws Exception {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        return hotelService.addHotel(hotel, authorizationHeader);
    }

    @GetMapping("/getAllHotels")
    public List<Hotel> getAllHotels() throws Exception {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        return hotelService.getAllHotels(authorizationHeader);
    }
}
