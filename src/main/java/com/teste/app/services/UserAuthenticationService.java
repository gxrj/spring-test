package com.teste.app.services;

import java.util.Optional;

import com.teste.app.configs.UserSecurityConfigMapping;
import com.teste.app.entities.Usuario;
import com.teste.app.repositories.UsuarioRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserAuthenticationService implements UserDetailsService {
    

    private final UsuarioRepository repository;

    UserAuthenticationService( UsuarioRepository repository ){ this.repository = repository; }

    @Override
    public UserDetails loadUserByUsername( String login ) throws UsernameNotFoundException{

        Optional<Usuario> usuarioOptional = this.repository.findByLogin( login );

        if( usuarioOptional.isEmpty() ) 
            throw new UsernameNotFoundException( String.format( "Login %s nao encontrado", login ) );
        
        Usuario usuario = usuarioOptional.get();

        return new UserSecurityConfigMapping( usuario );
    }
}
