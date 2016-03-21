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
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;



@ManagedBean
@ViewScoped
public class VtProyectosView {

	public final static Logger log=LoggerFactory.getLogger(VtProyectosView.class);


	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private InputText txtCNombre;
	private InputText txtCDescripcion;

	private InputText txtMNombre;
	private InputText txtMDescripcion;

	private SelectOneMenu somProyectoActivo;
	private SelectOneMenu somProyectoPublico;

	private CommandButton btnCrear;
	private	CommandButton btnModificar;
	private CommandButton btnCrearProyecto;
	private CommandButton btnModificarProyecto;
	private	CommandButton btnSeleccionarProyecto;
	private	CommandButton btnSeleccionarBacklog;
	private CommandButton btnCLimpiar;
	private CommandButton btnMLimpiar;

	private DataTable dtProyectos;

	private VtProyecto proyectoSeleccionado;
	private List<VtProyecto> losProyectos;

	private VtEmpresa vtEmpresaSelected;

	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();

	@PostConstruct
	public void init(){

		try {
			vtEmpresaSelected = (VtEmpresa) FacesUtils.getfromSession("empresaSeleccionada");
		} catch (Exception e) {
			log.info(e.getMessage());
		}

	}


	public CommandButton getBtnSeleccionarProyecto() {
		return btnSeleccionarProyecto;
	}


