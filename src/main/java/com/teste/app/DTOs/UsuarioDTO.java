package com.teste.app.DTOs;

public class UsuarioDTO {

    public String login;
    public String nome;
    public String token;

    public UsuarioDTO( String login, String nome, String token ){
        this.login = login;
        this.nome = nome;
        this.token = token;
    }
}
