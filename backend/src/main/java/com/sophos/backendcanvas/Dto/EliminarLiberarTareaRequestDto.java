package com.sophos.backendcanvas.Dto;

import io.swagger.annotations.ApiModelProperty;

public class EliminarLiberarTareaRequestDto {
    @ApiModelProperty(example = "1")
    private Integer idTarea;

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

}
