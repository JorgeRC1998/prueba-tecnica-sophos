package com.sophos.backendcanvas.Adaptadores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sophos.backendcanvas.Dto.RespuestaGenericaDto;
import com.sophos.backendcanvas.Util.ConstantesApp;
import com.sophos.backendcanvas.Util.ErrorLog;

import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class GeneralAdapter {

    private static final String USER = "usuario";
    Logger logger = LoggerFactory.getLogger("exception");
    
    // public RespuestaGenericaDto obtenerRespuestaExcepcion(Exception exp){
    //     RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

    //     respuestaGenericaDto.setStatus(ConstantesApp.STATUS_CODE_ERROR);

    //     Map<String, String> response = new HashMap<>();
    //     response.put(ConstantesApp.CODIGO, ConstantesApp.CODIGO_NOK_EXC);
    //     response.put(ConstantesApp.MENSAJE, ConstantesApp.MENSAJE_NOK_EXC);
    //     response.put(ConstantesApp.DESCRIPCION, exp.toString());

    //     respuestaGenericaDto.setData(response);

    //     return respuestaGenericaDto;
    // }

    public RespuestaGenericaDto obtenerRespuestaExcepcionGenerica(String mensaje){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        respuestaGenericaDto.setStatus(ConstantesApp.STATUS_CODE_ERROR);

        Map<String, String> response = new HashMap<>();
        response.put(ConstantesApp.CODIGO, ConstantesApp.CODIGO_NOK_EXC);
        response.put(ConstantesApp.MENSAJE, ConstantesApp.MENSAJE_NOK_EXC);
        response.put(ConstantesApp.DESCRIPCION, mensaje);

        respuestaGenericaDto.setData(response);

        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto obtenerRespuestaOk(){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        Map<String, Object> respuesta = new HashMap<>();

        respuestaGenericaDto.setStatus(ConstantesApp.STATUS_CODE_OK);

        respuesta.put(ConstantesApp.CODIGO, ConstantesApp.CODIGO_OK);
        respuesta.put(ConstantesApp.MENSAJE, ConstantesApp.MENSAJE_OK);
        respuesta.put(ConstantesApp.DESCRIPCION, ConstantesApp.OPERACION_OK);
        
        respuestaGenericaDto.setData(respuesta);

        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto obtenerValidacionRequestNOK(List<String> errores){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        Map<String, Object> respuesta = new HashMap<>();

        respuestaGenericaDto.setStatus(400);

        respuesta.put(ConstantesApp.CODIGO, ConstantesApp.CODIGO_NOK);
        respuesta.put(ConstantesApp.MENSAJE, ConstantesApp.MENSAJE_NOK);
        respuesta.put(ConstantesApp.ERRORES, errores);
        
        respuestaGenericaDto.setData(respuesta);

        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto getRespuestaExcepcion(String exception, HttpServletRequest httpServletRequest, String className) {
        ObjectMapper mapper = new ObjectMapper();
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();
        ErrorLog errorLog = new ErrorLog((String) httpServletRequest.getSession().getAttribute(USER), className, exception);
        respuestaGenericaDto = obtenerRespuestaExcepcionGenerica(ConstantesApp.MENSAJE_ERROR_INTERNO + errorLog.getUUID());
        try {
            logger.error(mapper.writeValueAsString(errorLog));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return respuestaGenericaDto;
    }

}
