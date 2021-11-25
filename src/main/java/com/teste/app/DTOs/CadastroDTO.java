package com.teste.app.DTOs;

import java.io.Serializable;

public class CadastroDTO implements Serializable{
    
    public String login;
    public String nome;
    String senha;

    public CadastroDTO( String login, String nome, String senha ){
        this.login = login;
        this.nome = nome;
        this.senha = senha;
    }

    public String getSenha(){ return this.senha; }

}
