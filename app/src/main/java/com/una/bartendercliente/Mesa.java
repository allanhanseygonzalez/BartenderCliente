package com.una.bartendercliente;

public class Mesa {
    String key;
    Integer numero;
    Boolean estado;

    public Mesa(){

    }

    public Mesa(String key,Integer numero, Boolean estado) {
        this.key = key;
        this.numero = numero;
        this.estado = estado;
    }

    public String getKey(){ return key; }

    public Integer getNumero() {
        return numero;
    }

    public Boolean getEstado() {
        return estado;
    }
}
