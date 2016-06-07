package com.vobi.team.presentation.backingBeans;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputmask.InputMask;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.modelo.VtArchivo;
import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtEstado;
import com.vobi.team.modelo.VtInteres;
import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtPrioridad;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtSprint;
import com.vobi.team.modelo.VtTipoArtefacto;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.VtUsuarioArtefacto;
import com.vobi.team.modelo.dto.VtArtefactoDTO;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;
import com.vobi.team.utilities.Utilities;

@ManagedBean
@ViewScoped
public class VtArtefactoDesarrolladorView {

	public final static Logger log=LoggerFactory.getLogger(VtArtefactoDesarrolladorView.class);

	private String usuarioActual = SecurityContextHolder.getContext().getAuthentication().getName();

	//////// Atributos Crear Artefacto ////////

	private InputText txtCrearNombre;
	private InputTextarea txtCrearDescripcion;
	private InputMask txtCrearEsfuerzoEstimado;
	private InputMask txtCrearEsfuerzoReal;
	private InputMask txtCrearEsfuerzoRestante;
	private InputMask txtCrearPuntos;
	private InputText txtCrearOrigen;
	private List<SelectItem> losCrearTiposArtefactos;
	private SelectOneMenu somCrearTipoArtefacto;
	private List<SelectItem> lasCrearPrioridadesArtefactos;
	private SelectOneMenu somCrearPrioridadesArtefacto;
	private List<SelectItem> losCrearEstadosArtefactos;
	private SelectOneMenu somCrearEstadoArtefacto;


	private CommandButton btnCrear;
	private CommandButton btnCArtefacto;
	private CommandButton btnCrearLimpiar;


	


	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private VtArtefacto artefactoSeleccionado;
	private VtArtefactoDTO artefactoSeleccionadoDTO;
	private List<VtArtefacto> losArtefactos;

	private List<VtArchivo> subirArchivos;
	
	private StreamedContent file;
	private VtArchivo archivoSeleccionado;
	private List<VtArchivo> losArchivos;


	private VtPilaProducto backlogSeleccionado;
	private VtProyecto proyectoActual;
	private VtUsuarioArtefacto usuarioArtefacto;
	
	private Long permisos;

	@PostConstruct
	public void init(){

		try {
			
			permisos = (Long) FacesUtils.getfromSession("permisos");
			
			if (FacesUtils.getfromSession("backlogSeleccionado") == null) {
				if (permisos == 1) {
					FacesUtils.getExternalContext().redirect("/vobiTeam/XHTML/TreeTable.xhtml");
				}
				if (permisos == 2) {
					FacesUtils.getExternalContext().redirect("/vobiTeam/desarrollador/tableDesarrollador.xhtml");
				}
				if (permisos == 3) {
					FacesUtils.getExternalContext().redirect("/vobiTeam/cliente/TreeTableCliente.xhtml");
				}
			}
			
			backlogSeleccionado = (VtPilaProducto) FacesUtils.getfromSession("backlogSeleccionado");
			subirArchivos = new ArrayList<VtArchivo>();
			proyectoActual = backlogSeleccionado.getVtProyecto();
			artefactoSeleccionado = (VtArtefacto) FacesUtils.getfromSession("artefactoSeleccionado");			
			usuarioArtefacto = (VtUsuarioArtefacto) FacesUtils.getfromSession("usuarioArtefacto");
			
			if(artefactoSeleccionado != null){
				artefactoSeleccionadoDTO = businessDelegatorView.getVtArtefactoDTO(artefactoSeleccionado);
			}else {
				artefactoSeleccionadoDTO = new VtArtefactoDTO();
			}
			
		} catch (Exception e) {
			log.info(e.getMessage());
		}		
		
	}
	
	public Long getPermisos() {
		return permisos;
	}
	
	public void setPermisos(Long permisos) {
		this.permisos = permisos;
	}

	public VtArtefactoDTO getArtefactoSeleccionadoDTO() {
		return artefactoSeleccionadoDTO;
	}

	public void setArtefactoSeleccionadoDTO(VtArtefactoDTO artefactoSeleccionadoDTO) {
		this.artefactoSeleccionadoDTO = artefactoSeleccionadoDTO;
	}

	public String getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(String usuarioActual) {
		this.usuarioActual = usuarioActual;
	}	

	public VtArchivo getArchivoSeleccionado() {
		return archivoSeleccionado;
	}
	
