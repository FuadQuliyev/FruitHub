package com.example.fruithub.controller;


import com.example.fruithub.dto.AuthResponse;
import com.example.fruithub.dto.AuthSignUp;
import com.example.fruithub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public AuthResponse SignUp(@RequestBody AuthSignUp userDto) throws Exception {
        return userService.signUp(userDto);
    }

}
