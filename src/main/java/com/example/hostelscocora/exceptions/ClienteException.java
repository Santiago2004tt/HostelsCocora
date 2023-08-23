package com.example.hostelscocora.exceptions;

public class ClienteException extends Exception{
    public ClienteException(String cliente_existe) {
        super(cliente_existe);
    }
}
