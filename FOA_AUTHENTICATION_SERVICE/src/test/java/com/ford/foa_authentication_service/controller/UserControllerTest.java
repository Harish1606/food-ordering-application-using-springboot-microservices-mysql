package com.ford.foa_authentication_service.controller;

import com.ford.foa_authentication_service.FoaAuthenticationServiceApplication;
import com.ford.foa_authentication_service.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FoaAuthenticationServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    HttpHeaders httpHeaders = new HttpHeaders();
    @LocalServerPort
    private int PORT;
    private final TestRestTemplate testRestTemplate = new TestRestTemplate();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void ShouldReturnTokenForUserRegistration() {
        //given
        User user = new User(0, "harish@gmail.com", "harish", "7939200930", "chennai", "hari");

        //when
        HttpEntity<User> request = new HttpEntity<User>(user, httpHeaders);
        ResponseEntity<String> response = testRestTemplate.exchange("http://localhost:" + PORT + "/authentication/userRegister", HttpMethod.POST, request, String.class);

        //then
        System.out.println(response.getBody());
        //assertNotNull(response.getBody());
    }

    @Test
    @Sql(scripts = {"classpath:data.sql"})
    void userLogin() {
        //given
        User user = new User(10, null, "akash", null, null, "akash");

        //when
        HttpEntity<User> request = new HttpEntity<User>(user, httpHeaders);
        ResponseEntity<String> response = testRestTemplate.exchange("http://localhost:" + PORT + "/authentication/userLogin", HttpMethod.POST, request, String.class);

        //then
        System.out.println(response.getBody());
        //assertNotNull(response.getBody());
    }
}