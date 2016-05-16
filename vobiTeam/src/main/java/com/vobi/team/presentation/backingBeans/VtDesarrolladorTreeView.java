package com.vobi.team.presentation.backingBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtProgresoArtefacto;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtProyectoUsuario;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.VtUsuarioArtefacto;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;
import com.vobi.team.utilities.Utilities;


@ManagedBean
@ViewScoped
public class VtDesarrolladorTreeView {
	public final static Logger log=LoggerFactory.getLogger(VtDesarrolladorTreeView.class);

	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private VtProyecto proyectoSeleccionado;
	private VtArtefacto artefactoSeleccionado;	

	private SelectOneMenu somProyectos;
	private List<SelectItem> losProyectos;

	private List<VtArtefacto> losArtefactos;


	//.......................Dialog Progreso..................

	private InputText txtEsfuerzoRestante;
	private InputText txtEsfuerzoReal;
	private InputText txtPuntos;	

	private InputText txtTiempoEstimado;
	private InputTextarea txtDescripcion;

	//.......................................................
	
	private List<VtProgresoArtefacto> losProgresos;


	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();	
	private VtUsuario desarrollador;



	@PostConstruct
	public void init(){
		try {
			desarrollador = businessDelegatorView.findUsuarioByLogin(usuarioActual);
			losArtefactos = new ArrayList<VtArtefacto>();

		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

	//-------------------------------------Inicio de Gets y Sets--------------------------------------------
	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public String getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(String usuarioActual) {
		this.usuarioActual = usuarioActual;
	}

	public VtProyecto getProyectoSeleccionado() {
		return proyectoSeleccionado;
	}

	public void setProyectoSeleccionado(VtProyecto proyectoSeleccionado) {
		this.proyectoSeleccionado = proyectoSeleccionado;
	}

	public SelectOneMenu getSomProyectos() {
		return somProyectos;
	}

	public void setSomProyectos(SelectOneMenu somProyectos) {
		this.somProyectos = somProyectos;
	}

	public List<SelectItem> getLosProyectos() {

		try {
			if(losProyectos == null){
				VtUsuario usuario = businessDelegatorView.findUsuarioByLogin(usuarioActual);
				losProyectos = new ArrayList<SelectItem>();
				for (VtProyectoUsuario proyectoUsuario : usuario.getVtProyectoUsuarios()) {
					losProyectos.add(new SelectItem(proyectoUsuario.getVtProyecto().getProyCodigo(), proyectoUsuario.getVtProyecto().getNombre()));
				}							
			}	

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return losProyectos;
	}

	public void setLosProyectos(List<SelectItem> losProyectos) {
		this.losProyectos = losProyectos;
	}

	public List<VtArtefacto> getLosArtefactos() {
		return losArtefactos;
	}

	public void setLosArtefactos(List<VtArtefacto> losArtefactos) {
		this.losArtefactos = losArtefactos;
	}

	public VtUsuario getDesarrollador() {
		return desarrollador;
	}

	public void setDesarrollador(VtUsuario desarrollador) {
		this.desarrollador = desarrollador;
	}	


	public VtArtefacto getArtefactoSeleccionado() {
		return artefactoSeleccionado;
	}

	public void setArtefactoSeleccionado(VtArtefacto artefactoSeleccionado) {
		this.artefactoSeleccionado = artefactoSeleccionado;
	}
	

	public List<VtProgresoArtefacto> getLosProgresos() {
		
		try {
			if(losProgresos == null){				
				losProgresos = new ArrayList<VtProgresoArtefacto>();			
				
			}else{
				if(artefactoSeleccionado != null){
					losProgresos = businessDelegatorView.findProgresoArtefactosPorArtefactos(artefactoSeleccionado);
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return losProgresos;
	}

	public void setLosProgresos(List<VtProgresoArtefacto> losProgresos) {
		this.losProgresos = losProgresos;
	}
	
	

	public InputText getTxtTiempoEstimado() {
		return txtTiempoEstimado;
	}

	public void setTxtTiempoEstimado(InputText txtTiempoEstimado) {
		this.txtTiempoEstimado = txtTiempoEstimado;
	}

	public InputTextarea getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(InputTextarea txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}	

	public InputText getTxtEsfuerzoRestante() {
		return txtEsfuerzoRestante;
	}

	public void setTxtEsfuerzoRestante(InputText txtEsfuerzoRestante) {
		this.txtEsfuerzoRestante = txtEsfuerzoRestante;
	}

	public InputText getTxtEsfuerzoReal() {
		return txtEsfuerzoReal;
	}

	public void setTxtEsfuerzoReal(InputText txtEsfuerzoReal) {
		this.txtEsfuerzoReal = txtEsfuerzoReal;
	}

	public InputText getTxtPuntos() {
		return txtPuntos;
	}

	public void setTxtPuntos(InputText txtPuntos) {
		this.txtPuntos = txtPuntos;
	}

	//---------------------------------Fin de los Gets y Sets--------------------------------------------------------

	public void proyectoListener() throws Exception{
		String codigoProyecto = somProyectos.getValue().toString().trim();

		VtProyecto proyecto = businessDelegatorView.getVtProyecto(Long.parseLong(codigoProyecto));

		if(!codigoProyecto.equals("-1") ){			

			for (VtUsuarioArtefacto usuarioArtefacto : desarrollador.getVtUsuarioArtefactos()) {				

				if(proyecto.getProyCodigo() == usuarioArtefacto.getVtArtefacto().getVtPilaProducto().getVtProyecto().getProyCodigo()){
					losArtefactos.add(usuarioArtefacto.getVtArtefacto());
				}
			}
		}
	}
	
	public void artefactoSeleccionadoAction(){
		txtEsfuerzoReal.setValue(artefactoSeleccionado.getEsfuerzoReal());
		txtEsfuerzoRestante.setValue(artefactoSeleccionado.getEsfuerzoRestante());
		txtPuntos.setValue(artefactoSeleccionado.getPuntos());
	}
	
	public void historiaProgresoAction(){
		getLosProgresos();
	}

	public void crearAction() {

		try {
			if (txtDescripcion.getValue().toString().trim().equals("") == true || txtDescripcion.getValue() == null) {
				throw new Exception("Por favor ingrese la descripción");
			}

			if (txtTiempoEstimado.getValue().toString().trim().equals("") == true || txtTiempoEstimado.getValue() == null || !Utilities.isNumeric(txtTiempoEstimado.getValue().toString().trim())) {
				throw new Exception("Por favor ingrese el esfuerzo estimado, recuerde este campo solo acepta numeros");
			}

			VtUsuario vtUsuario = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			VtProgresoArtefacto vtProgresoArtefacto =  new VtProgresoArtefacto();		

			vtProgresoArtefacto.setTiempoDedicado(Integer.parseInt(txtTiempoEstimado.getValue().toString().trim()));			
			vtProgresoArtefacto.setEsfuerzoRestante(artefactoSeleccionado.getEsfuerzoRestante() - (Integer.parseInt(txtTiempoEstimado.getValue().toString().trim())));			
			vtProgresoArtefacto.setPuntos(artefactoSeleccionado.getPuntos());			
			vtProgresoArtefacto.setEsfuerzoReal(artefactoSeleccionado.getEsfuerzoReal()+ vtProgresoArtefacto.getTiempoDedicado());			
			vtProgresoArtefacto.setDescripcion(txtDescripcion.getValue().toString());			
			vtProgresoArtefacto.setFechaCreacion(new Date());			
			vtProgresoArtefacto.setFechaModificacion(new Date());			
			vtProgresoArtefacto.setUsuCreador(vtUsuario.getUsuaCodigo());			
			vtProgresoArtefacto.setUsuModificador(vtUsuario.getUsuaCodigo());			
			vtProgresoArtefacto.setActivo("S");			
			vtProgresoArtefacto.setVtArtefacto(artefactoSeleccionado);

			artefactoSeleccionado.setEsfuerzoRestante(vtProgresoArtefacto.getEsfuerzoRestante());			
			artefactoSeleccionado.setEsfuerzoReal(vtProgresoArtefacto.getEsfuerzoReal());

			businessDelegatorView.saveVtProgresoArtefacto(vtProgresoArtefacto);			
			businessDelegatorView.updateVtArtefacto(artefactoSeleccionado);

			FacesUtils.addInfoMessage("El progreso fue agregado con exito");
			
			artefactoSeleccionadoAction();

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void limpiarAction() {
		txtDescripcion.resetValue();
		txtTiempoEstimado.resetValue();
	}

	




}
