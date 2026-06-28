package com.example.security.service;

import com.example.security.enums.ROLES;
import com.example.security.jpa.entity.Users;
import com.example.security.jpa.reposistory.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitilzer {

    @Bean
    public CommandLineRunner createAdminUser(UserRepository userRepository,
                                             PasswordEncoder passwordEncoder) {

        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {

                Users admin = new Users();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin1234"));
                admin.setRole(ROLES.ADMIN);

                userRepository.save(admin);

                System.out.println("Default admin user created!");
            }
            if (userRepository.findByUsername("user").isEmpty()) {

                Users admin = new Users();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("user1234"));
                admin.setRole(ROLES.USER);

                userRepository.save(admin);

                System.out.println("Default admin user created!");
            }
        };
    }
}