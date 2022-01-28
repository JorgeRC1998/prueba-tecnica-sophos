package com.sophos.backendcanvas.Servicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import com.sophos.backendcanvas.Adaptadores.GeneralAdapter;
import com.sophos.backendcanvas.Adaptadores.TareasAdapter;
import com.sophos.backendcanvas.Adaptadores.UsuariosAdapter;
import com.sophos.backendcanvas.Dao.TareasDao;
import com.sophos.backendcanvas.Dao.UsuariosDao;
import com.sophos.backendcanvas.Dto.ActualizarTareaRequestDto;
import com.sophos.backendcanvas.Dto.AsignarTareaRequestDto;
import com.sophos.backendcanvas.Dto.ConsultarTareasRequestDto;
import com.sophos.backendcanvas.Dto.CrearTareaRequestDto;
import com.sophos.backendcanvas.Dto.EliminarLiberarTareaRequestDto;
import com.sophos.backendcanvas.Dto.RespuestaGenericaDto;
import com.sophos.backendcanvas.Entidades.TareasEntity;
import com.sophos.backendcanvas.Entidades.UsuariosEntity;
import com.sophos.backendcanvas.Validadores.TareaRequestValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TareasService {

    @Autowired
    TareasDao tareasDao;
    @Autowired
    TareasAdapter tareasAdapter;
    @Autowired
    TareaRequestValidator tareaRequestValidator;
    @Autowired
    GeneralAdapter generalAdapter;
    @Autowired
    EntityManager entityManager;
    @Autowired
    UsuariosDao usuariosDao;
    @Autowired
    UsuariosAdapter usuariosAdapter;
    @Autowired
    HttpServletRequest httpServletRequest;

    public RespuestaGenericaDto insertarNuevaTarea(CrearTareaRequestDto crearTareaRequestDto) {
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        try{
            List<String> errores = tareaRequestValidator.validacionCrearTareaRequest(crearTareaRequestDto);
            if(errores.size() > 0){
                respuestaGenericaDto = generalAdapter.obtenerValidacionRequestNOK(errores);
            }else{
                Integer idTarea = tareasDao.obtenerIdTarea();
                tareasDao.insertarTarea(idTarea, 
                                        crearTareaRequestDto.getTitulo(),
                                        crearTareaRequestDto.getDescripcion());
                respuestaGenericaDto = generalAdapter.obtenerRespuestaOk();
            }
        }catch(Exception e){
            respuestaGenericaDto = generalAdapter.getRespuestaExcepcion(e.toString(), httpServletRequest, getClass().getCanonicalName());
        }

        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto obtenerTareas(ConsultarTareasRequestDto consultarTareasRequestDto){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        try{
            List<String> errores = tareaRequestValidator.validacionConsultaTareasRequest(consultarTareasRequestDto);
            if(errores.size() > 0){
                respuestaGenericaDto = generalAdapter.obtenerValidacionRequestNOK(errores);
            }else{
                String queryIni = "SELECT t FROM TareasEntity t WHERE";

                if(!(consultarTareasRequestDto.getIdUsuario() == null)){
                    queryIni +=  " t.idUsuario = " + consultarTareasRequestDto.getIdUsuario();
                }

                if(!(consultarTareasRequestDto.getEstado() == null) && !(consultarTareasRequestDto.getEstado().trim().equals(""))){
                    queryIni +=  " AND t.estado = " + "'" + consultarTareasRequestDto.getEstado() + "'";
                }

                queryIni = queryIni.replace("WHERE AND", "WHERE");
                Query tareasQuery = entityManager.createQuery(queryIni);
                List<TareasEntity> tareas = tareasQuery.getResultList();
                respuestaGenericaDto = tareasAdapter.obtenerConsultaTareaOk(tareas);
            }
        }catch(Exception e){
            respuestaGenericaDto = generalAdapter.getRespuestaExcepcion(e.toString(), httpServletRequest, getClass().getCanonicalName());
        }
        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto obtenerTareasSinAsignar(){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        try{
            List<TareasEntity> tareas = tareasDao.consultarTareasNoAsignacion();
            respuestaGenericaDto = tareasAdapter.obtenerConsultaTareaOk(tareas);
        }catch(Exception e){
            respuestaGenericaDto = generalAdapter.getRespuestaExcepcion(e.toString(), httpServletRequest, getClass().getCanonicalName());
        }
        return respuestaGenericaDto;
    }
    
    public RespuestaGenericaDto actualizarTarea(ActualizarTareaRequestDto actualizarTareaRequestDto){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        try{
            List<String> errores = tareaRequestValidator.validacionActualizarUsuarioRequest(actualizarTareaRequestDto);
            if(errores.size() > 0){
                respuestaGenericaDto = generalAdapter.obtenerValidacionRequestNOK(errores);
            }else{
                List<TareasEntity> tareaExistente = tareasDao.findTareaById(actualizarTareaRequestDto.getId());
                if(tareaExistente.size() == 0){
                    respuestaGenericaDto = tareasAdapter.obtenerActTarNoExiste(actualizarTareaRequestDto.getId().toString());
                }else{
                    tareasDao.actualizarTarea(actualizarTareaRequestDto.getId(),
                                                actualizarTareaRequestDto.getTitulo(),
                                                actualizarTareaRequestDto.getDescripcion(),
                                                actualizarTareaRequestDto.getEstado());
                    respuestaGenericaDto = generalAdapter.obtenerRespuestaOk();
                }
            }
        }catch(Exception e){
            respuestaGenericaDto = generalAdapter.getRespuestaExcepcion(e.toString(), httpServletRequest, getClass().getCanonicalName());
        }
        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto eliminarTarea(Integer idTarea){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        try{
            List<String> errores = tareaRequestValidator.validacionLiberarEliminarTareaRequest(idTarea);
            if(errores.size() > 0){
                respuestaGenericaDto = generalAdapter.obtenerValidacionRequestNOK(errores);
            }else{
                List<TareasEntity> tareaExistente = tareasDao.findTareaById(idTarea);
                if(tareaExistente.size() == 0){
                    respuestaGenericaDto = tareasAdapter.obtenerActTarNoExiste(idTarea.toString());
                }else{
                    if(tareaExistente.get(0).getIdUsuario() != null){
                        respuestaGenericaDto = tareasAdapter.obtenerNoElimTarea(tareaExistente.get(0));
                    }else{
                        tareasDao.eliminarTarea(idTarea);
                        respuestaGenericaDto = generalAdapter.obtenerRespuestaOk();
                    }
                }
            }
        }catch(Exception e){
            respuestaGenericaDto = generalAdapter.getRespuestaExcepcion(e.toString(), httpServletRequest, getClass().getCanonicalName());
        }
        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto asignarTarea(AsignarTareaRequestDto asignarTareaRequestDto){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        try{
            List<String> errores = tareaRequestValidator.validacionAsignarTareaRequest(asignarTareaRequestDto);
            if(errores.size() > 0){
                respuestaGenericaDto = generalAdapter.obtenerValidacionRequestNOK(errores);
            }else{
                List<TareasEntity> tareaExistente = tareasDao.findTareaById(asignarTareaRequestDto.getId());
                List<UsuariosEntity> usuarioExistente = usuariosDao.findUserById(asignarTareaRequestDto.getIdUsuario());
                if(tareaExistente.size() == 0){
                    respuestaGenericaDto = tareasAdapter.obtenerActTarNoExiste(asignarTareaRequestDto.getId().toString());
                }else{
                    if(usuarioExistente.size() == 0){
                        respuestaGenericaDto = usuariosAdapter.obtenerActUsuNoExiste(asignarTareaRequestDto.getIdUsuario().toString());
                    }else{
                        tareasDao.asignarTarea(asignarTareaRequestDto.getId(), asignarTareaRequestDto.getIdUsuario());
                        respuestaGenericaDto = generalAdapter.obtenerRespuestaOk();
                    }
                }
            }
        }catch(Exception e){
            respuestaGenericaDto = generalAdapter.getRespuestaExcepcion(e.toString(), httpServletRequest, getClass().getCanonicalName());
        }
        return respuestaGenericaDto;
    }

    public RespuestaGenericaDto liberarTarea(EliminarLiberarTareaRequestDto eliminarLiberarTareaRequestDto){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        try{
            List<String> errores = tareaRequestValidator.validacionLiberarEliminarTareaRequest(eliminarLiberarTareaRequestDto.getIdTarea());
            if(errores.size() > 0){
                respuestaGenericaDto = generalAdapter.obtenerValidacionRequestNOK(errores);
            }else{
                List<TareasEntity> tareaExistente = tareasDao.findTareaById(eliminarLiberarTareaRequestDto.getIdTarea());
                if(tareaExistente.size() == 0){
                    respuestaGenericaDto = tareasAdapter.obtenerActTarNoExiste(eliminarLiberarTareaRequestDto.getIdTarea().toString());
                }else{
                    if(tareaExistente.get(0).getIdUsuario() == null){
                        respuestaGenericaDto = tareasAdapter.obtenerNoLiberarTarea(eliminarLiberarTareaRequestDto.getIdTarea());
                    }else{
                        tareasDao.liberarTarea(eliminarLiberarTareaRequestDto.getIdTarea());
                        respuestaGenericaDto = generalAdapter.obtenerRespuestaOk();
                    }
                }
            }
        }catch(Exception e){
            respuestaGenericaDto = generalAdapter.getRespuestaExcepcion(e.toString(), httpServletRequest, getClass().getCanonicalName());
        }
        return respuestaGenericaDto;
    }
    
}
