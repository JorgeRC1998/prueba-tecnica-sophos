package com.sophos.backendcanvas.Dao;

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

    @Modifying
    @Query(value = "INSERT INTO TAREAS (ID, DESCRIPCION, ESTADO) VALUES " +
                    "(:id, :descripcion, :estado)", nativeQuery = true)
    @Transactional
    void insertarTarea(@Param("id") Integer id,
                        @Param("descripcion") String descripcion,
                        @Param("estado") String estado);

}
