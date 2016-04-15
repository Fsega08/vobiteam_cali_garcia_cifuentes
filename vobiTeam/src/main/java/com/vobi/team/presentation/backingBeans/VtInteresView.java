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

import com.vobi.team.modelo.VtInteres;
import com.vobi.team.modelo.VtPrioridad;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;


@ManagedBean
@ViewScoped
public class VtInteresView {
	public final static Logger log=LoggerFactory.getLogger(VtInteresView.class);

	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;
	
	// PARA EL CREAR
	private InputText txtNombre;

	private CommandButton btnCrear;
	private CommandButton btnLimpiar;
	///////////////////////////7


	//PARA EL MODIFICAR
	private InputText txtMNombre;
	private SelectOneMenu somInteresActivo;

	private CommandButton btnModificar;
	private CommandButton btnMLimpiar;
	////////////////////////////

	private VtInteres elInteres;

	private List<VtInteres> losIntereses;

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
	
	public SelectOneMenu getSomInteresActivo() {
		return somInteresActivo;
	}


	public void setSomInteresActivo(SelectOneMenu somInteresActivo) {
		this.somInteresActivo = somInteresActivo;
	}


	public VtInteres getElInteres() {
		return elInteres;
	}


	public void setElInteres(VtInteres elInteres) {
		this.elInteres = elInteres;
	}


	public List<VtInteres> getLosIntereses() {
		try {
			if (losIntereses==null) {
				losIntereses = businessDelegatorView.getVtInteres();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return losIntereses;
	}


	public void setLosIntereses(List<VtInteres> losIntereses) {
		this.losIntereses = losIntereses;
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

	public void crearAction() throws Exception{
		try {
			VtInteres vtInteres = new VtInteres();

			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if (txtNombre.getValue().toString().trim().equals("")== true || txtNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			
			
			vtInteres.setNombre(txtNombre.getValue().toString());
			vtInteres.setActivo("S");

			vtInteres.setUsuCreador(vtUsuarioActual.getUsuaCodigo());
			vtInteres.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			Date date = new Date();

			vtInteres.setFechaCreacion(date);
			vtInteres.setFechaModificacion(date);

			businessDelegatorView.saveVtInteres(vtInteres);
			limpiarAction();
			FacesUtils.addInfoMessage("Se creó el interés con éxito");
			losIntereses = businessDelegatorView.getVtInteres();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			losIntereses = businessDelegatorView.getVtInteres();
		}
	}

	public void modificarAction() throws Exception {
		try {
			
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if (txtMNombre.getValue().toString().trim().equals("")== true || txtNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}

			if (somInteresActivo.getValue().equals("-1") ==true ) {
				throw new Exception("Por favor llene todos los campos");
			}
			
			VtInteres vtInteres = elInteres;
			
			vtInteres.setNombre(txtMNombre.getValue().toString());
			vtInteres.setActivo(somInteresActivo.getValue().toString().trim());

			vtInteres.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			vtInteres.setFechaModificacion(new Date());

			businessDelegatorView.updateVtInteres(vtInteres);
			FacesUtils.addInfoMessage("Se modificó la prioridad con éxito");
			losIntereses = businessDelegatorView.getVtInteres();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			losIntereses = businessDelegatorView.getVtInteres();
		}
	}

	public void modificarListener() throws Exception{
		txtMNombre.setValue(elInteres.getNombre());
		somInteresActivo.setValue(elInteres.getActivo());
	}

	public void limpiarAction() {
		log.info("Limpiar");

		txtNombre.resetValue();

	}	
	
	public void limpiarMAction() {
		log.info("Limpiar Mod");

		txtMNombre.resetValue();

		somInteresActivo.setValue("-1");
		
	}	

}
