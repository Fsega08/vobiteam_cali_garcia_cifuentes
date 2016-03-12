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
public class VtProyectoUsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtProyectoUsuarioDTO.class);
    private String activo;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long prusCodigo;
    private Long usuCreador;
    private Long usuModificador;
    private Long proyCodigo_VtProyecto;
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

    public Long getPrusCodigo() {
        return prusCodigo;
    }

    public void setPrusCodigo(Long prusCodigo) {
        this.prusCodigo = prusCodigo;
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

    public Long getProyCodigo_VtProyecto() {
        return proyCodigo_VtProyecto;
    }

    public void setProyCodigo_VtProyecto(Long proyCodigo_VtProyecto) {
        this.proyCodigo_VtProyecto = proyCodigo_VtProyecto;
    }

    public Long getUsuaCodigo_VtUsuario() {
        return usuaCodigo_VtUsuario;
    }

    public void setUsuaCodigo_VtUsuario(Long usuaCodigo_VtUsuario) {
        this.usuaCodigo_VtUsuario = usuaCodigo_VtUsuario;
    }
}
