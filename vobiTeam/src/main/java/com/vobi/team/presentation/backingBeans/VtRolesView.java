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

import com.vobi.team.modelo.VtRol;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;


@ManagedBean
@ViewScoped
public class VtRolesView {
	public final static Logger log=LoggerFactory.getLogger(VtRolesView.class);

	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;
	
	// PARA EL CREAR
	private InputText txtNombre;

	private CommandButton btnCrear;
	private CommandButton btnLimpiar;
	///////////////////////////7


	//PARA EL MODIFICAR
	private InputText txtMNombre;
	private SelectOneMenu somRolActiva;

	private CommandButton btnModificar;
	private CommandButton btnMLimpiar;
	////////////////////////////


	private CommandButton btnListaEmpresa;

	private VtRol elRol;

	private List<VtRol> losRoles;

	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
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

	

	public SelectOneMenu getSomRolActiva() {
		return somRolActiva;
	}


	public void setSomRolActiva(SelectOneMenu somRolActiva) {
		this.somRolActiva = somRolActiva;
	}


	public CommandButton getBtnListaEmpresa() {
		return btnListaEmpresa;
	}

	public void setBtnListaEmpresa(CommandButton btnListaEmpresa) {
		this.btnListaEmpresa = btnListaEmpresa;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

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

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public VtRol getElRol() {
		return elRol;
	}

	public void setElRol(VtRol elRol) {
		this.elRol = elRol;
	}

	public List<VtRol> getLosRoles() {
		try {
			if (losRoles==null) {
				losRoles = businessDelegatorView.getVtRol();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return losRoles;
	}

	public void setLosRoles(List<VtRol> losRoles) {
		this.losRoles = losRoles;
	}

	public void crearAction(){
		try {
			VtRol vtRol = new VtRol();

			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if (txtNombre.getValue().toString().trim().equals("")== true || txtNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			
			
			vtRol.setRolNombre(txtNombre.getValue().toString());
			vtRol.setActivo("S");

			vtRol.setUsuCreador(vtUsuarioActual.getUsuaCodigo());
			vtRol.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			Date date = new Date();

			vtRol.setFechaCreacion(date);
			vtRol.setFechaModificacion(date);

			businessDelegatorView.saveVtRol(vtRol);
			limpiarAction();
			FacesUtils.addInfoMessage("Se creó el rol con éxito");
			losRoles = businessDelegatorView.getVtRol();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void modificarAction() {
		try {
			
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if (txtMNombre.getValue().toString().trim().equals("")== true || txtNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}

			if (somRolActiva.getValue().equals("-1") ==true ) {
				throw new Exception("Por favor llene todos los campos");
			}
			
			VtRol vtRol = elRol;
			
			vtRol.setRolNombre(txtMNombre.getValue().toString());
			vtRol.setActivo(somRolActiva.getValue().toString().trim());

			vtRol.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			vtRol.setFechaModificacion(new Date());

			businessDelegatorView.updateVtRol(vtRol);
			FacesUtils.addInfoMessage("Se modificó el rol con éxito");
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void modificarListener() throws Exception{
		txtMNombre.setValue(elRol.getRolNombre());
		somRolActiva.setValue(elRol.getActivo());
	}

	public void limpiarAction() {
		log.info("Limpiar");

		txtNombre.resetValue();

	}	
	
	public void limpiarMAction() {
		log.info("Limpiar Mod");

		txtMNombre.resetValue();

		somRolActiva.setValue("-1");
		
	}	

}
