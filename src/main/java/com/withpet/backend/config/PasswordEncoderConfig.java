package com.withpet.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * PasswordEncoder Config
 */
@Configuration
public class PasswordEncoderConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    public String encode() {
        String password = "password";

        return passwordEncoder.encode(password);
    }
}
