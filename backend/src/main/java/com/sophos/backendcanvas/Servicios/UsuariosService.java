package com.sophos.backendcanvas.Servicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sophos.backendcanvas.Adaptadores.GeneralAdapter;
import com.sophos.backendcanvas.Adaptadores.UsuariosAdapter;
import com.sophos.backendcanvas.Dao.UsuariosDao;
import com.sophos.backendcanvas.Dto.RespuestaGenericaDto;
import com.sophos.backendcanvas.Dto.ActualizarUsuarioDtoRequest;
import com.sophos.backendcanvas.Dto.ConsultarUsuariosRequestDto;
import com.sophos.backendcanvas.Dto.CrearUsuarioRequestDto;
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
    @Autowired
    EntityManager entityManager;

    public RespuestaGenericaDto insertarNuevoUsuario(CrearUsuarioRequestDto crearUsuarioRequestDto) {
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        try{
            // TODO Implementar validacion de token
            List<String> errores = usuarioRequestValidator.validacionCrearUsuarioRequest(crearUsuarioRequestDto);
            if(errores.size() > 0){
                respuestaGenericaDto = usuariosAdapter.obtenerValidacionUsuarioNOK(errores);
            }else{
                List<UsuariosEntity> usuario = usuariosDao.buscarUsuario(crearUsuarioRequestDto.getNombre());
                if(usuario.size() > 0){
                    respuestaGenericaDto = usuariosAdapter.obtenerUsuarioExisteResponse(crearUsuarioRequestDto.getNombre());
                }else{
                    Integer idUsuario = usuariosDao.obtenerIdUsuario();
                    usuariosDao.insertarUsuario(idUsuario, 
                                                crearUsuarioRequestDto.getNombre(), 
                                                crearUsuarioRequestDto.getIdentificacion(), 
                                                crearUsuarioRequestDto.getTipoUsuario(), 
                                                crearUsuarioRequestDto.getUsuario(),
                                                crearUsuarioRequestDto.getPassword());
                    respuestaGenericaDto = generalAdapter.obtenerRespuestaOk();
                }
            }
        }catch(Exception e){
            // TODO loggear en archivo plano json con el detalle del error
            respuestaGenericaDto = generalAdapter.obtenerRespuestaExcepcion(e);
        }

        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto obtenerUsuarios(ConsultarUsuariosRequestDto consultarUsuariosRequestDto){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        try{
            String queryIni = "SELECT u FROM UsuariosEntity u WHERE";
            if(!(consultarUsuariosRequestDto.getNombre() == null)){
                queryIni +=  " u.nombre like " + "'%" + consultarUsuariosRequestDto.getNombre() +  "%'";
            }
            if(!(consultarUsuariosRequestDto.getTipoUsuario() == null) && !(consultarUsuariosRequestDto.getTipoUsuario().trim().equals(""))){
                queryIni +=  " AND u.tipoUsuario = " + "'" + consultarUsuariosRequestDto.getTipoUsuario() + "'";
            }
            Query usuariosQuery = entityManager.createQuery(queryIni);
            List<UsuariosEntity> usuarios = usuariosQuery.getResultList();
            respuestaGenericaDto = usuariosAdapter.obtenerConsultaUsuarioOk(usuarios);
        }catch(Exception e){
            // TODO loggear en archivo plano json con el detalle del error
            respuestaGenericaDto = generalAdapter.obtenerRespuestaExcepcion(e);
        }
        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto actualizarUsuario(ActualizarUsuarioDtoRequest actualizarUsuarioDtoRequest){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        try{
            List<String> errores = usuarioRequestValidator.validacionActualizarUsuarioRequest(actualizarUsuarioDtoRequest);
            if(errores.size() > 0){
                respuestaGenericaDto = usuariosAdapter.obtenerValidacionUsuarioNOK(errores);
            }else{
                List<UsuariosEntity> usuarioExistente = usuariosDao.findUserById(actualizarUsuarioDtoRequest.getId());
                if(usuarioExistente.size() == 0){
                    respuestaGenericaDto = usuariosAdapter.obtenerActUsuNoExiste(actualizarUsuarioDtoRequest.getNombre());
                }else{
                    usuariosDao.actualizarUsuario(actualizarUsuarioDtoRequest.getId(),
                                                actualizarUsuarioDtoRequest.getNombre(),
                                                actualizarUsuarioDtoRequest.getIdentificacion(),
                                                actualizarUsuarioDtoRequest.getTipoUsuario(),
                                                actualizarUsuarioDtoRequest.getUsuario(),
                                                actualizarUsuarioDtoRequest.getPassword());
                    respuestaGenericaDto = generalAdapter.obtenerRespuestaOk();
                }
            }
        }catch(Exception e){
            // TODO loggear en archivo plano json con el detalle del error
            respuestaGenericaDto = generalAdapter.obtenerRespuestaExcepcion(e);
        }
        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto eliminarUsuario(Integer idUsuario){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        try{
            List<UsuariosEntity> usuarioExistente = usuariosDao.findUserById(idUsuario);
            if(usuarioExistente.size() == 0){
                respuestaGenericaDto = usuariosAdapter.obtenerActUsuNoExiste(idUsuario.toString());
            }else{
                usuariosDao.eliminarUsuario(idUsuario);
                respuestaGenericaDto = generalAdapter.obtenerRespuestaOk();
            }
        }catch(Exception e){
            // TODO loggear en archivo plano json con el detalle del error
            respuestaGenericaDto = generalAdapter.obtenerRespuestaExcepcion(e);
        }
        return respuestaGenericaDto;
    }

}
