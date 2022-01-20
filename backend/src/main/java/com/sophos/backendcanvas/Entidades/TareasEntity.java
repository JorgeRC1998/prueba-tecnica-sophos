package com.sophos.backendcanvas.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAREAS")
public class TareasEntity {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "ID_USUARIO")
    private Integer idUsuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
