package com.sophos.backendcanvas.Validadores;

import java.util.ArrayList;
import java.util.List;

import com.sophos.backendcanvas.Dto.ActualizarTareaRequestDto;
import com.sophos.backendcanvas.Dto.AsignarTareaRequestDto;
import com.sophos.backendcanvas.Dto.ConsultarTareasRequestDto;
import com.sophos.backendcanvas.Dto.CrearTareaRequestDto;

import org.springframework.stereotype.Component;

@Component
public class TareaRequestValidator {

    public List<String> validacionCrearTareaRequest(CrearTareaRequestDto crearTareaRequestDto){
        List<String> errores = new ArrayList<>();

        if(crearTareaRequestDto.getTitulo().trim().equals("")){
            errores.add("Campo titulo es requerido");
        }

        if(crearTareaRequestDto.getTitulo().trim().length() > 50){
            errores.add("La longitud del campo descripcion debe ser inferior a 50 caracteres");
        }

        if(crearTareaRequestDto.getDescripcion().trim().equals("")){
            errores.add("Campo descripcion es requerido");
        }

        if(crearTareaRequestDto.getDescripcion().trim().length() > 500){
            errores.add("La longitud del campo descripcion debe ser inferior a 500 caracteres");
        }

        return errores;
    }

    public List<String> validacionActualizarUsuarioRequest(ActualizarTareaRequestDto actualizarTareaRequestDto){
        List<String> errores = new ArrayList<>();
        List<String> estadosTarea = new ArrayList<String>();

        estadosTarea.add("pendiente");
        estadosTarea.add("ejecutando");
        estadosTarea.add("finalizado");

        if(actualizarTareaRequestDto.getId() == null){
            errores.add("Campo id es requerido");
        }

        if(actualizarTareaRequestDto.getTitulo().trim().equals("")){
            errores.add("Campo titulo es requerido");
        }

        if(actualizarTareaRequestDto.getTitulo().trim().length() > 50){
            errores.add("La longitud del campo titulo debe ser inferior a 50 caracteres");
        }

        if(actualizarTareaRequestDto.getDescripcion().trim().equals("")){
            errores.add("Campo descripcion es requerido");
        }

        if(actualizarTareaRequestDto.getDescripcion().trim().length() > 500){
            errores.add("La longitud del campo descripcion debe ser inferior a 500 caracteres");
        }

        if(actualizarTareaRequestDto.getEstado().trim().equals("")){
            errores.add("Campo estado es requerido");
        }

        if(actualizarTareaRequestDto.getEstado().trim().length() > 15){
            errores.add("La longitud del campo estado debe ser inferior a 15 caracteres");
        }
        
        if(!estadosTarea.contains(actualizarTareaRequestDto.getEstado().trim().toLowerCase())){
            errores.add("El estado ingresado no corresponde a pendiente, ejecutando, finalizado");
        }

        return errores;
    }

    public List<String> validacionAsignarTareaRequest(AsignarTareaRequestDto asignarTareaRequestDto){
        List<String> errores = new ArrayList<>();

        if(asignarTareaRequestDto.getId() == null){
            errores.add("Campo id es requerido");
        }

        if(asignarTareaRequestDto.getIdUsuario() == null){
            errores.add("Campo idUsuario es requerido");
        }

        return errores;
    }

    public List<String> validacionLiberarEliminarTareaRequest(Integer idTarea){
        List<String> errores = new ArrayList<>();

        if(idTarea == null){
            errores.add("Parametro en la url idTarea  es requerido");
        }

        return errores;
    }

    public List<String> validacionConsultaTareasRequest(ConsultarTareasRequestDto consultarTareasRequestDto){
        List<String> errores = new ArrayList<>();
        List<String> estadosTarea = new ArrayList<String>();

        estadosTarea.add("pendiente");
        estadosTarea.add("ejecutando");
        estadosTarea.add("finalizado");

        if(consultarTareasRequestDto.getEstado().trim().length() > 15){
            errores.add("La longitud del campo estado debe ser inferior a 15 caracteres");
        }

        if(!estadosTarea.contains(consultarTareasRequestDto.getEstado().trim().toLowerCase())){
            errores.add("El estado ingresado no corresponde a pendiente, ejecutando, finalizado");
        }

        return errores;
    }

}
