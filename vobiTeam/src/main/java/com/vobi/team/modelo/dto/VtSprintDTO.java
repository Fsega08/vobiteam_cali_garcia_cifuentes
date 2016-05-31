package com.vobi.team.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hirondelle.date4j.DateTime;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
*
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public class VtSprintDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtSprintDTO.class);
    private String activo;
    private Integer capacidadEstimada;
    private Integer capacidadReal;
    private Date fechaCreacion;
    private Date fechaFin;
    private Date fechaInicio;
    private Date fechaModificacion;
    private String nombre;
    private String objetivo;
    private Long spriCodigo;
    private Long usuCreador;
    private Long usuModificador;
    private Long estsprCodigo_VtEstadoSprint;
    private Long pilaCodigo_VtPilaProducto;

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Integer getCapacidadEstimada() {
        return capacidadEstimada;
    }

    public void setCapacidadEstimada(Integer capacidadEstimada) {
        this.capacidadEstimada = capacidadEstimada;

    }

    public Integer getCapacidadReal() {
        return capacidadReal;
    }

    public void setCapacidadReal(Integer capacidadReal) {
        this.capacidadReal = capacidadReal;

    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
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

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public Long getSpriCodigo() {
        return spriCodigo;
    }

    public void setSpriCodigo(Long spriCodigo) {
        this.spriCodigo = spriCodigo;
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

    public Long getEstsprCodigo_VtEstadoSprint() {
        return estsprCodigo_VtEstadoSprint;
    }

    public void setEstsprCodigo_VtEstadoSprint(Long estsprCodigo_VtEstadoSprint) {
        this.estsprCodigo_VtEstadoSprint = estsprCodigo_VtEstadoSprint;
    }

    public Long getPilaCodigo_VtPilaProducto() {
        return pilaCodigo_VtPilaProducto;
    }

    public void setPilaCodigo_VtPilaProducto(Long pilaCodigo_VtPilaProducto) {
        this.pilaCodigo_VtPilaProducto = pilaCodigo_VtPilaProducto;
    }
	
}
