package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.dto.Token;
import com.example.demo.services.JWTService;
import com.example.demo.services.impl.BcryptImpl;
import com.example.demo.services.impl.DefaultJWTService;

@Configuration
public class DependencyConfiguration {
    // @Bean
    // public service service(){
    //     return new implementation();
    // }

    @Bean
    public BcryptImpl bcryptImpl(){
        return new BcryptImpl();
    }

    @Bean 
    public JWTService<Token> jwtService(){
        return new DefaultJWTService();
    }

}
