package com.teste.app.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.app.entities.Usuario;

public interface UsuarioRepository extends JpaRepository< Usuario, UUID >{

    List<Usuario> findByNome(String nome);
    Usuario findByLogin(String login);
}
