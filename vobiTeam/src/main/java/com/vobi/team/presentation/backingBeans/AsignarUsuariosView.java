package com.vobi.team.presentation.backingBeans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtProyectoUsuario;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;



@ViewScoped
@ManagedBean(name = "asignarUsuariosView")
public class AsignarUsuariosView {
	
	private final static Logger log=LoggerFactory.getLogger(AsignarUsuariosView.class);
	
	private VtProyecto proyectoSeleccionado;
	private CommandButton btnGenerar;
	private DualListModel<VtUsuario> losUsuariosSeleccionados;
	private List<VtUsuario> usuariosSource;
	private List<VtUsuario> usuariosTarget;
	private List<VtProyecto> losProyectos;
	
	@PostConstruct
	public void usuariosNoAsignados(){
		
		try {
			proyectoSeleccionado = businessDelegatorView.getVtProyecto(1L);
			usuariosSource = businessDelegatorView.getVtUsuarioNoAsignados(proyectoSeleccionado);
			usuariosTarget = businessDelegatorView.getVtUsuarioAsignados(proyectoSeleccionado);
			
			losUsuariosSeleccionados = new DualListModel<VtUsuario>(usuariosSource, usuariosTarget);
		} catch (Exception e1) {
			log.info("" + e1.getMessage());
		}
	}
	
	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}	
	
	public List<VtUsuario> getUsuariosSource() {
		return usuariosSource;
	}

	public void setUsuariosSource(List<VtUsuario> usuariosSource) {
		this.usuariosSource = usuariosSource;
	}

	public List<VtUsuario> getUsuariosTarget() {
		return usuariosTarget;
	}

	public void setUsuariosTarget(List<VtUsuario> usuariosTarget) {
		this.usuariosTarget = usuariosTarget;
	}

	public VtProyecto getProyectoSeleccionado() {
		return proyectoSeleccionado;
	}

	public void setProyectoSeleccionado(VtProyecto proyectoSeleccionado) {
		this.proyectoSeleccionado = proyectoSeleccionado;
	}

	public CommandButton getBtnGenerar() {
		return btnGenerar;
	}

	public void setBtnGenerar(CommandButton btnGenerar) {
		this.btnGenerar = btnGenerar;
	}

	public DualListModel<VtUsuario> getLosUsuariosSeleccionados() {
		return losUsuariosSeleccionados;
	}

	public void setLosUsuariosSeleccionados(DualListModel<VtUsuario> losUsuariosSeleccionados) {
		this.losUsuariosSeleccionados = losUsuariosSeleccionados;
	}
	
	public List<VtProyecto> getLosProyectos() {
		try {
			if (losProyectos == null) {
				losProyectos = businessDelegatorView.getVtProyecto();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}	
		return losProyectos;
	}

	public void setLosProyectos(List<VtProyecto> losProyectos) {
		this.losProyectos = losProyectos;
	}

	
	public void asignarProyectoAction() throws Exception {
		
		log.info(""+proyectoSeleccionado.getProyCodigo());
		usuariosSource = businessDelegatorView.getVtUsuarioNoAsignados(proyectoSeleccionado);
		usuariosTarget = businessDelegatorView.getVtUsuarioAsignados(proyectoSeleccionado);	
		VtProyectoUsuario proyectoUsuario = null;
		
		if (usuariosSource!=null) {
			
//			for (VtUsuario vtUsuario : usuariosTarget) {
//				proyectoUsuario = businessDelegatorView.findProyectoUsuarioByProyectoAndUsuario(proyectoSeleccionado.getProyCodigo(), vtUsuario.getUsuaCodigo());
//				if(proyectoUsuario.getActivo().equals("N")){
//					usuariosTarget.remove(vtUsuario);
//					usuariosSource.add(vtUsuario);
//					
//				}
//			}
			
			
			
			losUsuariosSeleccionados.setTarget(usuariosTarget);
			losUsuariosSeleccionados.setSource(usuariosSource);
		}
	
		
	}
	
	 public void onTransfer(TransferEvent event) throws Exception {
	        
	    	for(Object item : event.getItems()) {
	            VtUsuario vtUsuario=(VtUsuario) item;
//	            log.info(vtUsuario.getNombre());
//	            FacesUtils.addInfoMessage("Se paso un usuario"+vtUsuario.getNombre());
	            
	            //true si paso de izquierda a derecha
	            if(event.isAdd()){
	            	asignarUsuarioAction(vtUsuario, proyectoSeleccionado);
	            }
	            if(event.isRemove()){
	            	removerUsuarioAction(vtUsuario, proyectoSeleccionado);
	            }
	        }
	    	

	    	

	    }

		
	 public void asignarUsuarioAction(VtUsuario usuario, VtProyecto proyecto) throws Exception{
		 
		 try {
			 VtProyectoUsuario proyectoUsuario = businessDelegatorView.findProyectoUsuarioByProyectoAndUsuario(proyectoSeleccionado.getProyCodigo(), usuario.getUsuaCodigo());
			 
			 if(proyectoUsuario == null){
				 proyectoUsuario = new VtProyectoUsuario();
				 
				 proyectoUsuario.setVtUsuario(usuario);
				 proyectoUsuario.setVtProyecto(proyecto);
				 proyectoUsuario.setUsuCreador(1L);
				 proyectoUsuario.setUsuModificador(1L);
				 proyectoUsuario.setFechaCreacion(new Date());
				 proyectoUsuario.setFechaModificacion(new Date());
				 proyectoUsuario.setActivo("S");
				 
				 businessDelegatorView.saveVtProyectoUsuario(proyectoUsuario);
				 FacesUtils.addInfoMessage("Se ha asignado exitosamente al usuario");
			 }else{
				 proyectoUsuario.setActivo("S");
				 proyectoUsuario.setFechaModificacion(new Date());
				 businessDelegatorView.updateVtProyectoUsuario(proyectoUsuario);
				 FacesUtils.addInfoMessage("Se ha re-asignado exitosamente al usuario");
			 }
			 
			 
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	 }
	
	 public void removerUsuarioAction(VtUsuario usuario, VtProyecto proyecto) throws Exception{
		try {
			VtProyectoUsuario proyectoUsuario = businessDelegatorView.findProyectoUsuarioByProyectoAndUsuario(proyectoSeleccionado.getProyCodigo(), usuario.getUsuaCodigo());
			
			businessDelegatorView.deleteVtProyectoUsuario(proyectoUsuario);
			FacesUtils.addInfoMessage("Se ha desasignado exitosamente al usuario");
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	 
	 
	 
	 }

	
	
}
