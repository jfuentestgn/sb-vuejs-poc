package com.example.comunidadesapi;

public class CreateComunidadRequest {

    String nombre;

    public CreateComunidadRequest() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreateComunidadRequest{");
        sb.append("nombre='").append(nombre).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
