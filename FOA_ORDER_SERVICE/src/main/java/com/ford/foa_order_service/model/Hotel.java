package com.ford.foa_order_service.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "HOTEL_TABLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int hotel_id;
    private String hotel_name;
    private String hotel_number;
    private String hotel_address;

}
