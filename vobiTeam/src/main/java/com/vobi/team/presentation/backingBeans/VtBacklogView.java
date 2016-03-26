package com.vobi.team.presentation.backingBeans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;



@ManagedBean
@ViewScoped
public class VtBacklogView {

	public final static Logger log=LoggerFactory.getLogger(VtBacklogView.class);


	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;	
	
	private DataTable dtBacklog;

	private VtProyecto proyectoSeleccionado;	
	
	private List<VtPilaProducto> losBacklogs;
	
	private VtPilaProducto backogSeleccionado;
	
	
	//Para el crear backog
	private InputText txtCNombre;
	private InputTextarea txtCDescripcion;
	
	//Abrir el dialog de crear
	private CommandButton btnCrear;
	
	//Crear el backlog
	private CommandButton btnCrearBacklog;
	
	//Para modificar el dialog
	private InputText txtMNombre;
	private InputTextarea txtMDescripcion;
	private SelectOneMenu somBacklogActivo;
	
	//El boton que manda a modificar
	private CommandButton btnModificarProyecto;
	
	//Toca 2 limpiar ya que el modificar tiene la lista y el crear no.
	private CommandButton btnCLimpiar;
	private CommandButton btnMLimpiar;
	
	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();
	
	@PostConstruct
	public void init(){
		
		try {
			proyectoSeleccionado = (VtProyecto) FacesUtils.getfromSession("proyectoSeleccionado");
			
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
	}
	
	
	public String getUsuarioActual() {
		return usuarioActual;
	}


	public void setUsuarioActual(String usuarioActual) {
		this.usuarioActual = usuarioActual;
	}


	public VtPilaProducto getBackogSeleccionado() {
		return backogSeleccionado;
	}


	public void setBackogSeleccionado(VtPilaProducto backogSeleccionado) {
		this.backogSeleccionado = backogSeleccionado;
	}


	public InputText getTxtCNombre() {
		return txtCNombre;
	}


	public void setTxtCNombre(InputText txtCNombre) {
		this.txtCNombre = txtCNombre;
	}

	public CommandButton getBtnCrear() {
		return btnCrear;
	}


	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}


	public CommandButton getBtnCrearBacklog() {
		return btnCrearBacklog;
	}


	public void setBtnCrearBacklog(CommandButton btnCrearBacklog) {
		this.btnCrearBacklog = btnCrearBacklog;
	}


	public InputText getTxtMNombre() {
		return txtMNombre;
	}


	public void setTxtMNombre(InputText txtMNombre) {
		this.txtMNombre = txtMNombre;
	}

	public SelectOneMenu getSomBacklogActivo() {
		return somBacklogActivo;
	}

	public InputTextarea getTxtCDescripcion() {
		return txtCDescripcion;
	}


	public void setTxtCDescripcion(InputTextarea txtCDescripcion) {
		this.txtCDescripcion = txtCDescripcion;
	}


	public InputTextarea getTxtMDescripcion() {
		return txtMDescripcion;
	}


	public void setTxtMDescripcion(InputTextarea txtMDescripcion) {
		this.txtMDescripcion = txtMDescripcion;
	}


	public void setSomBacklogActivo(SelectOneMenu somBacklogActivo) {
		this.somBacklogActivo = somBacklogActivo;
	}


	public CommandButton getBtnModificarProyecto() {
		return btnModificarProyecto;
	}


	public void setBtnModificarProyecto(CommandButton btnModificarProyecto) {
		this.btnModificarProyecto = btnModificarProyecto;
	}


	public CommandButton getBtnCLimpiar() {
		return btnCLimpiar;
	}


	public void setBtnCLimpiar(CommandButton btnCLimpiar) {
		this.btnCLimpiar = btnCLimpiar;
	}


	public CommandButton getBtnMLimpiar() {
		return btnMLimpiar;
	}


	public void setBtnMLimpiar(CommandButton btnMLimpiar) {
		this.btnMLimpiar = btnMLimpiar;
	}


	public List<VtPilaProducto> getLosBacklogs() throws Exception {
		try {
			if(losBacklogs == null){
				losBacklogs = businessDelegatorView.findBacklogByProyecto(proyectoSeleccionado);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		return losBacklogs;
	}
	public void setLosBacklogs(List<VtPilaProducto> losBacklogs) {
		this.losBacklogs = losBacklogs;
	}
	public DataTable getDtBacklog() {
		return dtBacklog;
	}
	public void setDtBacklog(DataTable dtBacklog) {
		this.dtBacklog = dtBacklog;
	}
	public VtProyecto getProyectoSeleccionado() {
		return proyectoSeleccionado;
	}
	public void setProyectoSeleccionado(VtProyecto proyectoSeleccionado) {
		this.proyectoSeleccionado = proyectoSeleccionado;
	}
	
	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}
	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}
	
