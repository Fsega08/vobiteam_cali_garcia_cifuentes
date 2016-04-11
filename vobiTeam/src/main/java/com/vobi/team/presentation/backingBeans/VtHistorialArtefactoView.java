package com.vobi.team.presentation.backingBeans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtHistoriaArtefacto;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;



@ManagedBean
@ViewScoped
public class VtHistorialArtefactoView {

	public final static Logger log=LoggerFactory.getLogger(VtHistorialArtefactoView.class);

	private String usuarioActual = SecurityContextHolder.getContext().getAuthentication().getName();
	
	//////// Atributos Historial Artefacto ////////

	private InputText txtHistorialNombre;
	private InputTextarea txtHtistorialDescripcion;
	private InputText txtHistorialEsfuerzoEstimado;
	private InputText txtHistorialEsfuerzoReal;
	private InputText txtHistorialEsfuerzoRestante;
	private InputText txtHistorialPuntos;
	private InputText txtHistorialOrigen;	
	
	private CommandButton btnAsignar;

	///////////////////////////////////////////////////////////


	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private VtArtefacto artefactoSeleccionado;

	private List<VtHistoriaArtefacto> elHistorialArtefacto;
	private VtHistoriaArtefacto historialArtefactoSeleccionado;
	
	//////////////////////////////////////////////////////////////
	
	
	@PostConstruct
	
	public void init(){
		try {			
			artefactoSeleccionado = (VtArtefacto) FacesUtils.getfromSession("artefactoSeleccionado");
			
		} catch (Exception e) {
			log.info(e.getMessage());
		}		
	}
	
	
	public String getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(String usuarioActual) {
		this.usuarioActual = usuarioActual;
	}

	public VtHistoriaArtefacto getHistorialArtefactoSeleccionado() {
		return historialArtefactoSeleccionado;
	}

	public void setHistorialArtefactoSeleccionado(VtHistoriaArtefacto historialArtefactoSeleccionado) {
		this.historialArtefactoSeleccionado = historialArtefactoSeleccionado;
	}
	

	public List<VtHistoriaArtefacto> getElHistorialArtefacto() {
		try {
			artefactoSeleccionado = (VtArtefacto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("artefactoSeleccionado");
			if (artefactoSeleccionado!=null) {
				elHistorialArtefacto = businessDelegatorView.findHistoriaByArtefacto(artefactoSeleccionado);

			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return elHistorialArtefacto;
	}

	public void setElHistorialArtefacto(List<VtHistoriaArtefacto> elHistorialArtefacto) {
		this.elHistorialArtefacto = elHistorialArtefacto;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public VtArtefacto getArtefactoSeleccionado() {
		return artefactoSeleccionado;
	}

	public void setArtefactoSeleccionado(VtArtefacto artefactoSeleccionado) {
		this.artefactoSeleccionado = artefactoSeleccionado;
	}

	public InputText getTxtHistorialNombre() {
		return txtHistorialNombre;
	}

	public void setTxtHistorialNombre(InputText txtHistorialNombre) {
		this.txtHistorialNombre = txtHistorialNombre;
	}

	public InputTextarea getTxtHtistorialDescripcion() {
		return txtHtistorialDescripcion;
	}

	public void setTxtHtistorialDescripcion(InputTextarea txtHtistorialDescripcion) {
		this.txtHtistorialDescripcion = txtHtistorialDescripcion;
	}

	public InputText getTxtHistorialEsfuerzoEstimado() {
		return txtHistorialEsfuerzoEstimado;
	}

	public void setTxtHistorialEsfuerzoEstimado(InputText txtHistorialEsfuerzoEstimado) {
		this.txtHistorialEsfuerzoEstimado = txtHistorialEsfuerzoEstimado;
	}

	public InputText getTxtHistorialEsfuerzoReal() {
		return txtHistorialEsfuerzoReal;
	}

	public void setTxtHistorialEsfuerzoReal(InputText txtHistorialEsfuerzoReal) {
		this.txtHistorialEsfuerzoReal = txtHistorialEsfuerzoReal;
	}

	public InputText getTxtHistorialEsfuerzoRestante() {
		return txtHistorialEsfuerzoRestante;
	}

	public void setTxtHistorialEsfuerzoRestante(InputText txtHistorialEsfuerzoRestante) {
		this.txtHistorialEsfuerzoRestante = txtHistorialEsfuerzoRestante;
	}

	public InputText getTxtHistorialPuntos() {
		return txtHistorialPuntos;
	}

	public void setTxtHistorialPuntos(InputText txtHistorialPuntos) {
		this.txtHistorialPuntos = txtHistorialPuntos;
	}

	public InputText getTxtHistorialOrigen() {
		return txtHistorialOrigen;
	}

	public void setTxtHistorialOrigen(InputText txtHistorialOrigen) {
		this.txtHistorialOrigen = txtHistorialOrigen;
	}

	public CommandButton getBtnAsignar() {
		return btnAsignar;
	}

	public void setBtnAsignar(CommandButton btnAsignar) {
		this.btnAsignar = btnAsignar;
	}
	
	
	
	public void hidratarArtefactoMod() {
		log.info("Historia Artefacto= " + historialArtefactoSeleccionado.getHistoriaCodigo());
		if (historialArtefactoSeleccionado != null) {

			txtHistorialNombre.setValue(historialArtefactoSeleccionado.getTitulo());
			txtHtistorialDescripcion.setValue(historialArtefactoSeleccionado.getDescripcion());
			txtHistorialEsfuerzoEstimado.setValue(""+historialArtefactoSeleccionado.getEsfuerzoEstimado());
			txtHistorialEsfuerzoRestante.setValue(""+historialArtefactoSeleccionado.getEsfuerzoRestante());
			txtHistorialEsfuerzoReal.setValue(""+historialArtefactoSeleccionado.getEsfuerzoReal());
			txtHistorialPuntos.setValue(""+historialArtefactoSeleccionado.getPuntos());
			txtHistorialOrigen.setValue(historialArtefactoSeleccionado.getOrigen());
			
		}else {
			log.info("No se ha seleccionado ningúna historia");
		}
	}

    public void asignarArtefactoAction() throws Exception{
    	
    	VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);
    	try {
			
    		artefactoSeleccionado.setTitulo(historialArtefactoSeleccionado.getTitulo());
        	artefactoSeleccionado.setDescripcion(historialArtefactoSeleccionado.getDescripcion());
        	artefactoSeleccionado.setEsfuerzoEstimado(historialArtefactoSeleccionado.getEsfuerzoEstimado());
        	artefactoSeleccionado.setEsfuerzoRestante(historialArtefactoSeleccionado.getEsfuerzoRestante());
        	artefactoSeleccionado.setEsfuerzoReal(historialArtefactoSeleccionado.getEsfuerzoReal());
        	artefactoSeleccionado.setPuntos(historialArtefactoSeleccionado.getPuntos());
        	artefactoSeleccionado.setOrigen(historialArtefactoSeleccionado.getOrigen());
        	artefactoSeleccionado.setUsuModificador(vtUsuarioActual.getUsuaCodigo());
        	artefactoSeleccionado.setFechaModificacion(new Date());
        	
        	businessDelegatorView.updateVtArtefacto(artefactoSeleccionado);
    		
    		FacesUtils.addInfoMessage("El artefacto se ha restaurado con exito");	

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
    }
}
