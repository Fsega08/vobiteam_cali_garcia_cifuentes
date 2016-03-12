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
public class VtUsuarioArtefactoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtUsuarioArtefactoDTO.class);
    private String activo;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long usuCreador;
    private Long usuModificador;
    private Long usuartCodigo;
    private Long arteCodigo_VtArtefacto;
    private Long inteCodigo_VtInteres;
    private Long usuaCodigo_VtUsuario;

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
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

    public Long getUsuartCodigo() {
        return usuartCodigo;
    }

    public void setUsuartCodigo(Long usuartCodigo) {
        this.usuartCodigo = usuartCodigo;
    }

    public Long getArteCodigo_VtArtefacto() {
        return arteCodigo_VtArtefacto;
    }

    public void setArteCodigo_VtArtefacto(Long arteCodigo_VtArtefacto) {
        this.arteCodigo_VtArtefacto = arteCodigo_VtArtefacto;
    }

    public Long getInteCodigo_VtInteres() {
        return inteCodigo_VtInteres;
    }

    public void setInteCodigo_VtInteres(Long inteCodigo_VtInteres) {
        this.inteCodigo_VtInteres = inteCodigo_VtInteres;
    }

    public Long getUsuaCodigo_VtUsuario() {
        return usuaCodigo_VtUsuario;
    }

    public void setUsuaCodigo_VtUsuario(Long usuaCodigo_VtUsuario) {
        this.usuaCodigo_VtUsuario = usuaCodigo_VtUsuario;
    }
}
