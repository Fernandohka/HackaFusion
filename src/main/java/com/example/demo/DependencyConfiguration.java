package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.dto.Token;
import com.example.demo.services.AuthService;
import com.example.demo.services.ImageStorageService;
import com.example.demo.services.JWTService;
import com.example.demo.services.UserService;
import com.example.demo.services.impl.AuthImpl;
import com.example.demo.services.impl.BcryptImpl;
import com.example.demo.services.impl.ImageStorageImplementation;
import com.example.demo.services.impl.JwtImplementation;
import com.example.demo.services.impl.UserImpl;

@Configuration
public class DependencyConfiguration {
    // @Bean
    // public service service(){
    //     return new implementation();
    // }

    @Bean
    public UserService userService(){
        return new UserImpl();
    }

    @Bean
    public AuthService authService(){
        return new AuthImpl();
    }

    @Bean
    public ImageStorageService imageStorageService(){
        return new ImageStorageImplementation();
    }

    @Bean
    public JWTService<Token> jwtService(){
        return new JwtImplementation();
    }

    @Bean
    public BcryptImpl bcryptImpl(){
        return new BcryptImpl();
    }

}
