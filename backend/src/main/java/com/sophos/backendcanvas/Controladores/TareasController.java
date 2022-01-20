package com.sophos.backendcanvas.Controladores;

import com.sophos.backendcanvas.Dto.CrearTareaRequestDto;
import com.sophos.backendcanvas.Dto.RespuestaGenericaDto;
import com.sophos.backendcanvas.Servicios.TareasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tareas")
public class TareasController {

    @Autowired
    TareasService tareasService;

    /**
     * Inserta una nueva tarea al sistema
     *
     * @param crearTareaRequestDto datos para guardar en el sistema
     * @return ResponseEntity resultado de la operacion
     * @author JorgeRojas
    */
    @PostMapping(path = "/insercion-tarea", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> guardarNuevoUsuario(@RequestBody CrearTareaRequestDto crearTareaRequestDto){
        RespuestaGenericaDto respuestaGenericaDto = tareasService.insertarNuevaTarea(crearTareaRequestDto);
        return new ResponseEntity<Object>(respuestaGenericaDto.getData(), HttpStatus.valueOf(respuestaGenericaDto.getStatus()));
    }
    
}
