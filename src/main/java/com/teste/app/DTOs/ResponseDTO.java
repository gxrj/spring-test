package com.teste.app.DTOs;

public class ResponseDTO {
    private String mensagem;
    private Object dados;

    public ResponseDTO( String mensagem, Object dados ){
        this.mensagem = mensagem;
        this.dados = dados;
    }
    public String getMensagem(){ return this.mensagem; }
    public Object getDados(){ return this.dados; }
}
