package com.teste.app.controllers;

import javax.ws.rs.core.MediaType;

import com.teste.app.DTOs.CredencialDTO;
import com.teste.app.DTOs.ResponseDTO;
import com.teste.app.DTOs.UsuarioDTO;
import com.teste.app.entities.Usuario;
import com.teste.app.repository.UsuarioRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController( UsuarioRepository usuarioRepository ) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping( value = "/autenticacao", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON )
    @ResponseBody
    public UsuarioDTO login( @RequestBody CredencialDTO credencial ){
        Usuario user = this.usuarioRepository.findByLogin( credencial.getLogin() );
        /*Validar*/
        return new UsuarioDTO( user.getLogin(), user.getNome(), user.generateToken() );
    }

    @PostMapping( value = "/cadastro", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON )
    @ResponseBody
    public ResponseDTO register( @RequestBody Usuario user ){
        /*Verificar duplicidade*/
        this.usuarioRepository.save( user );
        return new ResponseDTO( "successo", null );
    }
    
}
