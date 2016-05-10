package com.vobi.team.presentation.backingBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtInteres;
import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtProyectoUsuario;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.VtUsuarioArtefacto;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;


@ManagedBean
@ViewScoped
public class VtDesarrolladorTreeView {
	public final static Logger log=LoggerFactory.getLogger(VtDesarrolladorTreeView.class);

	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private VtProyecto proyectoSeleccionado;
	private VtArtefacto artefactoSeleccionado;
	
	private SelectOneMenu somProyectos;
	private List<SelectItem> losProyectos;
	
	private List<VtArtefacto> losArtefactos;
	
	
	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();	
	private VtUsuario desarrollador;
	
	
	
	@PostConstruct
	public void init(){
		try {
			desarrollador = businessDelegatorView.findUsuarioByLogin(usuarioActual);
			losArtefactos = new ArrayList<VtArtefacto>();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}
	
	//-------------------------------------Inicio de Gets y Sets--------------------------------------------
	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}
	
	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public String getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(String usuarioActual) {
		this.usuarioActual = usuarioActual;
	}

	public VtProyecto getProyectoSeleccionado() {
		return proyectoSeleccionado;
	}

	public void setProyectoSeleccionado(VtProyecto proyectoSeleccionado) {
		this.proyectoSeleccionado = proyectoSeleccionado;
	}

	public VtArtefacto getArtefactoSeleccionado() {
		return artefactoSeleccionado;
	}

	public void setArtefactoSeleccionado(VtArtefacto artefactoSeleccionado) {
		this.artefactoSeleccionado = artefactoSeleccionado;
	}

	public SelectOneMenu getSomProyectos() {
		return somProyectos;
	}

	public void setSomProyectos(SelectOneMenu somProyectos) {
		this.somProyectos = somProyectos;
	}

	public List<SelectItem> getLosProyectos() {
		
		try {
			if(losProyectos == null){
				VtUsuario usuario = businessDelegatorView.findUsuarioByLogin(usuarioActual);
				losProyectos = new ArrayList<SelectItem>();
				for (VtProyectoUsuario proyectoUsuario : usuario.getVtProyectoUsuarios()) {
					losProyectos.add(new SelectItem(proyectoUsuario.getVtProyecto().getProyCodigo(), proyectoUsuario.getVtProyecto().getNombre()));
				}							
			}	
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return losProyectos;
	}

	public void setLosProyectos(List<SelectItem> losProyectos) {
		this.losProyectos = losProyectos;
	}

	public List<VtArtefacto> getLosArtefactos() {
		return losArtefactos;
	}

	public void setLosArtefactos(List<VtArtefacto> losArtefactos) {
		this.losArtefactos = losArtefactos;
	}

	public VtUsuario getDesarrollador() {
		return desarrollador;
	}

	public void setDesarrollador(VtUsuario desarrollador) {
		this.desarrollador = desarrollador;
	}	
	

	//---------------------------------Fin de los Gets y Sets--------------------------------------------------------
	
	public void proyectoListener() throws Exception{
		String codigoProyecto = somProyectos.getValue().toString().trim();
		
		VtProyecto proyecto = businessDelegatorView.getVtProyecto(Long.parseLong(codigoProyecto));
		
		
		
		if(!codigoProyecto.equals("-1") ){			
			
			for (VtUsuarioArtefacto usuarioArtefacto : desarrollador.getVtUsuarioArtefactos()) {
				
				
				if(proyecto.getProyCodigo() == usuarioArtefacto.getVtArtefacto().getVtPilaProducto().getVtProyecto().getProyCodigo()){
					losArtefactos.add(usuarioArtefacto.getVtArtefacto());
				}
				
			}
		}
	}

	
	

}
