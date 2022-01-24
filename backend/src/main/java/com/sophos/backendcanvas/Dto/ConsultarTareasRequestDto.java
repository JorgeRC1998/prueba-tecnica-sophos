package com.sophos.backendcanvas.Dto;

import io.swagger.annotations.ApiModelProperty;

public class ConsultarTareasRequestDto {
    @ApiModelProperty(example = "1")
    private Integer idUsuario;
    @ApiModelProperty(example = "pendiente")
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
