package com.sophos.backendcanvas.Dto;

import io.swagger.annotations.ApiModelProperty;

public class CrearTareaRequestDto {
    @ApiModelProperty(example = "tarea1")
    private String titulo;
    @ApiModelProperty(example = "descripcion tarea 1")
    private String descripcion;

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
