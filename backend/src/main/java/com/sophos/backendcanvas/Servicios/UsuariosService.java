package com.sophos.backendcanvas.Servicios;

import java.util.List;

import com.sophos.backendcanvas.Adaptadores.GeneralAdapter;
import com.sophos.backendcanvas.Adaptadores.UsuariosAdapter;
import com.sophos.backendcanvas.Dao.UsuariosDao;
import com.sophos.backendcanvas.Dto.RespuestaGenericaDto;
import com.sophos.backendcanvas.Dto.UsuarioRequestDto;
import com.sophos.backendcanvas.Entidades.UsuariosEntity;
import com.sophos.backendcanvas.Validadores.UsuarioRequestValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService {
    
    @Autowired
    UsuariosDao usuariosDao;
    @Autowired
    UsuariosAdapter usuariosAdapter;
    @Autowired
    GeneralAdapter generalAdapter;
    @Autowired
    UsuarioRequestValidator usuarioRequestValidator;

    public RespuestaGenericaDto insertarNuevoUsuario(UsuarioRequestDto usuarioRequestDto) {
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        try{
            // TODO Implementar validacion de token
            List<String> errores = usuarioRequestValidator.validacionUsuarioRequest(usuarioRequestDto);
            if(errores.size() > 0){
                respuestaGenericaDto = usuariosAdapter.obtenerValidacionUsuarioNOK(errores);
            }else{
                List<UsuariosEntity> usuario = usuariosDao.buscarUsuario(usuarioRequestDto.getNombre());
                if(usuario.size() > 0){
                    respuestaGenericaDto = usuariosAdapter.obtenerUsuarioExisteResponse(usuarioRequestDto.getNombre());
                }else{
                    Integer idUsuario = usuariosDao.obtenerIdUsuario();
                    usuariosDao.insertarUsuario(idUsuario, 
                                                usuarioRequestDto.getNombre(), 
                                                usuarioRequestDto.getIdentificacion(), 
                                                usuarioRequestDto.getTipoUsuario(), 
                                                usuarioRequestDto.getUsuario(),
                                                usuarioRequestDto.getPassword());
                    respuestaGenericaDto = usuariosAdapter.obtenerInsertUsuarioOk();
                }
            }
        }catch(Exception e){
            // TODO loggear en archivo plano json con el detalle del error
            respuestaGenericaDto = generalAdapter.obtenerRespuestaExcepcion(e);
        }

        return respuestaGenericaDto;
    }

}
