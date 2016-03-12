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
public class VtArtefactoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtArtefactoDTO.class);
    private String activo;
    private Long arteCodigo;
    private String descripcion;
    private Integer esfuerzoEstimado;
    private Integer esfuerzoReal;
    private Integer esfuerzoRestante;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String origen;
    private Integer puntos;
    private String titulo;
    private Long usuCreador;
    private Long usuModificador;
    private Long estaCodigo_VtEstado;
    private Long pilaCodigo_VtPilaProducto;
    private Long prioCodigo_VtPrioridad;
    private Long spriCodigo_VtSprint;
    private Long tparCodigo_VtTipoArtefacto;

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Long getArteCodigo() {
        return arteCodigo;
    }

    public void setArteCodigo(Long arteCodigo) {
        this.arteCodigo = arteCodigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEsfuerzoEstimado() {
        return esfuerzoEstimado;
    }

    public void setEsfuerzoEstimado(Integer esfuerzoEstimado) {
        this.esfuerzoEstimado = esfuerzoEstimado;
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

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public Long getEstaCodigo_VtEstado() {
        return estaCodigo_VtEstado;
    }

    public void setEstaCodigo_VtEstado(Long estaCodigo_VtEstado) {
        this.estaCodigo_VtEstado = estaCodigo_VtEstado;
    }

    public Long getPilaCodigo_VtPilaProducto() {
        return pilaCodigo_VtPilaProducto;
    }

    public void setPilaCodigo_VtPilaProducto(Long pilaCodigo_VtPilaProducto) {
        this.pilaCodigo_VtPilaProducto = pilaCodigo_VtPilaProducto;
    }

    public Long getPrioCodigo_VtPrioridad() {
        return prioCodigo_VtPrioridad;
    }

    public void setPrioCodigo_VtPrioridad(Long prioCodigo_VtPrioridad) {
        this.prioCodigo_VtPrioridad = prioCodigo_VtPrioridad;
    }

    public Long getSpriCodigo_VtSprint() {
        return spriCodigo_VtSprint;
    }

    public void setSpriCodigo_VtSprint(Long spriCodigo_VtSprint) {
        this.spriCodigo_VtSprint = spriCodigo_VtSprint;
    }

    public Long getTparCodigo_VtTipoArtefacto() {
        return tparCodigo_VtTipoArtefacto;
    }

    public void setTparCodigo_VtTipoArtefacto(Long tparCodigo_VtTipoArtefacto) {
        this.tparCodigo_VtTipoArtefacto = tparCodigo_VtTipoArtefacto;
    }
}
