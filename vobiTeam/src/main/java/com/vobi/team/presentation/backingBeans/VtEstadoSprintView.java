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

import com.vobi.team.modelo.VtEstado;
import com.vobi.team.modelo.VtEstadoSprint;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;


@ManagedBean
@ViewScoped
public class VtEstadoSprintView {
	public final static Logger log=LoggerFactory.getLogger(VtEstadoSprintView.class);

	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;
	
	// PARA EL CREAR
	private InputText txtNombre;

	private CommandButton btnCrear;
	private CommandButton btnLimpiar;
	////////////////////////////

	//PARA EL MODIFICAR
	private InputText txtMNombre;
	private SelectOneMenu somEstadoSprintActivo;

	private CommandButton btnModificar;
	private CommandButton btnMLimpiar;
	////////////////////////////

	private VtEstadoSprint elEstadoSprint;

	private List<VtEstadoSprint> losEstadosSprint;

	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();
	//------------------------------------------------------------------------------------------------
	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public CommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public InputText getTxtMNombre() {
		return txtMNombre;
	}

	public void setTxtMNombre(InputText txtMNombre) {
		this.txtMNombre = txtMNombre;
	}

	public SelectOneMenu getSomEstadoSprintActivo() {
		return somEstadoSprintActivo;
	}

	public void setSomEstadoSprintActivo(SelectOneMenu somEstadoSprintActivo) {
		this.somEstadoSprintActivo = somEstadoSprintActivo;
	}

	public CommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public CommandButton getBtnMLimpiar() {
		return btnMLimpiar;
	}

	public void setBtnMLimpiar(CommandButton btnMLimpiar) {
		this.btnMLimpiar = btnMLimpiar;
	}

	public VtEstadoSprint getElEstadoSprint() {
		return elEstadoSprint;
	}

	public void setElEstadoSprint(VtEstadoSprint elEstadoSprint) {
		this.elEstadoSprint = elEstadoSprint;
	}
	

	public List<VtEstadoSprint> getLosEstadosSprint() {
		try {
			if(losEstadosSprint == null){
				losEstadosSprint = businessDelegatorView.getVtEstadoSprint();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return losEstadosSprint;
	}

	public void setLosEstadosSprint(List<VtEstadoSprint> losEstadosSprint) {
		this.losEstadosSprint = losEstadosSprint;
	}

	public String getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(String usuarioActual) {
		this.usuarioActual = usuarioActual;
	}
	//---------------------------------------------------------------------------------------------------
	
	

	public void crearAction() throws Exception{
		try {
			VtEstadoSprint vtEstadoSprint = new VtEstadoSprint();

			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if (txtNombre.getValue().toString().trim().equals("")== true || txtNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			
			
			vtEstadoSprint.setNombre(txtNombre.getValue().toString());
			vtEstadoSprint.setActivo("S");
			vtEstadoSprint.setUsuCreador(vtUsuarioActual.getUsuaCodigo());
			vtEstadoSprint.setUsuModificador(vtUsuarioActual.getUsuaCodigo());		
			vtEstadoSprint.setFechaCreacion(new Date());
			vtEstadoSprint.setFechaModificacion(new Date());
			
			businessDelegatorView.saveVtEstadoSprint(vtEstadoSprint);
			
			FacesUtils.addInfoMessage("Se creó el Estado-Sprint con éxito");
			losEstadosSprint = businessDelegatorView.getVtEstadoSprint();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			losEstadosSprint = businessDelegatorView.getVtEstadoSprint();
		}
	}

	public void modificarAction() throws Exception {
		try {
			
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if (txtMNombre.getValue().toString().trim().equals("")== true || txtNombre.getValue() == null) {
				throw new Exception("Por favor digite el nombre");
			}

			if (somEstadoSprintActivo.getValue().equals("-1") ==true ) {
				throw new Exception("Por favor seleccione un estado");
			}
			
			VtEstadoSprint vtEstadoSprint = elEstadoSprint;
			
			vtEstadoSprint.setNombre(txtMNombre.getValue().toString());
			vtEstadoSprint.setActivo(somEstadoSprintActivo.getValue().toString().trim());

			vtEstadoSprint.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			vtEstadoSprint.setFechaModificacion(new Date());

			businessDelegatorView.updateVtEstadoSprint(vtEstadoSprint);
			FacesUtils.addInfoMessage("Se modificó el Estado-Sprint con éxito");
			losEstadosSprint = businessDelegatorView.getVtEstadoSprint();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			losEstadosSprint = businessDelegatorView.getVtEstadoSprint();
		}
	}

	public void modificarListener() throws Exception{
		txtMNombre.setValue(elEstadoSprint.getNombre());
		somEstadoSprintActivo.setValue(elEstadoSprint.getActivo());
	}

	public void limpiarAction() {
		txtNombre.resetValue();

	}	
	
	public void limpiarMAction() {
		txtMNombre.resetValue();
		somEstadoSprintActivo.setValue("-1");
		
	}	

}