	public void setBtnSeleccionarProyecto(CommandButton btnSeleccionarProyecto) {
		this.btnSeleccionarProyecto = btnSeleccionarProyecto;
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

	public CommandButton getBtnCrearProyecto() {
		return btnCrearProyecto;
	}
	public void setBtnCrearProyecto(CommandButton btnCrearProyecto) {
		this.btnCrearProyecto = btnCrearProyecto;
	}
	public CommandButton getBtnModificarProyecto() {
		return btnModificarProyecto;
	}
	public void setBtnModificarProyecto(CommandButton btnModificarProyecto) {
		this.btnModificarProyecto = btnModificarProyecto;
	}
	public CommandButton getBtnModificar() {
		return btnModificar;
	}
	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}
	public DataTable getDtProyectos() {
		return dtProyectos;
	}
	public void setDtProyectos(DataTable dtProyectos) {
		this.dtProyectos = dtProyectos;
	}
	public VtEmpresa getVtEmpresaSelected() {
		return vtEmpresaSelected;
	}
	public void setVtEmpresaSelected(VtEmpresa vtEmpresaSelected) {
		this.vtEmpresaSelected = vtEmpresaSelected;
	}
	public List<VtProyecto> getLosProyectos() {
		try {
			if (losProyectos == null && vtEmpresaSelected!= null) {
				losProyectos = businessDelegatorView.findProyectsByEnterpriseIdentification(vtEmpresaSelected);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return losProyectos;
	}
	public void setLosProyectos(List<VtProyecto> losProyectos) {
		this.losProyectos = losProyectos;
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

	public CommandButton getBtnSeleccionarBacklog() {
		return btnSeleccionarBacklog;
	}


	public void setBtnSeleccionarBacklog(CommandButton btnSeleccionarBacklog) {
		this.btnSeleccionarBacklog = btnSeleccionarBacklog;
	}


	public InputText getTxtCNombre() {
		return txtCNombre;
	}


	public void setTxtCNombre(InputText txtCNombre) {
		this.txtCNombre = txtCNombre;
	}


	public InputText getTxtCDescripcion() {
		return txtCDescripcion;
	}


	public void setTxtCDescripcion(InputText txtCDescripcion) {
		this.txtCDescripcion = txtCDescripcion;
	}


	public InputText getTxtMNombre() {
		return txtMNombre;
	}


	public void setTxtMNombre(InputText txtMNombre) {
		this.txtMNombre = txtMNombre;
	}


	public InputText getTxtMDescripcion() {
		return txtMDescripcion;
	}


	public void setTxtMDescripcion(InputText txtMDescripcion) {
		this.txtMDescripcion = txtMDescripcion;
	}


	public CommandButton getBtnCrear() {
		return btnCrear;
	}
	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
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


	public void crearAction() throws Exception {

		try {
			if (txtCNombre.getValue().toString().trim().equals("") == true || txtCNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (txtCDescripcion.getValue().toString().trim().equals("") == true || txtCDescripcion.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}

			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			VtProyecto vtProyecto = new VtProyecto();

			vtProyecto.setNombre(txtCNombre.getValue().toString());
			vtProyecto.setDescripcion(txtCDescripcion.getValue().toString());
			vtProyecto.setPublico("S");
			vtProyecto.setActivo("S");
			vtProyecto.setVtEmpresa(vtEmpresaSelected);
			vtProyecto.setFechaCreacion(new Date());
			vtProyecto.setFechaModificacion(new Date());
			vtProyecto.setUsuCreador(vtUsuarioActual.getUsuaCodigo());
			vtProyecto.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			businessDelegatorView.saveVtProyecto(vtProyecto);

			FacesUtils.addInfoMessage("Se ha creado el Proyecto con éxito");

			losProyectos = businessDelegatorView.findProyectsByEnterpriseIdentification(vtEmpresaSelected);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			losProyectos = businessDelegatorView.findProyectsByEnterpriseIdentification(vtEmpresaSelected);

		}
	}

	public void modificarAction() throws Exception {
		log.info("Entro al modificar");
		try {

			VtProyecto vtProyecto = proyectoSeleccionado;
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if (txtMNombre.getValue().toString().trim().equals("") == true || txtMNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (txtMDescripcion.getValue().toString().trim().equals("") == true || txtMDescripcion.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (somProyectoActivo.getValue().equals("-1") ==true ) {
				throw new Exception("Por favor llene todos los campos");
			}

			if (somProyectoPublico.getValue().equals("-1") ==true ) {
				throw new Exception("Por favor llene todos los campos");
			}


			vtProyecto.setNombre(txtMNombre.getValue().toString());			
			vtProyecto.setDescripcion(txtMDescripcion.getValue().toString());			
			vtProyecto.setPublico(somProyectoPublico.getValue().toString().trim());			
			vtProyecto.setActivo(somProyectoActivo.getValue().toString().trim());			
			vtProyecto.setUsuModificador(vtUsuarioActual.getUsuaCodigo());			
			vtProyecto.setFechaModificacion(new Date());

			businessDelegatorView.updateVtProyecto(vtProyecto);

			FacesUtils.addInfoMessage("Se ha modificado el proyecto con éxito");
			losProyectos = businessDelegatorView.findProyectsByEnterpriseIdentification(vtEmpresaSelected);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			losProyectos = businessDelegatorView.findProyectsByEnterpriseIdentification(vtEmpresaSelected);
		}

	}


	public void modificarListener() {		

		VtProyecto vtProyecto = proyectoSeleccionado;

		txtMNombre.setValue(vtProyecto.getNombre());
		txtMDescripcion.setValue(vtProyecto.getDescripcion());
		somProyectoActivo.setValue(vtProyecto.getActivo());
		somProyectoPublico.setValue(vtProyecto.getPublico());

	}

	public String backlogListener(){

		//Guardo objeto en la sesion
		if (proyectoSeleccionado.getActivo().equals("S")) {
			FacesUtils.putinSession("proyectoSeleccionado", proyectoSeleccionado);
			return "/XHTML/listaBacklog.xhtml";
		}
		else{
			FacesUtils.addErrorMessage("El proyecto esta inactivo");
			return "";
		}

	}

	public String regresarAction(){

		return "/XHTML/listaEmpresa.xhtml";

	}

	public void limpiarCAction() {
		txtCNombre.resetValue();
		txtCDescripcion.resetValue();

	}

	public void limpiarAction() {
		txtMNombre.resetValue();
		txtMDescripcion.resetValue();
		somProyectoActivo.setValue("-1");
		somProyectoPublico.setValue("-1");
	}
}
