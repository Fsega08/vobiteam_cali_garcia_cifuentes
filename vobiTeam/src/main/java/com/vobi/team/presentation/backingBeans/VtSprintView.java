package com.vobi.team.presentation.backingBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtProyectoUsuario;
import com.vobi.team.modelo.VtSprint;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;



@ManagedBean
@ViewScoped
public class VtSprintView {

	public final static Logger log=LoggerFactory.getLogger(VtSprintView.class);


	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;	

	private List<VtSprint> losSprint;
	private VtSprint sprintSeleccionado;

	private VtPilaProducto backlogSeleccionado;

	/////////////////Crear Sprint
	private InputText txtNombre;
	private InputText txtDescripcion;
	private Calendar clndFechaIncio;
	private Calendar clndFechaFin;

	private CommandButton btnCrearSprint;

	private CommandButton btnCrearNuevo;
	private CommandButton btnLimpiar;

	private Date fechaInicio;
	private Date fechaFin;

	///////////////Modificar Sprint

	private InputText txtMNombre;
	private InputText txtMDescripcion;
	private Calendar clndMFechaIncio;
	private Calendar clndMFechaFin;
	private SelectOneMenu somSprintActivo;


	private CommandButton btnModificar;
	private CommandButton btnLimpiarM;

	private Date fechaInicioM;
	private Date fechaFinM;

	///////////////////////////////
	
	private DualListModel<VtArtefacto> losArtefactos;
	private List<VtArtefacto> artefactosSource;
	private List<VtArtefacto> artefactosTarget;
	
	
	////////////////////////////
	
	private List<VtArtefacto> losArtefactosAsginados;
	
