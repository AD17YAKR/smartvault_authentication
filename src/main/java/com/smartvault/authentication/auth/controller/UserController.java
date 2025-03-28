package com.smartvault.authentication.auth.controller;

import com.smartvault.authentication.auth.dto.UserDTO;
import com.smartvault.authentication.auth.model.User;
import com.smartvault.authentication.auth.service.UserService;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/smartvault/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping({ "/signup/", "/signup" })
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User savedUser = userService.createUser(userDTO);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping({ "/login/", "/login" })
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO) {
        UserDTO authenticatedUser = userService.authenticateUser(userDTO.getUsername(), userDTO.getPassword());
        return ResponseEntity.ok(authenticatedUser);
    }

    @PostMapping({ "/validateToken", "/validateToken/" })
    public ResponseEntity<Map<String, Boolean>> validateToken(@RequestBody Map<String, String> request) {
        try {
            userService.validateToken(request);
            return ResponseEntity.ok(Map.of("valid", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("valid", false));
        }
    }
}