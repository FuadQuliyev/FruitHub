package com.example.fruithub.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthLogin {
    private String email;
    private String password;
}
