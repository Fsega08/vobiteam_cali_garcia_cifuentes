package com.vobi.team.modelo;
// Generated 7/05/2016 11:16:36 PM by Hibernate Tools 4.3.1.Final


import java.util.Date;

/**
 * VtHistoriaArtefacto generated by hbm2java
 */
public class VtHistoriaArtefacto  implements java.io.Serializable {


     private Long historiaCodigo;
     private VtArtefacto vtArtefacto;
     private Long estaCodigo;
     private String titulo;
     private String descripcion;
     private Integer esfuerzoEstimado;
     private Integer esfuerzoRestante;
     private Integer esfuerzoReal;
     private Integer puntos;
     private String origen;
     private String activo;
     private Long tparCodigo;
     private Long prioCodigo;
     private Long pilaCodigo;
     private Long spriCodigo;
     private Date fechaCreacion;
     private Date fechaModificacion;
     private Long usuCreador;
     private Long usuModificador;

    public VtHistoriaArtefacto() {
    }

	
    public VtHistoriaArtefacto(Long historiaCodigo, VtArtefacto vtArtefacto, Long estaCodigo, String titulo, String activo, Long tparCodigo, Long prioCodigo, Long pilaCodigo, Long spriCodigo, Date fechaCreacion, Long usuCreador) {
        this.historiaCodigo = historiaCodigo;
        this.vtArtefacto = vtArtefacto;
        this.estaCodigo = estaCodigo;
        this.titulo = titulo;
        this.activo = activo;
        this.tparCodigo = tparCodigo;
        this.prioCodigo = prioCodigo;
        this.pilaCodigo = pilaCodigo;
        this.spriCodigo = spriCodigo;
        this.fechaCreacion = fechaCreacion;
        this.usuCreador = usuCreador;
    }
    public VtHistoriaArtefacto(Long historiaCodigo, VtArtefacto vtArtefacto, Long estaCodigo, String titulo, String descripcion, Integer esfuerzoEstimado, Integer esfuerzoRestante, Integer esfuerzoReal, Integer puntos, String origen, String activo, Long tparCodigo, Long prioCodigo, Long pilaCodigo, Long spriCodigo, Date fechaCreacion, Date fechaModificacion, Long usuCreador, Long usuModificador) {
       this.historiaCodigo = historiaCodigo;
       this.vtArtefacto = vtArtefacto;
       this.estaCodigo = estaCodigo;
       this.titulo = titulo;
       this.descripcion = descripcion;
       this.esfuerzoEstimado = esfuerzoEstimado;
       this.esfuerzoRestante = esfuerzoRestante;
       this.esfuerzoReal = esfuerzoReal;
       this.puntos = puntos;
       this.origen = origen;
       this.activo = activo;
       this.tparCodigo = tparCodigo;
       this.prioCodigo = prioCodigo;
       this.pilaCodigo = pilaCodigo;
       this.spriCodigo = spriCodigo;
       this.fechaCreacion = fechaCreacion;
       this.fechaModificacion = fechaModificacion;
       this.usuCreador = usuCreador;
       this.usuModificador = usuModificador;
    }
   
    public Long getHistoriaCodigo() {
        return this.historiaCodigo;
    }
    
    public void setHistoriaCodigo(Long historiaCodigo) {
        this.historiaCodigo = historiaCodigo;
    }
    public VtArtefacto getVtArtefacto() {
        return this.vtArtefacto;
    }
    
    public void setVtArtefacto(VtArtefacto vtArtefacto) {
        this.vtArtefacto = vtArtefacto;
    }
    public Long getEstaCodigo() {
        return this.estaCodigo;
    }
    
    public void setEstaCodigo(Long estaCodigo) {
        this.estaCodigo = estaCodigo;
    }
    public String getTitulo() {
        return this.titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Integer getEsfuerzoEstimado() {
        return this.esfuerzoEstimado;
    }
    
    public void setEsfuerzoEstimado(Integer esfuerzoEstimado) {
        this.esfuerzoEstimado = esfuerzoEstimado;
    }
    public Integer getEsfuerzoRestante() {
        return this.esfuerzoRestante;
    }
    
    public void setEsfuerzoRestante(Integer esfuerzoRestante) {
        this.esfuerzoRestante = esfuerzoRestante;
    }
    public Integer getEsfuerzoReal() {
        return this.esfuerzoReal;
    }
    
    public void setEsfuerzoReal(Integer esfuerzoReal) {
        this.esfuerzoReal = esfuerzoReal;
    }
    public Integer getPuntos() {
        return this.puntos;
    }
    
    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }
    public String getOrigen() {
        return this.origen;
    }
    
    public void setOrigen(String origen) {
        this.origen = origen;
    }
    public String getActivo() {
        return this.activo;
    }
    
    public void setActivo(String activo) {
        this.activo = activo;
    }
    public Long getTparCodigo() {
        return this.tparCodigo;
    }
    
    public void setTparCodigo(Long tparCodigo) {
        this.tparCodigo = tparCodigo;
    }
    public Long getPrioCodigo() {
        return this.prioCodigo;
    }
    
    public void setPrioCodigo(Long prioCodigo) {
        this.prioCodigo = prioCodigo;
    }
    public Long getPilaCodigo() {
        return this.pilaCodigo;
    }
    
    public void setPilaCodigo(Long pilaCodigo) {
        this.pilaCodigo = pilaCodigo;
    }
    public Long getSpriCodigo() {
        return this.spriCodigo;
    }
    
    public void setSpriCodigo(Long spriCodigo) {
        this.spriCodigo = spriCodigo;
    }
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }
    
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    public Long getUsuCreador() {
        return this.usuCreador;
    }
    
    public void setUsuCreador(Long usuCreador) {
        this.usuCreador = usuCreador;
    }
    public Long getUsuModificador() {
        return this.usuModificador;
    }
    
    public void setUsuModificador(Long usuModificador) {
        this.usuModificador = usuModificador;
    }




}


