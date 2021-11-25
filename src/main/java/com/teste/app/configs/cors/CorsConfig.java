package com.teste.app.configs.cors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;



public class CorsConfig implements CorsConfigurationSource{

    @Override
    public CorsConfiguration getCorsConfiguration( HttpServletRequest request ){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins( List.of( "fiscaliza.com.br" ) );
        config.setAllowedMethods( List.of( "GET", "POST", "PUT", "DELETE" ) );
        return config;
    }
}
