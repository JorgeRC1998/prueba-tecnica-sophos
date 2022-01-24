package com.sophos.backendcanvas.Adaptadores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sophos.backendcanvas.Dto.RespuestaGenericaDto;
import com.sophos.backendcanvas.Entidades.TareasEntity;
import com.sophos.backendcanvas.Util.ConstantesApp;

import org.springframework.stereotype.Component;

@Component
public class TareasAdapter {
    
    public RespuestaGenericaDto obtenerConsultaTareaOk(List<TareasEntity> tareas){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        Map<String, Object> respuesta = new HashMap<>();

        respuestaGenericaDto.setStatus(ConstantesApp.STATUS_CODE_OK);

        respuesta.put(ConstantesApp.CODIGO, ConstantesApp.CODIGO_OK);
        respuesta.put(ConstantesApp.MENSAJE, ConstantesApp.MENSAJE_OK);
        respuesta.put(ConstantesApp.TAREAS, tareas);
        
        respuestaGenericaDto.setData(respuesta);

        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto obtenerActTarNoExiste(String tarea){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        Map<String, Object> respuesta = new HashMap<>();

        respuestaGenericaDto.setStatus(ConstantesApp.STATUS_CODE_NOK);

        respuesta.put(ConstantesApp.CODIGO, ConstantesApp.CODIGO_NOK);
        respuesta.put(ConstantesApp.MENSAJE, ConstantesApp.MENSAJE_NOK);
        respuesta.put(ConstantesApp.DESCRIPCION, "Tarea con titulo o Id: " + tarea + " no se encuentra registrada en el sistema");
        
        respuestaGenericaDto.setData(respuesta);

        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto obtenerNoElimTarea(TareasEntity tareasEntity){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        Map<String, Object> respuesta = new HashMap<>();

        respuestaGenericaDto.setStatus(ConstantesApp.STATUS_CODE_NOK);

        respuesta.put(ConstantesApp.CODIGO, ConstantesApp.CODIGO_NOK);
        respuesta.put(ConstantesApp.MENSAJE, ConstantesApp.MENSAJE_NOK);
        respuesta.put(ConstantesApp.DESCRIPCION, "No puede eliminar la tarea: " + tareasEntity.getTitulo() + " debido a que el usuario: " + tareasEntity.getIdUsuario() + " ya la tiene asignada.");
        
        respuestaGenericaDto.setData(respuesta);

        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto obtenerNoLiberarTarea(Integer idTarea){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        Map<String, Object> respuesta = new HashMap<>();

        respuestaGenericaDto.setStatus(ConstantesApp.STATUS_CODE_NOK);

        respuesta.put(ConstantesApp.CODIGO, ConstantesApp.CODIGO_NOK);
        respuesta.put(ConstantesApp.MENSAJE, ConstantesApp.MENSAJE_NOK);
        respuesta.put(ConstantesApp.DESCRIPCION, "No puede liberar la tarea: " + idTarea + " ya que actualmente no se encuentra asignada a ningun usuario");
        
        respuestaGenericaDto.setData(respuesta);

        return respuestaGenericaDto;
    }

}
