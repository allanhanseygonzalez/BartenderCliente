package com.una.bartendercliente;

public class Mesa {
    Integer numero;
    Boolean estado;

    public Mesa(){

    }

    public Mesa(Integer numero, Boolean estado) {
        this.numero = numero;
        this.estado = estado;
    }

    public Integer getNumero() {
        return numero;
    }

    public Boolean getEstado() {
        return estado;
    }
}