	public void setArchivoSeleccionado(VtArchivo archivoSeleccionado) {
		this.archivoSeleccionado = archivoSeleccionado;
	}

	public List<VtArchivo> getLosArchivos() {
		try {
			if (artefactoSeleccionado!=null) {

				losArchivos = businessDelegatorView.findArchivosByArtefactos(artefactoSeleccionado);

			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}

		return losArchivos;
	}

	public void setLosArchivos(List<VtArchivo> losArchivos) {
		this.losArchivos = losArchivos;
	}

	
	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	
	public InputText getTxtCrearNombre() {
		return txtCrearNombre;
	}

	public void setTxtCrearNombre(InputText txtCrearNombre) {
		this.txtCrearNombre = txtCrearNombre;
	}

	public InputTextarea getTxtCrearDescripcion() {
		return txtCrearDescripcion;
	}

	public void setTxtCrearDescripcion(InputTextarea txtCrearDescripcion) {
		this.txtCrearDescripcion = txtCrearDescripcion;
	}

	public InputMask getTxtCrearEsfuerzoEstimado() {
		return txtCrearEsfuerzoEstimado;
	}

	public void setTxtCrearEsfuerzoEstimado(InputMask txtCrearEsfuerzoEstimado) {
		this.txtCrearEsfuerzoEstimado = txtCrearEsfuerzoEstimado;
	}

	public List<VtArchivo> getSubirArchivos() {
		return subirArchivos;
	}

	public void setSubirArchivos(List<VtArchivo> subirArchivos) {
		this.subirArchivos = subirArchivos;
	}

	public InputMask getTxtCrearEsfuerzoReal() {
		return txtCrearEsfuerzoReal;
	}

	public void setTxtCrearEsfuerzoReal(InputMask txtCrearEsfuerzoReal) {
		this.txtCrearEsfuerzoReal = txtCrearEsfuerzoReal;
	}

	public InputMask getTxtCrearEsfuerzoRestante() {
		return txtCrearEsfuerzoRestante;
	}

	public void setTxtCrearEsfuerzoRestante(InputMask txtCrearEsfuerzoRestante) {
		this.txtCrearEsfuerzoRestante = txtCrearEsfuerzoRestante;
	}

	public InputMask getTxtCrearPuntos() {
		return txtCrearPuntos;
	}

	public void setTxtCrearPuntos(InputMask txtCrearPuntos) {
		this.txtCrearPuntos = txtCrearPuntos;
	}

	public InputText getTxtCrearOrigen() {
		return txtCrearOrigen;
	}

	public CommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public CommandButton getBtnCrearLimpiar() {
		return btnCrearLimpiar;
	}

	public void setBtnCrearLimpiar(CommandButton btnCrearLimpiar) {
		this.btnCrearLimpiar = btnCrearLimpiar;
	}

	public void setTxtCrearOrigen(InputText txtCrearOrigen) {
		this.txtCrearOrigen = txtCrearOrigen;
	}

	public List<SelectItem> getLosCrearTiposArtefactos() {
		try {
			if (losCrearTiposArtefactos==null) {
				List<VtTipoArtefacto> listaTiposArtefactos = businessDelegatorView.getVtTipoArtefacto();
				losCrearTiposArtefactos = new ArrayList<SelectItem>();
				for (VtTipoArtefacto vtTipoArtefacto : listaTiposArtefactos) {
					if (vtTipoArtefacto.getActivo().equals("S")) {
						losCrearTiposArtefactos.add(new SelectItem(vtTipoArtefacto.getTparCodigo(), vtTipoArtefacto.getNombre()));
					}
					
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return losCrearTiposArtefactos;
	}

	public void setLosCrearTiposArtefactos(List<SelectItem> losCrearTiposArtefactos) {
		this.losCrearTiposArtefactos = losCrearTiposArtefactos;
	}

	public SelectOneMenu getSomCrearTipoArtefacto() {
		return somCrearTipoArtefacto;
	}

	public void setSomCrearTipoArtefacto(SelectOneMenu somCrearTipoArtefacto) {
		this.somCrearTipoArtefacto = somCrearTipoArtefacto;
	}

	public CommandButton getBtnCArtefacto() {
		return btnCArtefacto;
	}

	public void setBtnCArtefacto(CommandButton btnCArtefacto) {
		this.btnCArtefacto = btnCArtefacto;
	}

	public List<SelectItem> getLasCrearPrioridadesArtefactos() {
		try {
			if (lasCrearPrioridadesArtefactos==null) {
				List<VtPrioridad> listaPrioridadesArtefactos = businessDelegatorView.getVtPrioridad();
				lasCrearPrioridadesArtefactos = new ArrayList<SelectItem>();
				for (VtPrioridad vtPrioridad : listaPrioridadesArtefactos) {
					if (vtPrioridad.getActivo().equals("S")) {
						lasCrearPrioridadesArtefactos.add(new SelectItem(vtPrioridad.getPrioCodigo(), vtPrioridad.getNombre()));
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return lasCrearPrioridadesArtefactos;
	}

	public void setLasCrearPrioridadesArtefactos(List<SelectItem> lasCrearPrioridadesArtefactos) {
		this.lasCrearPrioridadesArtefactos = lasCrearPrioridadesArtefactos;
	}

	public SelectOneMenu getSomCrearPrioridadesArtefacto() {
		return somCrearPrioridadesArtefacto;
	}

	public void setSomCrearPrioridadesArtefacto(SelectOneMenu somCrearPrioridadesArtefacto) {
		this.somCrearPrioridadesArtefacto = somCrearPrioridadesArtefacto;
	}

	public List<SelectItem> getLosCrearEstadosArtefactos() {
		try {
			if (losCrearEstadosArtefactos==null) {
				List<VtEstado> listaEstadosArtefactos = businessDelegatorView.getVtEstado();
				losCrearEstadosArtefactos = new ArrayList<SelectItem>();
				for (VtEstado vtEstado : listaEstadosArtefactos) {
					if (vtEstado.getActivo().equals("S")) {
						losCrearEstadosArtefactos.add(new SelectItem(vtEstado.getEstaCodigo(), vtEstado.getNombre()));
					}
					
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return losCrearEstadosArtefactos;
	}

	public void setLosCrearEstadosArtefactos(List<SelectItem> losCrearEstadosArtefactos) {
		this.losCrearEstadosArtefactos = losCrearEstadosArtefactos;
	}

	public SelectOneMenu getSomCrearEstadoArtefacto() {
		return somCrearEstadoArtefacto;
	}

	public void setSomCrearEstadoArtefacto(SelectOneMenu somCrearEstadoArtefacto) {
		this.somCrearEstadoArtefacto = somCrearEstadoArtefacto;
	}

	public VtPilaProducto getBacklogSeleccionado() {
		return backlogSeleccionado;
	}

	public void setBacklogSeleccionado(VtPilaProducto backlogSeleccionado) {
		this.backlogSeleccionado = backlogSeleccionado;
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

	public List<VtArtefacto> getLosArtefactos() {
		try {
			if (backlogSeleccionado!=null) {
				losArtefactos = businessDelegatorView.findArtefactosVaciosPorBacklog(backlogSeleccionado.getPilaCodigo());
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return losArtefactos;
	}

	public void setLosArtefactos(List<VtArtefacto> losArtefactos) {
		this.losArtefactos = losArtefactos;
	}
	
	public VtProyecto getProyectoActual() {
		return proyectoActual;
	}

	public void setProyectoActual(VtProyecto proyectoActual) {
		this.proyectoActual = proyectoActual;
	}

	public VtUsuarioArtefacto getUsuarioArtefacto() {
		return usuarioArtefacto;
	}

	public void setUsuarioArtefacto(VtUsuarioArtefacto usuarioArtefacto) {
		this.usuarioArtefacto = usuarioArtefacto;
	}
	
	
	//.......................................Fin de los Gets y Sets..............................................................

	public void crearAction() {

		try {
			
			if (somCrearTipoArtefacto.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione un tipo de artefacto");
			}
			
			VtArtefacto vtArtefacto = new VtArtefacto();
			VtTipoArtefacto vtTipoArtefacto = businessDelegatorView.getVtTipoArtefacto(Long.parseLong(somCrearTipoArtefacto.getValue().toString().trim()));
			vtArtefacto.setVtTipoArtefacto(vtTipoArtefacto);
			vtArtefacto.setTitulo(txtCrearNombre.getValue().toString());
			vtArtefacto.setDescripcion(txtCrearDescripcion.getValue().toString());
			
			if(vtArtefacto.getVtTipoArtefacto() == null){
				throw new Exception("Por favor, seleccione el tipo de artefacto.");
			}
			
			if (txtCrearNombre.getValue().toString().trim().equals("") == true || txtCrearNombre.getValue() == null) {
				throw new Exception("Por favor ingrese el nombre");
			}
			if (txtCrearDescripcion.getValue().toString().trim().equals("") == true || txtCrearDescripcion.getValue() == null) {
				throw new Exception("Por favor ingrese la descripción");
			}
			if (somCrearEstadoArtefacto.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione un estado para el artefacto");
			}
			if (somCrearPrioridadesArtefacto.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione una prioridad para el artefacto");
			}
				
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);
			
			
			if (vtTipoArtefacto.getTparCodigo() == 1L || vtTipoArtefacto.getTparCodigo() == 4L) {
				if (txtCrearPuntos.getValue().toString().trim().equals("") == true || txtCrearPuntos.getValue() == null) {
					throw new Exception("Por favor ingrese los puntos, recuerde este campo solo acepta tiempo");
				}
				if (txtCrearEsfuerzoEstimado.getValue().toString().trim().equals("") == true || txtCrearEsfuerzoEstimado.getValue() == null) {
					throw new Exception("Por favor ingrese el esfuerzo estimado, recuerde este campo solo acepta numeros");
				}
				if (txtCrearEsfuerzoReal.getValue().toString().trim().equals("") == true || txtCrearEsfuerzoReal.getValue() == null) {
					throw new Exception("Por favor ingrese el esfuerzo real, recuerde este campo solo acepta numeros");
				}
				if (txtCrearEsfuerzoRestante.getValue().toString().trim().equals("") == true || txtCrearEsfuerzoRestante.getValue() == null) {
					throw new Exception("Por favor ingrese el esfuerzo restante, recuerde que este campo solo acepta numeros");
				}
				
				vtArtefacto.setEsfuerzoEstimado(Utilities.pasarFormatoHoraAInteger(txtCrearEsfuerzoEstimado.getValue().toString().trim()));
				vtArtefacto.setEsfuerzoRestante(Utilities.pasarFormatoHoraAInteger(txtCrearEsfuerzoRestante.getValue().toString().trim()));
				vtArtefacto.setEsfuerzoReal(Utilities.pasarFormatoHoraAInteger(txtCrearEsfuerzoReal.getValue().toString().trim()));			
				vtArtefacto.setPuntos(Utilities.pasarFormatoHoraAInteger(txtCrearPuntos.getValue().toString().trim()	));
			}else {
				vtArtefacto.setEsfuerzoEstimado(0000);
				vtArtefacto.setEsfuerzoRestante(0000);
				vtArtefacto.setEsfuerzoReal(0000);			
				vtArtefacto.setPuntos(0000);
			}
			
			if (txtCrearOrigen.getValue().toString().trim().equals("") == true || txtCrearOrigen.getValue() == null) {
				throw new Exception("Por favor ingrese el origen del artefacto");
			}
			
			vtArtefacto.setOrigen(txtCrearOrigen.getValue().toString());			
			vtArtefacto.setActivo("S");
			vtArtefacto.setFechaCreacion(new Date());
			vtArtefacto.setFechaModificacion(new Date());
			vtArtefacto.setUsuCreador(vtUsuarioActual.getUsuaCodigo());
			vtArtefacto.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			VtEstado vtEstado = businessDelegatorView.getVtEstado(Long.parseLong(somCrearEstadoArtefacto.getValue().toString().trim()));
			vtArtefacto.setVtEstado(vtEstado);

			VtSprint sprintActivo = businessDelegatorView.buscarSprintActivoEnLaMismaPila(backlogSeleccionado);
			vtArtefacto.setVtSprint(sprintActivo);
			VtPrioridad vtPrioridad = businessDelegatorView.getVtPrioridad(Long.parseLong(somCrearPrioridadesArtefacto.getValue().toString().trim()));
			vtArtefacto.setVtPrioridad(vtPrioridad);

			vtArtefacto.setVtPilaProducto(backlogSeleccionado);
			
			VtInteres vtInteres = businessDelegatorView.getVtInteres(4L);		
				
			businessDelegatorView.saveVtArtefacto(vtArtefacto);				
			asignarDesarrollador(vtArtefacto, vtInteres, vtUsuarioActual);
			subirArchivos(vtArtefacto);
			
			FacesUtils.addInfoMessage("El artefacto se ha creado con exito");	
			
			losArtefactos = businessDelegatorView.findArtefactosVaciosPorBacklog(backlogSeleccionado.getPilaCodigo());
			limpiarCrearAction();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}
	
	private void subirArchivos(VtArtefacto vtArtefacto) throws Exception{
		if (subirArchivos != null || !(subirArchivos.isEmpty())) {
			for (VtArchivo vtArchivo : subirArchivos) {
				vtArchivo.setVtArtefacto(vtArtefacto);
				businessDelegatorView.saveVtArchivo(vtArchivo);
			}
			
			subirArchivos = new ArrayList<VtArchivo>();			
		}
	}
	
	private void asignarDesarrollador(VtArtefacto vtArtefacto, VtInteres vtInteres, VtUsuario vtUsuario) throws Exception{
		try {
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);			
			VtUsuarioArtefacto vtUsuarioArtefacto = businessDelegatorView.findUsuarioArtefactoByArtefacto(vtArtefacto);
			
			if(vtUsuarioArtefacto == null){
				
				vtUsuarioArtefacto = new VtUsuarioArtefacto();
				
				vtUsuarioArtefacto.setVtUsuario(vtUsuario);
				vtUsuarioArtefacto.setVtInteres(vtInteres);
				vtUsuarioArtefacto.setVtArtefacto(vtArtefacto);
				
				vtUsuarioArtefacto.setFechaCreacion(new Date());
				vtUsuarioArtefacto.setFechaModificacion(new Date());
				vtUsuarioArtefacto.setUsuCreador(vtUsuarioActual.getUsuaCodigo());
				vtUsuarioArtefacto.setUsuModificador(vtUsuarioActual.getUsuaCodigo());
				vtUsuarioArtefacto.setActivo("S");
				
				businessDelegatorView.saveVtUsuarioArtefacto(vtUsuarioArtefacto);
			}else{
				
				vtUsuarioArtefacto.setVtUsuario(vtUsuario);
				vtUsuarioArtefacto.setVtInteres(vtInteres);
				
				vtUsuarioArtefacto.setFechaModificacion(new Date());
				vtUsuarioArtefacto.setUsuModificador(vtUsuarioActual.getUsuaCodigo());
				
				businessDelegatorView.updateVtUsuarioArtefacto(vtUsuarioArtefacto);
				
			}
			
			
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		
		
	}

	public void limpiarCrearAction() {
		txtCrearDescripcion.resetValue();
		txtCrearNombre.resetValue();
		txtCrearOrigen.resetValue();
	
		txtCrearEsfuerzoEstimado.resetValue();
		txtCrearEsfuerzoReal.resetValue();
		txtCrearEsfuerzoRestante.resetValue();
		txtCrearPuntos.resetValue();		

		txtCrearEsfuerzoReal.setDisabled(true);
		txtCrearEsfuerzoRestante.setDisabled(true);
		txtCrearPuntos.setDisabled(true);
		
		somCrearEstadoArtefacto.setValue("-1");
		somCrearPrioridadesArtefacto.setValue("-1");
		somCrearTipoArtefacto.setValue("-1");

	}
	


	public void tipoArtefactoListener() {
		int valorTipoArtefacto = Integer.parseInt(somCrearTipoArtefacto.getValue().toString().trim());

		if (valorTipoArtefacto == 1 || valorTipoArtefacto==4) {
			txtCrearEsfuerzoEstimado.setDisabled(false);

		}else if (valorTipoArtefacto == 2 || valorTipoArtefacto==3) {
			txtCrearEsfuerzoEstimado.setDisabled(true);
			txtCrearEsfuerzoReal.setDisabled(true);
			txtCrearEsfuerzoRestante.setDisabled(true);
			txtCrearPuntos.setDisabled(true);

			txtCrearEsfuerzoEstimado.setValue(0);
			txtCrearEsfuerzoReal.setValue(0);
			txtCrearEsfuerzoRestante.setValue(0);
			txtCrearPuntos.setValue(0);
		}
	}

	
	public void esfuerzoListener() {
		txtCrearEsfuerzoReal.setDisabled(false);
		txtCrearEsfuerzoRestante.setDisabled(false);
		txtCrearPuntos.setDisabled(false);;		
		
		txtCrearEsfuerzoReal.setValue(txtCrearEsfuerzoEstimado.getValue().toString());
		txtCrearEsfuerzoRestante.setValue(txtCrearEsfuerzoEstimado.getValue().toString());
		txtCrearPuntos.setValue(txtCrearEsfuerzoEstimado.getValue().toString());

	}

	
		


	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void handleFileUpload(FileUploadEvent event) {

		try {
			VtArchivo vtArchivo = new VtArchivo();
			VtUsuario vtUsuario = businessDelegatorView.findUsuarioByLogin(usuarioActual);


			vtArchivo.setNombre(event.getFile().getFileName());
			vtArchivo.setFechaCreacion(new Date());
			vtArchivo.setFechaModificacion(new Date());
			vtArchivo.setUsuCreador(vtUsuario.getUsuaCodigo());
			vtArchivo.setUsuModificador(vtUsuario.getUsuaCodigo());
			vtArchivo.setActivo("S");
			vtArchivo.setArchivo(event.getFile().getContents());
			vtArchivo.setVtArtefacto(artefactoSeleccionado);

			businessDelegatorView.saveVtArchivo(vtArchivo);
			FacesUtils.addInfoMessage("Se subio el archivo " + event.getFile().getFileName());
		} catch (Exception e) {
			log.info(e.getMessage());
			FacesUtils.addInfoMessage(e.getMessage());
		}
	}

	public void createHandleFileUpload(FileUploadEvent event) {

		try {
			VtUsuario vtUsuario = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			VtArchivo vtArchivo = new VtArchivo();

			vtArchivo.setNombre(event.getFile().getFileName());
			vtArchivo.setFechaCreacion(new Date());
			vtArchivo.setFechaModificacion(new Date());
			vtArchivo.setUsuCreador(vtUsuario.getUsuaCodigo());
			vtArchivo.setUsuModificador(vtUsuario.getUsuaCodigo());
			vtArchivo.setActivo("S");
			vtArchivo.setArchivo(event.getFile().getContents());

			subirArchivos.add(vtArchivo);
			
			FacesUtils.addInfoMessage("El archivo esta listo para cargarlo, presione cargar cuando este listo.");	
		} catch (Exception e) {
			log.info(e.getMessage());
			FacesUtils.addInfoMessage(e.getMessage());
		}
	}


	public void FileDownloadView() {        

		try {
			VtArchivo vtArchivo = archivoSeleccionado;
			log.info("el archivo es= " + vtArchivo.getNombre());

			byte[] archivo = vtArchivo.getArchivo();

			InputStream stream = new ByteArrayInputStream(archivo);

			file = new DefaultStreamedContent(stream, null, vtArchivo.getNombre());

			FacesUtils.addInfoMessage("Disfrute su archivo");
		} catch (Exception e) {
			FacesUtils.addInfoMessage("Lo siento no se pudo descargar");
		}

	}
	
	public void artefactoSesionAction() throws Exception {
		FacesUtils.putinSession("artefactoSeleccionado",artefactoSeleccionado);
	}
	

	public String regresarAction(){
		FacesUtils.putinSession("backlogSeleccionado", null);
		return "/desarrollador/tableDesarrollador.xhtml";

	}
	
	public String modificarArtefactoAction() throws Exception{
		
		usuarioArtefacto = businessDelegatorView.findUsuarioArtefactoByArtefacto(artefactoSeleccionado);		
		
		FacesUtils.putinSession("artefactoSeleccionado", artefactoSeleccionado);
		FacesUtils.putinSession("usuarioArtefacto", usuarioArtefacto);
		return "/XHTML/modificarArtefactos.xhtml";	
		
	}

	public void borrarArchivo(){
		try {

			businessDelegatorView.deleteVtArchivo(archivoSeleccionado);

			FacesUtils.addInfoMessage("Archivo eliminado");
		} catch (Exception e) {
			FacesUtils.addInfoMessage("No se pudo eliminar el Archivo");
		}
	}

	public void reHandleFileUpload(FileUploadEvent event) {

		try {
			VtUsuario vtUsuario = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			archivoSeleccionado.setNombre(event.getFile().getFileName());
			archivoSeleccionado.setFechaModificacion(new Date());
			archivoSeleccionado.setUsuModificador(vtUsuario.getUsuaCodigo());
			archivoSeleccionado.setArchivo(event.getFile().getContents());

			businessDelegatorView.updateVtArchivo(archivoSeleccionado);
			FacesUtils.addInfoMessage("Se cambio por el archivo " + event.getFile().getFileName());
		} catch (Exception e) {
			log.info(e.getMessage());
			FacesUtils.addInfoMessage(e.getMessage());
		}

	}
}
