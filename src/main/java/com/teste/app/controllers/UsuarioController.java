package com.teste.app.controllers;

import javax.ws.rs.core.MediaType;

import com.teste.app.DTOs.CadastroDTO;
import com.teste.app.DTOs.CredencialDTO;
import com.teste.app.DTOs.ResponseDTO;
import com.teste.app.DTOs.UsuarioDTO;
import com.teste.app.entities.Usuario;
import com.teste.app.repository.UsuarioRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController( UsuarioRepository usuarioRepository ) {
        this.usuarioRepository = usuarioRepository;
    }
    

    @PostMapping( value = "/cadastro", 
                  consumes = MediaType.APPLICATION_JSON,
                  produces = MediaType.APPLICATION_JSON )

    public ResponseDTO<String> register( @RequestBody CadastroDTO cadastro ){

        Usuario user = this.usuarioRepository.findByLogin( cadastro.login );

        if( user != null ) return new ResponseDTO<String>( "erro", "duplicidade" );
        
        user = new Usuario(cadastro.login, cadastro.nome, cadastro.getSenha() );
        this.usuarioRepository.save( user );
        /**Validar senhas */ 

        return new ResponseDTO<String>( "sucesso", "cadastrado com sucesso" );

        
    }


    @PostMapping( value = "/autenticacao",
                  consumes = MediaType.APPLICATION_JSON,
                  produces = MediaType.APPLICATION_JSON )
    
    public Object login( @RequestBody CredencialDTO credenciais ){
        Usuario user = this.usuarioRepository.findByLogin( credenciais.login );

        if( user != null && user.getSenha().equals( credenciais.getSenha() ) )
            return new UsuarioDTO( user.getLogin(), user.getNome(), user.getSenha() ) ;
        
        return new ResponseDTO<String>("erro", "login ou senha incorreto(s)" );
    }
}
