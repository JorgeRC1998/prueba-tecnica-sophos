package com.sophos.backendcanvas.Adaptadores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sophos.backendcanvas.Dto.RespuestaGenericaDto;
import com.sophos.backendcanvas.Entidades.UsuariosEntity;
import com.sophos.backendcanvas.Util.ConstantesApp;

import org.springframework.stereotype.Component;

@Component
public class UsuariosAdapter {
    
    public RespuestaGenericaDto obtenerUsuarioExisteResponse(String usuario){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        Map<String, Object> respuesta = new HashMap<>();

        respuestaGenericaDto.setStatus(ConstantesApp.STATUS_CODE_NOK);

        respuesta.put(ConstantesApp.CODIGO, ConstantesApp.CODIGO_NOK);
        respuesta.put(ConstantesApp.MENSAJE, ConstantesApp.MENSAJE_NOK);
        respuesta.put(ConstantesApp.DESCRIPCION, "Usuario: " + usuario + " ya está registrado en el sistema");
        
        respuestaGenericaDto.setData(respuesta);

        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto obtenerValidacionUsuarioNOK(List<String> errores){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        Map<String, Object> respuesta = new HashMap<>();

        respuestaGenericaDto.setStatus(400);

        respuesta.put(ConstantesApp.CODIGO, ConstantesApp.CODIGO_NOK);
        respuesta.put(ConstantesApp.MENSAJE, ConstantesApp.MENSAJE_NOK);
        respuesta.put(ConstantesApp.ERRORES, errores);
        
        respuestaGenericaDto.setData(respuesta);

        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto obtenerConsultaUsuarioOk(List<UsuariosEntity> usuarios){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        Map<String, Object> respuesta = new HashMap<>();

        respuestaGenericaDto.setStatus(ConstantesApp.STATUS_CODE_OK);

        respuesta.put(ConstantesApp.CODIGO, ConstantesApp.CODIGO_OK);
        respuesta.put(ConstantesApp.MENSAJE, ConstantesApp.MENSAJE_OK);
        respuesta.put(ConstantesApp.USUARIOS, usuarios);
        
        respuestaGenericaDto.setData(respuesta);

        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto obtenerActUsuNoExiste(String usuario){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        Map<String, Object> respuesta = new HashMap<>();

        respuestaGenericaDto.setStatus(ConstantesApp.STATUS_CODE_NOK);

        respuesta.put(ConstantesApp.CODIGO, ConstantesApp.CODIGO_NOK);
        respuesta.put(ConstantesApp.MENSAJE, ConstantesApp.MENSAJE_NOK);
        respuesta.put(ConstantesApp.DESCRIPCION, "Usuario con Id: " + usuario + " no se encuentra registrado en el sistema");
        
        respuestaGenericaDto.setData(respuesta);

        return respuestaGenericaDto;
    }

}
