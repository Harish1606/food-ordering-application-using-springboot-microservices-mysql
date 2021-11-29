package com.ford.foa_order_service.service;

import com.ford.foa_order_service.model.Cart;
import com.ford.foa_order_service.model.User;
import com.ford.foa_order_service.repository.CartRepository;
import com.ford.foa_order_service.util.JwtUtil;
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
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private JwtUtil jwtUtil;

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

    public boolean isTokenExpired(String authorizationHeader){
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            String token = authorizationHeader.substring(7);
            return jwtUtil.isTokenExpired(token);
        }
        return true;
    }

    public Cart addProductToCart(Cart cart,String authorizationHeader) throws Exception{
        User user = verifyToken(authorizationHeader);
        cart.setUserId(user.getUserId());
        return cartRepository.save(cart);
    }

    public List<Cart> getCartDetailsByUserId(String authorizationHeader) throws Exception{
        User user = verifyToken(authorizationHeader);
        List<Cart> cartListByUserId = cartRepository.findCartsByUserId(user.getUserId());
        return cartListByUserId;
    }

    public String deleteCartById(Integer id,String authorizationHeader) throws Exception{
        if(!isTokenExpired(authorizationHeader)){
            Cart cart=cartRepository.findById(id).orElseThrow(() -> new Exception("Product is not present in the cart"));
            cartRepository.delete(cart);
            return "Product removed from the cart successfully";
        }
        throw new Exception("Access denied");
    }
}
