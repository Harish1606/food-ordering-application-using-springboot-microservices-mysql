package com.ford.foa_order_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int userId;
    private String emailId;
    private String userName;
    private String mobileNumber;
    private String address;
    private String password;
}
