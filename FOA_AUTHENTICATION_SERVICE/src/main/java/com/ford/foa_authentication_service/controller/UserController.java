package com.ford.foa_authentication_service.controller;


import com.ford.foa_authentication_service.model.User;
import com.ford.foa_authentication_service.repository.UserRepository;
import com.ford.foa_authentication_service.service.UserService;
import com.ford.foa_authentication_service.util.JwtUtil;
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
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/")
    public String sampleEndPoint(){
        return "Welcome to the food ordering application";
    }

    @GetMapping("/verifyToken")
    public User verifyToken(){
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String token = null;
        String userName = null;
        User user=null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            userName = jwtUtil.extractUsername(token);
            user = userRepository.findByUserName(userName);
        }
        return user;
    }

    @PostMapping("/userRegister")
    public String userRegister(@RequestBody User user) throws Exception{
        return userService.userRegister(user);
    }

    @PostMapping("/userLogin")
    public String userLogin(@RequestBody User user) throws Exception{
        return userService.userLogin(user);
    }

}
