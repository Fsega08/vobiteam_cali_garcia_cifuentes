package com.vobi.team.presentation.backingBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtEstado;
import com.vobi.team.modelo.VtEstadoSprint;
import com.vobi.team.modelo.VtProgresoArtefacto;
import com.vobi.team.modelo.VtSprint;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.VtUsuarioArtefacto;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;
import com.vobi.team.utilities.Utilities;

import hirondelle.date4j.DateTime;

@ManagedBean
@ViewScoped
public class IniciarSprintView {

	public final static Logger log=LoggerFactory.getLogger(IniciarSprintView.class);


	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;	

	private List<VtArtefacto> losArtefactosPorHacer;
	private List<VtArtefacto> losArtefactosEnCurso;
	private List<VtArtefacto> losArtefactosFinalizados;

	private VtSprint sprintSeleccionado;
	private VtUsuarioArtefacto usuarioArtefacto;
	
	private VtArtefacto vtArtefactoPorHacer;
	private VtArtefacto vtArtefactoEnCurso;
	private VtArtefacto vtArtefactoFinalizado;
	private VtArtefacto artefactoSeleccionado;
	
	private CommandButton terminarSprint;
	
	
	private String estadoT = "";
	
	private int totalArtefactos;
	
	///Dialog Horas Trabajadas
	private InputText txtEsfuerzo;
	
	private LineChartModel burndownChart;

	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();
	
	public IniciarSprintView() {
		super();
		this.losArtefactosPorHacer = new ArrayList<VtArtefacto>();
		this.losArtefactosEnCurso = new ArrayList<VtArtefacto>() ;
		this.losArtefactosFinalizados = new ArrayList<VtArtefacto>();
	}

	@PostConstruct
	public void init(){
		try {			

			sprintSeleccionado = (VtSprint) FacesUtils.getfromSession("sprintSeleccionado");

			losArtefactosPorHacer = businessDelegatorView.findArtefactosBySprintAndEstado(sprintSeleccionado.getSpriCodigo(), 1L);
			losArtefactosEnCurso = businessDelegatorView.findArtefactosBySprintAndEstado(sprintSeleccionado.getSpriCodigo(), 2L);
			losArtefactosFinalizados = businessDelegatorView.findArtefactosBySprintAndEstado(sprintSeleccionado.getSpriCodigo(), 3L);
			
			totalArtefactos = losArtefactosPorHacer.size() + losArtefactosEnCurso.size();
			
			iniciarBurndown();
			
			verificarEstadoSprin();
		} catch (Exception e) {
			log.info(e.getMessage());
		}

	}	
	//............................................................................................
	
