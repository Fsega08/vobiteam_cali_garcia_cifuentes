package com.vobi.team.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public class VtProyectoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtProyectoDTO.class);
    private String activo;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String nombre;
    private Long proyCodigo;
    private String publico;
    private Long usuCreador;
    private Long usuModificador;
    private Long emprCodigo_VtEmpresa;

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getProyCodigo() {
        return proyCodigo;
    }

    public void setProyCodigo(Long proyCodigo) {
        this.proyCodigo = proyCodigo;
    }

    public String getPublico() {
        return publico;
    }

    public void setPublico(String publico) {
        this.publico = publico;
    }

    public Long getUsuCreador() {
        return usuCreador;
    }

    public void setUsuCreador(Long usuCreador) {
        this.usuCreador = usuCreador;
    }

    public Long getUsuModificador() {
        return usuModificador;
    }

    public void setUsuModificador(Long usuModificador) {
        this.usuModificador = usuModificador;
    }

    public Long getEmprCodigo_VtEmpresa() {
        return emprCodigo_VtEmpresa;
    }

    public void setEmprCodigo_VtEmpresa(Long emprCodigo_VtEmpresa) {
        this.emprCodigo_VtEmpresa = emprCodigo_VtEmpresa;
    }
}
