package com.sophos.backendcanvas.Dto;

public class CrearTareaRequestDto {
    private String descripcion;
    private String estado;

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