	public void verificarEstadoSprin() {
		if (sprintSeleccionado.getVtEstadoSprint().getEstsprCodigo() == 3L) {
			estadoT = "Ya se encuentra terminado!!";
			terminarSprint.setDisabled(true);
		}else {
			estadoT = "";
			terminarSprint.setDisabled(false);
		}
	}
	

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}


	public CommandButton getTerminarSprint() {
		return terminarSprint;
	}

	public void setTerminarSprint(CommandButton terminarSprint) {
		this.terminarSprint = terminarSprint;
	}



	public String getEstadoT() {
		return estadoT;
	}

	public void setEstadoT(String estadoT) {
		this.estadoT = estadoT;
	}

	public LineChartModel getBurndownChart() {
		return burndownChart;
	}

	public void setBurndownChart(LineChartModel burndownChart) {
		this.burndownChart = burndownChart;
	}

	public int getTotalArtefactos() {
		return totalArtefactos;
	}

	public void setTotalArtefactos(int totalArtefactos) {
		this.totalArtefactos = totalArtefactos;
	}

	public InputText getTxtEsfuerzo() {
		return txtEsfuerzo;
	}

	public void setTxtEsfuerzo(InputText txtEsfuerzo) {
		this.txtEsfuerzo = txtEsfuerzo;
	}

	public VtArtefacto getVtArtefactoPorHacer() {
		return vtArtefactoPorHacer;
	}

	public void setVtArtefactoPorHacer(VtArtefacto vtArtefactoPorHacer) {
		this.vtArtefactoPorHacer = vtArtefactoPorHacer;
	}

	public VtArtefacto getVtArtefactoEnCurso() {
		
		return vtArtefactoEnCurso;
	}

	public VtArtefacto getArtefactoSeleccionado() {
		return artefactoSeleccionado;
	}

	public void setArtefactoSeleccionado(VtArtefacto artefactoSeleccionado) {
		this.artefactoSeleccionado = artefactoSeleccionado;
	}

	public void setVtArtefactoEnCurso(VtArtefacto vtArtefactoEnCurso) {
		this.vtArtefactoEnCurso = vtArtefactoEnCurso;
	}

	public VtArtefacto getVtArtefactoFinalizado() {
		return vtArtefactoFinalizado;
	}

	public void setVtArtefactoFinalizado(VtArtefacto vtArtefactoFinalizado) {
		this.vtArtefactoFinalizado = vtArtefactoFinalizado;
	}


	public VtUsuarioArtefacto getUsuarioArtefacto() {
		return usuarioArtefacto;
	}

	public void setUsuarioArtefacto(VtUsuarioArtefacto usuarioArtefacto) {
		this.usuarioArtefacto = usuarioArtefacto;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public List<VtArtefacto> getLosArtefactosPorHacer() {
		try {
			if (sprintSeleccionado!=null) {
				losArtefactosPorHacer = new ArrayList<>();
				losArtefactosPorHacer = businessDelegatorView.findArtefactosBySprintAndEstado(sprintSeleccionado.getSpriCodigo(), 1L);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return losArtefactosPorHacer;
	}

	public void setLosArtefactosPorHacer(List<VtArtefacto> losArtefactosPorHacer) {
		this.losArtefactosPorHacer = losArtefactosPorHacer;
	}

	public List<VtArtefacto> getLosArtefactosEnCurso() {
		try {
			if (sprintSeleccionado!=null) {
				losArtefactosEnCurso = new ArrayList<>();
				losArtefactosEnCurso = businessDelegatorView.findArtefactosBySprintAndEstado(sprintSeleccionado.getSpriCodigo(), 2L);
				
				
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return losArtefactosEnCurso;
	}

	public void setLosArtefactosEnCurso(List<VtArtefacto> losArtefactosEnCurso) {
		this.losArtefactosEnCurso = losArtefactosEnCurso;
	}

	public List<VtArtefacto> getLosArtefactosFinalizados() {
		try {
			if (sprintSeleccionado!=null) {
				losArtefactosFinalizados = new ArrayList<>();
				losArtefactosFinalizados = businessDelegatorView.findArtefactosBySprintAndEstado(sprintSeleccionado.getSpriCodigo(), 3L);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return losArtefactosFinalizados;
	}

	public void setLosArtefactosFinalizados(List<VtArtefacto> losArtefactosFinalizados) {
		this.losArtefactosFinalizados = losArtefactosFinalizados;
	}

	public VtSprint getSprintSeleccionado() {
		return sprintSeleccionado;
	}

	public void setSprintSeleccionado(VtSprint sprintSeleccionado) {
		this.sprintSeleccionado = sprintSeleccionado;
	}	

	public String getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(String usuarioActual) {
		this.usuarioActual = usuarioActual;
	}
	//..................................................................................................	


	public String sprintAction(){
		return "/XHTML/listaSprint.xhtml";
	}
	
	public String sprintClienteAction(){
		return "/cliente/listaSprint.xhtml";
	}
	
	public void moverArtefacto() throws Exception{
		try {
			VtUsuario vtUsuario = businessDelegatorView.findUsuarioByLogin(usuarioActual);
			if (vtArtefactoPorHacer!=null) {
				VtEstado vtEstado = businessDelegatorView.getVtEstado(2L);
				vtArtefactoPorHacer.setVtEstado(vtEstado);
				vtArtefactoPorHacer.setUsuModificador(vtUsuario.getUsuaCodigo());
				vtArtefactoPorHacer.setFechaModificacion(new Date());
				businessDelegatorView.updateVtArtefacto(vtArtefactoPorHacer);
				
			}else if (vtArtefactoEnCurso != null) {
				
				VtEstado vtEstado = businessDelegatorView.getVtEstado(3L);			
				vtArtefactoEnCurso.setVtEstado(vtEstado);
				vtArtefactoEnCurso.setUsuModificador(vtUsuario.getUsuaCodigo());
				vtArtefactoEnCurso.setFechaModificacion(new Date());
				businessDelegatorView.updateVtArtefacto(vtArtefactoEnCurso);

			}else if (vtArtefactoFinalizado != null) {
				
				VtEstado vtEstado = businessDelegatorView.getVtEstado(2L);			
				vtArtefactoFinalizado.setVtEstado(vtEstado);
				vtArtefactoFinalizado.setUsuModificador(vtUsuario.getUsuaCodigo());
				vtArtefactoFinalizado.setFechaModificacion(new Date());
				businessDelegatorView.updateVtArtefacto(vtArtefactoFinalizado);

			}
			calculoArtefactos();
			FacesUtils.addInfoMessage("El artefacto se movio con exito.");
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	public void artefactoDetallado() throws Exception{
		if (vtArtefactoPorHacer!=null) {
			artefactoSeleccionado = vtArtefactoPorHacer;
		}else if (vtArtefactoEnCurso!=null) {
			artefactoSeleccionado = vtArtefactoEnCurso;
		}else if (vtArtefactoFinalizado!=null) {
			artefactoSeleccionado = vtArtefactoFinalizado;
		}	
		FacesUtils.putinSession("artefactoSeleccionado", artefactoSeleccionado);
		usuarioArtefacto = businessDelegatorView.findUsuarioArtefactoByArtefacto(artefactoSeleccionado);
		FacesUtils.putinSession("usuarioArtefacto", usuarioArtefacto);
		RequestContext.getCurrentInstance().execute("PF('dlgDetaArtefacto').show();");
	}
	
	public void calculoArtefactos() {
		if (losArtefactosEnCurso!=null || losArtefactosPorHacer != null) {
			totalArtefactos = losArtefactosPorHacer.size() + losArtefactosEnCurso.size();
		}
		
	}
	
	public String terminarSprintAction() {
		try {
			VtUsuario vtUsuario = businessDelegatorView.findUsuarioByLogin(usuarioActual);
			if (losArtefactosEnCurso!=null) {
				for (VtArtefacto vtArtefacto : losArtefactosEnCurso) {
					VtEstado vtEstado = businessDelegatorView.getVtEstado(1L);
					
					vtArtefacto.setVtEstado(vtEstado);
					vtArtefacto.setVtSprint(null);
					
					vtArtefacto.setFechaModificacion(new Date());
					vtArtefacto.setUsuModificador(vtUsuario.getUsuaCodigo());
					
					businessDelegatorView.updateVtArtefacto(vtArtefacto);
				}
				FacesUtils.addInfoMessage("Los artefactos que estaban 'En curso' "
						+ "se les asigno en estado 'Creado' y se sacaron del Sprint");
			}
			if (losArtefactosPorHacer != null) {
				for (VtArtefacto vtArtefacto : losArtefactosPorHacer) {
					VtEstado vtEstado = businessDelegatorView.getVtEstado(1L);
					
					vtArtefacto.setVtEstado(vtEstado);
					vtArtefacto.setVtSprint(null);
					
					vtArtefacto.setFechaModificacion(new Date());
					vtArtefacto.setUsuModificador(vtUsuario.getUsuaCodigo());
					
					businessDelegatorView.updateVtArtefacto(vtArtefacto);
				}
				FacesUtils.addInfoMessage("Los artefactos que estaban 'Por Hacer' "
						+ "se les asigno en estado 'Creado' y se sacaron del Sprint");
			}
			
			VtEstadoSprint vtEstadoSprint = businessDelegatorView.getVtEstadoSprint(3L);
			
			sprintSeleccionado.setUsuModificador(vtUsuario.getUsuaCodigo());
			sprintSeleccionado.setFechaModificacion(new Date());
			sprintSeleccionado.setVtEstadoSprint(vtEstadoSprint);
			
			businessDelegatorView.updateVtSprint(sprintSeleccionado);
			return "/XHTML/listaSprint.xhtml";
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			return "";
		}

	}
	
	private void iniciarBurndown() throws Exception{
		
		Long tiempoTotalEstimado = businessDelegatorView.totalEsfuerzoEstimadoArtefactoPorSprint(sprintSeleccionado.getSpriCodigo());
		
		Long totalDias = (sprintSeleccionado.getFechaFin().getTime() - sprintSeleccionado.getFechaInicio().getTime());
		totalDias = TimeUnit.MILLISECONDS.toDays(totalDias);
		
		DateTime diaInicio = new DateTime(""+sprintSeleccionado.getFechaInicio());
		diaInicio = DateTime.forDateOnly(diaInicio.getYear(), diaInicio.getMonth(), diaInicio.getDay());
		
		burndownChart = new LineChartModel();
		
		LineChartSeries blueLine = new LineChartSeries();
		blueLine.setLabel("Esfuerzo Real");
		
		
		LineChartSeries grayLine = new LineChartSeries();
		grayLine.setLabel("Esfuerzo Estimado");
		
		BarChartSeries barras = new BarChartSeries();
		
		
		blueLine.set("Estimado", tiempoTotalEstimado);
		grayLine.set("Estimado", tiempoTotalEstimado);
		barras.set("Estimado", tiempoTotalEstimado);
		
		Long tiempoDedicadoDia = businessDelegatorView.sumatoriaTiempoDedicadoPorSprintFecha(sprintSeleccionado.getSpriCodigo(), diaInicio);
		Long tiempoEstimadoDia = tiempoDedicadoDia;
		
		
		for (int i = 0; i <= totalDias; i++) {
			
			if (i < 1) {
				tiempoDedicadoDia = tiempoTotalEstimado - tiempoDedicadoDia; 
				tiempoEstimadoDia = tiempoTotalEstimado - (tiempoTotalEstimado/(totalDias+1));
			}else {
				tiempoDedicadoDia = tiempoDedicadoDia - businessDelegatorView.sumatoriaTiempoDedicadoPorSprintFecha(sprintSeleccionado.getSpriCodigo(), diaInicio.plusDays(i));
				tiempoEstimadoDia = tiempoEstimadoDia - (tiempoTotalEstimado/(totalDias+1));
			}
			
			barras.set(""+diaInicio.plusDays(i), tiempoDedicadoDia);
			blueLine.set(""+diaInicio.plusDays(i), tiempoDedicadoDia);
			grayLine.set(""+diaInicio.plusDays(i), tiempoEstimadoDia);
		}
		
		burndownChart.addSeries(barras);
		burndownChart.addSeries(blueLine);
		burndownChart.addSeries(grayLine);
		
		
		burndownChart.setTitle("Burndown Chart");
		burndownChart.setMouseoverHighlight(false);
		
		burndownChart.getAxes().put(AxisType.X, new CategoryAxis("Dias"));
		
		Axis yAxis = burndownChart.getAxis(AxisType.Y);
        yAxis.setMin(0);     
        yAxis.setMax(tiempoTotalEstimado+100);
	}

}
