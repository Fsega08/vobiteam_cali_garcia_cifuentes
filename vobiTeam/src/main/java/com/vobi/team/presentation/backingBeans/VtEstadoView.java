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
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;


@ManagedBean
@ViewScoped
public class VtEstadoView {
	public final static Logger log=LoggerFactory.getLogger(VtEstadoView.class);

	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;
	
	// PARA EL CREAR
	private InputText txtNombre;

	private CommandButton btnCrear;
	private CommandButton btnLimpiar;
	///////////////////////////7


	//PARA EL MODIFICAR
	private InputText txtMNombre;
	private SelectOneMenu somEstadoActiva;

	private CommandButton btnModificar;
	private CommandButton btnMLimpiar;
	////////////////////////////

	private VtEstado elEstado;

	private List<VtEstado> losEstados;

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
	
	
	
	public SelectOneMenu getSomEstadoActiva() {
		return somEstadoActiva;
	}

	public void setSomEstadoActiva(SelectOneMenu somEstadoActiva) {
		this.somEstadoActiva = somEstadoActiva;
	}

	public VtEstado getElEstado() {
		return elEstado;
	}

	public void setElEstado(VtEstado elEstado) {
		this.elEstado = elEstado;
	}

	public List<VtEstado> getLosEstados() {
		try {
			if (losEstados==null) {
				losEstados = businessDelegatorView.getVtEstado();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		return losEstados;
	}

	public void setLosEstados(List<VtEstado> losEstados) {
		this.losEstados = losEstados;
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
			VtEstado vtEstado = new VtEstado();

			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if (txtNombre.getValue().toString().trim().equals("")== true || txtNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			
			
			vtEstado.setNombre(txtNombre.getValue().toString());
			vtEstado.setActivo("S");

			vtEstado.setUsuCreador(vtUsuarioActual.getUsuaCodigo());
			vtEstado.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			Date date = new Date();

			vtEstado.setFechaCreacion(date);
			vtEstado.setFechaModificacion(date);

			businessDelegatorView.saveVtEstado(vtEstado);
			limpiarAction();
			FacesUtils.addInfoMessage("Se creó el Estado con éxito");
			losEstados = businessDelegatorView.getVtEstado();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			losEstados = businessDelegatorView.getVtEstado();
		}
	}

	public void modificarAction() throws Exception {
		try {
			
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if (txtMNombre.getValue().toString().trim().equals("")== true || txtNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}

			if (somEstadoActiva.getValue().equals("-1") ==true ) {
				throw new Exception("Por favor llene todos los campos");
			}
			
			VtEstado vtEstado = elEstado;
			
			vtEstado.setNombre(txtMNombre.getValue().toString());
			vtEstado.setActivo(somEstadoActiva.getValue().toString().trim());

			vtEstado.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			vtEstado.setFechaModificacion(new Date());

			businessDelegatorView.updateVtEstado(vtEstado);
			FacesUtils.addInfoMessage("Se modificó el Estado con éxito");
			losEstados = businessDelegatorView.getVtEstado();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			losEstados = businessDelegatorView.getVtEstado();
		}
	}

	public void modificarListener() throws Exception{
		txtMNombre.setValue(elEstado.getNombre());
		somEstadoActiva.setValue(elEstado.getActivo());
	}

	public void limpiarAction() {
		log.info("Limpiar");

		txtNombre.resetValue();

	}	
	
	public void limpiarMAction() {
		log.info("Limpiar Mod");

		txtMNombre.resetValue();

		somEstadoActiva.setValue("-1");
		
	}	

}
