package com.ford.foa_order_service.service;

import com.ford.foa_order_service.model.Hotel;
import com.ford.foa_order_service.repository.HotelRepository;
import com.ford.foa_order_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public boolean isTokenExpired(String authorizationHeader){
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            String token = authorizationHeader.substring(7);
            return jwtUtil.isTokenExpired(token);
        }
        return true;
    }

    public Hotel addHotel(Hotel hotel,String authorizationHeader) throws Exception{
        if(!isTokenExpired(authorizationHeader)) {
            return hotelRepository.save(hotel);
        }
        throw new Exception("Access denied");
    }

    public List<Hotel> getAllHotels(String authorizationHeader) throws Exception{
        if(!isTokenExpired(authorizationHeader)){
            return hotelRepository.findAll();
        }
        throw new Exception("Access denied");
    }
}
