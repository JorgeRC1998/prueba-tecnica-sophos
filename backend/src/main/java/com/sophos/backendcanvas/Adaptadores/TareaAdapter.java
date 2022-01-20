package com.sophos.backendcanvas.Adaptadores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sophos.backendcanvas.Dto.RespuestaGenericaDto;
import com.sophos.backendcanvas.Util.ConstantesApp;

import org.springframework.stereotype.Component;

@Component
public class TareaAdapter {
    
    public RespuestaGenericaDto obtenerValidacionTareaNOK(List<String> errores){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        Map<String, Object> respuesta = new HashMap<>();

        respuestaGenericaDto.setStatus(400);

        respuesta.put(ConstantesApp.CODIGO, ConstantesApp.CODIGO_NOK);
        respuesta.put(ConstantesApp.MENSAJE, ConstantesApp.MENSAJE_NOK);
        respuesta.put(ConstantesApp.ERRORES, errores);
        
        respuestaGenericaDto.setData(respuesta);

        return respuestaGenericaDto;
    }
    
}
