package com.sophos.backendcanvas.Servicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import com.sophos.backendcanvas.Adaptadores.GeneralAdapter;
import com.sophos.backendcanvas.Adaptadores.UsuariosAdapter;
import com.sophos.backendcanvas.Dao.UsuariosDao;
import com.sophos.backendcanvas.Dto.RespuestaGenericaDto;
import com.sophos.backendcanvas.Dto.ActualizarUsuarioRequestDto;
import com.sophos.backendcanvas.Dto.ConsultarUsuariosRequestDto;
import com.sophos.backendcanvas.Dto.CrearUsuarioRequestDto;
import com.sophos.backendcanvas.Entidades.UsuariosEntity;
import com.sophos.backendcanvas.Validadores.UsuarioRequestValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
    @Autowired
    HttpServletRequest httpServletRequest;

    public RespuestaGenericaDto insertarNuevoUsuario(CrearUsuarioRequestDto crearUsuarioRequestDto) {
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        try{
            List<String> errores = usuarioRequestValidator.validacionCrearUsuarioRequest(crearUsuarioRequestDto);
            if(errores.size() > 0){
                respuestaGenericaDto = generalAdapter.obtenerValidacionRequestNOK(errores);
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
                                                generarPasswordHasheado(crearUsuarioRequestDto.getPassword()));
                    respuestaGenericaDto = generalAdapter.obtenerRespuestaOk();
                }
            }
        }catch(Exception e){
            respuestaGenericaDto = generalAdapter.getRespuestaExcepcion(e.toString(), httpServletRequest, getClass().getCanonicalName());
        }

        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto obtenerUsuarios(ConsultarUsuariosRequestDto consultarUsuariosRequestDto){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        try{
            List<String> errores = usuarioRequestValidator.validacionConsultaUsuarioRequest(consultarUsuariosRequestDto);
            if(errores.size() > 0){
                respuestaGenericaDto = generalAdapter.obtenerValidacionRequestNOK(errores);
            }else{
                String queryIni = "SELECT u FROM UsuariosEntity u WHERE";
                if(!(consultarUsuariosRequestDto.getNombre() == null)){
                    queryIni +=  " u.nombre like " + "'%" + consultarUsuariosRequestDto.getNombre() +  "%'";
                }
                if(!(consultarUsuariosRequestDto.getTipoUsuario() == null) && !(consultarUsuariosRequestDto.getTipoUsuario().trim().equals(""))){
                    queryIni +=  " AND u.tipoUsuario = " + "'" + consultarUsuariosRequestDto.getTipoUsuario() + "'";
                }
                queryIni = queryIni.replace("WHERE AND", "WHERE");
                Query usuariosQuery = entityManager.createQuery(queryIni);
                List<UsuariosEntity> usuarios = usuariosQuery.getResultList();
                respuestaGenericaDto = usuariosAdapter.obtenerConsultaUsuarioOk(usuarios);
            }
        }catch(Exception e){
            respuestaGenericaDto = generalAdapter.getRespuestaExcepcion(e.toString(), httpServletRequest, getClass().getCanonicalName());
        }
        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto actualizarUsuario(ActualizarUsuarioRequestDto actualizarUsuarioDtoRequest){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        try{
            List<String> errores = usuarioRequestValidator.validacionActualizarUsuarioRequest(actualizarUsuarioDtoRequest);
            if(errores.size() > 0){
                respuestaGenericaDto = generalAdapter.obtenerValidacionRequestNOK(errores);
            }else{
                List<UsuariosEntity> usuarioExistente = usuariosDao.findUserById(actualizarUsuarioDtoRequest.getId());
                if(usuarioExistente.size() == 0){
                    respuestaGenericaDto = usuariosAdapter.obtenerActUsuNoExiste(actualizarUsuarioDtoRequest.getId().toString());
                }else{
                    usuariosDao.actualizarUsuario(actualizarUsuarioDtoRequest.getId(),
                                                actualizarUsuarioDtoRequest.getNombre(),
                                                actualizarUsuarioDtoRequest.getIdentificacion(),
                                                actualizarUsuarioDtoRequest.getTipoUsuario(),
                                                actualizarUsuarioDtoRequest.getUsuario(),
                                                generarPasswordHasheado(actualizarUsuarioDtoRequest.getPassword()));
                    respuestaGenericaDto = generalAdapter.obtenerRespuestaOk();
                }
            }
        }catch(Exception e){
            respuestaGenericaDto = generalAdapter.getRespuestaExcepcion(e.toString(), httpServletRequest, getClass().getCanonicalName());
        }
        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto eliminarUsuario(Integer idUsuario){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        try{
            List<String> errores = usuarioRequestValidator.validacionEliminarUsuarioRequest(idUsuario);
            if(errores.size() > 0){
                respuestaGenericaDto = generalAdapter.obtenerValidacionRequestNOK(errores);
            }else{
                List<UsuariosEntity> usuarioExistente = usuariosDao.findUserById(idUsuario);
                if(usuarioExistente.size() == 0){
                    respuestaGenericaDto = usuariosAdapter.obtenerActUsuNoExiste(idUsuario.toString());
                }else{
                    usuariosDao.eliminarUsuario(idUsuario);
                    respuestaGenericaDto = generalAdapter.obtenerRespuestaOk();
                }
            }
        }catch(Exception e){
            respuestaGenericaDto = generalAdapter.getRespuestaExcepcion(e.toString(), httpServletRequest, getClass().getCanonicalName());
        }
        return respuestaGenericaDto;
    }

    public String generarPasswordHasheado(String passwordOriginal){
		String passwordHasheado = BCrypt.hashpw(passwordOriginal, BCrypt.gensalt(12));
        return passwordHasheado;
    }

}
