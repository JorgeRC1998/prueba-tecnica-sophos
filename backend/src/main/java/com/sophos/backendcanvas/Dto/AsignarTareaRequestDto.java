package com.sophos.backendcanvas.Dto;

import io.swagger.annotations.ApiModelProperty;

public class AsignarTareaRequestDto {
    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "1")
    private Integer idUsuario;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
