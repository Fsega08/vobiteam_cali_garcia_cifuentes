package com.vobi.team.presentation.backingBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtSprint;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;

@ManagedBean
@ViewScoped

public class IniciarSprintView {

	public final static Logger log=LoggerFactory.getLogger(IniciarSprintView.class);


	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;	

	private List<VtArtefacto> losArtefactosPorHacer;
	private List<VtArtefacto> losArtefactosEnCurso;
	private List<VtArtefacto> losArtefactosFinalizados;
	
	private VtSprint sprintSeleccionado;
	private VtArtefacto artefactoSeleccionado;
	
	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();
	
	
	
	public IniciarSprintView() {
		super();
		this.losArtefactosPorHacer = new ArrayList<VtArtefacto>();
		this.losArtefactosEnCurso = new ArrayList<VtArtefacto>() ;
		this.losArtefactosFinalizados = new ArrayList<VtArtefacto>();
	}
	
	@PostConstruct
	public void init(){
		try {			
			sprintSeleccionado = (VtSprint) FacesUtils.getfromSession("sprintSeleccionado");
			
			for (VtArtefacto vtArtefacto : sprintSeleccionado.getVtArtefactos()) {
				if (vtArtefacto.getVtEstado().getEstaCodigo() == 1L) {
					losArtefactosPorHacer.add(vtArtefacto);
				}
				if (vtArtefacto.getVtEstado().getEstaCodigo() == 2L) {
					losArtefactosEnCurso.add(vtArtefacto);
				}
				if (vtArtefacto.getVtEstado().getEstaCodigo() == 4L) {
					losArtefactosFinalizados.add(vtArtefacto);
				}
			}

		} catch (Exception e) {
			log.info(e.getMessage());
		}

	}	
	//............................................................................................

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public List<VtArtefacto> getLosArtefactosPorHacer() {
		return losArtefactosPorHacer;
	}

	public void setLosArtefactosPorHacer(List<VtArtefacto> losArtefactosPorHacer) {
		this.losArtefactosPorHacer = losArtefactosPorHacer;
	}

	public List<VtArtefacto> getLosArtefactosEnCurso() {
		return losArtefactosEnCurso;
	}

	public void setLosArtefactosEnCurso(List<VtArtefacto> losArtefactosEnCurso) {
		this.losArtefactosEnCurso = losArtefactosEnCurso;
	}

	public List<VtArtefacto> getLosArtefactosFinalizados() {
		return losArtefactosFinalizados;
	}

	public void setLosArtefactosFinalizados(List<VtArtefacto> losArtefactosFinalizados) {
		this.losArtefactosFinalizados = losArtefactosFinalizados;
	}

	public VtSprint getSprintSeleccionado() {
		return sprintSeleccionado;
	}

	public void setSprintSeleccionado(VtSprint sprintSeleccionado) {
		this.sprintSeleccionado = sprintSeleccionado;
	}	

	public VtArtefacto getArtefactoSeleccionado() {
		return artefactoSeleccionado;
	}

	public void setArtefactoSeleccionado(VtArtefacto artefactoSeleccionado) {
		this.artefactoSeleccionado = artefactoSeleccionado;
	}

	public String getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(String usuarioActual) {
		this.usuarioActual = usuarioActual;
	}
	//..................................................................................................	
	
}
