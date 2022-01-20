package com.sophos.backendcanvas.Validadores;

import java.util.ArrayList;
import java.util.List;

import com.sophos.backendcanvas.Dto.CrearTareaRequestDto;

import org.springframework.stereotype.Component;

@Component
public class TareaRequestValidator {
    public List<String> validacionCrearTareaRequest(CrearTareaRequestDto crearTareaRequestDto){
        List<String> errores = new ArrayList<>();

        if(crearTareaRequestDto.getDescripcion().trim().equals("")){
            errores.add("Campo descripcion es requerido");
        }

        if(crearTareaRequestDto.getDescripcion().trim().length() > 100){
            errores.add("La longitud del campo descripcion debe ser inferior a 100 caracteres");
        }

        if(crearTareaRequestDto.getEstado().trim().equals("")){
            errores.add("Campo estado es requerido");
        }

        if(crearTareaRequestDto.getEstado().trim().length() > 20){
            errores.add("La longitud del campo estado debe ser inferior a 20 caracteres");
        }

        return errores;
    }

}
