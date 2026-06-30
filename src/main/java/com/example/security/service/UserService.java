package com.example.security.service;

import com.example.security.dto.UserResponse;
import com.example.security.jpa.entity.RegisterUserRequest;
import com.example.security.jpa.entity.Users;
import com.example.security.jpa.reposistory.UserRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(RegisterUserRequest registerUserRequest ){
        //TODO check if user if already present
        if(userRepository.findByUsername(registerUserRequest.getUsername()).isPresent()){
            throw  new RuntimeException(("Uer ALready Exist"));
        }

        //TODO encode password in request
        Users users = new Users();
        users.setUsername(registerUserRequest.getUsername());
        users.setRole(registerUserRequest.getRole());
        users.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));

        // save user
        Users savedUser = userRepository.save(users);

        return new UserResponse(savedUser.getId() , savedUser.getUsername() , savedUser.getRole().name());
    }
}
