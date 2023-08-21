package com.example.hostelscocora.exceptions;

import com.example.hostelscocora.model.Cliente;

public class ClienteException extends Exception{

    public ClienteException(String mensaje){
        super(mensaje);
    }
}
