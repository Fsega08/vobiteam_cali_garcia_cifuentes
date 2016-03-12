package com.vobi.team.presentation.backingBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vobi.team.modelo.VtEmpresa;
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
	
	
	private InputText txtIdentificacion;
	private InputText txtNombre;
	private SelectOneMenu somEmpresaActiva;

	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnLimpiar;

	private CommandButton btnListaEmpresa;
	
	private VtEmpresa laEmpresaSeleccionada;
	
	private List<VtEmpresa> lasEmpresas;

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public SelectOneMenu getSomEmpresaActiva() {
		return somEmpresaActiva;
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

	public void crearAction(){
		try {
			VtEmpresa vtEmpresa = new VtEmpresa();

			if (txtIdentificacion.getValue().toString().trim().equals("") == true || txtIdentificacion.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (txtNombre.getValue().toString().trim().equals("")== true || txtNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (somEmpresaActiva.getValue().equals("-1") ==true ) {
				throw new Exception("Por favor llene todos los campos");
			}

			String identificacion = txtIdentificacion.getValue().toString().trim();
			String nombre = txtNombre.getValue().toString();

			vtEmpresa.setIdentificacion(identificacion);
			vtEmpresa.setNombre(nombre);
			vtEmpresa.setActivo(somEmpresaActiva.getValue().toString().trim());

			vtEmpresa.setUsuCreador(1L);
			vtEmpresa.setUsuModificador(1L);

			Date date = new Date();

			vtEmpresa.setFechaCreacion(date);
			vtEmpresa.setFechaModificacion(date);
			
			businessDelegatorView.saveVtEmpresa(vtEmpresa);
			limpiarAction();
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Se creó con éxito la empresa"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(e.getMessage()));
		}
	}

	public void modificarAction() {
		try {
			VtEmpresa vtEmpresa = businessDelegatorView.findByEnterpriseIdentificacion(txtIdentificacion.getValue().toString().trim());

			if (txtIdentificacion.getValue().toString().trim().equals("") == true || txtIdentificacion.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (txtNombre.getValue().toString().trim().equals("")== true || txtNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}

			log.info("SomEmpresaActiva= " + somEmpresaActiva.getValue());

			if (somEmpresaActiva.getValue().equals("-1") ==true ) {
				throw new Exception("Por favor llene todos los campos");
			}

			String nombre = txtNombre.getValue().toString().trim();

			vtEmpresa.setNombre(nombre);
			vtEmpresa.setActivo(somEmpresaActiva.getValue().toString().trim());

			vtEmpresa.setUsuModificador(1L);

			Date date = new Date();

			vtEmpresa.setFechaModificacion(date);

			businessDelegatorView.updateVtEmpresa(vtEmpresa);
			limpiarAction();
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Se modificó con éxito la empresa"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(e.getMessage()));
		}
	}

	public void identificacionListener(){
		log.info("Se ejecuto el listener");
		VtEmpresa vtEmpresa = null;

		try {
			vtEmpresa=businessDelegatorView.findByEnterpriseIdentificacion(txtIdentificacion.getValue().toString()
					.trim());
		} catch (Exception e) {
			log.info(e.getMessage());;
		}

		if(vtEmpresa==null){
			log.info("No existe a crear");
			txtNombre.resetValue();
			somEmpresaActiva.setValue("-1");

			btnCrear.setDisabled(false);
			btnModificar.setDisabled(true);

		}else{

			txtIdentificacion.setDisabled(true);
			txtNombre.setValue(vtEmpresa.getNombre());
			somEmpresaActiva.setValue(vtEmpresa.getActivo());
			
			btnCrear.setDisabled(true);
			btnModificar.setDisabled(false);

		}

	}
	
	public String listaEmpresaListener(){
		//Guardo objeto en la sesion
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empresaSeleccionada", laEmpresaSeleccionada);
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
		somEmpresaActiva.setValue("-1");

		txtIdentificacion.setDisabled(false);

		btnCrear.setDisabled(true);
		btnModificar.setDisabled(true);

	}	
	

}
