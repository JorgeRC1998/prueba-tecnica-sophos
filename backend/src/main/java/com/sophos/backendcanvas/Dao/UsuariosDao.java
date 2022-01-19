package com.sophos.backendcanvas.Dao;

import java.util.List;

import javax.transaction.Transactional;

import com.sophos.backendcanvas.Entidades.UsuariosEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosDao extends JpaRepository<UsuariosEntity, Integer> {

    @Query(value="SELECT SEQ_USUARIOS_ID.NEXTVAL from dual", nativeQuery=true)
    Integer obtenerIdUsuario();

    @Query(value = "SELECT ID, NOMBRE, IDENTIFICACION, TIPO_USUARIO, USUARIO, PASSWORD FROM USUARIOS WHERE NOMBRE =:nombre", nativeQuery = true)
    List<UsuariosEntity> buscarUsuario(@Param("nombre") String nombre);

    @Query("SELECT u FROM UsuariosEntity u WHERE ID = :id")
    List<UsuariosEntity> findUserById(@Param("id") Integer id);

    @Modifying
    @Query(value = "INSERT INTO USUARIOS (ID, NOMBRE, IDENTIFICACION, TIPO_USUARIO, USUARIO, PASSWORD) VALUES " +
                    "(:id, :nombre, :identificacion, :tipoUsuario, :usuario, :password)", nativeQuery = true)
    @Transactional
    void insertarUsuario(@Param("id") Integer id,
                        @Param("nombre") String nombre,
                        @Param("identificacion") String identificacion,
                        @Param("tipoUsuario") String tipoUsuario,
                        @Param("usuario") String usuario,
                        @Param("password") String password);

    @Modifying
    @Query(value = "UPDATE USUARIOS SET NOMBRE =:nombre, IDENTIFICACION =:identificacion, TIPO_USUARIO =:tipoUsuario, USUARIO =:usuario, PASSWORD =:password WHERE ID =:id ", nativeQuery = true)
    @Transactional
    void actualizarUsuario(@Param("id") Integer id,
                        @Param("nombre") String nombre,
                        @Param("identificacion") String identificacion,
                        @Param("tipoUsuario") String tipoUsuario,
                        @Param("usuario") String usuario,
                        @Param("password") String password);

    @Modifying
    @Query(value = "DELETE FROM USUARIOS WHERE ID = :id", nativeQuery = true)
    @Transactional
    void eliminarUsuario(@Param("id") Integer id);
                        
}
