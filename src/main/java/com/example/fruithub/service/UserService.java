package com.example.fruithub.service;

import com.example.fruithub.dto.AuthResponse;
import com.example.fruithub.dto.AuthSignUp;

public interface UserService {
    AuthResponse signUp(AuthSignUp userDto) throws Exception;
}
