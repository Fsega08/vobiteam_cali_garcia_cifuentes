package com.vobi.team.presentation.backingBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.modelo.VtArchivo;
import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtEstado;
import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtPrioridad;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtProyectoUsuario;
import com.vobi.team.modelo.VtTipoArtefacto;
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
	
	private VtUsuario usuario;
	
	///////////////////Cliente SOM Reporte/////////////////////////
	
	private SelectOneMenu somProyectos;
	private List<SelectItem> losProyectosSOM;
	
	private SelectOneMenu somBacklogs;
	private List<SelectItem> losBacklogsSOM;	
	
	private InputText txtCrearNombre;
	private InputTextarea txtCrearDescripcion;
	private List<VtArchivo> subirArchivos;

	private List<SelectItem> losCrearTiposArtefactos;
	private SelectOneMenu somCrearTipoArtefacto;
	private List<SelectItem> lasCrearPrioridadesArtefactos;
	private SelectOneMenu somCrearPrioridadesArtefacto;
	
	//////////////////////////////////////////////////////////////

	
	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();
		
	
	public VtTreeClienteView() throws Exception {
		super();		
		this.root = new DefaultTreeNode("root", null);
			
	}
	
	@PostConstruct
	public void init(){
		
		try {			
			usuario = businessDelegatorView.findUsuarioByLogin(usuarioActual);			
			subirArchivos = new ArrayList<VtArchivo>();
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
	
	public List<SelectItem> getLosCrearTiposArtefactos() {
		try {
			if (losCrearTiposArtefactos==null) {
				List<VtTipoArtefacto> listaTiposArtefactos = businessDelegatorView.getVtTipoArtefacto();
				losCrearTiposArtefactos = new ArrayList<SelectItem>();
				for (VtTipoArtefacto vtTipoArtefacto : listaTiposArtefactos) {
					losCrearTiposArtefactos.add(new SelectItem(vtTipoArtefacto.getTparCodigo(), vtTipoArtefacto.getNombre()));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return losCrearTiposArtefactos;
	}
	
	public InputText getTxtCrearNombre() {
		return txtCrearNombre;
	}

	public void setTxtCrearNombre(InputText txtCrearNombre) {
		this.txtCrearNombre = txtCrearNombre;
	}

	public InputTextarea getTxtCrearDescripcion() {
		return txtCrearDescripcion;
	}

	public void setTxtCrearDescripcion(InputTextarea txtCrearDescripcion) {
		this.txtCrearDescripcion = txtCrearDescripcion;
	}

	public List<VtArchivo> getSubirArchivos() {
		return subirArchivos;
	}

	public void setSubirArchivos(List<VtArchivo> subirArchivos) {
		this.subirArchivos = subirArchivos;
	}

	public void setLosCrearTiposArtefactos(List<SelectItem> losCrearTiposArtefactos) {
		this.losCrearTiposArtefactos = losCrearTiposArtefactos;
	}

	public SelectOneMenu getSomCrearTipoArtefacto() {
		return somCrearTipoArtefacto;
	}

	public void setSomCrearTipoArtefacto(SelectOneMenu somCrearTipoArtefacto) {
		this.somCrearTipoArtefacto = somCrearTipoArtefacto;
	}

	
	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}
	
	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}
	
	
	public SelectOneMenu getSomProyectos() {
		return somProyectos;
	}

	public void setSomProyectos(SelectOneMenu somProyectos) {
		this.somProyectos = somProyectos;
	}
	
	public List<SelectItem> getLasCrearPrioridadesArtefactos() {
		try {
			if (lasCrearPrioridadesArtefactos==null) {
				List<VtPrioridad> listaPrioridadesArtefactos = businessDelegatorView.getVtPrioridad();
				lasCrearPrioridadesArtefactos = new ArrayList<SelectItem>();
				for (VtPrioridad vtPrioridad : listaPrioridadesArtefactos) {
					lasCrearPrioridadesArtefactos.add(new SelectItem(vtPrioridad.getPrioCodigo(), vtPrioridad.getNombre()));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return lasCrearPrioridadesArtefactos;
	}

	public void setLasCrearPrioridadesArtefactos(List<SelectItem> lasCrearPrioridadesArtefactos) {
		this.lasCrearPrioridadesArtefactos = lasCrearPrioridadesArtefactos;
	}

	public SelectOneMenu getSomCrearPrioridadesArtefacto() {
		return somCrearPrioridadesArtefacto;
	}

	public void setSomCrearPrioridadesArtefacto(SelectOneMenu somCrearPrioridadesArtefacto) {
		this.somCrearPrioridadesArtefacto = somCrearPrioridadesArtefacto;
	}

	public List<SelectItem> getLosProyectosSOM() {

		try {
			if(losProyectosSOM == null){
				VtUsuario usuario = businessDelegatorView.findUsuarioByLogin(usuarioActual);
				losProyectosSOM = new ArrayList<SelectItem>();
				for (VtProyectoUsuario proyectoUsuario : usuario.getVtProyectoUsuarios()) {
					losProyectosSOM.add(new SelectItem(proyectoUsuario.getVtProyecto().getProyCodigo(), proyectoUsuario.getVtProyecto().getNombre()));
				}							
			}	

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return losProyectosSOM;
	}

	public void setLosProyectosSOM(List<SelectItem> losProyectosSOM) {
		this.losProyectosSOM = losProyectosSOM;
	}
	
	public SelectOneMenu getSomBacklogs() {
		return somBacklogs;
	}

	public void setSomBacklogs(SelectOneMenu somBacklogs) {
		this.somBacklogs = somBacklogs;
	}

	public List<SelectItem> getLosBacklogsSOM() {
		
		try {
			if(losBacklogsSOM == null){
				losBacklogsSOM = new ArrayList<SelectItem>();	
				
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return losBacklogsSOM;
	}

	public void setLosBacklogsSOM(List<SelectItem> losBacklogsSOM) {
		this.losBacklogsSOM = losBacklogsSOM;
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
	
	public void crearReportarNoConformidadCliente() {
		somCrearTipoArtefacto.setValue("3");
	}
	
	public void crearReportarControlCambioCliente() {
		somCrearTipoArtefacto.setValue("2");
	}
	
	public void crearReporteClienteAction() {

		try {
			
			if (somProyectos.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione un proyecto para el artefacto");
			}
			
			if (somBacklogs.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione una pila de producto para el artefacto");
			}
			
			if (txtCrearNombre.getValue().toString().trim().equals("") == true || txtCrearNombre.getValue() == null) {
				throw new Exception("Por favor ingrese el nombre");
			}
			if (txtCrearDescripcion.getValue().toString().trim().equals("") == true || txtCrearDescripcion.getValue() == null) {
				throw new Exception("Por favor ingrese la descripción");
			}
		
			if (somCrearPrioridadesArtefacto.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione una prioridad para el artefacto");
			}
			if (somCrearTipoArtefacto.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione un tipo de artefacto");
			}

			VtArtefacto vtArtefacto = new VtArtefacto();
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);
			vtArtefacto.setTitulo(txtCrearNombre.getValue().toString());
			vtArtefacto.setDescripcion(txtCrearDescripcion.getValue().toString());
			vtArtefacto.setEsfuerzoEstimado(0);
			vtArtefacto.setEsfuerzoRestante(0);
			vtArtefacto.setEsfuerzoReal(0);
			vtArtefacto.setOrigen(vtUsuarioActual.getNombre());
			vtArtefacto.setPuntos(0);
			vtArtefacto.setActivo("S");
			vtArtefacto.setFechaCreacion(new Date());
			vtArtefacto.setFechaModificacion(new Date());
			vtArtefacto.setUsuCreador(vtUsuarioActual.getUsuaCodigo());
			vtArtefacto.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			VtEstado vtEstado = businessDelegatorView.getVtEstado(1L);
			vtArtefacto.setVtEstado(vtEstado);

			VtTipoArtefacto vtTipoArtefacto = businessDelegatorView.getVtTipoArtefacto(Long.parseLong(somCrearTipoArtefacto.getValue().toString().trim()));
			vtArtefacto.setVtTipoArtefacto(vtTipoArtefacto);

			VtPrioridad vtPrioridad = businessDelegatorView.getVtPrioridad(Long.parseLong(somCrearPrioridadesArtefacto.getValue().toString().trim()));
			vtArtefacto.setVtPrioridad(vtPrioridad);
			
			VtPilaProducto vtPilaProducto = businessDelegatorView.getVtPilaProducto(Long.parseLong(somBacklogs.getValue().toString().trim()));
			vtArtefacto.setVtPilaProducto(vtPilaProducto);
			
			businessDelegatorView.saveVtArtefacto(vtArtefacto);				
			subirArchivos(vtArtefacto);
			
			FacesUtils.addInfoMessage("El artefacto se ha creado con exito");	
			
			businessDelegatorView.correoReporteCliente(vtUsuarioActual, vtArtefacto);
			
			limpiarCrearClienteAction();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}
	
	private void subirArchivos(VtArtefacto vtArtefacto) throws Exception{
		if (subirArchivos != null || !(subirArchivos.isEmpty())) {
			for (VtArchivo vtArchivo : subirArchivos) {
				vtArchivo.setVtArtefacto(vtArtefacto);
				businessDelegatorView.saveVtArchivo(vtArchivo);
			}
			
			subirArchivos = new ArrayList<VtArchivo>();			
		}
	}
	
	public void limpiarCrearClienteAction() {
		txtCrearDescripcion.resetValue();
		txtCrearNombre.resetValue();
		somCrearPrioridadesArtefacto.setValue("-1");
	}
	
	
	/////////////////////////////CLIENTE SOM!///////////////////////////////////////////////////
	
	public void proyectoListener() throws Exception{
		String codigoProyecto = somProyectos.getValue().toString().trim();

		VtProyecto proyecto = businessDelegatorView.getVtProyecto(Long.parseLong(codigoProyecto));

		if(!codigoProyecto.equals("-1") ){			
			
			List<VtPilaProducto> vtPilaProductos =  businessDelegatorView.findBacklogByProyecto(proyecto);
			
			for (VtPilaProducto vtPilaProducto : vtPilaProductos) {
				losBacklogsSOM.add(new SelectItem(vtPilaProducto.getPilaCodigo(), vtPilaProducto.getNombre()));
			}
			
			
		}
	}
	
	public void createHandleFileUpload(FileUploadEvent event) {

		try {
			VtUsuario vtUsuario = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			VtArchivo vtArchivo = new VtArchivo();

			vtArchivo.setNombre(event.getFile().getFileName());
			vtArchivo.setFechaCreacion(new Date());
			vtArchivo.setFechaModificacion(new Date());
			vtArchivo.setUsuCreador(vtUsuario.getUsuaCodigo());
			vtArchivo.setUsuModificador(vtUsuario.getUsuaCodigo());
			vtArchivo.setActivo("S");
			vtArchivo.setArchivo(event.getFile().getContents());

			subirArchivos.add(vtArchivo);
			
			FacesUtils.addInfoMessage("El archivo esta listo para cargarlo, presione cargar cuando este listo.");	
		} catch (Exception e) {
			log.info(e.getMessage());
			FacesUtils.addInfoMessage(e.getMessage());
		}
	}

	
	////////////////////////////////////////////////////////////////////////////////////////////
	

}
