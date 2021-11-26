package com.ford.foa_order_service.service;

import com.ford.foa_order_service.model.Hotel;
import com.ford.foa_order_service.model.Product;
import com.ford.foa_order_service.model.User;
import com.ford.foa_order_service.repository.HotelRepository;
import com.ford.foa_order_service.repository.ProductRepository;
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
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

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

    public Product addProduct(Product product,String token) throws Exception{
        User user = verifyToken(token);
        Hotel hotel=hotelRepository.findById(product.getHotelId()).orElseThrow(() -> new Exception("Hotel not exists"));
        return productRepository.save(product);
    }

    public List<Product> getProductsByHotelId(Integer id,String token) throws Exception{
        User user = verifyToken(token);
        List<Product> productListByHotelId = productRepository.findProductsByHotelId(id);
        return productListByHotelId;
    }

    public Product getProductById(Integer id,String token) throws Exception{
        User user = verifyToken(token);
        Product product=productRepository.findById(id).orElseThrow(() -> new Exception("Product not exists"));
        return product;
    }
}
