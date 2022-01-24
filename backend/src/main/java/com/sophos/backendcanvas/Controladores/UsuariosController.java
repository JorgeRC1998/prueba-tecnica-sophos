package com.sophos.backendcanvas.Controladores;

import com.sophos.backendcanvas.Dto.RespuestaGenericaDto;

import java.util.ArrayList;

import com.sophos.backendcanvas.Dto.ActualizarUsuarioRequestDto;
import com.sophos.backendcanvas.Dto.ConsultarUsuariosRequestDto;
import com.sophos.backendcanvas.Dto.CrearUsuarioRequestDto;
import com.sophos.backendcanvas.Servicios.UsuariosService;
import com.sophos.backendcanvas.Util.ConstantesSwagger;

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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    UsuariosService usuariosService;
    
    /**
     * Inserta un nuevo usuario al sistema de tareas
     *
     * @param crearUsuarioRequestDto datos para guardar en el sistema
     * @return ResponseEntity resultado de la operacion
     * @author JorgeRojas
    */
    @ApiOperation(value = "Insertar usuario", response = ArrayList.class, notes = "Crea un nuevo usuario al sistema de tareas", authorizations = {
        @Authorization(value = "JWT") })
    @ApiResponses(value = { @ApiResponse(code = 200, message = ConstantesSwagger.OPERACION_OK), @ApiResponse(code = 400, message = ConstantesSwagger.INSERTAR_USUARIO_NOK) })
    @PostMapping(path = "/insercion-usuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> guardarNuevoUsuario(@RequestBody CrearUsuarioRequestDto crearUsuarioRequestDto){
        RespuestaGenericaDto respuestaGenericaDto = usuariosService.insertarNuevoUsuario(crearUsuarioRequestDto);
        return new ResponseEntity<Object>(respuestaGenericaDto.getData(), HttpStatus.valueOf(respuestaGenericaDto.getStatus()));
    }

    /**
     * Realiza la consulta de usuarios con filtros si aplica
     *
     * @param consultarUsuariosRequestDto parametros para realizar la consulta
     * @return ResponseEntity Listado con los usuarios que cumplen el filtro
     * @author JorgeRojas
     */
    @ApiOperation(value = "Consultar usuario", response = ArrayList.class, notes = "Consulta los usuarios del sistema que cumplen el filtro", authorizations = {
        @Authorization(value = "JWT") })
    @ApiResponses(value = { @ApiResponse(code = 200, message = ConstantesSwagger.CONSULTA_USUARIO_OK), @ApiResponse(code = 400, message = ConstantesSwagger.CONSULTA_USUARIO_NOK) })
    @PostMapping(path = "/consulta-usuarios", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> obtenerUsuarios(@RequestBody ConsultarUsuariosRequestDto consultarUsuariosRequestDto){
        RespuestaGenericaDto respuestaGenericaDto = usuariosService.obtenerUsuarios(consultarUsuariosRequestDto);
        return new ResponseEntity<Object>(respuestaGenericaDto.getData(), HttpStatus.valueOf(respuestaGenericaDto.getStatus()));
    }

    /**
     * Actualiza un usuario previamente registrado en el sistema
     *
     * @param actualizarUsuarioDtoRequest Parámetros para realizar la actualización del usuario
     * @return ResponseEntity Resultado de la operación
     * @author JorgeRojas
     */
    @ApiOperation(value = "Actualizar usuario", response = ArrayList.class, notes = "Actualiza un usuario del sistema", authorizations = {
        @Authorization(value = "JWT") })
    @ApiResponses(value = { @ApiResponse(code = 200, message = ConstantesSwagger.OPERACION_OK), @ApiResponse(code = 400, message = ConstantesSwagger.ACTUALIZAR_USUARIO_NOK) })
    @PutMapping(path = "/actualizacion-usuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> actualizarUsuario(@RequestBody ActualizarUsuarioRequestDto actualizarUsuarioDtoRequest){
        RespuestaGenericaDto respuestaGenericaDto = usuariosService.actualizarUsuario(actualizarUsuarioDtoRequest);
        return new ResponseEntity<Object>(respuestaGenericaDto.getData(), HttpStatus.valueOf(respuestaGenericaDto.getStatus()));
    }

    /**
     * Elimina un usuario
     *
     * @param idUsuario Id del usuario a eliminar
     * @return ResponseEntity Resultado de la operación
     * @author JorgeRojas
     */
    @ApiOperation(value = "Eliminar usuario", response = ArrayList.class, notes = "Elimina un usuario del sistema", authorizations = {
        @Authorization(value = "JWT") })
    @ApiResponses(value = { @ApiResponse(code = 200, message = ConstantesSwagger.OPERACION_OK), @ApiResponse(code = 400, message = ConstantesSwagger.ELIMINAR_USUARIO_NOK) })
    @DeleteMapping(path = "/eliminado-usuario/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> eliminarUsuario(@ApiParam(example = "1") @PathVariable("idUsuario") Integer idUsuario){
        RespuestaGenericaDto respuestaGenericaDto = usuariosService.eliminarUsuario(idUsuario);
        return new ResponseEntity<Object>(respuestaGenericaDto.getData(), HttpStatus.valueOf(respuestaGenericaDto.getStatus()));
    }

}
