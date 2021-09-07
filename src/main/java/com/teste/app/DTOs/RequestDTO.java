package com.teste.app.DTOs;

import java.io.Serializable;

public class RequestDTO<Type> implements Serializable{

    Type dados;

    public RequestDTO( Type dados ){ this.dados = dados; }
    
    public Type getDados(){ return this.dados; }
}
