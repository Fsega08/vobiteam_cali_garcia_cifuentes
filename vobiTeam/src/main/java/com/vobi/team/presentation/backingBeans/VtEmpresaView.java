package com.vobi.team.presentation.backingBeans;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;


@ManagedBean
@ViewScoped
public class VtEmpresaView {
	public final static Logger log=LoggerFactory.getLogger(VtEmpresaView.class);

	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	//	private MenuModel menuDinamicoEmpresa;
	//	private DefaultSubMenu subMenuEmpresa;
	//	private DefaultMenuItem item;

	// PARA EL CREAR
	private InputText txtIdentificacion;
	private InputText txtNombre;

	private CommandButton btnCrear;
	private CommandButton btnLimpiar;
	///////////////////////////7


	//PARA EL MODIFICAR
	private InputText txtMIdentificacion;
	private InputText txtMNombre;
	private SelectOneMenu somEmpresaActiva;

	private CommandButton btnModificar;
	private CommandButton btnMLimpiar;
	////////////////////////////


	private CommandButton btnListaEmpresa;

	private VtEmpresa laEmpresaSeleccionada;

	private List<VtEmpresa> lasEmpresas;

	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public SelectOneMenu getSomEmpresaActiva() {
		return somEmpresaActiva;
	}

	public InputText getTxtMIdentificacion() {
		return txtMIdentificacion;
	}

	public void setTxtMIdentificacion(InputText txtMIdentificacion) {
		this.txtMIdentificacion = txtMIdentificacion;
	}

	public InputText getTxtMNombre() {
		return txtMNombre;
	}

	public void setTxtMNombre(InputText txtMNombre) {
		this.txtMNombre = txtMNombre;
	}

	public CommandButton getBtnMLimpiar() {
		return btnMLimpiar;
	}

	public void setBtnMLimpiar(CommandButton btnMLimpiar) {
		this.btnMLimpiar = btnMLimpiar;
	}

	public String getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(String usuarioActual) {
		this.usuarioActual = usuarioActual;
	}

	public void setSomEmpresaActiva(SelectOneMenu somEmpresaActiva) {
		this.somEmpresaActiva = somEmpresaActiva;
	}

	public CommandButton getBtnListaEmpresa() {
		return btnListaEmpresa;
	}

	public void setBtnListaEmpresa(CommandButton btnListaEmpresa) {
		this.btnListaEmpresa = btnListaEmpresa;
	}

	public VtEmpresa getLaEmpresaSeleccionada() {
		return laEmpresaSeleccionada;
	}

	public void setLaEmpresaSeleccionada(VtEmpresa laEmpresaSeleccionada) {
		this.laEmpresaSeleccionada = laEmpresaSeleccionada;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
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

	//	public MenuModel getMenuDinamicoEmpresa() {
	//		try {
	//			lasEmpresas = businessDelegatorView.getVtEmpresa();
	//			menuDinamicoEmpresa =  new DefaultMenuModel();
	//			
	//			subMenuEmpresa = new DefaultSubMenu("Lista Empresas Asociadas");
	//			subMenuEmpresa.setIcon("icon-building");
	//			for (VtEmpresa vtEmpresa : lasEmpresas) {
	//				if (subMenuEmpresa != null && vtEmpresa != null) {
	//					log.info("La empresa es: " + vtEmpresa.getNombre());
	//					item = new DefaultMenuItem(vtEmpresa.getNombre());
	//					subMenuEmpresa.addElement(item);
	//					item.setAjax(true);
	//					item.setUrl("/XHTML/vtEmpresa.xhtml");
	//					item.setIcon("icon-building-filled");
	//					item.setOnclick(vtEmpresa.getIdentificacion());
	//					item.setCommand("#{vtEmpresaView.pruebaListener}");
	//				}
	//			}
	//			menuDinamicoEmpresa.addElement(subMenuEmpresa);
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//		return menuDinamicoEmpresa;
	//	}
	//
	//	public void setMenuDinamicoEmpresa(MenuModel menuDinamicoEmpresa) {
	//		this.menuDinamicoEmpresa = menuDinamicoEmpresa;
	//	}

	public CommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public CommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public InputText getTxtIdentificacion() {
		return txtIdentificacion;
	}

	public void setTxtIdentificacion(InputText txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public void crearAction() throws Exception{
		try {
			VtEmpresa vtEmpresa = new VtEmpresa();

			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if (txtIdentificacion.getValue().toString().trim().equals("") == true || txtIdentificacion.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (txtNombre.getValue().toString().trim().equals("")== true || txtNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}

			String identificacion = txtIdentificacion.getValue().toString().trim();
			String nombre = txtNombre.getValue().toString();

			vtEmpresa.setIdentificacion(identificacion);
			vtEmpresa.setNombre(nombre);
			vtEmpresa.setActivo("S");

			vtEmpresa.setUsuCreador(vtUsuarioActual.getUsuaCodigo());
			vtEmpresa.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			Date date = new Date();

			vtEmpresa.setFechaCreacion(date);
			vtEmpresa.setFechaModificacion(date);

			businessDelegatorView.saveVtEmpresa(vtEmpresa);
			limpiarAction();
			FacesUtils.addInfoMessage("Se creó con éxito la empresa");
			lasEmpresas = businessDelegatorView.getVtEmpresa();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			lasEmpresas = businessDelegatorView.getVtEmpresa();
		}
	}

	public void modificarAction() throws Exception {
		try {
			
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if (txtMNombre.getValue().toString().trim().equals("")== true || txtNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}

			if (somEmpresaActiva.getValue().equals("-1") ==true ) {
				throw new Exception("Por favor llene todos los campos");
			}
			
			VtEmpresa vtEmpresa = laEmpresaSeleccionada;
			

			vtEmpresa.setNombre(txtMNombre.getValue().toString());
			vtEmpresa.setActivo(somEmpresaActiva.getValue().toString().trim());

			vtEmpresa.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			vtEmpresa.setFechaModificacion(new Date());

			businessDelegatorView.updateVtEmpresa(vtEmpresa);
			FacesUtils.addInfoMessage("Se modificó con éxito la empresa");
			lasEmpresas = businessDelegatorView.getVtEmpresa();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			lasEmpresas = businessDelegatorView.getVtEmpresa();
		}
	}

	public void modificarListener() throws Exception{	

		txtMNombre.setValue(laEmpresaSeleccionada.getNombre());
		somEmpresaActiva.setValue(laEmpresaSeleccionada.getActivo());
	}

	public String listaEmpresaListener(){
		//Guardo objeto en la sesion
		FacesUtils.putinSession("empresaSeleccionada", laEmpresaSeleccionada);
		if (laEmpresaSeleccionada.getActivo().equals("S")) {
			return "/XHTML/listaProyectos.xhtml";
		}
		else {
			FacesUtils.addInfoMessage("Lo siento la empresa esta inactiva");
			return "";
		}

	}

	public void limpiarAction() {
		log.info("Limpiar");

		txtNombre.resetValue();
		txtIdentificacion.resetValue();
	}	
	
	public void limpiarMAction() {
		log.info("Limpiar Mod");
		txtMNombre.resetValue();
		somEmpresaActiva.setValue("-1");
		
	}	

}
