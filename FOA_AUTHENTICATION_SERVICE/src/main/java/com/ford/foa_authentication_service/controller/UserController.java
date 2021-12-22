package com.ford.foa_authentication_service.controller;


import com.ford.foa_authentication_service.model.User;
import com.ford.foa_authentication_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/authentication")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/")
    public String sampleEndPoint() {
        return "Welcome to the food ordering application";
    }

    @GetMapping("/verifyToken")
    public User verifyToken() {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        return userService.verifyToken(authorizationHeader);
    }

    @PostMapping("/userRegister")
    public String userRegister(@RequestBody User user) throws Exception {
        return userService.userRegister(user);
    }

    @PostMapping("/userLogin")
    public String userLogin(@RequestBody User user) throws Exception {
        return userService.userLogin(user);
    }

}
