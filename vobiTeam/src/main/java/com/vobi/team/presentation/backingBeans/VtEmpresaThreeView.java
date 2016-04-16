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
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;


@ManagedBean
@ViewScoped
public class VtEmpresaThreeView {
	public final static Logger log=LoggerFactory.getLogger(VtEmpresaThreeView.class);

	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private TreeNode root;	
	private TreeNode selectedNode;
	
	private VtEmpresa laEmpresaSeleccionada;
	private VtProyecto proyectoSeleccionado;
	private VtPilaProducto backogSeleccionado;
	
	private List<VtEmpresa> lasEmpresas;
	private List<VtProyecto> losProyectos;
	private List<VtPilaProducto> losBacklogs;
	
	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();
	
	
	
	public VtEmpresaThreeView() throws Exception {
		super();		
		this.root = new DefaultTreeNode("root", null);
			
	}
	
	@PostConstruct
	public void init(){
		getLasEmpresas();
		getLosProyectos();
		getLosBacklogs();
		
		try {			
			
			for (VtEmpresa vtEmpresa : lasEmpresas) {
				TreeNode empresa = new DefaultTreeNode("Empresa",vtEmpresa, root);
				losProyectos = businessDelegatorView.findProyectsByEnterpriseIdentification(vtEmpresa);
				
				for (VtProyecto vtProyecto : losProyectos) {
					TreeNode proyecto = new DefaultTreeNode("Proyecto",vtProyecto, empresa);
					losBacklogs = businessDelegatorView.findBacklogByProyecto(vtProyecto);
					
					for (VtPilaProducto vtPilaProducto : losBacklogs) {
						TreeNode backlog = new DefaultTreeNode("Backlog",vtPilaProducto, proyecto);
					}					
				}				
			}	
		
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

	public void setSelectedNode(TreeNode selectedNode) {
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

	public VtPilaProducto getBackogSeleccionado() {
		return backogSeleccionado;
	}

	public void setBackogSeleccionado(VtPilaProducto backogSeleccionado) {
		this.backogSeleccionado = backogSeleccionado;
	}

	public String irEmpresa(){
		
		return "/XHTML/listaEmpresa.xhtml";
	}
	
	public String irProyectos(){
		
		laEmpresaSeleccionada = (VtEmpresa)selectedNode.getParent().getData();
		
		FacesUtils.putinSession("empresaSeleccionada", laEmpresaSeleccionada);		
		return "/XHTML/listaProyectos.xhtml";
	}
	
	public String irBacklog(){
		
		proyectoSeleccionado = (VtProyecto)selectedNode.getParent().getData();
		
		FacesUtils.putinSession("proyectoSeleccionado", proyectoSeleccionado);
		return "/XHTML/listaBacklog.xhtml";
	}
	

}
