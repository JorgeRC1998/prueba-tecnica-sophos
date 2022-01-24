package com.sophos.backendcanvas.Servicios;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sophos.backendcanvas.Adaptadores.AutenticacionAdapter;
import com.sophos.backendcanvas.Adaptadores.GeneralAdapter;
import com.sophos.backendcanvas.Dao.AutenticacionDao;
import com.sophos.backendcanvas.Dto.LoginRequestDto;
import com.sophos.backendcanvas.Dto.RespuestaGenericaDto;
import com.sophos.backendcanvas.Entidades.UsuariosEntity;
import com.sophos.backendcanvas.Util.ConstantesApp;
import com.sophos.backendcanvas.Validadores.AutenticacionRequestValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AutenticacionService {

    @Value("${llave_secreta}")
    private String claveSecreta;
    
    @Autowired
    AutenticacionDao autenticacionDao;
    @Autowired
    AutenticacionRequestValidator autenticacionRequestValidator;
    @Autowired
    GeneralAdapter generalAdapter;
    @Autowired
    AutenticacionAdapter autenticacionAdapter;
    @Autowired
    HttpServletRequest httpServletRequest;

    public RespuestaGenericaDto validarUsuario(LoginRequestDto loginRequestDto){
        RespuestaGenericaDto respuestaGenericaDto = new RespuestaGenericaDto();

        try{
            List<String> errores = autenticacionRequestValidator.validacionLoginRequest(loginRequestDto);
            if(errores.size() > 0){
                respuestaGenericaDto = generalAdapter.obtenerValidacionRequestNOK(errores);
            }else{
                UsuariosEntity usuariosEntity = autenticacionDao.findUserByUsr(loginRequestDto.getUsuario());
                if(usuariosEntity == null){
                    respuestaGenericaDto = autenticacionAdapter.obtenerLoginNok(ConstantesApp.MENSAJE_USR_PSS_NOK);
                }else{
                    Boolean passwordCorrecto = verificarPassword(loginRequestDto.getPassword(), usuariosEntity.getPassword());
                    if(passwordCorrecto){
                        String token = generarJWTToken(usuariosEntity.getNombre(), usuariosEntity.getIdentificacion());
                        respuestaGenericaDto = autenticacionAdapter.obtenerLoginOk(token);
                    }else{
                        respuestaGenericaDto = autenticacionAdapter.obtenerLoginNok(ConstantesApp.MENSAJE_USR_PSS_NOK);
                    }
                }
            }
        }catch(Exception e){
            respuestaGenericaDto = generalAdapter.getRespuestaExcepcion(e.toString(), httpServletRequest, getClass().getCanonicalName());
        }
        return respuestaGenericaDto;
    }

    public Boolean verificarPassword(String password, String passwordHasheado){
        Boolean esIgual = BCrypt.checkpw(password, passwordHasheado);
        return esIgual;
    }

    public String generarJWTToken(String nombre, String identificacion) {
        return Jwts.builder()
                .setSubject(nombre)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .claim("identificacion", identificacion)
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS512, claveSecreta.getBytes()).compact();
    }

}
