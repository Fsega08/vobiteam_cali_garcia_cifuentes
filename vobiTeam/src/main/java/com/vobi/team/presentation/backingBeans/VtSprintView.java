package com.vobi.team.presentation.backingBeans;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtSprint;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;



@ManagedBean
@ViewScoped
public class VtSprintView {

	public final static Logger log=LoggerFactory.getLogger(VtSprintView.class);


	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;	
	
	private VtArtefactoView vtArtefactoView;
	
	
	private List<VtSprint> losSprint;
	private VtSprint sprintSeleccionado;
	
	private VtPilaProducto backlogSeleccionado;
	
	private InputText txtNombre;
	private InputText txtDescripcion;
	private Calendar clndFechaIncio;
	private Calendar clndFechaFin;
	
	private CommandButton btnCrearSprint;
	
	private CommandButton btnCrearNuevo;
	private CommandButton btnLimpiar;
	
	private Date fechaInicio;
	private Date fechaFin;
	
	@PostConstruct
	public void init(){
		
		try {
			backlogSeleccionado = (VtPilaProducto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("backlogSeleccionado");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
	}
	
	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public InputText getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(InputText txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public Calendar getClndFechaIncio() {
		return clndFechaIncio;
	}

	public void setClndFechaIncio(Calendar clndFechaIncio) {
		this.clndFechaIncio = clndFechaIncio;
	}

	public Calendar getClndFechaFin() {
		return clndFechaFin;
	}

	public void setClndFechaFin(Calendar clndFechaFin) {
		this.clndFechaFin = clndFechaFin;
	}	
	
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public CommandButton getBtnCrearNuevo() {
		return btnCrearNuevo;
	}

	public void setBtnCrearNuevo(CommandButton btnCrearNuevo) {
		this.btnCrearNuevo = btnCrearNuevo;
	}

	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public CommandButton getBtnCrearSprint() {
		return btnCrearSprint;
	}

	public void setBtnCrearSprint(CommandButton btnCrearSprint) {
		this.btnCrearSprint = btnCrearSprint;
	}

	public VtPilaProducto getBacklogSeleccionado() {
		return backlogSeleccionado;
	}
	public void setBacklogSeleccionado(VtPilaProducto backlogSeleccionado) {
		this.backlogSeleccionado = backlogSeleccionado;
	}
	public List<VtSprint> getLosSprint() {
		try {
			if(losSprint == null){
				losSprint = businessDelegatorView.findSprintByBacklog(backlogSeleccionado);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		return losSprint;
	}
	public void setLosSprint(List<VtSprint> losSprint) {
		this.losSprint = losSprint;
	}
	
	public VtSprint getSprintSeleccionado() {
		return sprintSeleccionado;
	}


	public void setSprintSeleccionado(VtSprint sprintSeleccionado) {
		this.sprintSeleccionado = sprintSeleccionado;
	}


	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}
	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public void crearAction() throws Exception{
		try {
			String nombre = txtNombre.getValue().toString();
			String descripcion = txtDescripcion.getValue().toString();
			String fechaIni = fechaInicio.toString();
			String fechaFinal = fechaFin.toString();
			
			if(nombre.equals("")|| nombre == null){
				throw new Exception("El nombre es requerido");
			}
			
			if(descripcion.equals("")|| descripcion == null){
				throw new Exception("La descripción es requerida");
			}
			
			if(fechaInicio.equals("")|| fechaInicio == null){
				throw new Exception("Se necesita una fecha de inicio");
			}
			
			if(fechaFin.equals("")|| fechaFin == null){
				throw new Exception("Se necesita una fecha de fin");
			}
			
			//SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy"); 
			
			
			VtSprint sprint = new VtSprint();
			sprint.setNombre(nombre);
			sprint.setObjetivo(descripcion);
			sprint.setFechaInicio(fechaInicio);
			sprint.setFechaFin(fechaFin);
			sprint.setFechaCreacion(new Date());
			sprint.setFechaModificacion(new Date());
			sprint.setActivo("S");
			sprint.setUsuCreador(1L);
			sprint.setUsuModificador(1L);
			sprint.setVtPilaProducto(backlogSeleccionado);
			
			businessDelegatorView.saveVtSprint(sprint);
			FacesUtils.addInfoMessage("El sprint se ha creado con exito");			
			
			losSprint = businessDelegatorView.findSprintByBacklog(backlogSeleccionado);
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	public void limpiarAction(){
		txtNombre.resetValue();
		txtDescripcion.resetValue();
		clndFechaFin.resetValue();
		clndFechaIncio.resetValue();
	}
	//Este listener es para mandar el backlog seleccionado y setear los datos en el dialogo
	
	
	
	public void activarListaArtefacto(){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sprintSeleccionado", sprintSeleccionado);
		log.info("sprint seleccionado= " + sprintSeleccionado.getNombre());
		vtArtefactoView.getLosArtefactos();
	}
}
