package com.ford.foa_authentication_service.service;

import com.ford.foa_authentication_service.model.User;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @SneakyThrows
    @Test
    void shouldReturnTokenIfAllInputIsValidInUserRegistration() {
        //given
        User user = new User(0,"aravindh@gmail.com","Aravindh","9429402004","chennai","aravindh");

        //then
        assertNotNull(userService.userRegister(user));
    }


    @Test
    void shouldThrowExceptionIfEmailIdAlreadyExistsInUserRegistration(){
        //given
        User user = new User(0,"akash@gmail.com","akash","9429402004","chennai","akash");

        //then
        Exception exception = assertThrows(Exception.class, () -> userService.userRegister(user));
    }


    @Test
    void shouldThrowExceptionIfUsernameAlreadyExistsInUserRegistration(){
        //given
        User user = new User(0,"akash@gmail.com","akash","9429402004","chennai","akash");

        //then
        Exception exception = assertThrows(Exception.class, () -> userService.userRegister(user));
    }


    @SneakyThrows
    @Test
    void shouldReturnTokenIfAllInputIsValidInUserLogin() {
        //given
        User user = new User(0,null,"akash",null,null,"akash");

        //then
        assertNotNull(userService.userLogin(user));
    }


    @Test
    void shouldThrowExceptionIfUsernameAndPasswordDoesNotExistsInUserLogin(){
        //given
        User user = new User(0,null,"Hemchandhar",null,null,"hemchandhar");

        //then
        Exception exception = assertThrows(Exception.class, () -> userService.userLogin(user));
    }
}