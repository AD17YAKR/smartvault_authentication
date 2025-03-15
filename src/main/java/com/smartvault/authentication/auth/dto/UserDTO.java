package com.smartvault.authentication.auth.dto;

import com.smartvault.authentication.auth.utils.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
public class UserDTO {
    private Long userId;

    private String username;
    private String email;
    private String password;
    private Role role;
    private String jwtToken;
    private LocalDateTime createdAt;
}