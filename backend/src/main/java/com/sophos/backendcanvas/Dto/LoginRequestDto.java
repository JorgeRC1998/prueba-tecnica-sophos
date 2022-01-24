package com.sophos.backendcanvas.Dto;

import io.swagger.annotations.ApiModelProperty;

public class LoginRequestDto {
    @ApiModelProperty(example = "usuario1")
    private String usuario;
    @ApiModelProperty(example = "usuario1")
    private String password;
    
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
