package com.teste.app.controllers;

import javax.ws.rs.core.MediaType;

import com.teste.app.DTOs.CadastroDTO;
import com.teste.app.DTOs.CredencialDTO;
import com.teste.app.DTOs.UsuarioDTO;
import com.teste.app.entities.Usuario;
import com.teste.app.repositories.UsuarioRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder; /** Injecao atraves do Bean getPasswordEncoder() declarado em AppSecurityConfig */

    public UsuarioController( UsuarioRepository usuarioRepository, PasswordEncoder encoder ) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
    }

    @PostMapping( value = "/cadastro", 
                  consumes = MediaType.APPLICATION_JSON,
                  produces = MediaType.APPLICATION_JSON )

    public String register( @RequestBody CadastroDTO cadastro ){

        Boolean vazio = this.usuarioRepository.findByLogin( cadastro.login ).isEmpty();

        if( !vazio ) return "erro, duplicidade" ;
        
        Usuario user = new Usuario( cadastro.login, cadastro.nome, this.encoder.encode( cadastro.getSenha() ) );

        this.usuarioRepository.save( user ) ;

        return "sucesso, cadastrado com sucesso" ;  
    }


    @PostMapping( value = "/autenticacao",
                  consumes = MediaType.APPLICATION_JSON,
                  produces = MediaType.APPLICATION_JSON )
    
    public Object login( @RequestBody CredencialDTO credenciais ){
        Usuario user = this.usuarioRepository.findByLogin( credenciais.login ).get() ;

        if( user != null && this.encoder.matches( credenciais.getSenha(), user.getSenha() ) )
            return new UsuarioDTO( user.getLogin(), user.getNome(), user.getSenha() ) ;
        
        return "erro, login ou senha incorreto(s)" ;
    }
}
