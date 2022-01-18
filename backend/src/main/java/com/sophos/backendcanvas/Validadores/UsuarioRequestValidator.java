package com.sophos.backendcanvas.Validadores;

import java.util.ArrayList;
import java.util.List;

import com.sophos.backendcanvas.Dto.UsuarioRequestDto;

import org.springframework.stereotype.Component;

@Component
public class UsuarioRequestValidator {
    
    public List<String> validacionUsuarioRequest(UsuarioRequestDto usuarioRequestDto){
        List<String> errores = new ArrayList<>();

        if(usuarioRequestDto.getNombre().trim().equals("")){
            errores.add("Campo nombre es requerido");
        }

        if(usuarioRequestDto.getNombre().trim().length() > 25){
            errores.add("La longitud del campo nombre debe ser inferior a 25 caracteres");
        }

        if(usuarioRequestDto.getIdentificacion().trim().equals("")){
            errores.add("Campo identificacion es requerido");
        }

        if(usuarioRequestDto.getIdentificacion().trim().length() > 15){
            errores.add("La longitud del campo identificacion debe ser inferior a 15 caracteres");
        }

        if(usuarioRequestDto.getTipoUsuario().trim().equals("")){
            errores.add("Campo tipoUsuario es requerido");
        }

        if(usuarioRequestDto.getTipoUsuario().trim().length() > 15){
            errores.add("La longitud del campo tipoUsuario debe ser inferior a 15 caracteres");
        }

        if(usuarioRequestDto.getUsuario().trim().equals("")){
            errores.add("Campo usuario es requerido");
        }

        if(usuarioRequestDto.getUsuario().trim().length() > 15){
            errores.add("La longitud del campo usuario debe ser inferior a 15 caracteres");
        }
    
        if(usuarioRequestDto.getPassword().trim().equals("")){
            errores.add("Campo password es requerido");
        }

        if(usuarioRequestDto.getUsuario().trim().length() > 15){
            errores.add("La longitud del password nombre debe ser inferior a 15 caracteres");
        }

        return errores;
    }

}
