package com.sophos.backendcanvas.Adaptadores;

import java.util.HashMap;
import java.util.Map;

import com.sophos.backendcanvas.Dto.RespuestaGenericaDto;
import com.sophos.backendcanvas.Util.ConstantesApp;

import org.springframework.stereotype.Component;

@Component
public class AutenticacionAdapter {
    
    public RespuestaGenericaDto obtenerLoginOk(String token){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        Map<String, Object> respuesta = new HashMap<>();

        respuestaGenericaDto.setStatus(ConstantesApp.STATUS_CODE_OK);

        respuesta.put(ConstantesApp.CODIGO, ConstantesApp.CODIGO_OK);
        respuesta.put(ConstantesApp.MENSAJE, ConstantesApp.MENSAJE_OK);
        respuesta.put(ConstantesApp.TOKEN, token);
        
        respuestaGenericaDto.setData(respuesta);

        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto obtenerLoginNok(String mensaje){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        Map<String, Object> respuesta = new HashMap<>();

        respuestaGenericaDto.setStatus(ConstantesApp.STATUS_CODE_NOK);

        respuesta.put(ConstantesApp.CODIGO, ConstantesApp.CODIGO_NOK);
        respuesta.put(ConstantesApp.MENSAJE, ConstantesApp.MENSAJE_NOK);
        respuesta.put(ConstantesApp.DESCRIPCION, mensaje);
        
        respuestaGenericaDto.setData(respuesta);

        return respuestaGenericaDto;
    }

}
