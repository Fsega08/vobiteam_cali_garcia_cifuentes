package com.vobi.team.presentation.backingBeans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtEstadoSprint;
import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtPrioridad;
import com.vobi.team.modelo.VtSprint;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.dto.VtSprintDTO;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;
import com.vobi.team.utilities.Utilities;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class VtSprintView {

	public final static Logger log=LoggerFactory.getLogger(VtSprintView.class);


	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;	

	private List<VtSprint> losSprint;
	private VtSprint sprintSeleccionado;
	private VtSprintDTO sprintDTOseleccionado;

	private VtPilaProducto backlogSeleccionado;

	/////////////////Crear Sprint
	private InputText txtNombre;
	private InputTextarea txtDescripcion;
	private Calendar clndFechaIncio;
	private Calendar clndFechaFin;
	private InputText txtCapacidadEstimada;

	private CommandButton btnCrearSprint;

	private CommandButton btnCrearNuevo;
	private CommandButton btnLimpiar;

	private Date fechaInicio;
	private Date fechaFin;

	///////////////Modificar Sprint

	private InputText txtMNombre;
	private InputTextarea txtMDescripcion;
	private Calendar clndMFechaIncio;
	private Calendar clndMFechaFin;

	private InputText txtMCapacidadEstimada;
	private InputText txtMCapacidadReal;

	private SelectOneMenu somSprintActivo;
	
	private List<SelectItem> losEstadosSprint;
	private SelectOneMenu somSprintEstado;

	private CommandButton btnModificar;
	private CommandButton btnLimpiarM;

	private Date fechaInicioM;
	private Date fechaFinM;

	///////////////////////////////

	private DualListModel<VtArtefacto> losArtefactos;
	private List<VtArtefacto> artefactosSource;
	private List<VtArtefacto> artefactosTarget;	

	private DualListModel<VtArtefacto> losCArtefactos;
	private List<VtArtefacto> artefactosCSource;
	private List<VtArtefacto> artefactosCTarget;	
	
	private List<VtArtefacto> artefactosSacadosDelSprint;
	////////////////////////////

	private List<VtArtefacto> losArtefactosAsignados;
	private List<VtArtefacto> losArtefactosParaAsignar;	

	///////////////////////////// Grafico Crear

	private MeterGaugeChartModel chartCrear;
	double sumaCrearEsfuerzoReal;
	double sumaCrearEsfuerzoEstimado;
	private String capacidadEstimada;
	/////////////////////////// Grafico

	private MeterGaugeChartModel chartModel;
	int sumaEsfuerzoReal;

	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();

	@PostConstruct
	public void init(){

		try {
			sumaEsfuerzoReal = 0;
			sumaCrearEsfuerzoEstimado = 0;
			sumaCrearEsfuerzoReal = 0;
			capacidadEstimada = "";

			backlogSeleccionado = (VtPilaProducto) FacesUtils.getfromSession("backlogSeleccionado");
			sprintSeleccionado = (VtSprint) FacesUtils.getfromSession("sprintSeleccionado");
			
			if (sprintSeleccionado != null) {
				sprintDTOseleccionado = businessDelegatorView.getDataVtSprintDTO(sprintSeleccionado);
			}else {
				sprintDTOseleccionado = new VtSprintDTO();
			}
			
			losArtefactosParaAsignar = new ArrayList<VtArtefacto>();

			iniciarMeterGaugeModelsCreate();
			createMeterGaugeModels();
			
			//Picklist inmediato	
			artefactosSource = businessDelegatorView.findArtefactosVaciosPorBacklogYDesarrollador(backlogSeleccionado.getPilaCodigo());
			artefactosCSource = artefactosSource;
			
			if (artefactosSource== null) {
				artefactosSource = new ArrayList<VtArtefacto>();
				artefactosCSource = new ArrayList<VtArtefacto>();
			}
			
			if(sprintSeleccionado != null){
				artefactosTarget = businessDelegatorView.findArtefactosBySpring(sprintSeleccionado);
				artefactosCTarget = artefactosTarget;
				
				if (artefactosTarget == null) {
					artefactosTarget = new ArrayList<VtArtefacto>();
					artefactosCTarget = new ArrayList<VtArtefacto>();
				}
				
				
				artefactosSacadosDelSprint = new ArrayList<VtArtefacto>();
				
				capacidadEstimada = ""+sprintSeleccionado.getCapacidadEstimada();
				losArtefactosParaAsignar = artefactosCTarget;
				
				actualizarCrearChartAction();
				actualizarChartAction();
			}else{
				artefactosTarget = new ArrayList<VtArtefacto>();
				artefactosCTarget = new ArrayList<VtArtefacto>();
				pickListAsignarArtefactoAction();
			}			

			losArtefactos = new DualListModel<>(artefactosSource, artefactosTarget);
			losCArtefactos = new DualListModel<>(artefactosCSource, artefactosCTarget);
			

		} catch (Exception e) {
			log.info(e.getMessage());
		}

	}	
	
	
	
	public List<VtArtefacto> getArtefactosSacadosDelSprint() {
		return artefactosSacadosDelSprint;
	}



	public void setArtefactosSacadosDelSprint(List<VtArtefacto> artefactosSacadosDelSprint) {
		this.artefactosSacadosDelSprint = artefactosSacadosDelSprint;
	}

	

	public InputText getTxtCapacidadEstimada() {
		return txtCapacidadEstimada;
	}



	public void setTxtCapacidadEstimada(InputText txtCapacidadEstimada) {
		this.txtCapacidadEstimada = txtCapacidadEstimada;
	}

	
///////////////////////////////////////**************************/////////////////////////

	public List<SelectItem> getLosEstadosSprint() {
		try {
			if (losEstadosSprint==null) {
				List<VtEstadoSprint> listaEstadoSprint = businessDelegatorView.getVtEstadoSprint();
				losEstadosSprint = new ArrayList<SelectItem>();
				for (VtEstadoSprint vtEstadoSprint : listaEstadoSprint) {
					losEstadosSprint.add(new SelectItem(vtEstadoSprint.getEstsprCodigo(), vtEstadoSprint.getNombre()));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return losEstadosSprint;
	}



	public void setLosEstadosSprint(List<SelectItem> losEstadosSprint) {
		this.losEstadosSprint = losEstadosSprint;
	}



	public SelectOneMenu getSomSprintEstado() {
		return somSprintEstado;
	}


	
	

	public void setSomSprintEstado(SelectOneMenu somSprintEstado) {
		this.somSprintEstado = somSprintEstado;
	}

////////////////////////////////////////////////////////////////////////////

	public String getCapacidadEstimada() {
		return capacidadEstimada;
	}

	public void setCapacidadEstimada(String capacidadEstimada) {
		this.capacidadEstimada = capacidadEstimada;
	}

	public MeterGaugeChartModel getChartModel() {
		return chartModel;
	}

	public void setChartModel(MeterGaugeChartModel chartModel) {
		this.chartModel = chartModel;
	}
	
	public VtSprintDTO getSprintDTOseleccionado() {
		try {
			if (sprintSeleccionado!=null) {
				sprintDTOseleccionado = businessDelegatorView.getDataVtSprintDTO(sprintSeleccionado);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return sprintDTOseleccionado;
	}



	public void setSprintDTOseleccionado(VtSprintDTO sprintDTOseleccionado) {
		this.sprintDTOseleccionado = sprintDTOseleccionado;
	}



	public List<VtArtefacto> getLosArtefactosParaAsignar() {
		return losArtefactosParaAsignar;
	}

	public void setLosArtefactosParaAsignar(List<VtArtefacto> losArtefactosParaAsignar) {
		this.losArtefactosParaAsignar = losArtefactosParaAsignar;
	}

	public DualListModel<VtArtefacto> getLosCArtefactos() {
		return losCArtefactos;
	}

	public void setLosCArtefactos(DualListModel<VtArtefacto> losCArtefactos) {
		this.losCArtefactos = losCArtefactos;
	}

	public List<VtArtefacto> getArtefactosCSource() {
		return artefactosCSource;
	}

	public void setArtefactosCSource(List<VtArtefacto> artefactosCSource) {
		this.artefactosCSource = artefactosCSource;
	}

	public List<VtArtefacto> getArtefactosCTarget() {
		return artefactosCTarget;
	}

	public void setArtefactosCTarget(List<VtArtefacto> artefactosCTarget) {
		this.artefactosCTarget = artefactosCTarget;
	}

	public MeterGaugeChartModel getChartCrear() {
		return chartCrear;
	}

	public void setChartCrear(MeterGaugeChartModel chartCrear) {
		this.chartCrear = chartCrear;
	}

	public List<VtArtefacto> getLosArtefactosAsignados() {
		try {

			losArtefactosAsignados = businessDelegatorView.findArtefactosBySpring(sprintSeleccionado);

		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return losArtefactosAsignados;
	}

	public void setLosArtefactosAsignados(List<VtArtefacto> losArtefactosAsignados) {
		this.losArtefactosAsignados = losArtefactosAsignados;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public InputTextarea getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(InputTextarea txtDescripcion) {
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

	public InputTextarea getTxtMDescripcion() {
		return txtMDescripcion;
	}

	public void setTxtMDescripcion(InputTextarea txtMDescripcion) {
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

	public InputText getTxtMCapacidadEstimada() {
		return txtMCapacidadEstimada;
	}

	public void setTxtMCapacidadEstimada(InputText txtMCapacidadEstimada) {
		this.txtMCapacidadEstimada = txtMCapacidadEstimada;
	}

	public InputText getTxtMCapacidadReal() {
		return txtMCapacidadReal;
	}

	public void setTxtMCapacidadReal(InputText txtMCapacidadReal) {
		this.txtMCapacidadReal = txtMCapacidadReal;
	}

	private MeterGaugeChartModel initMeterGaugeModel() {

		List<Number> intervals = new ArrayList<Number>(){{
			add(0);
			add(0);
			add(0);
			add(0);
		}};

		return new MeterGaugeChartModel(0, intervals);
	}

	private void createMeterGaugeModels() {

		chartModel = initMeterGaugeModel();
		chartModel.setTitle("Esfuerzo Estimado");
		chartModel.setSeriesColors("66cc66,93b75f,E7E658,cc6666");
		chartModel.setIntervalOuterRadius(100);

	}


	private void iniciarMeterGaugeModelsCreate() {

		chartCrear = initMeterGaugeModel();
		chartCrear.setTitle("Esfuerzo Estimado");
		chartCrear.setSeriesColors("66cc66,93b75f,E7E658,cc6666");
		chartCrear.setIntervalOuterRadius(100);

	}

	public void crearAction() throws Exception{
		try {
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);
			VtEstadoSprint estadoSprint = businessDelegatorView.getVtEstadoSprint(1L);
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

			if(capacidadEstimada.equals("")|| capacidadEstimada == null || !Utilities.isNumeric(capacidadEstimada)){
				throw new Exception("Es requerido un valor valido de Capacidad Estimada");
			}

			VtSprint sprint = new VtSprint();

			sprint.setNombre(nombre);
			sprint.setObjetivo(descripcion);
			sprint.setFechaInicio(fechaInicio);
			sprint.setFechaFin(fechaFin);
			sprint.setFechaCreacion(new Date());
			sprint.setFechaModificacion(new Date());
			sprint.setCapacidadEstimada(Integer.parseInt(capacidadEstimada));
			sprint.setCapacidadReal(0);
			sprint.setActivo("S");
			sprint.setUsuCreador(vtUsuarioActual.getUsuaCodigo());
			sprint.setUsuModificador(vtUsuarioActual.getUsuaCodigo());
			sprint.setVtPilaProducto(backlogSeleccionado);
			sprint.setVtEstadoSprint(estadoSprint);

			businessDelegatorView.saveVtSprint(sprint);
			
			if (losArtefactosParaAsignar != null) {
				for (VtArtefacto vtArtefacto : losArtefactosParaAsignar) {
					vtArtefacto.setVtSprint(sprint);
					businessDelegatorView.updateVtArtefacto(vtArtefacto);
				}
			}
			
			FacesUtils.addInfoMessage("El sprint se ha creado con exito");		
			losSprint = businessDelegatorView.findSprintByBacklog(backlogSeleccionado);
			pickListAsignarArtefactoAction();
			limpiarAction();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void limpiarAction(){
		txtNombre.resetValue();
		txtDescripcion.resetValue();
		capacidadEstimada = "";
		txtCapacidadEstimada.resetValue();
		fechaFin = null;
		fechaInicio = null;
	}

	public void limpiarActionM(){
		sprintSeleccionado.setNombre("");
		sprintSeleccionado.setObjetivo("");
		sprintSeleccionado.setCapacidadEstimada(0);
		sprintSeleccionado.setCapacidadReal(0);
		sprintSeleccionado.setFechaInicio(null);
		sprintSeleccionado.setFechaFin(null);
		somSprintActivo.setValue("-1");
		txtMCapacidadEstimada.resetValue();
	}

	public void modificarAction()throws Exception{

		try {

			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			String nombre = txtMNombre.getValue().toString();
			String descripcion = txtMDescripcion.getValue().toString();
			String capacidadE = ""+TimeUnit.HOURS.toMinutes(Long.parseLong(txtMCapacidadEstimada.getValue().toString()));
			fechaInicioM = sprintSeleccionado.getFechaInicio();
			fechaFinM = sprintSeleccionado.getFechaFin();

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

			if(capacidadE.equals("")|| capacidadE == null || !Utilities.isNumeric(capacidadE)){
				throw new Exception("Es requerido un valor valido de Capacidad Estimada");
			}

			sprintSeleccionado.setNombre(nombre);
			sprintSeleccionado.setObjetivo(descripcion);
			sprintSeleccionado.setFechaInicio(fechaInicioM);
			sprintSeleccionado.setFechaFin(fechaFinM);
			sprintSeleccionado.setFechaModificacion(new Date());
			sprintSeleccionado.setUsuModificador(vtUsuarioActual.getUsuaCodigo());
			sprintSeleccionado.setCapacidadEstimada(Integer.parseInt(capacidadE));
			sprintSeleccionado.setActivo(somSprintActivo.getValue().toString().trim());
			
			log.info("asdfasdfadfad "+ sprintDTOseleccionado.getEstsprCodigo_VtEstadoSprint());
			
			VtEstadoSprint vtEstadoSprint = businessDelegatorView.getVtEstadoSprint(Long.parseLong(somSprintEstado.getValue().toString().trim()));
			
			sprintSeleccionado.setVtEstadoSprint(vtEstadoSprint);

			businessDelegatorView.updateVtSprint(sprintSeleccionado);
			
			if (losArtefactosParaAsignar != null) {
				for (VtArtefacto vtArtefacto : losArtefactosParaAsignar) {
					vtArtefacto.setVtSprint(sprintSeleccionado);
					businessDelegatorView.updateVtArtefacto(vtArtefacto);
				}
			}
			
			if (artefactosSacadosDelSprint != null) {
				for (VtArtefacto vtArtefacto : artefactosSacadosDelSprint) {
					vtArtefacto.setVtSprint(null);
					businessDelegatorView.updateVtArtefacto(vtArtefacto);
				}
			}
			
			FacesUtils.addInfoMessage("Se ha actualizado el Sprint con exito");
			losSprint = businessDelegatorView.findSprintByBacklog(backlogSeleccionado);

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			losSprint = businessDelegatorView.findSprintByBacklog(backlogSeleccionado);
		}



	}
	
	public void estadoDelSprint() {
		log.info("Codigo ssom" + somSprintEstado.getValue().toString().trim());
	}
	
	public void modificarListener() {

		VtSprint sprint = sprintSeleccionado;

		txtMNombre.setValue(sprint.getNombre());
		txtMDescripcion.setValue(sprint.getObjetivo());
		somSprintActivo.setValue(sprint.getActivo());
		fechaInicioM = sprint.getFechaInicio();
		fechaFinM = sprint.getFechaFin();
		txtMCapacidadEstimada.setValue(sprint.getCapacidadEstimada());
		txtMCapacidadReal.setValue(sprint.getCapacidadReal());		
	}

	public void actualizarChartAction(){		

		getLosArtefactosAsignados();

		double inteval1 = sprintSeleccionado.getCapacidadEstimada()*(0.333);
		double inteval2 = sprintSeleccionado.getCapacidadEstimada()*(0.666);		

		List<Number> intervals = new ArrayList<Number>();

		if(losArtefactosAsignados != null){
			for (VtArtefacto vtArtefacto : losArtefactosAsignados) {
				sumaEsfuerzoReal += vtArtefacto.getEsfuerzoReal();
			}
		}

		if(sumaEsfuerzoReal > sprintSeleccionado.getCapacidadEstimada()){
			intervals = new ArrayList<Number>(){{
				add(inteval1);
				add(inteval2);
				add(sprintSeleccionado.getCapacidadEstimada());
				add(sumaEsfuerzoReal);
			}};
		}else{
			intervals = new ArrayList<Number>(){{
				add(inteval1);
				add(inteval2);
				add(sumaEsfuerzoReal);
				add(sprintSeleccionado.getCapacidadEstimada());

			}};

		}			
		Collections.sort(intervals, new Comparador());
		chartModel =  new MeterGaugeChartModel(sumaEsfuerzoReal, intervals);

		chartModel.setTitle("Esfuerzo");
		chartModel.setSeriesColors("66cc66,93b75f,E7E658,cc6666");
		chartModel.setIntervalOuterRadius(100);

		sumaEsfuerzoReal = 0;
	}

	public String regresarAction(){

		return "/XHTML/TreeTable.xhtml";

	}

	public String artefactosAction(){

		return "/XHTML/listarArtefactos.xhtml";

	}

	public void activarListaArtefacto(){
		FacesUtils.putinSession("sprintSeleccionado", sprintSeleccionado);

	}

	public void asignarArtefactoAction() throws Exception {

		try {

			artefactosSource = businessDelegatorView.findArtefactosVaciosPorBacklogYDesarrollador(backlogSeleccionado.getPilaCodigo());
			artefactosTarget = businessDelegatorView.findArtefactosBySpring(sprintSeleccionado);

			
			if (artefactosSource != null) {
				losArtefactos.setSource(artefactosSource);
			}else {
				List<VtArtefacto> artSrc = new ArrayList<VtArtefacto>();
				losArtefactos.setSource(artSrc);
			}			

			if (artefactosTarget != null) {
				losArtefactos.setTarget(artefactosTarget);
			}else {
				List<VtArtefacto> artTarget = new ArrayList<VtArtefacto>();
				losArtefactos.setTarget(artTarget);
			}			

			actualizarChartAction();

		} catch (Exception e) {
			log.info(e.getMessage());
		}

	}


	public void onTransfer(TransferEvent event) throws Exception {
		try {
			if (sprintSeleccionado!=null) {
				if (sprintSeleccionado.getVtEstadoSprint().getEstsprCodigo()==3L) {
					throw new Exception("El sprint se encuentra terminado");
				}
				if (sprintSeleccionado.getVtEstadoSprint().getEstsprCodigo()==2L) {
					throw new Exception("El sprint esta en curso");
				}
			}
			StringBuilder builder = new StringBuilder();

			for(Object item : event.getItems()) {
				VtArtefacto vtArtefacto=(VtArtefacto) item;

				builder.append(((VtArtefacto) item).getTitulo()).append("<br />");

				//true si paso de izquierda a derecha
				if(event.isAdd()){
					asignarArtefactoASprint(vtArtefacto, sprintSeleccionado);

				}
				if(event.isRemove()){
					removerArtefacto(vtArtefacto, sprintSeleccionado);
				}


			}
			actualizarChartAction();
			FacesUtils.addInfoMessage("Artefacto(s) Transferidos");
		} catch (Exception e) {
			asignarArtefactoAction();
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void asignarArtefactoASprint(VtArtefacto vtArtefacto, VtSprint vtSprint) {
		try {
			vtArtefacto.setVtSprint(vtSprint);
			vtSprint.setCapacidadReal(vtSprint.getCapacidadReal()+vtArtefacto.getEsfuerzoReal());

			businessDelegatorView.updateVtArtefacto(vtArtefacto);
			businessDelegatorView.updateVtSprint(vtSprint);

			actualizarChartAction();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}


	public void removerArtefacto(VtArtefacto vtArtefacto, VtSprint vtSprint) {
		try {
			vtArtefacto.setVtSprint(null);
			vtSprint.setCapacidadReal(vtSprint.getCapacidadReal()-vtArtefacto.getEsfuerzoReal());

			businessDelegatorView.updateVtArtefacto(vtArtefacto);
			businessDelegatorView.updateVtSprint(vtSprint);			
			actualizarChartAction();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	class Comparador implements Comparator<Number>{

		@Override
		public int compare(Number o1, Number o2) {
			return o1.doubleValue()<o2.doubleValue()?-1:
				o1.doubleValue()>o2.doubleValue()?1:
					0;
		}

	}

	public String crearSprintAction() throws Exception{
		pickListAsignarArtefactoAction();
		return "/XHTML/crearSprint.xhtml";
	}

	public String modificarSprintAction() throws Exception{

		FacesUtils.putinSession("sprintSeleccionado", sprintSeleccionado);
		actualizarChartAction();
		return "/XHTML/modificarSprint.xhtml";
		
	}

	public void pickListAsignarArtefactoAction() throws Exception {
		try {		
			artefactosCSource = businessDelegatorView.findArtefactosVaciosPorBacklogYDesarrollador(backlogSeleccionado.getPilaCodigo());
			artefactosCTarget = new ArrayList<VtArtefacto>();

			if (artefactosCSource != null) {
				losCArtefactos.setSource(artefactosCSource);
			}else {
				List<VtArtefacto> artSrc = new ArrayList<VtArtefacto>();
				losCArtefactos.setSource(artSrc);
			}			
			
			
			losCArtefactos.setTarget(artefactosCTarget);
			
			if (sprintSeleccionado != null) {
				capacidadEstimada = ""+(sprintSeleccionado.getCapacidadEstimada());
			}

		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	public void onTransferCrear(TransferEvent event) throws Exception {
		try {
			if (sprintSeleccionado == null) {
				if(capacidadEstimada.equals("") == true || capacidadEstimada == null || !(Utilities.isNumeric(capacidadEstimada)) ){
					throw new Exception("Es requerido un valor valido de Capacidad Estimada");
				}
			}
			for(Object item : event.getItems()) {
				VtArtefacto vtArtefacto=(VtArtefacto) item;				

				//true si paso de izquierda a derecha
				if(event.isAdd()){
					if (sprintSeleccionado!=null) {
						artefactosSacadosDelSprint.remove(vtArtefacto);
					}

					losArtefactosParaAsignar.add(vtArtefacto);

				}
				if(event.isRemove()){
					if (sprintSeleccionado!=null) {
						artefactosSacadosDelSprint.add(vtArtefacto);
					}
					
					losArtefactosParaAsignar.remove(vtArtefacto);
				}

			}
			actualizarCrearChartAction();
			FacesUtils.addInfoMessage("Artefacto(s) Listo Para Transferir");

		} catch (Exception e) {
			pickListAsignarArtefactoAction();
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void actualizarCrearChartAction(){		

		try {

			double inteval1 = Double.parseDouble(capacidadEstimada)*(0.333);
			double inteval2 = Double.parseDouble(capacidadEstimada)*(0.666);

			List<Number> intervals = new ArrayList<Number>();

			if(losArtefactosParaAsignar != null){
				for (VtArtefacto vtArtefacto : losArtefactosParaAsignar) {
					sumaCrearEsfuerzoReal += vtArtefacto.getEsfuerzoReal();
					sumaCrearEsfuerzoEstimado += vtArtefacto.getEsfuerzoEstimado();
				}
			}

			if(sumaCrearEsfuerzoReal > Double.parseDouble(capacidadEstimada)){
				intervals = new ArrayList<Number>(){{
					add(inteval1);
					add(inteval2);
					add(Double.parseDouble(capacidadEstimada));
					add(sumaCrearEsfuerzoReal);
				}};
			}else{
				intervals = new ArrayList<Number>(){{
					add(inteval1);
					add(inteval2);
					add(sumaCrearEsfuerzoReal);
					add(Double.parseDouble(capacidadEstimada));

				}};

			}			
			Collections.sort(intervals, new Comparador());
			chartCrear =  new MeterGaugeChartModel(sumaCrearEsfuerzoReal, intervals);

			chartCrear.setTitle("Esfuerzo");
			chartCrear.setSeriesColors("66cc66,93b75f,E7E658,cc6666");
			chartCrear.setIntervalOuterRadius(100);

			sumaCrearEsfuerzoReal = 0;
			sumaCrearEsfuerzoEstimado = 0;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}


	}

	public String sprintAction(){
		FacesUtils.putinSession("sprintSeleccionado", null);
		return "/XHTML/listaSprint.xhtml";
	}
	
	public String iniciarSprintAction(){
		try {
			if (sprintSeleccionado.getVtEstadoSprint().getEstsprCodigo() == 1L) {
				RequestContext.getCurrentInstance().execute("PF('cdSprint').show();");
			}else {
				FacesUtils.putinSession("sprintSeleccionado", sprintSeleccionado);
				return "/XHTML/iniciarSprint.xhtml";
			} 
			
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return "";
	}
	
	public String verSprintClienteAction(){
		try {
			if (sprintSeleccionado.getVtEstadoSprint().getEstsprCodigo() == 1L) {
				FacesUtils.addErrorMessage("El sprint se no se ha iniciado.");
			}else {
				FacesUtils.putinSession("sprintSeleccionado", sprintSeleccionado);
				return "/cliente/iniciarSprint.xhtml";
			} 
			
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return "";
	}
	
	public String cambioEstadoSprint() throws Exception {
 		VtEstadoSprint estadoSprint = businessDelegatorView.getVtEstadoSprint(2L);		
		sprintSeleccionado.setVtEstadoSprint(estadoSprint);
		FacesUtils.putinSession("sprintSeleccionado", sprintSeleccionado);
		businessDelegatorView.updateVtSprint(sprintSeleccionado);
		return "/XHTML/iniciarSprint.xhtml";
	}
	
	public void setCapEstimadaAction(){

		try {
			if (sprintSeleccionado != null) {
				capacidadEstimada = ""+(TimeUnit.HOURS.toMinutes(Long.parseLong(txtMCapacidadEstimada.getValue().toString())));
			}
			else {
				capacidadEstimada = ""+(TimeUnit.HOURS.toMinutes(Long.parseLong(txtCapacidadEstimada.getValue().toString())));
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
}
