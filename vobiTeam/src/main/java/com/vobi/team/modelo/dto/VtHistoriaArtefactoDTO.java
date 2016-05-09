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
public class VtHistoriaArtefactoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtHistoriaArtefactoDTO.class);
    private String activo;
    private String descripcion;
    private Integer esfuerzoEstimado;
    private Integer esfuerzoReal;
    private Integer esfuerzoRestante;
    private Long estaCodigo;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long historiaCodigo;
    private String origen;
    private Long pilaCodigo;
    private Long prioCodigo;
    private Integer puntos;
    private Long spriCodigo;
    private String titulo;
    private Long tparCodigo;
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

    public Long getEstaCodigo() {
        return estaCodigo;
    }

    public void setEstaCodigo(Long estaCodigo) {
        this.estaCodigo = estaCodigo;
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

    public Long getHistoriaCodigo() {
        return historiaCodigo;
    }

    public void setHistoriaCodigo(Long historiaCodigo) {
        this.historiaCodigo = historiaCodigo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Long getPilaCodigo() {
        return pilaCodigo;
    }

    public void setPilaCodigo(Long pilaCodigo) {
        this.pilaCodigo = pilaCodigo;
    }

    public Long getPrioCodigo() {
        return prioCodigo;
    }

    public void setPrioCodigo(Long prioCodigo) {
        this.prioCodigo = prioCodigo;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Long getSpriCodigo() {
        return spriCodigo;
    }

    public void setSpriCodigo(Long spriCodigo) {
        this.spriCodigo = spriCodigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getTparCodigo() {
        return tparCodigo;
    }

    public void setTparCodigo(Long tparCodigo) {
        this.tparCodigo = tparCodigo;
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
