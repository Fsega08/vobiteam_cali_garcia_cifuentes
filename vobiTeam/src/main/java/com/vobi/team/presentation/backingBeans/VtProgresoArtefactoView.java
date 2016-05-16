package com.vobi.team.presentation.backingBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.password.Password;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtProgresoArtefacto;
import com.vobi.team.modelo.VtRol;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.VtUsuarioArtefacto;
import com.vobi.team.modelo.VtUsuarioRol;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;
import com.vobi.team.utilities.Utilities;



@ManagedBean
@ViewScoped
public class VtProgresoArtefactoView {

	public final static Logger log=LoggerFactory.getLogger(VtProgresoArtefactoView.class);

	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;	
	
	private InputText txtEsfuerzoRestante;
	private InputText txtEsfuerzoReal;
	private InputText txtPuntos;	
	
	private InputText txtTiempoEstimado;
	private InputTextarea txtDescripcion;
	
	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();

	private VtArtefacto artefactoSeleccionado;
	
	@PostConstruct
	public void init(){
		try {
			
			
		} catch (Exception e) {
			//log.error(e.getMessage());
		}


	}
	
	
	public VtArtefacto getArtefactoSeleccionado() {
		return artefactoSeleccionado;
	}

	public void setArtefactoSeleccionado(VtArtefacto artefactoSeleccionado) {
		this.artefactoSeleccionado = artefactoSeleccionado;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}
	
	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public InputText getTxtTiempoEstimado() {
		return txtTiempoEstimado;
	}

	public void setTxtTiempoEstimado(InputText txtTiempoEstimado) {
		this.txtTiempoEstimado = txtTiempoEstimado;
	}

	public InputTextarea getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(InputTextarea txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}	
	
	public InputText getTxtEsfuerzoRestante() {
		return txtEsfuerzoRestante;
	}

	public void setTxtEsfuerzoRestante(InputText txtEsfuerzoRestante) {
		this.txtEsfuerzoRestante = txtEsfuerzoRestante;
	}

	public InputText getTxtEsfuerzoReal() {
		return txtEsfuerzoReal;
	}

	public void setTxtEsfuerzoReal(InputText txtEsfuerzoReal) {
		this.txtEsfuerzoReal = txtEsfuerzoReal;
	}

	public InputText getTxtPuntos() {
		return txtPuntos;
	}

	public void setTxtPuntos(InputText txtPuntos) {
		this.txtPuntos = txtPuntos;
	}


	//--------------------------------------------------------------------------------------------
	public void crearAction() {
		
		try {
			if (txtDescripcion.getValue().toString().trim().equals("") == true || txtDescripcion.getValue() == null) {
				throw new Exception("Por favor ingrese la descripción");
			}
			
			if (txtTiempoEstimado.getValue().toString().trim().equals("") == true || txtTiempoEstimado.getValue() == null || !Utilities.isNumeric(txtTiempoEstimado.getValue().toString().trim())) {
				throw new Exception("Por favor ingrese el esfuerzo estimado, recuerde este campo solo acepta numeros");
			}
			
			VtUsuario vtUsuario = businessDelegatorView.findUsuarioByLogin(usuarioActual);
			
			VtProgresoArtefacto vtProgresoArtefacto =  new VtProgresoArtefacto();		
			
			vtProgresoArtefacto.setTiempoDedicado(Integer.parseInt(txtTiempoEstimado.getValue().toString().trim()));			
			vtProgresoArtefacto.setEsfuerzoRestante(artefactoSeleccionado.getEsfuerzoRestante() - (Integer.parseInt(txtTiempoEstimado.getValue().toString().trim())));			
			vtProgresoArtefacto.setPuntos(artefactoSeleccionado.getPuntos());			
			vtProgresoArtefacto.setEsfuerzoReal(artefactoSeleccionado.getEsfuerzoReal()+ vtProgresoArtefacto.getTiempoDedicado());			
			vtProgresoArtefacto.setDescripcion(txtDescripcion.getValue().toString());			
			vtProgresoArtefacto.setFechaCreacion(new Date());			
			vtProgresoArtefacto.setFechaModificacion(new Date());			
			vtProgresoArtefacto.setUsuCreador(vtUsuario.getUsuaCodigo());			
			vtProgresoArtefacto.setUsuModificador(vtUsuario.getUsuaCodigo());			
			vtProgresoArtefacto.setActivo("S");			
			vtProgresoArtefacto.setVtArtefacto(artefactoSeleccionado);
			
			artefactoSeleccionado.setEsfuerzoRestante(vtProgresoArtefacto.getEsfuerzoRestante());			
			artefactoSeleccionado.setEsfuerzoReal(vtProgresoArtefacto.getEsfuerzoReal());
			
			businessDelegatorView.saveVtProgresoArtefacto(vtProgresoArtefacto);			
			businessDelegatorView.updateVtArtefacto(artefactoSeleccionado);

			FacesUtils.addInfoMessage("El progreso fue agregado con exito");
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		
	}
	
	public void limpiarAction() {
		txtDescripcion.resetValue();
		txtTiempoEstimado.resetValue();
	}
	
	public void artefactoSeleccionadoAction(){
		txtEsfuerzoReal.setValue(artefactoSeleccionado.getEsfuerzoReal());
		txtEsfuerzoRestante.setValue(artefactoSeleccionado.getEsfuerzoRestante());
		txtPuntos.setValue(artefactoSeleccionado.getPuntos());
	}
}
