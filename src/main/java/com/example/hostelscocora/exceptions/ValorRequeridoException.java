package com.example.hostelscocora.exceptions;

public class ValorRequeridoException extends Exception{

    public ValorRequeridoException(String valor_requerido){
        super(valor_requerido);
    }
}
