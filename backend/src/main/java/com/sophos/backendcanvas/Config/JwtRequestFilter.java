package com.sophos.backendcanvas.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer";
    private static final String USUARIO = "usuario";

    @Value("${llave_secreta}")
    private String llaveSecreta;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException{

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, authorization");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");

        Pattern patternUrl = Pattern.compile("(/sophos/servicios/auth/login|/sophos/servicios/h2|/swagger-ui/#)", Pattern.CASE_INSENSITIVE);
        Matcher matcherUrl = patternUrl.matcher(request.getRequestURI());
        boolean matchFoundUrl = matcherUrl.find();

        if("OPTIONS".equals(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
        }else if(matchFoundUrl){
            filterChain.doFilter(request, response);
        }else{
            String jwtToken = request.getHeader(HEADER);

            if(!existeJWTToken(jwtToken)){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se encontro el encabezado de autorizacion o el prefijo bearer");
                return;
            }
            Claims token = null;
            try{
                token = Jwts.parser().setSigningKey(llaveSecreta.getBytes()).parseClaimsJws(jwtToken.replace(PREFIX, "")).getBody();
                String nombreUsuarioToken = token.get("sub").toString();
                request.getSession().setAttribute(USUARIO, nombreUsuarioToken);
                String exp = token.get("exp").toString();
                Date fechaExpiracion = new Date(Long.parseLong(exp) * 1000);

                if (estaTokenExpirado(fechaExpiracion)) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "El token ha vencido");
                } else {
                    filterChain.doFilter(request, response);
                }

            } catch (JwtException | IllegalArgumentException e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());

            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());

            }
        }
    }

    /**
     * @param jwtToken
     * @return falso o verdadero si el token llega o no
     */
    private boolean existeJWTToken(String jwtToken) {
        boolean token = true;
        if (jwtToken == null || !jwtToken.startsWith(PREFIX))
            token = false;
        return token;
    }

    /**
     * @param expirationDate
     * @return fecha de expiración del token
     */
    public boolean estaTokenExpirado(Date expirationDate) {
        return expirationDate.before(new Date());
    }


}
