package com.ford.foa_authentication_service.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USER_TABLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String emailId;
    private String userName;
    private String mobileNumber;
    private String address;
    private String password;

}
