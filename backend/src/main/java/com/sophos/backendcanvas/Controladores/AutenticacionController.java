package com.sophos.backendcanvas.Controladores;

import java.util.ArrayList;

import com.sophos.backendcanvas.Dto.LoginRequestDto;
import com.sophos.backendcanvas.Dto.RespuestaGenericaDto;
import com.sophos.backendcanvas.Servicios.AutenticacionService;
import com.sophos.backendcanvas.Util.ConstantesSwagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.Authorization;

@RestController 
@RequestMapping("/auth")
public class AutenticacionController {
    
    @Autowired
    AutenticacionService autenticacionService;

    /**
     * Asigna una tarea a un usuario especifico en el sistema
     *
     * @param asignarTareaRequestDto datos para asignar la tareaa
     * @return ResponseEntity Resultado de la operación
     * @author JorgeRojas
     */
    @ApiOperation(value = "Valida usuario", response = ArrayList.class, notes = "Verifica que el usuario y password ingresados esten en el sistema", authorizations = {
        @Authorization(value = "JWT") })
    @ApiResponses(value = { @ApiResponse(code = 200, message = ConstantesSwagger.LOGIN_OK), @ApiResponse(code = 400, message = ConstantesSwagger.LOGIN_NOK) })
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> validarUsuario(@RequestBody LoginRequestDto loginRequestDto){
        RespuestaGenericaDto respuestaGenericaDto = autenticacionService.validarUsuario(loginRequestDto);
        return new ResponseEntity<Object>(respuestaGenericaDto.getData(), HttpStatus.valueOf(respuestaGenericaDto.getStatus()));
    }

}
