package com.vobi.team.presentation.backingBeans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtUsuario;
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
	
	//....................................Empresa.................................................
	
	// PARA EL CREAR
		private InputText txtEmpresaCIdentificacion;
		private InputText txtEmpresaCNombre;

		private CommandButton btnEmpresaCCrear;
		private CommandButton btnEmpresaCLimpiar;
		///////////////////////////


		//PARA EL MODIFICAR
		private InputText txtEmpresaMIdentificacion;
		private InputText txtEmpresaMNombre;
		private SelectOneMenu somEmpresaActiva;

		private CommandButton btnEmpresaModificar;
		private CommandButton btnEmpresaMLimpiar;
		////////////////////////////


	//...........................................................................................
	
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
				
				if(losProyectos != null){
					for (VtProyecto vtProyecto : losProyectos) {
						TreeNode proyecto = new DefaultTreeNode("Proyecto",vtProyecto, empresa);
						losBacklogs = businessDelegatorView.findBacklogByProyecto(vtProyecto);
						
						if(losBacklogs != null){
							for (VtPilaProducto vtPilaProducto : losBacklogs) {
								TreeNode backlog = new DefaultTreeNode("Backlog",vtPilaProducto, proyecto);
							}
						}
											
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
	
	//...............................Gets y Sets de Empresa.............................
	public InputText getTxtEmpresaCIdentificacion() {
		return txtEmpresaCIdentificacion;
	}

	public void setTxtEmpresaCIdentificacion(InputText txtEmpresaCIdentificacion) {
		this.txtEmpresaCIdentificacion = txtEmpresaCIdentificacion;
	}

	public InputText getTxtEmpresaCNombre() {
		return txtEmpresaCNombre;
	}

	public void setTxtEmpresaCNombre(InputText txtEmpresaCNombre) {
		this.txtEmpresaCNombre = txtEmpresaCNombre;
	}

	public CommandButton getBtnEmpresaCCrear() {
		return btnEmpresaCCrear;
	}

	public void setBtnEmpresaCCrear(CommandButton btnEmpresaCCrear) {
		this.btnEmpresaCCrear = btnEmpresaCCrear;
	}

	public CommandButton getBtnEmpresaCLimpiar() {
		return btnEmpresaCLimpiar;
	}

	public void setBtnEmpresaCLimpiar(CommandButton btnEmpresaCLimpiar) {
		this.btnEmpresaCLimpiar = btnEmpresaCLimpiar;
	}

	public InputText getTxtEmpresaMIdentificacion() {
		return txtEmpresaMIdentificacion;
	}

	public void setTxtEmpresaMIdentificacion(InputText txtEmpresaMIdentificacion) {
		this.txtEmpresaMIdentificacion = txtEmpresaMIdentificacion;
	}

	public InputText getTxtEmpresaMNombre() {
		return txtEmpresaMNombre;
	}

	public void setTxtEmpresaMNombre(InputText txtEmpresaMNombre) {
		this.txtEmpresaMNombre = txtEmpresaMNombre;
	}

	public SelectOneMenu getSomEmpresaActiva() {
		return somEmpresaActiva;
	}

	public void setSomEmpresaActiva(SelectOneMenu somEmpresaActiva) {
		this.somEmpresaActiva = somEmpresaActiva;
	}

	public CommandButton getBtnEmpresaModificar() {
		return btnEmpresaModificar;
	}

	public void setBtnEmpresaModificar(CommandButton btnEmpresaModificar) {
		this.btnEmpresaModificar = btnEmpresaModificar;
	}

	public CommandButton getBtnEmpresaMLimpiar() {
		return btnEmpresaMLimpiar;
	}

	public void setBtnEmpresaMLimpiar(CommandButton btnEmpresaMLimpiar) {
		this.btnEmpresaMLimpiar = btnEmpresaMLimpiar;
	}	
	//..................................Fin de los Gets y Sets..........................................
	
	public void crearEmpresaAction() throws Exception{
		try {
			VtEmpresa vtEmpresa = new VtEmpresa();

			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if (txtEmpresaCIdentificacion.getValue().toString().trim().equals("") == true || txtEmpresaCIdentificacion.getValue() == null) {
				throw new Exception("Por favor digite la Identificacion de la Empresa");
			}
			if (txtEmpresaCNombre.getValue().toString().trim().equals("")== true || txtEmpresaCNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}

			String identificacion = txtEmpresaCIdentificacion.getValue().toString().trim();
			String nombre = txtEmpresaCNombre.getValue().toString();

			vtEmpresa.setIdentificacion(identificacion);
			vtEmpresa.setNombre(nombre);
			vtEmpresa.setActivo("S");
			vtEmpresa.setUsuCreador(vtUsuarioActual.getUsuaCodigo());
			vtEmpresa.setUsuModificador(vtUsuarioActual.getUsuaCodigo());
			vtEmpresa.setFechaCreacion(new Date());
			vtEmpresa.setFechaModificacion(new Date());

			businessDelegatorView.saveVtEmpresa(vtEmpresa);
			limpiarCrearEmpresaAction();
			FacesUtils.addInfoMessage("Se creó con éxito la empresa");
			
			new DefaultTreeNode("Empresa",vtEmpresa, root);
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void modificarEmpresaAction() throws Exception {
		try {
			laEmpresaSeleccionada = (VtEmpresa) selectedNode.getData();
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);
			
			if (txtEmpresaMNombre.getValue().toString().trim().equals("")== true || txtEmpresaMNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}

			if (somEmpresaActiva.getValue().equals("-1") ==true ) {
				throw new Exception("Por favor llene todos los campos");
			}
			
			VtEmpresa vtEmpresa = laEmpresaSeleccionada;			

			vtEmpresa.setNombre(laEmpresaSeleccionada.getNombre());
			vtEmpresa.setActivo(laEmpresaSeleccionada.getActivo());
			vtEmpresa.setUsuModificador(vtUsuarioActual.getUsuaCodigo());
			vtEmpresa.setFechaModificacion(new Date());

			businessDelegatorView.updateVtEmpresa(vtEmpresa);
			FacesUtils.addInfoMessage("Se modificó con éxito la empresa");
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			
		}
	}

	public void modificarListener() throws Exception{	
	
		laEmpresaSeleccionada = (VtEmpresa) selectedNode.getData();
		txtEmpresaMNombre.setValue(laEmpresaSeleccionada.getNombre());
		somEmpresaActiva.setValue(laEmpresaSeleccionada.getActivo());
		
	}
	
	public void limpiarCrearEmpresaAction() {

		txtEmpresaCIdentificacion.resetValue();
		txtEmpresaCNombre.resetValue();
	}
	public void limpiarModificarEmpresaAction() {
		
		
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
