package com.sophos.backendcanvas.Dto;

import io.swagger.annotations.ApiModelProperty;

public class ActualizarTareaRequestDto {
    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "Tarea 1")
    private String titulo;
    @ApiModelProperty(example = "Desceipcion tarea 1")
    private String descripcion;
    @ApiModelProperty(example = "pendiente")
    private String estado;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
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
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
