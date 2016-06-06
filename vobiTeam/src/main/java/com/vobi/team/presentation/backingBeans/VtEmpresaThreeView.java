package com.vobi.team.presentation.backingBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
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
public class VtEmpresaThreeView {
	public final static Logger log=LoggerFactory.getLogger(VtEmpresaThreeView.class);

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

	//..................................Proyecto.................................................
	//..............Crear...................
	private InputText txtProyectoCNombre;
	private InputTextarea txtProyectoCDescripcion;

	private CommandButton btnProyectoCrear;
	private CommandButton btnProyectoCLimpiar;
	//.......................................

	//...............Modificar...................
	private InputText txtProyectoMNombre;
	private InputTextarea txtProyectoMDescripcion;

	private SelectOneMenu somProyectoActivo;
	private SelectOneMenu somProyectoPublico;

	private	CommandButton btnProyectoModificar;
	private CommandButton btnProyectoMLimpiar;
	//...........................................

	private CommandButton btnCrearProyecto;	

	//....................Picklist.....................
	private DualListModel<VtUsuario> losUsuariosSeleccionados;
	private List<VtUsuario> usuariosSource;
	private List<VtUsuario> usuariosTarget;
	//................................................		
	//...........................................................................................	

	//.....................................Backlog..............................................
	//................Para el crear backlog...............
	private InputText txtBacklogCNombre;
	private InputTextarea txtBacklogCDescripcion;

	private CommandButton btnCrearBacklog;
	private CommandButton btnBacklogCLimpiar;
	//....................................................


	//.................Para modificar backlog................
	private InputText txtBacklogMNombre;
	private InputTextarea txtBacklogMDescripcion;
	private SelectOneMenu somBacklogActivo;

	private CommandButton btnModificarBacklog;
	private CommandButton btnBacklogMLimpiar;
	//....................................................


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

			proyectoSeleccionado = businessDelegatorView.getVtProyecto(1L);

			usuariosSource = businessDelegatorView.getVtUsuarioNoAsignados(proyectoSeleccionado);
			usuariosTarget = businessDelegatorView.getVtUsuarioAsignados(proyectoSeleccionado);

			losUsuariosSeleccionados = new DualListModel<VtUsuario>(usuariosSource, usuariosTarget);

