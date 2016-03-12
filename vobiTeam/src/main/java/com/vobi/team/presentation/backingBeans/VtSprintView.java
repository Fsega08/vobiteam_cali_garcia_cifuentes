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

import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtSprint;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;



@ManagedBean
@ViewScoped
public class VtSprintView {

	public final static Logger log=LoggerFactory.getLogger(VtSprintView.class);


	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;	
	
	private List<VtSprint> losSprint;
	private VtSprint sprintSeleccionado;
	
	private VtPilaProducto backlogSeleccionado;
	
	
	@PostConstruct
	public void init(){
		
		try {
			backlogSeleccionado = (VtPilaProducto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("backlogSeleccionado");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
	}
	
	public VtPilaProducto getBacklogSeleccionado() {
		return backlogSeleccionado;
	}




	public void setBacklogSeleccionado(VtPilaProducto backlogSeleccionado) {
		this.backlogSeleccionado = backlogSeleccionado;
	}




	public List<VtSprint> getLosSprint() {
		try {
			if(losSprint == null){
				losSprint = businessDelegatorView.findSprintByBacklog(backlogSeleccionado);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		return losSprint;
	}


	public void setLosSprint(List<VtSprint> losSprint) {
		this.losSprint = losSprint;
	}
	
	public VtSprint getSprintSeleccionado() {
		return sprintSeleccionado;
	}


	public void setSprintSeleccionado(VtSprint sprintSeleccionado) {
		this.sprintSeleccionado = sprintSeleccionado;
	}


	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}
	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	
	//Este listener es para mandar el backlog seleccionado y setear los datos en el dialogo

}