	//Metodo para crear el backlog
	public void crearAction() {
		try {
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);
			if (txtCNombre.getValue().toString().trim().equals("") == true || txtCNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (txtCDescripcion.getValue().toString().trim().equals("") == true || txtCDescripcion.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			
			VtPilaProducto vtPilaProducto= new VtPilaProducto();
			
			vtPilaProducto.setNombre(txtCNombre.getValue().toString());			
			vtPilaProducto.setDescripcion(txtCDescripcion.getValue().toString());			
			vtPilaProducto.setFechaCreacion(new Date());			
			vtPilaProducto.setFechaCreacion(new Date());			
			vtPilaProducto.setUsuCreador(vtUsuarioActual.getUsuaCodigo());			
			vtPilaProducto.setUsuModificador(vtUsuarioActual.getUsuaCodigo());			
			vtPilaProducto.setActivo("S");
			
			vtPilaProducto.setVtProyecto(proyectoSeleccionado);
			
			businessDelegatorView.saveVtPilaProducto(vtPilaProducto);
			
			FacesUtils.addInfoMessage("Se ha creado el backlog con éxito");
			
			losBacklogs = businessDelegatorView.findBacklogByProyecto(proyectoSeleccionado);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}
	
	//Limpiar el Crear dialog
	public void limpiarCAction() {
		txtCNombre.resetValue();
		txtCDescripcion.resetValue();

	}
	
	//Este listener es para mandar el backlog seleccionado y setear los datos en el dialogo
	public void modificarListener() {
		
		VtPilaProducto vtPilaProducto = backogSeleccionado;
		
		txtMNombre.setValue(vtPilaProducto.getNombre());
		txtMDescripcion.setValue(vtPilaProducto.getDescripcion());
		somBacklogActivo.setValue(vtPilaProducto.getActivo());
		
	}
	
	public void modificarAction() {
		try {
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);
			
			if (txtMNombre.getValue().toString().trim().equals("") == true || txtMNombre.getValue() == null) {
				throw new Exception("El nombre no es valido");
			}
			if (txtMDescripcion.getValue().toString().trim().equals("") == true || txtMDescripcion.getValue() == null) {
				throw new Exception("La descripción no es valida");
			}
			if (somBacklogActivo.getValue().equals("-1") ==true ) {
				throw new Exception("Asigne un estado al backlog");
			}
			
			VtPilaProducto vtPilaProducto = backogSeleccionado;
			
			vtPilaProducto.setNombre(txtMNombre.getValue().toString());			
			vtPilaProducto.setDescripcion(txtMDescripcion.getValue().toString());			
			vtPilaProducto.setFechaModificacion(new Date());
			
			vtPilaProducto.setUsuModificador(vtUsuarioActual.getUsuaCodigo());
			
			vtPilaProducto.setActivo(somBacklogActivo.getValue().toString().trim());
			
			
			businessDelegatorView.updateVtPilaProducto(vtPilaProducto);
			
			FacesUtils.addInfoMessage("Se ha modificado con éxito");
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}
	
	
	public void limpiarMAction() {
		txtMNombre.resetValue();
		txtMDescripcion.resetValue();
		somBacklogActivo.setValue("-1");

	}
	
	public String regresarAction(){

		return "/XHTML/listaProyectos.xhtml";

	}
	
		public String artefactoListener(){
		
		//Guardo objeto en la sesion
		if (backogSeleccionado.getActivo().equals("S")) {
			FacesUtils.putinSession("backlogSeleccionado", backogSeleccionado);
			return "/XHTML/listarArtefactos.xhtml";
		}
		else{
			FacesUtils.addErrorMessage("La pila producto esta inactiva");
			return "";
		}
		
	}
	
	public String sprintListener(){
		
		//Guardo objeto en la sesion
		if (backogSeleccionado.getActivo().equals("S")) {
			FacesUtils.putinSession("backlogSeleccionado", backogSeleccionado);
			return "/XHTML/listaSprint.xhtml";
		}
		else{
			FacesUtils.addErrorMessage("La pila producto esta inactiva");
			return "";
		}
		
	}
	
	
}
