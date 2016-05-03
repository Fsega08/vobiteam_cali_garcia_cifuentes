package com.vobi.team.presentation.backingBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtEstado;
import com.vobi.team.modelo.VtEstadoSprint;
import com.vobi.team.modelo.VtSprint;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.VtUsuarioArtefacto;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;
import com.vobi.team.utilities.Utilities;

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
	
	private int totalArtefactos;
	
	///Dialog Horas Trabajadas
	private InputText txtEsfuerzo;
	

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

			getLosArtefactosPorHacer();
			getLosArtefactosEnCurso();
			getLosArtefactosFinalizados();
			
			totalArtefactos = losArtefactosPorHacer.size() + losArtefactosEnCurso.size();

		} catch (Exception e) {
			log.info(e.getMessage());
		}

	}	
	//............................................................................................

	

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
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
				for (VtArtefacto vtArtefacto : sprintSeleccionado.getVtArtefactos()) {
					if (vtArtefacto.getVtEstado().getEstaCodigo() == 1L) {
						losArtefactosPorHacer.add(vtArtefacto);
					}
				}
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
				for (VtArtefacto vtArtefacto : sprintSeleccionado.getVtArtefactos()) {
					if (vtArtefacto.getVtEstado().getEstaCodigo() == 2L) {
						losArtefactosEnCurso.add(vtArtefacto);
					}
				}
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
				for (VtArtefacto vtArtefacto : sprintSeleccionado.getVtArtefactos()) {
					if (vtArtefacto.getVtEstado().getEstaCodigo() == 3L) {
						losArtefactosFinalizados.add(vtArtefacto);
					}
				}
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
	
	public void esfuerzoRealAction() throws Exception{
		try {
			VtUsuario vtUsuario = businessDelegatorView.findUsuarioByLogin(usuarioActual);
			if (vtArtefactoPorHacer!=null) {
				
				if (txtEsfuerzo.getValue().toString().trim().equals("") == true || txtEsfuerzo.getValue() == null || !Utilities.isNumeric(txtEsfuerzo.getValue().toString().trim())) {
					throw new Exception("Por favor ingrese el esfuerzo real, recuerde que este campo es de valor numerico");
				}
				
				int esfuerzo = Integer.parseInt(txtEsfuerzo.getValue().toString().trim());
				esfuerzo = esfuerzo + vtArtefactoPorHacer.getEsfuerzoReal();
				vtArtefactoPorHacer.setEsfuerzoReal(esfuerzo);
				VtEstado vtEstado = businessDelegatorView.getVtEstado(2L);
				
				vtArtefactoPorHacer.setVtEstado(vtEstado);
				vtArtefactoPorHacer.setUsuModificador(vtUsuario.getUsuaCodigo());
				vtArtefactoPorHacer.setFechaModificacion(new Date());
				businessDelegatorView.updateVtArtefacto(vtArtefactoPorHacer);
				
				limpiarAction();
			}else if (vtArtefactoEnCurso != null) {
				if (txtEsfuerzo.getValue().toString().trim().equals("") == true || txtEsfuerzo.getValue() == null || !Utilities.isNumeric(txtEsfuerzo.getValue().toString().trim())) {
					throw new Exception("Por favor ingrese el esfuerzo real, recuerde que este campo es de valor numerico");
				}
				int esfuerzo = Integer.parseInt(txtEsfuerzo.getValue().toString().trim());
				esfuerzo = esfuerzo + vtArtefactoEnCurso.getEsfuerzoReal();
				vtArtefactoEnCurso.setEsfuerzoReal(esfuerzo);
				VtEstado vtEstado = businessDelegatorView.getVtEstado(3L);
				
				vtArtefactoEnCurso.setVtEstado(vtEstado);
				vtArtefactoEnCurso.setUsuModificador(vtUsuario.getUsuaCodigo());
				vtArtefactoEnCurso.setFechaModificacion(new Date());
				businessDelegatorView.updateVtArtefacto(vtArtefactoEnCurso);
				limpiarAction();
			}
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
	

	public void limpiarAction() {
		txtEsfuerzo.resetValue();
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

}
