package com.sophos.backendcanvas.Dao;

import com.sophos.backendcanvas.Entidades.UsuariosEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutenticacionDao extends JpaRepository<UsuariosEntity, Integer>{
    
    @Query("SELECT u FROM UsuariosEntity u WHERE USUARIO = :usuario")
    UsuariosEntity findUserByUsr(@Param("usuario") String usuario);

}
