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

import com.vobi.team.modelo.VtPrioridad;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;


@ManagedBean
@ViewScoped
public class VtPrioridadView {
	public final static Logger log=LoggerFactory.getLogger(VtPrioridadView.class);

	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;
	
	// PARA EL CREAR
	private InputText txtNombre;

	private CommandButton btnCrear;
	private CommandButton btnLimpiar;
	///////////////////////////7


	//PARA EL MODIFICAR
	private InputText txtMNombre;
	private SelectOneMenu somPrioridadActiva;

	private CommandButton btnModificar;
	private CommandButton btnMLimpiar;
	////////////////////////////


	private VtPrioridad laPrioridad;

	private List<VtPrioridad> lasPrioridades;

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

	
	
	public SelectOneMenu getSomPrioridadActiva() {
		return somPrioridadActiva;
	}


	public void setSomPrioridadActiva(SelectOneMenu somPrioridadActiva) {
		this.somPrioridadActiva = somPrioridadActiva;
	}

	public List<VtPrioridad> getLasPrioridades() {
		try {
			if (lasPrioridades==null) {
				lasPrioridades = businessDelegatorView.getVtPrioridad();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return lasPrioridades;
	}


	public void setLasPrioridades(List<VtPrioridad> lasPrioridades) {
		this.lasPrioridades = lasPrioridades;
	}
	
	public VtPrioridad getLaPrioridad() {
		return laPrioridad;
	}


	public void setLaPrioridad(VtPrioridad laPrioridad) {
		this.laPrioridad = laPrioridad;
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
			VtPrioridad vtPrioridad = new VtPrioridad();

			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if (txtNombre.getValue().toString().trim().equals("")== true || txtNombre.getValue() == null) {
				throw new Exception("Por favor digite el nombre");
			}
			
			
			vtPrioridad.setNombre(txtNombre.getValue().toString());
			vtPrioridad.setActivo("S");

			vtPrioridad.setUsuCreador(vtUsuarioActual.getUsuaCodigo());
			vtPrioridad.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			Date date = new Date();

			vtPrioridad.setFechaCreacion(date);
			vtPrioridad.setFechaModificacion(date);

			businessDelegatorView.saveVtPrioridad(vtPrioridad);
			limpiarAction();
			FacesUtils.addInfoMessage("Se creó la prioridad con éxito");
			lasPrioridades = businessDelegatorView.getVtPrioridad();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			lasPrioridades = businessDelegatorView.getVtPrioridad();
		}
	}

	public void modificarAction() throws Exception {
		try {
			
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			if (txtMNombre.getValue().toString().trim().equals("")== true || txtNombre.getValue() == null) {
				throw new Exception("Por favor digite el nombre");
			}

			if (somPrioridadActiva.getValue().equals("-1") ==true ) {
				throw new Exception("Por favor seleccione un estado");
			}
			
			VtPrioridad vtPrioridad = laPrioridad;
			
			vtPrioridad.setNombre(txtMNombre.getValue().toString());
			vtPrioridad.setActivo(somPrioridadActiva.getValue().toString().trim());

			vtPrioridad.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			vtPrioridad.setFechaModificacion(new Date());

			businessDelegatorView.updateVtPrioridad(vtPrioridad);
			FacesUtils.addInfoMessage("Se modificó la prioridad con éxito");
			lasPrioridades = businessDelegatorView.getVtPrioridad();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			lasPrioridades = businessDelegatorView.getVtPrioridad();
		}
	}

	public void modificarListener() throws Exception{
		txtMNombre.setValue(laPrioridad.getNombre());
		somPrioridadActiva.setValue(laPrioridad.getActivo());
	}

	public void limpiarAction() {
		log.info("Limpiar");

		txtNombre.resetValue();

	}	
	
	public void limpiarMAction() {
		log.info("Limpiar Mod");

		txtMNombre.resetValue();

		somPrioridadActiva.setValue("-1");
		
	}	

}
