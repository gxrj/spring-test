package com.teste.app.DTOs;

public class UsuarioDTO {
    
    private String login, nome, token;

    public UsuarioDTO( String login, String nome, String token ){
        this.login = login;
        this.nome = nome;
        this.token = token;
    }

    public String getLogin(){ return this.login; }
    public String getNome(){ return this.nome; }
    public String getToken(){ return this.token; }
}
