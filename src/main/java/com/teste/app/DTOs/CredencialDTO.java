package com.teste.app.DTOs;

public class CredencialDTO {
    private String login, senha;

    public CredencialDTO( String login, String senha){
        this.login = login;
        this.senha = senha;
    }
    
    public String getLogin(){ return this.login; }
    public String getSenha(){ return this.senha; }
}
