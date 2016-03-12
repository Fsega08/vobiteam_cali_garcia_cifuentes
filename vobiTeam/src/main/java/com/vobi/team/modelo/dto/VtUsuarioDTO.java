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
public class VtUsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtUsuarioDTO.class);
    private String activo;
    private String clave;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String login;
    private String nombre;
    private Long usuCreador;
    private Long usuModificador;
    private Long usuaCodigo;
    private Long emprCodigo_VtEmpresa;

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public Long getUsuaCodigo() {
        return usuaCodigo;
    }

    public void setUsuaCodigo(Long usuaCodigo) {
        this.usuaCodigo = usuaCodigo;
    }

    public Long getEmprCodigo_VtEmpresa() {
        return emprCodigo_VtEmpresa;
    }

    public void setEmprCodigo_VtEmpresa(Long emprCodigo_VtEmpresa) {
        this.emprCodigo_VtEmpresa = emprCodigo_VtEmpresa;
    }
}
