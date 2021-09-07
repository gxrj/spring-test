package com.teste.app.DTOs;

import java.io.Serializable;

public class ResponseDTO<Type> implements Serializable{

    public String mensagem;
    public Type dados;

    public ResponseDTO( String mensagem, Type dados ){
        this.mensagem = mensagem;
        this.dados = dados;
    }
}
