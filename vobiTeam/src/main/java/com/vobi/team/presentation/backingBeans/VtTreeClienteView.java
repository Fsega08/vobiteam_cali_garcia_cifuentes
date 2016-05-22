package com.vobi.team.presentation.backingBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtProyectoUsuario;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;


@ManagedBean
@ViewScoped
public class VtTreeClienteView {
	public final static Logger log=LoggerFactory.getLogger(VtTreeClienteView.class);

	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private TreeNode root;	
	private DefaultTreeNode selectedNode;
	
	private VtEmpresa laEmpresaSeleccionada;
	private VtProyecto proyectoSeleccionado;
	private VtPilaProducto backlogSeleccionado;
	
	private List<VtEmpresa> lasEmpresas;
	private List<VtProyecto> losProyectos;
	private List<VtPilaProducto> losBacklogs;
	
	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();
		
	
	public VtTreeClienteView() throws Exception {
		super();		
		this.root = new DefaultTreeNode("root", null);
			
	}
	
	@PostConstruct
	public void init(){
		
		try {			
			VtUsuario usuario = businessDelegatorView.findUsuarioByLogin(usuarioActual);			
			
			TreeNode empresa = new DefaultTreeNode("Empresa",usuario.getVtEmpresa(), root);	
			
			if (usuario.getVtProyectoUsuarios() != null) {
				for (VtProyectoUsuario vtProyectoUsuarios  : usuario.getVtProyectoUsuarios()) {
					TreeNode proyecto = new DefaultTreeNode("Proyecto",vtProyectoUsuarios.getVtProyecto(), empresa);
					
					losBacklogs = businessDelegatorView.findBacklogByProyecto(vtProyectoUsuarios.getVtProyecto());
					
					if(losBacklogs != null){
						for (VtPilaProducto vtPilaProducto : losBacklogs) {
							new DefaultTreeNode("Backlog",vtPilaProducto, proyecto);
						}
					}
				}
			}			
			
			
			FacesUtils.putinSession("proyectoSeleccionado", null);
			FacesUtils.putinSession("empresaSeleccionada", null);
			FacesUtils.putinSession("backogSeleccionado", null);
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

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

	public VtEmpresa getLaEmpresaSeleccionada() {
		return laEmpresaSeleccionada;
	}

	public void setLaEmpresaSeleccionada(VtEmpresa laEmpresaSeleccionada) {
		this.laEmpresaSeleccionada = laEmpresaSeleccionada;
	}

	
	public List<VtEmpresa> getLasEmpresas() {
		try {
			if (lasEmpresas == null) {
				lasEmpresas = businessDelegatorView.getVtEmpresa();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return lasEmpresas;
	}

	public void setLasEmpresas(List<VtEmpresa> lasEmpresas) {
		this.lasEmpresas = lasEmpresas;
	}	
	
	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(DefaultTreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public List<VtProyecto> getLosProyectos() {
		try {
			if (losProyectos == null ) {
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

	public List<VtPilaProducto> getLosBacklogs() {
		try {
			if(losBacklogs == null){
				losBacklogs = businessDelegatorView.getVtPilaProducto();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}

		return losBacklogs;
	}

	public void setLosBacklogs(List<VtPilaProducto> losBacklogs) {
		this.losBacklogs = losBacklogs;
	}

	public VtProyecto getProyectoSeleccionado() {
		return proyectoSeleccionado;
	}

	public void setProyectoSeleccionado(VtProyecto proyectoSeleccionado) {
		this.proyectoSeleccionado = proyectoSeleccionado;
	}

	public VtPilaProducto getBacklogSeleccionado() {
		return backlogSeleccionado;
	}

	public void setBacklogSeleccionado(VtPilaProducto backogSeleccionado) {
		this.backlogSeleccionado = backogSeleccionado;
	}	
	
	//...............................Gets y Sets .............................
	
	


	
	//....................................................................................................................

	public String artefactoListener(){
		
		backlogSeleccionado = (VtPilaProducto)selectedNode.getData();
		//Guardo objeto en la sesion
		if (backlogSeleccionado.getActivo().equals("S")) {
			FacesUtils.putinSession("backlogSeleccionado", backlogSeleccionado);
			return "/cliente/listarArtefactos.xhtml";
		}
		else{
			FacesUtils.addErrorMessage("La pila producto esta inactiva");
			return "";
		}

	}

	public String sprintListener(){
		
		backlogSeleccionado = (VtPilaProducto)selectedNode.getData();
		//Guardo objeto en la sesion
		if (backlogSeleccionado.getActivo().equals("S")) {
			FacesUtils.putinSession("backlogSeleccionado", backlogSeleccionado);
			proyectoSeleccionado = backlogSeleccionado.getVtProyecto();
			FacesUtils.putinSession("proyectoSeleccionado", proyectoSeleccionado);
			return "/cliente/listaSprint.xhtml";
		}
		else{
			FacesUtils.addErrorMessage("La pila producto esta inactiva");
			return "";
		}

	}
	
	public String regresarAction(){
		FacesUtils.putinSession("backlogSeleccionado", null);
		FacesUtils.putinSession("proyectoSeleccionado", null);
		FacesUtils.putinSession("sprintSeleccionado", null);
		
		return "/cliente/TreeTableCliente.xhtml";
	}
	
	public String artefactosAction(){

		return "/cliente/listarArtefactos.xhtml";

	}
	
	public String sprintAction(){ 
		FacesUtils.putinSession("sprintSeleccionado", null);
		return "/cliente/listaSprint.xhtml";

	}
	
	
	

}
