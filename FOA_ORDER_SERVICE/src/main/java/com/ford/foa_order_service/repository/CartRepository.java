package com.ford.foa_order_service.repository;

import com.ford.foa_order_service.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {

    List<Cart> findCartsByUserId(Integer id);
}
