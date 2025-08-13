package com.example.fruithub.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthSignUp {
    private String email;
    private String password;
    private String confirmPassword;
    private boolean rememberPassword;
    private UUID statusUuid;
}
