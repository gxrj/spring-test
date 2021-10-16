package com.teste.app.controllers;

import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {
    
    @PostMapping( value = "/teste", 
                  consumes = MediaType.APPLICATION_JSON,
                  produces = MediaType.TEXT_PLAIN )
    public String teste(){
        return "Autenticado";
    }
}
