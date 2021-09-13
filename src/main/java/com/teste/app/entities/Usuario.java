package com.teste.app.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity( name= "usuario" )
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

    public UUID getId() { return id; }
    public void setId( UUID id ) { this.id = id; }
    public String getLogin() { return login; }
    public void setLogin( String login ) { this.login = login; }
    public String getNome() { return nome; }
    public void setNome( String nome ) { this.nome = nome; }
    public String getSenha() { return senha; }
    public void setSenha( String senha ) { this.senha = senha; }
    
    @Override
    public String toString(){
        return String.format( "Usuario [ login = %s, nome = %s ]", login, nome );
    }
}
