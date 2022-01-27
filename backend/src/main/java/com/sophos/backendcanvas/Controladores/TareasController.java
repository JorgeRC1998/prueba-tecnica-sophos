package com.sophos.backendcanvas.Controladores;

import java.util.ArrayList;

import com.sophos.backendcanvas.Dto.ActualizarTareaRequestDto;
import com.sophos.backendcanvas.Dto.AsignarTareaRequestDto;
import com.sophos.backendcanvas.Dto.ConsultarTareasRequestDto;
import com.sophos.backendcanvas.Dto.CrearTareaRequestDto;
import com.sophos.backendcanvas.Dto.RespuestaGenericaDto;
import com.sophos.backendcanvas.Servicios.TareasService;
import com.sophos.backendcanvas.Util.ConstantesSwagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.Authorization;

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
    @ApiOperation(value = "Guardar tarea", response = ArrayList.class, notes = "Guarda una nueva tarea en el sistema", authorizations = {
        @Authorization(value = "JWT") })
    @ApiResponses(value = { @ApiResponse(code = 200, message = ConstantesSwagger.OPERACION_OK), @ApiResponse(code = 400, message = ConstantesSwagger.GUARDAR_TAREA_NOK) })
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
    @ApiOperation(value = "Consultar tarea", response = ArrayList.class, notes = "Consulta las tareas en el sistema de acuerdo al filtro", authorizations = {
        @Authorization(value = "JWT") })
    @ApiResponses(value = { @ApiResponse(code = 200, message = ConstantesSwagger.CONSULTA_TAREA_OK), @ApiResponse(code = 400, message = ConstantesSwagger.CONSULTA_TAREA_NOK) })
    @PostMapping(path = "/consulta-tareas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> obtenerTareas(@RequestBody ConsultarTareasRequestDto consultarTareasRequestDto){
        RespuestaGenericaDto respuestaGenericaDto = tareasService.obtenerTareas(consultarTareasRequestDto);
        return new ResponseEntity<Object>(respuestaGenericaDto.getData(), HttpStatus.valueOf(respuestaGenericaDto.getStatus()));
    }

    /**
     * Realiza la consulta de las tareas segun usuario y estado en el sistema
     *
     * @param ConsultarTareasRequestDto parametros para realizar la consulta
     * @return ResponseEntity Listado con las tareas que cumplen el filtro
     * @author JorgeRojas
     */
    @ApiOperation(value = "Consultar tareas sin asignar", response = ArrayList.class, notes = "Consulta las tareas en el sistema que no tienen asignado un usuario", authorizations = {
        @Authorization(value = "JWT") })
    @ApiResponses(value = { @ApiResponse(code = 200, message = ConstantesSwagger.CONSULTA_TAREA_OK), @ApiResponse(code = 400, message = ConstantesSwagger.CONSULTA_TAREA_NOK) })
    @GetMapping(path = "/consulta-tareas-noasignadas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> obtenerTareasSinAsignar(){
        RespuestaGenericaDto respuestaGenericaDto = tareasService.obtenerTareasSinAsignar();
        return new ResponseEntity<Object>(respuestaGenericaDto.getData(), HttpStatus.valueOf(respuestaGenericaDto.getStatus()));
    }

    /**
     * Actualiza una tarea
     *
     * @param actualizarTareaRequestDto Parámetros para realizar la actualización de una tarea
     * @return ResponseEntity Resultado de la operación
     * @author JorgeRojas
     */
    @ApiOperation(value = "Actualizar tarea", response = ArrayList.class, notes = "Realiza la actualizacion de una tarea en el sistema", authorizations = {
        @Authorization(value = "JWT") })
    @ApiResponses(value = { @ApiResponse(code = 200, message = ConstantesSwagger.OPERACION_OK), @ApiResponse(code = 400, message = ConstantesSwagger.ACTUALIZAR_TAREA_NOK) })
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
    @ApiOperation(value = "Asignar tarea", response = ArrayList.class, notes = "Asigna una tarea a un usuario especifico", authorizations = {
        @Authorization(value = "JWT") })
    @ApiResponses(value = { @ApiResponse(code = 200, message = ConstantesSwagger.OPERACION_OK), @ApiResponse(code = 400, message = ConstantesSwagger.ASIGNAR_TAREA_NOK) })
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
    @ApiOperation(value = "Liberar tarea", response = ArrayList.class, notes = "Libera una tarea para que otros usuarios la puedan tomar", authorizations = {
        @Authorization(value = "JWT") })
    @ApiResponses(value = { @ApiResponse(code = 200, message = ConstantesSwagger.OPERACION_OK), @ApiResponse(code = 400, message = ConstantesSwagger.LIBERAR_TAREA_NOK) })
    @PutMapping(path = "/liberacion-tarea/{idTarea}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> liberarTarea(@ApiParam(example = "1") @PathVariable("idTarea") Integer idTarea){
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
    @ApiOperation(value = "Eliminar tarea", response = ArrayList.class, notes = "Elimina una tarea del sistema", authorizations = {
        @Authorization(value = "JWT") })
    @ApiResponses(value = { @ApiResponse(code = 200, message = ConstantesSwagger.OPERACION_OK), @ApiResponse(code = 400, message = ConstantesSwagger.ELIMINAR_TAREA_NOK) })
    @DeleteMapping(path = "/eliminado-tarea/{idTarea}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> eliminarTarea(@ApiParam(example = "1") @PathVariable("idTarea") Integer idTarea){
        RespuestaGenericaDto respuestaGenericaDto = tareasService.eliminarTarea(idTarea);
        return new ResponseEntity<Object>(respuestaGenericaDto.getData(), HttpStatus.valueOf(respuestaGenericaDto.getStatus()));
    }

}
