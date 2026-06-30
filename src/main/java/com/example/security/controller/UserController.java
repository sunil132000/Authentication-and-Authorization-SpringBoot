package com.example.security.controller;

import com.example.security.dto.UserResponse;
import com.example.security.enums.ROLES;
import com.example.security.jpa.entity.RegisterUserRequest;
import com.example.security.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest){
        registerUserRequest.setRole(ROLES.USER);
         UserResponse userResponse = userService.registerUser(registerUserRequest);
        return  ResponseEntity.ok(userResponse);

    }
}
