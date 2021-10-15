package com.teste.app.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.app.entities.Usuario;

public interface UsuarioRepository extends JpaRepository< Usuario, UUID >{

    public List<Usuario> findByNome( String nome );
    public Optional<Usuario> findByLogin( String login );
}