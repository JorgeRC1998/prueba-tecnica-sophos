package com.sophos.backendcanvas.Dto;

import io.swagger.annotations.ApiModelProperty;

public class ConsultarUsuariosRequestDto {
    @ApiModelProperty(example = "usuario1")
    private String nombre;
    @ApiModelProperty(example = "administrador")
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
