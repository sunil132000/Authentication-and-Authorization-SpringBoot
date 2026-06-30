package com.example.security.config;

import com.example.security.enums.Permissions;
import com.example.security.enums.ROLES;
import com.example.security.filter.JwtAuthFilter;
import com.example.security.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // is this there?
                )
                .authorizeHttpRequests(auth-> auth.antMatchers("/authenticate").permitAll()
                        .antMatchers("/authenticate", "/api/users/register").permitAll()
                        //below is role based authorization
                        //.antMatchers(HttpMethod.GET ,"/employee/getEmployee").hasRole(String.valueOf(ROLES.ADMIN))
                        //below three are access based authorization
                        //.antMatchers(HttpMethod.GET ,"/employee/getEmployee").hasAuthority(String.valueOf(Permissions.EMPLOYEE_READ))
                        //.antMatchers(HttpMethod.POST ,"/employee/getEmployee").hasAuthority(String.valueOf(Permissions.EMPLOYEE_WRITE))
                        //.antMatchers(HttpMethod.DELETE ,"/employee/getEmployee").hasAuthority(String.valueOf(Permissions.EMPLOYEE_DELETE))
                        .anyRequest().authenticated());
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService , PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(daoAuthenticationProvider);
    }
}
