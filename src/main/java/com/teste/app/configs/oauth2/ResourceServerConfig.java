package com.teste.app.configs.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

public class ResourceServerConfig {
 
    /**
     * Estabelece quais rotas são permitidas 
     * sem autorização e quais são proibídas 
     * sem autorização
    */
    @Bean
    SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception{
        http
        .authorizeRequests( 
            a -> a.antMatchers( "/login", "/cadastro" )
                        .permitAll()
                  .anyRequest().authenticated() )
        .exceptionHandling( 
            e -> e.authenticationEntryPoint( 
                new HttpStatusEntryPoint( HttpStatus.UNAUTHORIZED ) ) )
        .oauth2ResourceServer().jwt();

        return http.build();
    }
}