	public List<VtArtefacto> getLosArtefactosAsginados() {
		try {
			
			losArtefactosAsginados = businessDelegatorView.findArtefactosBySpring(sprintSeleccionado);
			
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return losArtefactosAsginados;
	}

	public void setLosArtefactosAsginados(List<VtArtefacto> losArtefactosAsginados) {
		this.losArtefactosAsginados = losArtefactosAsginados;
	}


	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();

	@PostConstruct
	public void init(){

		try {
			backlogSeleccionado = (VtPilaProducto) FacesUtils.getfromSession("backlogSeleccionado");
			
			artefactosSource = businessDelegatorView.getVtArtefacto();
			artefactosTarget = businessDelegatorView.getVtArtefacto();

			losArtefactos = new DualListModel<>(artefactosSource, artefactosTarget);
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
	
	public DualListModel<VtArtefacto> getLosArtefactos() {
		return losArtefactos;
	}

	public void setLosArtefactos(DualListModel<VtArtefacto> losArtefactos) {
		this.losArtefactos = losArtefactos;
	}

	public List<VtArtefacto> getArtefactosSource() {
		return artefactosSource;
	}

	public void setArtefactosSource(List<VtArtefacto> artefactosSource) {
		this.artefactosSource = artefactosSource;
	}

	public List<VtArtefacto> getArtefactosTarget() {
		return artefactosTarget;
	}

	public void setArtefactosTarget(List<VtArtefacto> artefactosTarget) {
		this.artefactosTarget = artefactosTarget;
	}

	public String getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(String usuarioActual) {
		this.usuarioActual = usuarioActual;
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

	public Calendar getClndMFechaIncio() {
		return clndMFechaIncio;
	}

	public void setClndMFechaIncio(Calendar clndMFechaIncio) {
		this.clndMFechaIncio = clndMFechaIncio;
	}

	public Calendar getClndMFechaFin() {
		return clndMFechaFin;
	}

	public void setClndMFechaFin(Calendar clndMFechaFin) {
		this.clndMFechaFin = clndMFechaFin;
	}

	public SelectOneMenu getSomSprintActivo() {
		return somSprintActivo;
	}

	public void setSomSprintActivo(SelectOneMenu somSprintActivo) {
		this.somSprintActivo = somSprintActivo;
	}

	public CommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public CommandButton getBtnLimpiarM() {
		return btnLimpiarM;
	}

	public void setBtnLimpiarM(CommandButton btnLimpiarM) {
		this.btnLimpiarM = btnLimpiarM;
	}

	public Date getFechaInicioM() {
		return fechaInicioM;
	}

	public void setFechaInicioM(Date fechaInicioM) {
		this.fechaInicioM = fechaInicioM;
	}

	public Date getFechaFinM() {
		return fechaFinM;
	}

	public void setFechaFinM(Date fechaFinM) {
		this.fechaFinM = fechaFinM;
	}

	public void crearAction() throws Exception{
		try {
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			String nombre = txtNombre.getValue().toString();
			String descripcion = txtDescripcion.getValue().toString();


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


			VtSprint sprint = new VtSprint();

			sprint.setNombre(nombre);
			sprint.setObjetivo(descripcion);
			sprint.setFechaInicio(fechaInicio);
			sprint.setFechaFin(fechaFin);
			sprint.setFechaCreacion(new Date());
			sprint.setFechaModificacion(new Date());
			sprint.setActivo("S");
			sprint.setUsuCreador(vtUsuarioActual.getUsuaCodigo());
			sprint.setUsuModificador(vtUsuarioActual.getUsuaCodigo());
			sprint.setVtPilaProducto(backlogSeleccionado);

			businessDelegatorView.saveVtSprint(sprint);
			FacesUtils.addInfoMessage("El sprint se ha creado con exito");			

			losSprint = businessDelegatorView.findSprintByBacklog(backlogSeleccionado);
			limpiarAction();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void limpiarAction(){
		txtNombre.resetValue();
		txtDescripcion.resetValue();
		fechaFin = null;
		fechaInicio = null;
	}

	public void limpiarActionM(){
		txtMNombre.resetValue();
		txtMDescripcion.resetValue();
		fechaFinM = null;
		fechaInicioM = null;
	}

	public void modificarAction()throws Exception{

		try {

			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			String nombre = txtMNombre.getValue().toString();
			String descripcion = txtMDescripcion.getValue().toString();
			if(nombre.equals("")|| nombre == null){
				throw new Exception("El nombre es requerido");
			}

			if(descripcion.equals("")|| descripcion == null){
				throw new Exception("La descripción es requerida");
			}

			if(fechaInicioM.equals("")|| fechaInicioM == null){
				throw new Exception("Se necesita una fecha de inicio");
			}

			if(fechaFinM.equals("")|| fechaFinM == null){
				throw new Exception("Se necesita una fecha de fin");				
			}

			VtSprint sprint = sprintSeleccionado;

			sprint.setNombre(nombre);
			sprint.setObjetivo(descripcion);
			sprint.setFechaInicio(fechaInicioM);
			sprint.setFechaFin(fechaFinM);
			sprint.setFechaModificacion(new Date());
			sprint.setUsuModificador(vtUsuarioActual.getUsuaCodigo());
			sprint.setActivo(somSprintActivo.getValue().toString().trim());

			businessDelegatorView.updateVtSprint(sprint);
			FacesUtils.addInfoMessage("Se ha actualizado el Sprint con exito");
			losSprint = businessDelegatorView.findSprintByBacklog(backlogSeleccionado);

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			losSprint = businessDelegatorView.findSprintByBacklog(backlogSeleccionado);
		}
		
		

	}

	public void modificarListener() {

		VtSprint sprint = sprintSeleccionado;

		txtMNombre.setValue(sprint.getNombre());
		txtMDescripcion.setValue(sprint.getObjetivo());
		somSprintActivo.setValue(sprint.getActivo());
		fechaInicioM = sprint.getFechaInicio();
		fechaFinM = sprint.getFechaFin();

	}
	
	public String regresarAction(){

		return "/XHTML/listaBacklog.xhtml";

	}
	
	public String artefactosAction(){

		return "/XHTML/listarArtefactos.xhtml";

	}

	public void activarListaArtefacto(){
		FacesUtils.putinSession("sprintSeleccionado", sprintSeleccionado);

	}
	
	public void asignarArtefactoAction() throws Exception {

		try {
			
			artefactosSource = businessDelegatorView.findArtefactosVaciosPorBacklog(backlogSeleccionado.getPilaCodigo());
			artefactosTarget = businessDelegatorView.findArtefactosBySpring(sprintSeleccionado);
			
			losArtefactos.setSource(artefactosSource);
			
			if (artefactosTarget != null) {
				
				losArtefactos.setTarget(artefactosTarget);
			}else {
				List<VtArtefacto> artTarget = new ArrayList<VtArtefacto>();
				losArtefactos.setTarget(artTarget);
			}
			
			
			
			
		} catch (Exception e) {
			log.info(e.getMessage());
		}

	}
	
	
	public void onTransfer(TransferEvent event) throws Exception {
		StringBuilder builder = new StringBuilder();

		for(Object item : event.getItems()) {
			VtArtefacto vtArtefacto=(VtArtefacto) item;

			builder.append(((VtArtefacto) item).getTitulo()).append("<br />");

			//true si paso de izquierda a derecha
			if(event.isAdd()){
				asignarArtefactoASprint(vtArtefacto, sprintSeleccionado);
			}
			if(event.isRemove()){
				removerArtefacto(vtArtefacto);
			}
		}

		FacesUtils.addInfoMessage("Artefacto(s) Transferidos");

	}
	
	public void asignarArtefactoASprint(VtArtefacto vtArtefacto, VtSprint vtSprint) {
		try {
			log.info("asigno");
			vtArtefacto.setVtSprint(vtSprint);
			
			businessDelegatorView.updateVtArtefacto(vtArtefacto);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	
	public void removerArtefacto(VtArtefacto vtArtefacto) {
		try {
			log.info("removio");
			vtArtefacto.setVtSprint(null);
			
			businessDelegatorView.updateVtArtefacto(vtArtefacto);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	
}
