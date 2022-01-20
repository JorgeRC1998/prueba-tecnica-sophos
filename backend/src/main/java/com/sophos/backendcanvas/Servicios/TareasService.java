package com.sophos.backendcanvas.Servicios;

import java.util.List;

import com.sophos.backendcanvas.Adaptadores.GeneralAdapter;
import com.sophos.backendcanvas.Adaptadores.TareaAdapter;
import com.sophos.backendcanvas.Dao.TareasDao;
import com.sophos.backendcanvas.Dto.CrearTareaRequestDto;
import com.sophos.backendcanvas.Dto.RespuestaGenericaDto;
import com.sophos.backendcanvas.Validadores.TareaRequestValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TareasService {

    @Autowired
    TareasDao tareasDao;
    @Autowired
    TareaAdapter tareaAdapter;
    @Autowired
    TareaRequestValidator tareaRequestValidator;
    @Autowired
    GeneralAdapter generalAdapter;

    public RespuestaGenericaDto insertarNuevaTarea(CrearTareaRequestDto crearTareaRequestDto) {
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        try{
            // TODO Implementar validacion de token
            List<String> errores = tareaRequestValidator.validacionCrearTareaRequest(crearTareaRequestDto);
            if(errores.size() > 0){
                respuestaGenericaDto = tareaAdapter.obtenerValidacionTareaNOK(errores);
            }else{
                Integer idTarea = tareasDao.obtenerIdTarea();
                tareasDao.insertarTarea(idTarea, 
                                        crearTareaRequestDto.getDescripcion(), 
                                        crearTareaRequestDto.getEstado());
                respuestaGenericaDto = generalAdapter.obtenerRespuestaOk();
            }
        }catch(Exception e){
            // TODO loggear en archivo plano json con el detalle del error
            respuestaGenericaDto = generalAdapter.obtenerRespuestaExcepcion(e);
        }

        return respuestaGenericaDto;
    }
    
}
