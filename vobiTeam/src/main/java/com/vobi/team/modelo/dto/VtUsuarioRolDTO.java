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
public class VtUsuarioRolDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtUsuarioRolDTO.class);
    private String activo;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long usroCodigo;
    private Long usuCreador;
    private Long usuModificador;
    private Long rolCodigo_VtRol;
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

    public Long getUsroCodigo() {
        return usroCodigo;
    }

    public void setUsroCodigo(Long usroCodigo) {
        this.usroCodigo = usroCodigo;
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

    public Long getRolCodigo_VtRol() {
        return rolCodigo_VtRol;
    }

    public void setRolCodigo_VtRol(Long rolCodigo_VtRol) {
        this.rolCodigo_VtRol = rolCodigo_VtRol;
    }

    public Long getUsuaCodigo_VtUsuario() {
        return usuaCodigo_VtUsuario;
    }

    public void setUsuaCodigo_VtUsuario(Long usuaCodigo_VtUsuario) {
        this.usuaCodigo_VtUsuario = usuaCodigo_VtUsuario;
    }
}
