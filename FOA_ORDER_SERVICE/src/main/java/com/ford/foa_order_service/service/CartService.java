package com.ford.foa_order_service.service;

import com.ford.foa_order_service.model.Cart;
import com.ford.foa_order_service.model.User;
import com.ford.foa_order_service.repository.CartRepository;
import com.ford.foa_order_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public Cart addProductToCart(Cart cart, String authorizationHeader) throws Exception {
        User user = jwtUtil.verifyToken(authorizationHeader);
        cart.setUserId(user.getUserId());
        return cartRepository.save(cart);
    }

    public List<Cart> getCartDetailsByUserId(String authorizationHeader) throws Exception {
        User user = jwtUtil.verifyToken(authorizationHeader);
        List<Cart> cartListByUserId = cartRepository.findCartsByUserId(user.getUserId());
        return cartListByUserId;
    }

    public String deleteCartById(Integer id, String authorizationHeader) throws Exception {
        if (!jwtUtil.isTokenExpired(authorizationHeader)) {
            Cart cart = cartRepository.findById(id).orElseThrow(() -> new Exception("Product is not present in the cart"));
            cartRepository.delete(cart);
            return "Product removed from the cart successfully";
        }
        throw new Exception("Access denied");
    }
}
