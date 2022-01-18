package com.sophos.backendcanvas.Adaptadores;

import java.util.HashMap;
import java.util.Map;

import com.sophos.backendcanvas.Dto.RespuestaGenericaDto;
import com.sophos.backendcanvas.Util.ConstantesApp;

import org.springframework.stereotype.Component;

@Component
public class GeneralAdapter {
    
    public RespuestaGenericaDto obtenerRespuestaExcepcion(Exception exp){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        respuestaGenericaDto.setStatus(ConstantesApp.STATUS_CODE_ERROR);

        Map<String, String> response = new HashMap<>();
        response.put(ConstantesApp.CODIGO, ConstantesApp.CODIGO_NOK_EXC);
        response.put(ConstantesApp.MENSAJE, ConstantesApp.MENSAJE_NOK_EXC);
        response.put(ConstantesApp.DESCRIPCION, exp.toString());

        respuestaGenericaDto.setData(response);

        return respuestaGenericaDto;
    }

}
