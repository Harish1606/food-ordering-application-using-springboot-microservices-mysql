package com.ford.foa_order_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "CART_TABLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int cartId;
    private int userId;
    private int hotelId;
    private int productId;
    private String productName;
    private int productAmount;
    private int quantity;

}
