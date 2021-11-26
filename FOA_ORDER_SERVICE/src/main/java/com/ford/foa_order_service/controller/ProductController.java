package com.ford.foa_order_service.controller;

import com.ford.foa_order_service.model.Product;
import com.ford.foa_order_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) throws Exception{
        String token = httpServletRequest.getHeader("Authorization");
        return productService.addProduct(product,token);
    }

    @GetMapping("/getProductsByHotelId/{id}")
    public List<Product> getProductsByHotelId(@PathVariable Integer id) throws Exception{
        String token = httpServletRequest.getHeader("Authorization");
        return productService.getProductsByHotelId(id,token);
    }

    @GetMapping("/getProduct/{id}")
    public Product getProductById(@PathVariable Integer id) throws Exception{
        String token = httpServletRequest.getHeader("Authorization");
        return productService.getProductById(id,token);
    }

}
