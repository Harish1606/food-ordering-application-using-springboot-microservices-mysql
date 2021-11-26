package com.ford.foa_order_service.service;

import com.ford.foa_order_service.model.Hotel;
import com.ford.foa_order_service.model.User;
import com.ford.foa_order_service.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    public User verifyToken(String token){
        String verify_token_url = "http://AUTHENTICATION-SERVICE/authentication/verifyToken";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        headers.add("Authorization",token);
        HttpEntity request=new HttpEntity(headers);
        ResponseEntity<User> response = restTemplate.exchange(verify_token_url, HttpMethod.GET, request, User.class);
        User user=response.getBody();
        return user;
    }

    public Hotel addHotel(Hotel hotel,String token) throws Exception{
        User user = verifyToken(token);
        return hotelRepository.save(hotel);
    }

    public List<Hotel> getAllHotels(String token) throws Exception{
        User user = verifyToken(token);
        return hotelRepository.findAll();
    }
}
