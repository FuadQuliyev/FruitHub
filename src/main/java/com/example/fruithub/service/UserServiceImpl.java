package com.example.fruithub.service;

import com.example.fruithub.dto.AuthResponse;
import com.example.fruithub.dto.AuthSignUp;
import com.example.fruithub.model.User;
import com.example.fruithub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.time.ZonedDateTime;


@Service
@RequiredArgsConstructor
    public class UserServiceImpl implements UserService{

        private final BCryptPasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final UserRepository userRepository;

    @Override
    public AuthResponse signUp(AuthSignUp userDto) throws Exception {
        Optional<User> checkUser = userRepository.findByEmail(userDto.getEmail());

        if(checkUser.isPresent()){
           throw new Exception("User already exists");
        } else if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            throw new Exception("Password not equal");
        }else{
            User user = new User();
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss z");
            ZonedDateTime defaultZone = ZonedDateTime.now();
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setCreateDate(defaultZone.format(fmt));
            userRepository.save(user);

            String token = jwtService.generateToken(user.getEmail());
            return new AuthResponse(token);
        }

    }
}