			FacesUtils.putinSession("proyectoSeleccionado", null);
			FacesUtils.putinSession("empresaSeleccionada", null);
			FacesUtils.putinSession("backogSeleccionado", null);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@PreDestroy
	public void destroy(){

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
	//..................................Gets y Sets de Proyecto.........................................
	public InputText getTxtProyectoCNombre() {
		return txtProyectoCNombre;
	}

	public void setTxtProyectoCNombre(InputText txtProyectoCNombre) {
		this.txtProyectoCNombre = txtProyectoCNombre;
	}

	public InputTextarea getTxtProyectoCDescripcion() {
		return txtProyectoCDescripcion;
	}

	public void setTxtProyectoCDescripcion(InputTextarea txtProyectoCDescripcion) {
		this.txtProyectoCDescripcion = txtProyectoCDescripcion;
	}

	public CommandButton getBtnProyectoCrear() {
		return btnProyectoCrear;
	}

	public void setBtnProyectoCrear(CommandButton btnProyectoCrear) {
		this.btnProyectoCrear = btnProyectoCrear;
	}

	public CommandButton getBtnProyectoCLimpiar() {
		return btnProyectoCLimpiar;
	}

	public void setBtnProyectoCLimpiar(CommandButton btnProyectoCLimpiar) {
		this.btnProyectoCLimpiar = btnProyectoCLimpiar;
	}

	public InputText getTxtProyectoMNombre() {
		return txtProyectoMNombre;
	}

	public void setTxtProyectoMNombre(InputText txtProyectoMNombre) {
		this.txtProyectoMNombre = txtProyectoMNombre;
	}

	public InputTextarea getTxtProyectoMDescripcion() {
		return txtProyectoMDescripcion;
	}

	public void setTxtProyectoMDescripcion(InputTextarea txtProyectoMDescripcion) {
		this.txtProyectoMDescripcion = txtProyectoMDescripcion;
	}

	public SelectOneMenu getSomProyectoActivo() {
		return somProyectoActivo;
	}

	public void setSomProyectoActivo(SelectOneMenu somProyectoActivo) {
		this.somProyectoActivo = somProyectoActivo;
	}

	public SelectOneMenu getSomProyectoPublico() {
		return somProyectoPublico;
	}

	public void setSomProyectoPublico(SelectOneMenu somProyectoPublico) {
		this.somProyectoPublico = somProyectoPublico;
	}

	public CommandButton getBtnProyectoModificar() {
		return btnProyectoModificar;
	}

	public void setBtnProyectoModificar(CommandButton btnProyectoModificar) {
		this.btnProyectoModificar = btnProyectoModificar;
	}

	public CommandButton getBtnProyectoMLimpiar() {
		return btnProyectoMLimpiar;
	}

	public void setBtnProyectoMLimpiar(CommandButton btnProyectoMLimpiar) {
		this.btnProyectoMLimpiar = btnProyectoMLimpiar;
	}

	public CommandButton getBtnCrearProyecto() {
		return btnCrearProyecto;
	}

	public void setBtnCrearProyecto(CommandButton btnCrearProyecto) {
		this.btnCrearProyecto = btnCrearProyecto;
	}

	public DualListModel<VtUsuario> getLosUsuariosSeleccionados() {
		return losUsuariosSeleccionados;
	}

	public void setLosUsuariosSeleccionados(DualListModel<VtUsuario> losUsuariosSeleccionados) {
		this.losUsuariosSeleccionados = losUsuariosSeleccionados;
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

	//..................................Fin de los Gets y Sets..........................................
	//..................................Gets y Sets de Backlog.........................................

	public InputText getTxtBacklogCNombre() {
		return txtBacklogCNombre;
	}

	public void setTxtBacklogCNombre(InputText txtBacklogCNombre) {
		this.txtBacklogCNombre = txtBacklogCNombre;
	}

	public InputTextarea getTxtBacklogCDescripcion() {
		return txtBacklogCDescripcion;
	}

	public void setTxtBacklogCDescripcion(InputTextarea txtBacklogCDescripcion) {
		this.txtBacklogCDescripcion = txtBacklogCDescripcion;
	}

	public CommandButton getBtnCrearBacklog() {
		return btnCrearBacklog;
	}

	public void setBtnCrearBacklog(CommandButton btnCrearBacklog) {
		this.btnCrearBacklog = btnCrearBacklog;
	}

	public CommandButton getBtnBacklogCLimpiar() {
		return btnBacklogCLimpiar;
	}

	public void setBtnBacklogCLimpiar(CommandButton btnBacklogCLimpiar) {
		this.btnBacklogCLimpiar = btnBacklogCLimpiar;
	}

	public InputText getTxtBacklogMNombre() {
		return txtBacklogMNombre;
	}

	public void setTxtBacklogMNombre(InputText txtBacklogMNombre) {
		this.txtBacklogMNombre = txtBacklogMNombre;
	}

	public InputTextarea getTxtBacklogMDescripcion() {
		return txtBacklogMDescripcion;
	}

	public void setTxtBacklogMDescripcion(InputTextarea txtBacklogMDescripcion) {
		this.txtBacklogMDescripcion = txtBacklogMDescripcion;
	}

	public SelectOneMenu getSomBacklogActivo() {
		return somBacklogActivo;
	}

	public void setSomBacklogActivo(SelectOneMenu somBacklogActivo) {
		this.somBacklogActivo = somBacklogActivo;
	}

	public CommandButton getBtnModificarBacklog() {
		return btnModificarBacklog;
	}

	public void setBtnModificarBacklog(CommandButton btnModificarBacklog) {
		this.btnModificarBacklog = btnModificarBacklog;
	}

	public CommandButton getBtnBacklogMLimpiar() {
		return btnBacklogMLimpiar;
	}

	public void setBtnBacklogMLimpiar(CommandButton btnBacklogMLimpiar) {
		this.btnBacklogMLimpiar = btnBacklogMLimpiar;
	}

	//..................................................................................................
	public void crearEmpresaAction() throws Exception{
		try {
			VtEmpresa vtEmpresa = new VtEmpresa();

			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if (txtEmpresaCIdentificacion.getValue().toString().trim().equals("") == true || txtEmpresaCIdentificacion.getValue() == null) {
				throw new Exception("Por favor digite la Identificacion de la Empresa");
			}
			if (txtEmpresaCNombre.getValue().toString().trim().equals("")== true || txtEmpresaCNombre.getValue() == null) {
				throw new Exception("Por favor digite el nombre de la Empresa");
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
				throw new Exception("Por favor digite el nombre de la Empresa");
			}

			if (somEmpresaActiva.getValue().equals("-1") ==true ) {
				throw new Exception("Por favor digite el estado de la Empresa");
			}

			VtEmpresa vtEmpresa = laEmpresaSeleccionada;			

			vtEmpresa.setNombre(txtEmpresaMNombre.getValue().toString().trim());
			vtEmpresa.setActivo(somEmpresaActiva.getValue().toString());
			vtEmpresa.setUsuModificador(vtUsuarioActual.getUsuaCodigo());
			vtEmpresa.setFechaModificacion(new Date());

			businessDelegatorView.updateVtEmpresa(vtEmpresa);
			FacesUtils.addInfoMessage("Se modificó con éxito la empresa");
			selectedNode.setData(vtEmpresa);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}

	public void modificarListener() throws Exception{	
		try {
			laEmpresaSeleccionada = (VtEmpresa) selectedNode.getData();
			txtEmpresaMNombre.setValue(laEmpresaSeleccionada.getNombre());
			somEmpresaActiva.setValue(laEmpresaSeleccionada.getActivo());


		} catch (Exception e) {
			log.info(e.getMessage());
		}		
	}

	public void limpiarCrearEmpresaAction() {

		txtEmpresaCIdentificacion.resetValue();
		txtEmpresaCNombre.resetValue();
	}
	public void limpiarModificarEmpresaAction() {
		txtEmpresaMNombre.resetValue();
		somEmpresaActiva.resetValue();

	}
	//........................................................................................................
	public void crearProyectoAction() throws Exception {

		try {
			if (txtProyectoCNombre.getValue().toString().trim().equals("") == true || txtProyectoCNombre.getValue() == null) {
				throw new Exception("Por favor digite el nombre del proyecto");
			}
			if (txtProyectoCDescripcion.getValue().toString().trim().equals("") == true || txtProyectoCDescripcion.getValue() == null) {
				throw new Exception("Por favor digite la descripción del proyecto");
			}
			laEmpresaSeleccionada = (VtEmpresa)selectedNode.getData();
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			VtProyecto vtProyecto = new VtProyecto();

			vtProyecto.setNombre(txtProyectoCNombre.getValue().toString());
			vtProyecto.setDescripcion(txtProyectoCDescripcion.getValue().toString());
			vtProyecto.setPublico("S");
			vtProyecto.setActivo("S");
			vtProyecto.setVtEmpresa(laEmpresaSeleccionada);
			vtProyecto.setFechaCreacion(new Date());
			vtProyecto.setFechaModificacion(new Date());
			vtProyecto.setUsuCreador(vtUsuarioActual.getUsuaCodigo());
			vtProyecto.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			businessDelegatorView.saveVtProyecto(vtProyecto);

			FacesUtils.addInfoMessage("Se ha creado el Proyecto con éxito");

			limpiarProyectoCAction();		

			TreeNode proyecto = new DefaultTreeNode("Proyecto",vtProyecto, selectedNode);

			List<VtPilaProducto> losBacklog = businessDelegatorView.findBacklogByProyecto(vtProyecto);
			if(losBacklog!=null){
				List<TreeNode> hijos = new ArrayList<TreeNode>();
				for (VtPilaProducto vtPilaProducto : losBacklog) {                	   				
					hijos.add(new DefaultTreeNode("Backlog",vtPilaProducto, proyecto));
				}
			}


		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void modificarProyectoAction() throws Exception {

		try {

			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if (txtProyectoMNombre.getValue().toString().trim().equals("") == true || txtProyectoMNombre.getValue() == null) {
				throw new Exception("Por favor digite el nombre del Proyecto");
			}
			if (txtProyectoMDescripcion.getValue().toString().trim().equals("") == true || txtProyectoMDescripcion.getValue() == null) {
				throw new Exception("Por favor digite la descripción del Proyecto");
			}
			if (somProyectoActivo.getValue().equals("-1") ==true ) {
				throw new Exception("Por favor seleccione el estado del Proyecto");
			}

			if (somProyectoPublico.getValue().equals("-1") ==true ) {
				throw new Exception("Por favor seleccione si el proyecto es público o privada");
			}


			proyectoSeleccionado.setNombre(txtProyectoMNombre.getValue().toString());			
			proyectoSeleccionado.setDescripcion(txtProyectoMDescripcion.getValue().toString());			
			proyectoSeleccionado.setPublico(somProyectoPublico.getValue().toString().trim());			
			proyectoSeleccionado.setActivo(somProyectoActivo.getValue().toString().trim());			
			proyectoSeleccionado.setUsuModificador(vtUsuarioActual.getUsuaCodigo());			
			proyectoSeleccionado.setFechaModificacion(new Date());			

			businessDelegatorView.updateVtProyecto(proyectoSeleccionado);

			FacesUtils.addInfoMessage("Se ha modificado el proyecto con éxito");
			selectedNode.setData(proyectoSeleccionado);	


			List<VtPilaProducto> losBacklog = businessDelegatorView.findBacklogByProyecto(proyectoSeleccionado);
			if(losBacklog!=null){
				List<TreeNode> hijos = selectedNode.getChildren();
				hijos.clear();
				for (VtPilaProducto vtPilaProducto : losBacklog) {                	
					vtPilaProducto.setActivo(proyectoSeleccionado.getActivo());
					vtPilaProducto.setUsuModificador(proyectoSeleccionado.getUsuModificador());
					vtPilaProducto.setFechaModificacion(proyectoSeleccionado.getFechaModificacion());
					businessDelegatorView.updateVtPilaProducto(vtPilaProducto);

					hijos.add(new DefaultTreeNode("Backlog",vtPilaProducto, selectedNode));
				}
			}



		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void limpiarProyectoCAction() {
		txtProyectoCNombre.resetValue();
		txtProyectoCDescripcion.resetValue();

	}

	public void limpiarProyectoAction() {
		txtProyectoMNombre.resetValue();
		txtProyectoMDescripcion.resetValue();
		somProyectoActivo.setValue("-1");
		somProyectoPublico.setValue("-1");
	}

	public void modificarProyectoListener() {		

		proyectoSeleccionado = (VtProyecto) selectedNode.getData();	
		txtProyectoMNombre.setValue(proyectoSeleccionado.getNombre());
		txtProyectoMDescripcion.setValue(proyectoSeleccionado.getDescripcion());
		somProyectoActivo.setValue(proyectoSeleccionado.getActivo());
		somProyectoPublico.setValue(proyectoSeleccionado.getPublico());



	}
	//............................................Picklist...............................................................
	public void asignarProyectoAction() throws Exception {
		proyectoSeleccionado = (VtProyecto) selectedNode.getData();

		usuariosSource = businessDelegatorView.getVtUsuarioNoAsignados(proyectoSeleccionado);
		usuariosTarget = businessDelegatorView.getVtUsuarioAsignados(proyectoSeleccionado);	

		if (usuariosSource!=null) {		
			losUsuariosSeleccionados.setTarget(usuariosTarget);
			losUsuariosSeleccionados.setSource(usuariosSource);
		}


	}

	public void onTransfer(TransferEvent event) throws Exception {		

		for(Object item : event.getItems()) {
			VtUsuario vtUsuario=(VtUsuario) item;

			//true si paso de izquierda a derecha
			if(event.isAdd()){
				asignarUsuarioAction(vtUsuario, proyectoSeleccionado);
			}
			if(event.isRemove()){
				removerUsuarioAction(vtUsuario, proyectoSeleccionado);
			}
		}

		FacesUtils.addInfoMessage("Usuario(s) Transferidos");

	}


	public void asignarUsuarioAction(VtUsuario usuario, VtProyecto proyecto) throws Exception{

		try {
			VtProyectoUsuario proyectoUsuario = businessDelegatorView.findProyectoUsuarioByProyectoAndUsuario(proyectoSeleccionado.getProyCodigo(), usuario.getUsuaCodigo());
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if(proyectoUsuario == null){
				proyectoUsuario = new VtProyectoUsuario();

				proyectoUsuario.setVtUsuario(usuario);
				proyectoUsuario.setVtProyecto(proyecto);
				proyectoUsuario.setUsuCreador(vtUsuarioActual.getUsuaCodigo());
				proyectoUsuario.setUsuModificador(vtUsuarioActual.getUsuaCodigo());
				proyectoUsuario.setFechaCreacion(new Date());
				proyectoUsuario.setFechaModificacion(new Date());
				proyectoUsuario.setActivo("S");

				businessDelegatorView.saveVtProyectoUsuario(proyectoUsuario);

			}else{
				proyectoUsuario.setActivo("S");
				proyectoUsuario.setFechaModificacion(new Date());
				proyectoUsuario.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

				businessDelegatorView.updateVtProyectoUsuario(proyectoUsuario);
			}


		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void removerUsuarioAction(VtUsuario usuario, VtProyecto proyecto) throws Exception{
		try {
			VtProyectoUsuario proyectoUsuario = businessDelegatorView.findProyectoUsuarioByProyectoAndUsuario(proyectoSeleccionado.getProyCodigo(), usuario.getUsuaCodigo());
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			proyectoUsuario.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			businessDelegatorView.deleteVtProyectoUsuario(proyectoUsuario);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}
	//....................................................................................................................
	public void crearBacklogAction() {
		try {
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);
			if (txtBacklogCNombre.getValue().toString().trim().equals("") == true || txtBacklogCNombre.getValue() == null) {
				throw new Exception("Por favor ingrese el nombre");
			}
			if (txtBacklogCDescripcion.getValue().toString().trim().equals("") == true || txtBacklogCDescripcion.getValue() == null) {
				throw new Exception("Por favor ingrese la descripción");
			}

			proyectoSeleccionado = (VtProyecto)selectedNode.getData();

			VtPilaProducto vtPilaProducto= new VtPilaProducto();

			vtPilaProducto.setNombre(txtBacklogCNombre.getValue().toString());			
			vtPilaProducto.setDescripcion(txtBacklogCDescripcion.getValue().toString());			
			vtPilaProducto.setFechaCreacion(new Date());			
			vtPilaProducto.setFechaCreacion(new Date());			
			vtPilaProducto.setUsuCreador(vtUsuarioActual.getUsuaCodigo());			
			vtPilaProducto.setUsuModificador(vtUsuarioActual.getUsuaCodigo());			
			vtPilaProducto.setActivo("S");

			vtPilaProducto.setVtProyecto(proyectoSeleccionado);

			businessDelegatorView.saveVtPilaProducto(vtPilaProducto);

			FacesUtils.addInfoMessage("Se ha creado el backlog con éxito");
			limpiarBacklogCAction();
			new DefaultTreeNode("Backlog",vtPilaProducto, selectedNode);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}

	//Limpiar el Crear dialog
	public void limpiarBacklogCAction() {		
		txtBacklogCNombre.resetValue();
		txtBacklogCDescripcion.resetValue();

	}

	public void modificarBacklogListener() {		

		backlogSeleccionado = (VtPilaProducto)selectedNode.getData();

		txtBacklogMNombre.setValue(backlogSeleccionado.getNombre());
		txtBacklogMDescripcion.setValue(backlogSeleccionado.getDescripcion());
		somBacklogActivo.setValue(backlogSeleccionado.getActivo());

	}

	public void modificarBacklogAction() {
		try {
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if (txtBacklogMNombre.getValue().toString().trim().equals("") == true || txtBacklogMNombre.getValue() == null) {
				throw new Exception("El nombre no es valido");
			}
			if (txtBacklogMDescripcion.getValue().toString().trim().equals("") == true || txtBacklogMDescripcion.getValue() == null) {
				throw new Exception("La descripción no es valida");
			}
			if (somBacklogActivo.getValue().equals("-1") ==true ) {
				throw new Exception("Asigne un estado al backlog");
			}

			backlogSeleccionado.setNombre(txtBacklogMNombre.getValue().toString());			
			backlogSeleccionado.setDescripcion(txtBacklogMDescripcion.getValue().toString());			
			backlogSeleccionado.setFechaModificacion(new Date());

			backlogSeleccionado.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			backlogSeleccionado.setActivo(somBacklogActivo.getValue().toString().trim());


			businessDelegatorView.updateVtPilaProducto(backlogSeleccionado);
			selectedNode.setData(backlogSeleccionado);
			limpiarBacklogMAction();
			FacesUtils.addInfoMessage("Se ha modificado con éxito");

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}


	public void limpiarBacklogMAction() {
		txtBacklogMNombre.resetValue();
		txtBacklogMDescripcion.resetValue();
		somBacklogActivo.setValue("-1");

	}

	//....................................................................................................................

	public String artefactoListener(){

		backlogSeleccionado = (VtPilaProducto)selectedNode.getData();
		//Guardo objeto en la sesion
		if (backlogSeleccionado.getActivo().equals("S")) {
			FacesUtils.putinSession("backlogSeleccionado", backlogSeleccionado);
			return "/XHTML/listarArtefactos.xhtml";
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
			return "/XHTML/listaSprint.xhtml";
		}
		else{
			FacesUtils.addErrorMessage("La pila producto esta inactiva");
			return "";
		}

	}
	
	
	public void abrirCrearProyecto() {
		
		try {
			
			laEmpresaSeleccionada = (VtEmpresa) selectedNode.getData();
			
			if (laEmpresaSeleccionada.getActivo().equals("S")) {
				RequestContext.getCurrentInstance().execute("PF('dlgCrearProyecto').show();");
			}else {
				throw new Exception("La empresa esta inactiva.");
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	public void abrirCrearBacklog() {
		
		try {
			
			proyectoSeleccionado = (VtProyecto) selectedNode.getData();	
			
			if (proyectoSeleccionado.getActivo().equals("S")) {
				RequestContext.getCurrentInstance().execute("PF('dlgCrearProyecto').show();");
			}else {
				throw new Exception("El proyecto esta inactiva.");
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
}
