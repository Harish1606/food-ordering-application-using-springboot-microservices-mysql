package com.ford.foa_order_service.service;

import com.ford.foa_order_service.model.Hotel;
import com.ford.foa_order_service.model.Product;
import com.ford.foa_order_service.repository.HotelRepository;
import com.ford.foa_order_service.repository.ProductRepository;
import com.ford.foa_order_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

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

    public Product addProduct(Product product,String authorizationHeader) throws Exception{
        if(!isTokenExpired(authorizationHeader)){
            Hotel hotel=hotelRepository.findById(product.getHotelId()).orElseThrow(() -> new Exception("Hotel not exists"));
            return productRepository.save(product);
        }
        throw new Exception("Access denied");
    }

    public List<Product> getProductsByHotelId(Integer id,String authorizationHeader) throws Exception{
        if(!isTokenExpired(authorizationHeader)){
            List<Product> productListByHotelId = productRepository.findProductsByHotelId(id);
            return productListByHotelId;
        }
        throw new Exception("Access denied");
    }

    public Product getProductById(Integer id,String authorizationHeader) throws Exception{
        if(!isTokenExpired(authorizationHeader)){
            Product product=productRepository.findById(id).orElseThrow(() -> new Exception("Product not exists"));
            return product;
        }
        throw new Exception("Access denied");
    }
}
