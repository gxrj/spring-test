package com.teste.app.DTOs;

public class CredencialDTO {
    
    public String login;
    String senha;

    public CredencialDTO( String login, String senha ){
        this.login = login;
        this.senha = senha;
    }

    public String getSenha(){ return this.senha; }
}
