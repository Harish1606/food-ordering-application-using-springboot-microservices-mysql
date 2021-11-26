package com.ford.foa_order_service.repository;

import com.ford.foa_order_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findProductsByHotelId(Integer id);
}
