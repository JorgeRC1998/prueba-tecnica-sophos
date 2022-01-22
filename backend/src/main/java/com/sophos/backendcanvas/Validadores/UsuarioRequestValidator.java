package com.sophos.backendcanvas.Validadores;

import java.util.ArrayList;
import java.util.List;

import com.sophos.backendcanvas.Dto.ActualizarUsuarioRequestDto;
import com.sophos.backendcanvas.Dto.ConsultarUsuariosRequestDto;
import com.sophos.backendcanvas.Dto.CrearUsuarioRequestDto;

import org.springframework.stereotype.Component;

@Component
public class UsuarioRequestValidator {

    public List<String> validacionCrearUsuarioRequest(CrearUsuarioRequestDto crearUsuarioRequestDto){
        List<String> errores = new ArrayList<>();
        List<String> tiposUsuarios = new ArrayList<String>();

        tiposUsuarios.add("administrador");
        tiposUsuarios.add("miembro");
        tiposUsuarios.add("invitado");

        if(crearUsuarioRequestDto.getNombre().trim().equals("")){
            errores.add("Campo nombre es requerido");
        }

        if(crearUsuarioRequestDto.getNombre().trim().length() > 25){
            errores.add("La longitud del campo nombre debe ser inferior a 25 caracteres");
        }

        if(crearUsuarioRequestDto.getIdentificacion().trim().equals("")){
            errores.add("Campo identificacion es requerido");
        }

        if(crearUsuarioRequestDto.getIdentificacion().trim().length() > 15){
            errores.add("La longitud del campo identificacion debe ser inferior a 15 caracteres");
        }

        if(crearUsuarioRequestDto.getTipoUsuario().trim().equals("")){
            errores.add("Campo tipoUsuario es requerido");
        }

        if(crearUsuarioRequestDto.getTipoUsuario().trim().length() > 15){
            errores.add("La longitud del campo tipoUsuario debe ser inferior a 15 caracteres");
        }

        if(!tiposUsuarios.contains(crearUsuarioRequestDto.getTipoUsuario().trim().toLowerCase())){
            errores.add("El tipo de usuario ingresado no corresponde a administrador, miembro, invitado");
        }

        if(crearUsuarioRequestDto.getUsuario().trim().equals("")){
            errores.add("Campo usuario es requerido");
        }

        if(crearUsuarioRequestDto.getUsuario().trim().length() > 15){
            errores.add("La longitud del campo usuario debe ser inferior a 15 caracteres");
        }
    
        if(crearUsuarioRequestDto.getPassword().trim().equals("")){
            errores.add("Campo password es requerido");
        }

        if(crearUsuarioRequestDto.getPassword().trim().length() > 15){
            errores.add("La longitud del password nombre debe ser inferior a 15 caracteres");
        }

        return errores;
    }

    public List<String> validacionActualizarUsuarioRequest(ActualizarUsuarioRequestDto actualizarUsuarioDtoRequest){
        List<String> errores = new ArrayList<>();
        List<String> tiposUsuarios = new ArrayList<String>();

        tiposUsuarios.add("administrador");
        tiposUsuarios.add("miembro");
        tiposUsuarios.add("invitado");

        if(actualizarUsuarioDtoRequest.getId() == null){
            errores.add("Campo id es requerido");
        }

        if(actualizarUsuarioDtoRequest.getNombre().trim().equals("")){
            errores.add("Campo nombre es requerido");
        }

        if(actualizarUsuarioDtoRequest.getNombre().trim().length() > 25){
            errores.add("La longitud del campo nombre debe ser inferior a 25 caracteres");
        }

        if(actualizarUsuarioDtoRequest.getIdentificacion().trim().equals("")){
            errores.add("Campo identificacion es requerido");
        }

        if(actualizarUsuarioDtoRequest.getIdentificacion().trim().length() > 15){
            errores.add("La longitud del campo identificacion debe ser inferior a 15 caracteres");
        }

        if(actualizarUsuarioDtoRequest.getTipoUsuario().trim().equals("")){
            errores.add("Campo tipoUsuario es requerido");
        }

        if(actualizarUsuarioDtoRequest.getTipoUsuario().trim().length() > 15){
            errores.add("La longitud del campo tipoUsuario debe ser inferior a 15 caracteres");
        }

        if(!tiposUsuarios.contains(actualizarUsuarioDtoRequest.getTipoUsuario().trim().toLowerCase())){
            errores.add("El tipo de usuario ingresado no corresponde a administrador, miembro, invitado");
        }

        if(actualizarUsuarioDtoRequest.getUsuario().trim().equals("")){
            errores.add("Campo usuario es requerido");
        }

        if(actualizarUsuarioDtoRequest.getUsuario().trim().length() > 15){
            errores.add("La longitud del campo usuario debe ser inferior a 15 caracteres");
        }
    
        if(actualizarUsuarioDtoRequest.getPassword().trim().equals("")){
            errores.add("Campo password es requerido");
        }

        if(actualizarUsuarioDtoRequest.getUsuario().trim().length() > 15){
            errores.add("La longitud del password nombre debe ser inferior a 15 caracteres");
        }

        return errores;
    }

    public List<String> validacionEliminarUsuarioRequest(Integer idUsuario){
        List<String> errores = new ArrayList<>();

        if(idUsuario == null){
            errores.add("Parametro en la url idUsuario  es requerido");
        }

        return errores;
    }

    public List<String> validacionConsultaUsuarioRequest(ConsultarUsuariosRequestDto consultarUsuariosRequestDto){
        List<String> errores = new ArrayList<>();
        List<String> tiposUsuarios = new ArrayList<String>();

        tiposUsuarios.add("administrador");
        tiposUsuarios.add("miembro");
        tiposUsuarios.add("invitado");

        if(consultarUsuariosRequestDto.getNombre().trim().length() > 25){
            errores.add("La longitud del campo nombre debe ser inferior a 25 caracteres");
        }

        if(consultarUsuariosRequestDto.getTipoUsuario().trim().length() > 15){
            errores.add("La longitud del campo tipoUsuario debe ser inferior a 15 caracteres");
        }

        if(!tiposUsuarios.contains(consultarUsuariosRequestDto.getTipoUsuario().trim().toLowerCase())){
            errores.add("El tipo de usuario ingresado no corresponde a administrador, miembro, invitado");
        }

        return errores;
    }

}
