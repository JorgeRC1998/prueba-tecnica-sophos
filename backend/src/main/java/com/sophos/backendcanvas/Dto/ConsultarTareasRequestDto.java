package com.sophos.backendcanvas.Dto;

public class ConsultarTareasRequestDto {
    private Integer idUsuario;
    private String estado;
    
    public Integer getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
