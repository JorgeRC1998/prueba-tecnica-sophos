package com.sophos.backendcanvas.Validadores;

import java.util.ArrayList;
import java.util.List;

import com.sophos.backendcanvas.Dto.LoginRequestDto;

import org.springframework.stereotype.Component;

@Component
public class AutenticacionRequestValidator {
    
    public List<String> validacionLoginRequest(LoginRequestDto loginRequestDto){
        List<String> errores = new ArrayList<>();

        if(loginRequestDto.getUsuario().trim().equals("")){
            errores.add("Campo usuario es requerido");
        }

        if(loginRequestDto.getUsuario().trim().length() > 100){
            errores.add("La longitud del campo usuario debe ser inferior a 100 caracteres");
        }

        if(loginRequestDto.getPassword().trim().equals("")){
            errores.add("Campo password es requerido");
        }

        if(loginRequestDto.getPassword().trim().length() > 100){
            errores.add("La longitud del campo password debe ser inferior a 100 caracteres");
        }

        return errores;
    }

}
