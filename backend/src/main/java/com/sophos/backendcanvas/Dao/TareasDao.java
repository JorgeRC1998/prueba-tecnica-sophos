package com.sophos.backendcanvas.Dao;

import java.util.List;

import javax.transaction.Transactional;

import com.sophos.backendcanvas.Entidades.TareasEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TareasDao extends JpaRepository<TareasEntity, Integer> {
    
    @Query(value="SELECT SEQ_TAREAS_ID.NEXTVAL from dual", nativeQuery=true)
    Integer obtenerIdTarea();

    @Query("SELECT t FROM TareasEntity t WHERE ID = :id")
    List<TareasEntity> findTareaById(@Param("id") Integer id);

    @Query("SELECT t FROM TareasEntity t WHERE ID_USUARIO IS NULL")
    List<TareasEntity> consultarTareasNoAsignacion();

    @Modifying
    @Query(value = "INSERT INTO TAREAS (ID, TITULO, DESCRIPCION, ESTADO) VALUES " +
                    "(:id, :titulo, :descripcion, 'pendiente')", nativeQuery = true)
    @Transactional
    void insertarTarea(@Param("id") Integer id,
                        @Param("titulo") String titulo,
                        @Param("descripcion") String descripcion);

    @Modifying
    @Query(value = "UPDATE TAREAS SET TITULO =:titulo, DESCRIPCION =:descripcion, ESTADO =:estado WHERE ID =:id ", nativeQuery = true)
    @Transactional
    void actualizarTarea(@Param("id") Integer id,
                        @Param("titulo") String titulo,
                        @Param("descripcion") String descripcion,
                        @Param("estado") String estado);

    @Modifying
    @Query(value = "UPDATE TAREAS SET ID_USUARIO =:idUsuario WHERE ID =:id ", nativeQuery = true)
    @Transactional
    void asignarTarea(@Param("id") Integer id,
                        @Param("idUsuario") Integer idUsuario);

    @Modifying
    @Query(value = "UPDATE TAREAS SET ID_USUARIO = null WHERE ID =:id ", nativeQuery = true)
    @Transactional
    void liberarTarea(@Param("id") Integer id);

    
    @Modifying
    @Query(value = "DELETE FROM TAREAS WHERE ID = :id", nativeQuery = true)
    @Transactional
    void eliminarTarea(@Param("id") Integer id);

}
