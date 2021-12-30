package com.teste.app.configs;

import com.teste.app.services.UserAuthenticationService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.userdateails.UserDetailsService;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig{
    
    @Bean
    public UserDetailService getSecurityUserBean(){ return new UserAuthenticationService(); }
    
    @Bean
    public PasswordEncoder getPasswordEncoder(){ return new BCryptPasswordEncoder(); }
}
