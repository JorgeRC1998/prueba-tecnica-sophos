package com.sophos.backendcanvas.Dto;

public class ConsultarUsuariosRequestDto {
    private String nombre;
    private String tipoUsuario;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
}
