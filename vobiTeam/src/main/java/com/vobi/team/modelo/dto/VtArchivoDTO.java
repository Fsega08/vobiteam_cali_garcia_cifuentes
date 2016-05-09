package com.vobi.team.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public class VtArchivoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtArchivoDTO.class);
    private String activo;
    private Long archCodigo;
    private byte[] archivo;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String nombre;
    private Long usuCreador;
    private Long usuModificador;
    private Long arteCodigo_VtArtefacto;

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Long getArchCodigo() {
        return archCodigo;
    }

    public void setArchCodigo(Long archCodigo) {
        this.archCodigo = archCodigo;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
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

    public Long getArteCodigo_VtArtefacto() {
        return arteCodigo_VtArtefacto;
    }

    public void setArteCodigo_VtArtefacto(Long arteCodigo_VtArtefacto) {
        this.arteCodigo_VtArtefacto = arteCodigo_VtArtefacto;
    }
}
