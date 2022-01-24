package com.sophos.backendcanvas.Dto;

import io.swagger.annotations.ApiModelProperty;

public class CrearUsuarioRequestDto {
    @ApiModelProperty(example = "usuario1")
    private String nombre;
    @ApiModelProperty(example = "999999999")
    private String identificacion;
    @ApiModelProperty(example = "administrador")
    private String tipoUsuario;
    @ApiModelProperty(example = "user1")
    private String usuario;
    @ApiModelProperty(example = "pass1")
    private String password;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getIdentificacion() {
        return identificacion;
    }
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}
