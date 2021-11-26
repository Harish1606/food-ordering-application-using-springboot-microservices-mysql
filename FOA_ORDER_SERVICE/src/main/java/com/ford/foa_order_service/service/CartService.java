package com.ford.foa_order_service.service;

import com.ford.foa_order_service.model.Cart;
import com.ford.foa_order_service.model.User;
import com.ford.foa_order_service.repository.CartRepository;
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

    public Cart addProductToCart(Cart cart,String token) throws Exception{
        User user = verifyToken(token);
        cart.setUserId(user.getUserId());
        return cartRepository.save(cart);
    }

    public List<Cart> getCartDetailsByUserId(String token) throws Exception{
        User user = verifyToken(token);
        List<Cart> cartListByUserId = cartRepository.findCartsByUserId(user.getUserId());
        return cartListByUserId;
    }

    public String deleteCartById(Integer id,String token) throws Exception{
        User user = verifyToken(token);
        Cart cart=cartRepository.findById(id).orElseThrow(() -> new Exception("Product is not present in the cart"));
        cartRepository.delete(cart);
        return "Product removed from the cart successfully";
    }
}
