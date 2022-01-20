package com.sophos.backendcanvas.Controladores;

import com.sophos.backendcanvas.Dto.RespuestaGenericaDto;
import com.sophos.backendcanvas.Dto.ActualizarUsuarioDtoRequest;
import com.sophos.backendcanvas.Dto.ConsultarUsuariosRequestDto;
import com.sophos.backendcanvas.Dto.CrearUsuarioRequestDto;
import com.sophos.backendcanvas.Servicios.UsuariosService;

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
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    UsuariosService usuariosService;
    
    /**
     * Inserta un nuevo usuario al sistema de tareas
     *
     * @param usuarioRequestDto datos para guardar en el sistema
     * @return ResponseEntity resultado de la operacion
     * @author JorgeRojas
    */
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
    @PostMapping(path = "/consulta-usuarios", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> obtenerUsuarios(@RequestBody ConsultarUsuariosRequestDto consultarUsuariosRequestDto){
        RespuestaGenericaDto respuestaGenericaDto = usuariosService.obtenerUsuarios(consultarUsuariosRequestDto);
        return new ResponseEntity<Object>(respuestaGenericaDto.getData(), HttpStatus.valueOf(respuestaGenericaDto.getStatus()));
    }

    /**
     * Actualiza un usuario previamente registrado en el sistema
     *
     * @param usuarioEntity Parámetros para realizar la actualización del nuevo usuario
     * @return ResponseEntity Resultado de la operación
     * @author JorgeRojas
     */
    @PutMapping(path = "/actualizacion-usuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> actualizarUsuario(@RequestBody ActualizarUsuarioDtoRequest actualizarUsuarioDtoRequest){
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
    @DeleteMapping(path = "/eliminado-usuario/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> actualizarUsuario(@PathVariable("idUsuario") Integer idUsuario){
        RespuestaGenericaDto respuestaGenericaDto = usuariosService.eliminarUsuario(idUsuario);
        return new ResponseEntity<Object>(respuestaGenericaDto.getData(), HttpStatus.valueOf(respuestaGenericaDto.getStatus()));
    }

}
