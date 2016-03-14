package com.vobi.team.presentation.backingBeans;

import java.util.Date;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtSprint;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;



@ManagedBean
@ViewScoped
public class VtArtefactoView {

	public final static Logger log=LoggerFactory.getLogger(VtArtefactoView.class);


	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private VtArtefacto artefactoSeleccionado;
	private List<VtArtefacto> losArtefactos;

	private VtSprint sprintSeleccionado;

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public VtArtefacto getArtefactoSeleccionado() {
		return artefactoSeleccionado;
	}

	public void setArtefactoSeleccionado(VtArtefacto artefactoSeleccionado) {
		this.artefactoSeleccionado = artefactoSeleccionado;
	}

	public List<VtArtefacto> getLosArtefactos() {
		try {
			sprintSeleccionado = (VtSprint) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sprintSeleccionado");
			if (sprintSeleccionado!=null) {
				losArtefactos = businessDelegatorView.findArtefactosBySpring(sprintSeleccionado);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return losArtefactos;
	}

	public void setLosArtefactos(List<VtArtefacto> losArtefactos) {
		this.losArtefactos = losArtefactos;
	}

	public VtSprint getSprintSeleccionado() {
		return sprintSeleccionado;
	}

	public void setSprintSeleccionado(VtSprint sprintSeleccionado) {
		this.sprintSeleccionado = sprintSeleccionado;
	}

	public void actualizarLista() throws Exception {
		log.info("bu!");

		sprintSeleccionado = (VtSprint) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sprintSeleccionado");
		if (sprintSeleccionado!=null) {
			losArtefactos = businessDelegatorView.findArtefactosBySpring(sprintSeleccionado);
		}

	}
}
