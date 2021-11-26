package com.ford.foa_authentication_service.service;

import com.ford.foa_authentication_service.model.User;
import com.ford.foa_authentication_service.repository.UserRepository;
import com.ford.foa_authentication_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String userRegister(User user) throws Exception{
        User userByEmailId=userRepository.findByEmailId(user.getEmailId());
        if(userByEmailId!=null){
            throw new Exception("Email id already exists");
        }
        User userByUserName=userRepository.findByUserName(user.getUserName());
        if(userByUserName!=null){
            throw new Exception("Username already exists");
        }
        userRepository.save(user);
        return jwtUtil.generateToken(user.getUserName());
    }

    public String userLogin(User user) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
        }
        catch (Exception e){
            throw new Exception("Invalid username or password");
        }
        return jwtUtil.generateToken(user.getUserName());
    }

}
