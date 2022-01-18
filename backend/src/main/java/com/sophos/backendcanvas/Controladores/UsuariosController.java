package com.sophos.backendcanvas.Controladores;

import com.sophos.backendcanvas.Dto.RespuestaGenericaDto;
import com.sophos.backendcanvas.Dto.UsuarioRequestDto;
import com.sophos.backendcanvas.Servicios.UsuariosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> guardarNuevoUsuario(@RequestBody UsuarioRequestDto usuarioRequestDto){
        RespuestaGenericaDto respuestaGenericaDto = usuariosService.insertarNuevoUsuario(usuarioRequestDto);
        return new ResponseEntity<Object>(respuestaGenericaDto.getData(), HttpStatus.valueOf(respuestaGenericaDto.getStatus()));
    }

}
