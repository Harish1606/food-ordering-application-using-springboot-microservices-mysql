package com.ford.foa_order_service.controller;

import com.ford.foa_order_service.model.Cart;
import com.ford.foa_order_service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @PostMapping("/addProductToCart")
    public Cart addProductToCart(@RequestBody Cart cart) throws Exception{
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        return cartService.addProductToCart(cart,authorizationHeader);
    }

    @GetMapping("/getCartDetailsByUserId")
    public List<Cart> getCartDetailsByUserId() throws Exception{
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        return cartService.getCartDetailsByUserId(authorizationHeader);
    }

    @DeleteMapping("/deleteCart/{id}")
    public String deleteCartById(@PathVariable Integer id) throws Exception{
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        return cartService.deleteCartById(id,authorizationHeader);
    }

}
