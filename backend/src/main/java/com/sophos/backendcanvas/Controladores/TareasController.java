package com.sophos.backendcanvas.Controladores;

import com.sophos.backendcanvas.Dto.ActualizarTareaRequestDto;
import com.sophos.backendcanvas.Dto.AsignarTareaRequestDto;
import com.sophos.backendcanvas.Dto.ConsultarTareasRequestDto;
import com.sophos.backendcanvas.Dto.CrearTareaRequestDto;
import com.sophos.backendcanvas.Dto.RespuestaGenericaDto;
import com.sophos.backendcanvas.Servicios.TareasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<Object> guardarNuevaTarea(@RequestBody CrearTareaRequestDto crearTareaRequestDto){
        RespuestaGenericaDto respuestaGenericaDto = tareasService.insertarNuevaTarea(crearTareaRequestDto);
        return new ResponseEntity<Object>(respuestaGenericaDto.getData(), HttpStatus.valueOf(respuestaGenericaDto.getStatus()));
    }

    /**
     * Realiza la consulta de las tareas segun usuario y estado en el sistema
     *
     * @param ConsultarTareasRequestDto parametros para realizar la consulta
     * @return ResponseEntity Listado con las tareas que cumplen el filtro
     * @author JorgeRojas
     */
    @PostMapping(path = "/consulta-tareas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> obtenerTareas(@RequestBody ConsultarTareasRequestDto consultarTareasRequestDto){
        RespuestaGenericaDto respuestaGenericaDto = tareasService.obtenerTareas(consultarTareasRequestDto);
        return new ResponseEntity<Object>(respuestaGenericaDto.getData(), HttpStatus.valueOf(respuestaGenericaDto.getStatus()));
    }

    /**
     * Asigna una tarea a un usuario especifico
     *
     * @param actualizarTareaRequestDto Parámetros para realizar la actualización de una tarea
     * @return ResponseEntity Resultado de la operación
     * @author JorgeRojas
     */
    @PutMapping(path = "/actualizacion-tarea", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> actualizarTarea(@RequestBody ActualizarTareaRequestDto actualizarTareaRequestDto){
        RespuestaGenericaDto respuestaGenericaDto = tareasService.actualizarTarea(actualizarTareaRequestDto);
        return new ResponseEntity<Object>(respuestaGenericaDto.getData(), HttpStatus.valueOf(respuestaGenericaDto.getStatus()));
    }

    /**
     * Asigna una tarea a un usuario especifico en el sistema
     *
     * @param asignarTareaRequestDto datos para asignar la tareaa
     * @return ResponseEntity Resultado de la operación
     * @author JorgeRojas
     */
    @PutMapping(path = "/asignacion-tarea", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> asignarTarea(@RequestBody AsignarTareaRequestDto asignarTareaRequestDto){
        RespuestaGenericaDto respuestaGenericaDto = tareasService.asignarTarea(asignarTareaRequestDto);
        return new ResponseEntity<Object>(respuestaGenericaDto.getData(), HttpStatus.valueOf(respuestaGenericaDto.getStatus()));
    }

    /**
     * Libera una tarea en el sistema
     *
     * @param idTarea id para liberar la tarea
     * @return ResponseEntity Resultado de la operación
     * @author JorgeRojas
     */
    @PutMapping(path = "/liberacion-tarea/{idTarea}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> liberarTarea(@PathVariable("idTarea") Integer idTarea){
        RespuestaGenericaDto respuestaGenericaDto = tareasService.liberarTarea(idTarea);
        return new ResponseEntity<Object>(respuestaGenericaDto.getData(), HttpStatus.valueOf(respuestaGenericaDto.getStatus()));
    }

    /**
     * Elimina una tarea
     *
     * @param idTarea Id de la tarea a eliminar
     * @return ResponseEntity Resultado de la operación
     * @author JorgeRojas
     */
    @DeleteMapping(path = "/eliminado-tarea/{idTarea}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> eliminarTarea(@PathVariable("idTarea") Integer idTarea){
        RespuestaGenericaDto respuestaGenericaDto = tareasService.eliminarTarea(idTarea);
        return new ResponseEntity<Object>(respuestaGenericaDto.getData(), HttpStatus.valueOf(respuestaGenericaDto.getStatus()));
    }

}
