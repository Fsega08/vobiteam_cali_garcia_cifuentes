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
import com.vobi.team.modelo.VtPrioridad;
import com.vobi.team.modelo.VtTipoArtefacto;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;


@ManagedBean
@ViewScoped
public class VtTipoArtefactoView {
	public final static Logger log=LoggerFactory.getLogger(VtTipoArtefactoView.class);

	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;
	
	// PARA EL CREAR
	private InputText txtNombre;

	private CommandButton btnCrear;
	private CommandButton btnLimpiar;
	///////////////////////////7


	//PARA EL MODIFICAR
	private InputText txtMNombre;
	private SelectOneMenu somTipoArfeactoActiva;

	private CommandButton btnModificar;
	private CommandButton btnMLimpiar;
	////////////////////////////

	private VtTipoArtefacto elTipoArtefacto;

	private List<VtTipoArtefacto> losTiposArtefactos;

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

	public SelectOneMenu getSomTipoArfeactoActiva() {
		return somTipoArfeactoActiva;
	}

	public void setSomTipoArfeactoActiva(SelectOneMenu somTipoArfeactoActiva) {
		this.somTipoArfeactoActiva = somTipoArfeactoActiva;
	}

	public VtTipoArtefacto getElTipoArtefacto() {
		return elTipoArtefacto;
	}

	public void setElTipoArtefacto(VtTipoArtefacto elTipoArtefacto) {
		this.elTipoArtefacto = elTipoArtefacto;
	}

	public List<VtTipoArtefacto> getLosTiposArtefactos() {
		try {
			if (losTiposArtefactos==null) {
				losTiposArtefactos = businessDelegatorView.getVtTipoArtefacto();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		return losTiposArtefactos;
	}

	public void setLosTiposArtefactos(List<VtTipoArtefacto> losTiposArtefactos) {
		this.losTiposArtefactos = losTiposArtefactos;
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
			VtTipoArtefacto vtTipoArtefacto = new VtTipoArtefacto();

			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if (txtNombre.getValue().toString().trim().equals("")== true || txtNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			
			
			vtTipoArtefacto.setNombre(txtNombre.getValue().toString());
			vtTipoArtefacto.setActivo("S");

			vtTipoArtefacto.setUsuCreador(vtUsuarioActual.getUsuaCodigo());
			vtTipoArtefacto.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			Date date = new Date();

			vtTipoArtefacto.setFechaCreacion(date);
			vtTipoArtefacto.setFechaModificacion(date);

			businessDelegatorView.saveVtTipoArtefacto(vtTipoArtefacto);
			limpiarAction();
			FacesUtils.addInfoMessage("Se creó el tipo artefacto con éxito");
			losTiposArtefactos = businessDelegatorView.getVtTipoArtefacto();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			losTiposArtefactos = businessDelegatorView.getVtTipoArtefacto();
		}
	}

	public void modificarAction() throws Exception {
		try {
			
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if (txtMNombre.getValue().toString().trim().equals("")== true || txtNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}

			if (somTipoArfeactoActiva.getValue().equals("-1") ==true ) {
				throw new Exception("Por favor llene todos los campos");
			}
			
			VtTipoArtefacto vtTipoArtefacto = elTipoArtefacto;
			
			vtTipoArtefacto.setNombre(txtMNombre.getValue().toString());
			vtTipoArtefacto.setActivo(somTipoArfeactoActiva.getValue().toString().trim());

			vtTipoArtefacto.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			vtTipoArtefacto.setFechaModificacion(new Date());

			businessDelegatorView.updateVtTipoArtefacto(vtTipoArtefacto);
			FacesUtils.addInfoMessage("Se modificó el tipo artefacto con éxito");
			losTiposArtefactos = businessDelegatorView.getVtTipoArtefacto();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			losTiposArtefactos = businessDelegatorView.getVtTipoArtefacto();
		}
	}

	public void modificarListener() throws Exception{
		txtMNombre.setValue(elTipoArtefacto.getNombre());
		somTipoArfeactoActiva.setValue(elTipoArtefacto.getActivo());
	}

	public void limpiarAction() {
		log.info("Limpiar");

		txtNombre.resetValue();

	}	
	
	public void limpiarMAction() {
		log.info("Limpiar Mod");

		txtMNombre.resetValue();

		somTipoArfeactoActiva.setValue("-1");
		
	}	

}
