package com.example.fruithub.service;

import com.example.fruithub.dto.AuthLogin;
import com.example.fruithub.dto.AuthResponse;
import com.example.fruithub.dto.AuthSignUp;
import com.example.fruithub.exception.PasswordHandlerException;
import com.example.fruithub.exception.UserHandlerException;
import com.example.fruithub.model.Status;
import com.example.fruithub.model.User;
import com.example.fruithub.repository.StatusRepository;
import com.example.fruithub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@RequiredArgsConstructor
    public class UserServiceImpl implements UserService{

        private final BCryptPasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final UserRepository userRepository;
        private final StatusRepository statusRepository;

    @Override
    public AuthResponse signUp(AuthSignUp userDto) throws Exception {
        Optional<User> checkUser = userRepository.findByEmail(userDto.getEmail());

        if(checkUser.isPresent()){
           throw new UserHandlerException("User already exists");
        } else if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            throw new PasswordHandlerException("Password not equal");
        }else{
            User user = new User();
            Optional<Status> status = statusRepository.findById(userDto.getStatusUuid());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setStatus(status.get());
            userRepository.save(user);
            String token = jwtService.generateToken(user.getEmail());
            return new AuthResponse(token);
        }

    }

    @Override
    public AuthResponse login(AuthLogin userDto) throws Exception {
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if (user.isPresent()){
            if (passwordEncoder.matches(userDto.getPassword(), user.get().getPassword())){
                String token = jwtService.generateToken(user.get().getEmail());
                return new AuthResponse(token);
            }else{
                throw new PasswordHandlerException("Password wrong");
            }
        }else{
            throw new UserHandlerException("User not find");
        }
    }
}
