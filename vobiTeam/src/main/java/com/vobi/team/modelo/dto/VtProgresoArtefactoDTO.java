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
public class VtProgresoArtefactoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtProgresoArtefactoDTO.class);
    private String activo;
    private String descripcion;
    private Integer esfuerzoReal;
    private Integer esfuerzoRestante;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long proartCodigo;
    private Integer puntos;
    private Integer tiempoDedicado;
    private Long usuCreador;
    private Long usuModificador;
    private Long arteCodigo_VtArtefacto;

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

    public Integer getEsfuerzoReal() {
        return esfuerzoReal;
    }

    public void setEsfuerzoReal(Integer esfuerzoReal) {
        this.esfuerzoReal = esfuerzoReal;
    }

    public Integer getEsfuerzoRestante() {
        return esfuerzoRestante;
    }

    public void setEsfuerzoRestante(Integer esfuerzoRestante) {
        this.esfuerzoRestante = esfuerzoRestante;
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

    public Long getProartCodigo() {
        return proartCodigo;
    }

    public void setProartCodigo(Long proartCodigo) {
        this.proartCodigo = proartCodigo;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Integer getTiempoDedicado() {
        return tiempoDedicado;
    }

    public void setTiempoDedicado(Integer tiempoDedicado) {
        this.tiempoDedicado = tiempoDedicado;
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
