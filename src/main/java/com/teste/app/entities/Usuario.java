package com.teste.app.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    @Column( name = "login", unique = true, nullable = false )
    private String login;

    @Column( name = "nome", nullable = false )
    private String nome;

    @Column( name = "senha", nullable = false )
    private String senha;


    protected Usuario() { }

    public Usuario( String login, String nome, String senha ) {
        this.login = login ;
        this.nome = nome ;
        this.senha = senha ;
    }

    public void setSenha( String senha){ this.senha = senha; }
    @Override
    public String toString(){
        return String.format( "Usuario [ login = %s, nome = %s ]", login, nome );
    }
}
