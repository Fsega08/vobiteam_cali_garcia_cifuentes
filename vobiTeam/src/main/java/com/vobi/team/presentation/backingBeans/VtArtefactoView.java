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
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;
import com.vobi.team.utilities.Utilities;



@ManagedBean
@ViewScoped
public class VtArtefactoView {

	public final static Logger log=LoggerFactory.getLogger(VtArtefactoView.class);

	private String usuarioActual = SecurityContextHolder.getContext().getAuthentication().getName();

	//////// Atributos Crear Artefacto ////////

	private InputText txtCrearNombre;
	private InputTextarea txtCrearDescripcion;
	private InputText txtCrearEsfuerzoEstimado;
	private InputText txtCrearEsfuerzoReal;
	private InputText txtCrearEsfuerzoRestante;
	private InputText txtCrearPuntos;
	private InputText txtCrearOrigen;
	private List<SelectItem> losCrearTiposArtefactos;
	private SelectOneMenu somCrearTipoArtefacto;
	private List<SelectItem> lasCrearPrioridadesArtefactos;
	private SelectOneMenu somCrearPrioridadesArtefacto;
	private List<SelectItem> losCrearEstadosArtefactos;
	private SelectOneMenu somCrearEstadoArtefacto;
	private List<SelectItem> losCrearDesarrolladores;
	private SelectOneMenu somCrearDesarrolladores;
	private List<SelectItem> losCrearInteres;
	private SelectOneMenu somCrearInteres;

	private CommandButton btnCrear;
	private CommandButton btnCArtefacto;
	private CommandButton btnCrearLimpiar;


	/////////////////////////////////////////////////////////777

	//////////////////////MODIFICAR ARTEFACTO////////////////////////7
	private InputText txtNombre;
	private InputTextarea txtDescripcion;
	private InputText txtEsfuerzoEstimado;
	private InputText txtEsfuerzoReal;
	private InputText txtEsfuerzoRestante;
	private InputText txtPuntos;
	private InputText txtOrigen;
	private SelectOneMenu somArtefactoActivo;
	private List<SelectItem> losTiposArtefactos;
	private SelectOneMenu somTipoArtefacto;
	private List<SelectItem> lasPrioridadesArtefactos;
	private SelectOneMenu somPrioridadesArtefacto;
	private List<SelectItem> losEstadosArtefactos;
	private SelectOneMenu somEstadoArtefacto;
	private List<SelectItem> losDesarrolladores;
	private SelectOneMenu somDesarrolladores;
	private List<SelectItem> losInteres;
	private SelectOneMenu somInteres;
	
	private CommandButton btnModificar;

	private CommandButton btnLimpiar;

	//////////////////////////////////////////////////////////////


	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private VtArtefacto artefactoSeleccionado;
	private List<VtArtefacto> losArtefactos;

	private List<VtArchivo> subirArchivos;
	
	private StreamedContent file;
	private VtArchivo archivoSeleccionado;
	private List<VtArchivo> losArchivos;

	private VtSprint sprintSeleccionado;
	private VtPilaProducto backlogSeleccionado;
	private VtProyecto proyectoActual;
	private VtUsuarioArtefacto usuarioArtefacto;

	@PostConstruct
	public void init(){

		try {
			sprintSeleccionado = (VtSprint) FacesUtils.getfromSession("sprintSeleccionado");
			backlogSeleccionado = (VtPilaProducto) FacesUtils.getfromSession("backlogSeleccionado");
			subirArchivos = new ArrayList<VtArchivo>();
			proyectoActual = backlogSeleccionado.getVtProyecto();
			artefactoSeleccionado = (VtArtefacto) FacesUtils.getfromSession("artefactoSeleccionado");			
			usuarioArtefacto = (VtUsuarioArtefacto) FacesUtils.getfromSession("usuarioArtefacto");
			
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

	public SelectOneMenu getSomArtefactoActivo() {
		return somArtefactoActivo;
	}

	public void setSomArtefactoActivo(SelectOneMenu somArtefactoActivo) {
		this.somArtefactoActivo = somArtefactoActivo;
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
	
	public InputText getTxtEsfuerzoEstimado() {
		return txtEsfuerzoEstimado;
	}

	public void setTxtEsfuerzoEstimado(InputText txtEsfuerzoEstimado) {
		this.txtEsfuerzoEstimado = txtEsfuerzoEstimado;
	}
	
	public InputText getTxtEsfuerzoReal() {
		return txtEsfuerzoReal;
	}

	public void setTxtEsfuerzoReal(InputText txtEsfuerzoReal) {
		this.txtEsfuerzoReal = txtEsfuerzoReal;
	}

	public InputText getTxtEsfuerzoRestante() {
		return txtEsfuerzoRestante;
	}

	public void setTxtEsfuerzoRestante(InputText txtEsfuerzoRestante) {
		this.txtEsfuerzoRestante = txtEsfuerzoRestante;
	}

	public InputText getTxtPuntos() {
		return txtPuntos;
	}

	public void setTxtPuntos(InputText txtPuntos) {
		this.txtPuntos = txtPuntos;
	}

	public InputText getTxtOrigen() {
		return txtOrigen;
	}

	public void setTxtOrigen(InputText txtOrigen) {
		this.txtOrigen = txtOrigen;
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

	public List<SelectItem> getLosTiposArtefactos() {
		try {
			if (losTiposArtefactos==null) {
				List<VtTipoArtefacto> listaTiposArtefactos = businessDelegatorView.getVtTipoArtefacto();
				losTiposArtefactos = new ArrayList<SelectItem>();
				for (VtTipoArtefacto vtTipoArtefacto : listaTiposArtefactos) {
					losTiposArtefactos.add(new SelectItem(vtTipoArtefacto.getTparCodigo(), vtTipoArtefacto.getNombre()));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return losTiposArtefactos;
	}

	public void setLosTiposArtefactos(List<SelectItem> losTiposArtefactos) {
		this.losTiposArtefactos = losTiposArtefactos;
	}

	public SelectOneMenu getSomTipoArtefacto() {
		return somTipoArtefacto;
	}

	public void setSomTipoArtefacto(SelectOneMenu somTipoArtefacto) {
		this.somTipoArtefacto = somTipoArtefacto;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public List<SelectItem> getLasPrioridadesArtefactos() {
		try {
			if (lasPrioridadesArtefactos==null) {
				List<VtPrioridad> listaPrioridadesArtefactos = businessDelegatorView.getVtPrioridad();
				lasPrioridadesArtefactos = new ArrayList<SelectItem>();
				for (VtPrioridad vtPrioridad : listaPrioridadesArtefactos) {
					lasPrioridadesArtefactos.add(new SelectItem(vtPrioridad.getPrioCodigo(), vtPrioridad.getNombre()));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return lasPrioridadesArtefactos;
	}

	public void setLasPrioridadesArtefactos(List<SelectItem> lasPrioridadesArtefactos) {
		this.lasPrioridadesArtefactos = lasPrioridadesArtefactos;
	}

	public SelectOneMenu getSomPrioridadesArtefacto() {
		return somPrioridadesArtefacto;
	}

	public void setSomPrioridadesArtefacto(SelectOneMenu somPrioridadesArtefacto) {
		this.somPrioridadesArtefacto = somPrioridadesArtefacto;
	}

	public List<SelectItem> getLosEstadosArtefactos() {
		try {
			if (losEstadosArtefactos==null) {
				List<VtEstado> listaEstadosArtefactos = businessDelegatorView.getVtEstado();
				losEstadosArtefactos = new ArrayList<SelectItem>();
				for (VtEstado vtEstado : listaEstadosArtefactos) {
					losEstadosArtefactos.add(new SelectItem(vtEstado.getEstaCodigo(), vtEstado.getNombre()));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return losEstadosArtefactos;
	}

	public void setLosEstadosArtefactos(List<SelectItem> losEstadosArtefactos) {
		this.losEstadosArtefactos = losEstadosArtefactos;
	}

	public SelectOneMenu getSomEstadoArtefacto() {
		return somEstadoArtefacto;
	}

	public void setSomEstadoArtefacto(SelectOneMenu somEstadoArtefacto) {
		this.somEstadoArtefacto = somEstadoArtefacto;
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

	public InputText getTxtCrearEsfuerzoEstimado() {
		return txtCrearEsfuerzoEstimado;
	}

	public void setTxtCrearEsfuerzoEstimado(InputText txtCrearEsfuerzoEstimado) {
		this.txtCrearEsfuerzoEstimado = txtCrearEsfuerzoEstimado;
	}

	public List<VtArchivo> getSubirArchivos() {
		return subirArchivos;
	}

	public void setSubirArchivos(List<VtArchivo> subirArchivos) {
		this.subirArchivos = subirArchivos;
	}

	public InputText getTxtCrearEsfuerzoReal() {
		return txtCrearEsfuerzoReal;
	}

	public void setTxtCrearEsfuerzoReal(InputText txtCrearEsfuerzoReal) {
		this.txtCrearEsfuerzoReal = txtCrearEsfuerzoReal;
	}

	public InputText getTxtCrearEsfuerzoRestante() {
		return txtCrearEsfuerzoRestante;
	}

	public void setTxtCrearEsfuerzoRestante(InputText txtCrearEsfuerzoRestante) {
		this.txtCrearEsfuerzoRestante = txtCrearEsfuerzoRestante;
	}

	public InputText getTxtCrearPuntos() {
		return txtCrearPuntos;
	}

	public void setTxtCrearPuntos(InputText txtCrearPuntos) {
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
					losCrearTiposArtefactos.add(new SelectItem(vtTipoArtefacto.getTparCodigo(), vtTipoArtefacto.getNombre()));
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
					lasCrearPrioridadesArtefactos.add(new SelectItem(vtPrioridad.getPrioCodigo(), vtPrioridad.getNombre()));
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
					losCrearEstadosArtefactos.add(new SelectItem(vtEstado.getEstaCodigo(), vtEstado.getNombre()));
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


	public VtSprint getSprintSeleccionado() {
		return sprintSeleccionado;
	}

	public void setSprintSeleccionado(VtSprint sprintSeleccionado) {
		this.sprintSeleccionado = sprintSeleccionado;
	}
	

	//	public void actualizarLista() throws Exception {
	//
	//		sprintSeleccionado = (VtSprint) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sprintSeleccionado");
	//		btnCArtefacto.setDisabled(false);
	//		if (sprintSeleccionado!=null) {
	//			losArtefactos = businessDelegatorView.findArtefactosBySpring(sprintSeleccionado);
	//		}
	//
	//	}


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

	public List<SelectItem> getLosCrearDesarrolladores() {
		
		try {
			if(losCrearDesarrolladores == null){
				List<VtUsuario> listaDesarrolladores = businessDelegatorView.getVtUsuarioAsignados(proyectoActual);
				losCrearDesarrolladores = new ArrayList<SelectItem>();
				for (VtUsuario vtUsuario : listaDesarrolladores) {
					losCrearDesarrolladores.add(new SelectItem(vtUsuario.getUsuaCodigo(), vtUsuario.getNombre()));
				}				
			}	
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}		
		
		return losCrearDesarrolladores;
	}

	public void setLosCrearDesarrolladores(List<SelectItem> losCrearDesarrolladores) {
		this.losCrearDesarrolladores = losCrearDesarrolladores;
	}

	public SelectOneMenu getSomCrearDesarrolladores() {
		return somCrearDesarrolladores;
	}

	public void setSomCrearDesarrolladores(SelectOneMenu somCrearDesarrolladores) {
		this.somCrearDesarrolladores = somCrearDesarrolladores;
	}

	public List<SelectItem> getLosDesarrolladores() {
		try {
			if(losDesarrolladores == null){
				List<VtUsuario> listaDesarrolladores = businessDelegatorView.getVtUsuarioAsignados(proyectoActual);
				losDesarrolladores = new ArrayList<SelectItem>();
				for (VtUsuario vtUsuario : listaDesarrolladores) {
					losDesarrolladores.add(new SelectItem(vtUsuario.getUsuaCodigo(), vtUsuario.getNombre()));
				}				
			}	
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}	
		
		return losDesarrolladores;
	}

	public void setLosDesarrolladores(List<SelectItem> losDesarrolladores) {
		this.losDesarrolladores = losDesarrolladores;
	}

	public SelectOneMenu getSomDesarrolladores() {
		return somDesarrolladores;
	}

	public void setSomDesarrolladores(SelectOneMenu somDesarrolladores) {
		this.somDesarrolladores = somDesarrolladores;
	}	

	public List<SelectItem> getLosCrearInteres() {
		try {
			if(losCrearInteres == null){
				List<VtInteres> listaInteres = businessDelegatorView.getVtInteres();
				losCrearInteres = new ArrayList<SelectItem>();
				for (VtInteres vtInteres : listaInteres) {
					losCrearInteres.add(new SelectItem(vtInteres.getInteCodigo(), vtInteres.getNombre()));
				}			
			}	
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return losCrearInteres;
	}

	public void setLosCrearInteres(List<SelectItem> losCrearInteres) {
		this.losCrearInteres = losCrearInteres;
	}

	public SelectOneMenu getSomCrearInteres() {
		return somCrearInteres;
	}

	public void setSomCrearInteres(SelectOneMenu somCrearInteres) {
		this.somCrearInteres = somCrearInteres;
	}

	public List<SelectItem> getLosInteres() {
		try {
			if(losInteres == null){
				List<VtInteres> listaInteres = businessDelegatorView.getVtInteres();
				losInteres = new ArrayList<SelectItem>();
				for (VtInteres vtInteres : listaInteres) {
					losInteres.add(new SelectItem(vtInteres.getInteCodigo(), vtInteres.getNombre()));
				}			
			}	
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return losInteres;
	}

	public void setLosInteres(List<SelectItem> losInteres) {
		this.losInteres = losInteres;
	}

	public SelectOneMenu getSomInteres() {
		return somInteres;
	}

	public void setSomInteres(SelectOneMenu somInteres) {
		this.somInteres = somInteres;
	}
	
	//.......................................Fin de los Gets y Sets..............................................................

	public void crearAction() {

		try {
			if (txtCrearNombre.getValue().toString().trim().equals("") == true || txtCrearNombre.getValue() == null) {
				throw new Exception("Por favor ingrese el nombre");
			}
			if (txtCrearDescripcion.getValue().toString().trim().equals("") == true || txtCrearDescripcion.getValue() == null) {
				throw new Exception("Por favor ingrese la descripción");
			}
			if (txtCrearOrigen.getValue().toString().trim().equals("") == true || txtCrearOrigen.getValue() == null) {
				throw new Exception("Por favor ingrese el origen del artefacto");
			}
			if (txtCrearPuntos.getValue().toString().trim().equals("") == true || txtCrearPuntos.getValue() == null || !Utilities.isNumeric(txtCrearPuntos.getValue().toString().trim())) {
				throw new Exception("Por favor ingrese los puntos, recuerde este campo solo acepta numeros");
			}
			if (txtCrearEsfuerzoEstimado.getValue().toString().trim().equals("") == true || txtCrearEsfuerzoEstimado.getValue() == null || !Utilities.isNumeric(txtCrearEsfuerzoEstimado.getValue().toString().trim())) {
				throw new Exception("Por favor ingrese el esfuerzo estimado, recuerde este campo solo acepta numeros");
			}
			if (txtCrearEsfuerzoReal.getValue().toString().trim().equals("") == true || txtCrearEsfuerzoReal.getValue() == null || !Utilities.isNumeric(txtCrearEsfuerzoReal.getValue().toString().trim()) ) {
				throw new Exception("Por favor ingrese el esfuerzo real, recuerde este campo solo acepta numeros");
			}
			if (txtCrearEsfuerzoRestante.getValue().toString().trim().equals("") == true || txtCrearEsfuerzoRestante.getValue() == null || !Utilities.isNumeric(txtCrearEsfuerzoRestante.getValue().toString().trim())) {
				throw new Exception("Por favor ingrese el esfuerzo restante, recuerde que este campo solo acepta numeros");
			}

			if (somCrearEstadoArtefacto.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione un estado para el artefacto");
			}
			if (somCrearPrioridadesArtefacto.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione una prioridad para el artefacto");
			}
			if (somCrearTipoArtefacto.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione un tipo de artefacto");
			}
			
			if (somCrearDesarrolladores.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione un Desarrollador para el artefacto");
			}
			if (somCrearInteres.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione un interés ");
			}

			VtArtefacto vtArtefacto = new VtArtefacto();
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);
			vtArtefacto.setTitulo(txtCrearNombre.getValue().toString());
			vtArtefacto.setDescripcion(txtCrearDescripcion.getValue().toString());
			vtArtefacto.setEsfuerzoEstimado(Integer.parseInt(txtCrearEsfuerzoEstimado.getValue().toString().trim() ));
			vtArtefacto.setEsfuerzoRestante(Integer.parseInt(txtCrearEsfuerzoRestante.getValue().toString().trim() ));
			vtArtefacto.setEsfuerzoReal(Integer.parseInt(txtCrearEsfuerzoReal.getValue().toString().trim() ));
			vtArtefacto.setOrigen(txtCrearOrigen.getValue().toString());
			vtArtefacto.setPuntos(Integer.parseInt(txtCrearPuntos.getValue().toString().trim()));
			vtArtefacto.setActivo("S");
			vtArtefacto.setFechaCreacion(new Date());
			vtArtefacto.setFechaModificacion(new Date());
			vtArtefacto.setUsuCreador(vtUsuarioActual.getUsuaCodigo());
			vtArtefacto.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			VtEstado vtEstado = businessDelegatorView.getVtEstado(Long.parseLong(somCrearEstadoArtefacto.getValue().toString().trim()));
			vtArtefacto.setVtEstado(vtEstado);

			VtTipoArtefacto vtTipoArtefacto = businessDelegatorView.getVtTipoArtefacto(Long.parseLong(somCrearTipoArtefacto.getValue().toString().trim()));
			vtArtefacto.setVtTipoArtefacto(vtTipoArtefacto);

			VtPrioridad vtPrioridad = businessDelegatorView.getVtPrioridad(Long.parseLong(somCrearPrioridadesArtefacto.getValue().toString().trim()));
			vtArtefacto.setVtPrioridad(vtPrioridad);

			vtArtefacto.setVtPilaProducto(backlogSeleccionado);
			
			VtInteres vtInteres = businessDelegatorView.getVtInteres(Long.parseLong(somCrearInteres.getValue().toString().trim()));
			VtUsuario vtUsuario = businessDelegatorView.getVtUsuario(Long.parseLong(somCrearDesarrolladores.getValue().toString().trim()));
				
			businessDelegatorView.saveVtArtefacto(vtArtefacto);				
			asignarDesarrollador(vtArtefacto, vtInteres, vtUsuario);
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
		txtCrearEsfuerzoEstimado.resetValue();
		txtCrearEsfuerzoRestante.resetValue();
		txtCrearEsfuerzoReal.resetValue();
		txtCrearOrigen.resetValue();
		txtCrearPuntos.resetValue();
		txtCrearEsfuerzoReal.setDisabled(true);
		txtCrearEsfuerzoRestante.setDisabled(true);
		txtCrearPuntos.setDisabled(true);
		somCrearEstadoArtefacto.setValue("-1");
		somCrearPrioridadesArtefacto.setValue("-1");
		somCrearTipoArtefacto.setValue("-1");
		somCrearDesarrolladores.setValue("-1");
		somCrearInteres.setValue("-1");

	}

	public void limpiarAction() {
		artefactoSeleccionado.setTitulo("");
		artefactoSeleccionado.setDescripcion("");
		artefactoSeleccionado.setEsfuerzoEstimado(0);
		artefactoSeleccionado.setEsfuerzoRestante(0);
		artefactoSeleccionado.setEsfuerzoReal(0);
		artefactoSeleccionado.setOrigen("");
		artefactoSeleccionado.setPuntos(0);
		txtEsfuerzoReal.setDisabled(true);
		txtEsfuerzoRestante.setDisabled(true);
		txtPuntos.setDisabled(true);
		somEstadoArtefacto.setValue("-1");
		somPrioridadesArtefacto.setValue("-1");
		somTipoArtefacto.setValue("-1");
		somDesarrolladores.setValue("-1");
		somInteres.setValue("-1");
		somArtefactoActivo.setValue("-1");
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

	public void tipoModArtefactoListener() {
		int valorModTipoArtefacto = Integer.parseInt(somTipoArtefacto.getValue().toString().trim());

		log.info("entro al metodo modificar");

		if (valorModTipoArtefacto == 1 || valorModTipoArtefacto==4) {
			txtEsfuerzoEstimado.setDisabled(false);
			txtEsfuerzoReal.setDisabled(false);
			txtEsfuerzoRestante.setDisabled(false);
			txtPuntos.setDisabled(false);


		}else if (valorModTipoArtefacto == 2 || valorModTipoArtefacto ==3) {
			txtEsfuerzoEstimado.setDisabled(true);
			txtEsfuerzoReal.setDisabled(true);
			txtEsfuerzoRestante.setDisabled(true);
			txtPuntos.setDisabled(true);

			txtEsfuerzoEstimado.setValue(0);
			txtEsfuerzoReal.setValue(0);
			txtEsfuerzoRestante.setValue(0);
			txtPuntos.setValue(0);


		}
	}

	public void esfuerzoListener() {
		int valor = Integer.parseInt(txtCrearEsfuerzoEstimado.getValue().toString().trim());

		txtCrearEsfuerzoReal.setDisabled(false);
		txtCrearEsfuerzoRestante.setDisabled(false);
		txtCrearPuntos.setDisabled(false);

		txtCrearEsfuerzoReal.setValue(valor);
		txtCrearEsfuerzoRestante.setValue(valor);
		txtCrearPuntos.setValue(valor);

	}

	public void esfuerzoModListener() {
		int valor = Integer.parseInt(txtEsfuerzoEstimado.getValue().toString().trim());

		txtEsfuerzoReal.setDisabled(false);
		txtEsfuerzoRestante.setDisabled(false);
		txtPuntos.setDisabled(false);

		txtEsfuerzoReal.setValue(valor);
		txtEsfuerzoRestante.setValue(valor);
		txtPuntos.setValue(valor);
	}

	public void hidratarArtefactoMod() throws Exception {		
		
		log.info("Artefacto= " + artefactoSeleccionado.getTitulo());
		if (artefactoSeleccionado != null) {
			txtNombre.setValue(artefactoSeleccionado.getTitulo());
			txtDescripcion.setValue(artefactoSeleccionado.getDescripcion());
			txtEsfuerzoEstimado.setValue(""+artefactoSeleccionado.getEsfuerzoEstimado());
			txtEsfuerzoRestante.setValue(""+artefactoSeleccionado.getEsfuerzoRestante());
			txtEsfuerzoReal.setValue(""+artefactoSeleccionado.getEsfuerzoReal());
			txtPuntos.setValue(""+artefactoSeleccionado.getPuntos());
			txtOrigen.setValue(""+artefactoSeleccionado.getOrigen());
			somArtefactoActivo.setValue(artefactoSeleccionado.getActivo());
			somEstadoArtefacto.setValue(artefactoSeleccionado.getVtEstado().getEstaCodigo());
			somPrioridadesArtefacto.setValue(artefactoSeleccionado.getVtPrioridad().getPrioCodigo());
			somTipoArtefacto.setValue(artefactoSeleccionado.getVtTipoArtefacto().getTparCodigo());
			
			
			usuarioArtefacto = businessDelegatorView.findUsuarioArtefactoByArtefacto(artefactoSeleccionado);
			
			if(usuarioArtefacto == null ){
				somDesarrolladores.setValue("-1");
				somInteres.setValue("-1");
			}else{
				somDesarrolladores.setValue(usuarioArtefacto.getVtUsuario().getUsuaCodigo());
				somInteres.setValue(usuarioArtefacto.getVtInteres().getInteCodigo());
			}		
			
			//tipoModArtefactoListener();
		}else {
			log.info("No se ha seleccionado ningún artefacto");
		}
		

	}
	


	public void modificarAction() {

		try {
			if (txtNombre.getValue().toString().trim().equals("") == true || txtNombre.getValue() == null) {
				throw new Exception("Por favor ingrese el nombre");
			}
			if (txtDescripcion.getValue().toString().trim().equals("") == true || txtDescripcion.getValue() == null) {
				throw new Exception("Por favor ingrese una descripción");
			}
			if (txtOrigen.getValue().toString().trim().equals("") == true || txtOrigen.getValue() == null) {
				throw new Exception("Por favor ingrese el origen del artefacto");
			}
			if (txtPuntos.getValue().toString().trim().equals("") == true || txtPuntos.getValue() == null || !Utilities.isNumeric(txtPuntos.getValue().toString().trim())) {
				throw new Exception("Por favor ingrese los puntos, recuerde que este campo es de valor numerico");
			}
			if (txtEsfuerzoEstimado.getValue().toString().trim().equals("") == true || txtEsfuerzoEstimado.getValue() == null || !Utilities.isNumeric(txtEsfuerzoEstimado.getValue().toString().trim())) {
				throw new Exception("Por favor ingrese el esfuerzo estimado, recuerde que este campo es de valor numerico");
			}
			if (txtEsfuerzoReal.getValue().toString().trim().equals("") == true || txtEsfuerzoReal.getValue() == null || !Utilities.isNumeric(txtEsfuerzoReal.getValue().toString().trim())) {
				throw new Exception("Por favor ingrese el esfuerzo real, recuerde que este campo es de valor numerico");
			}
			if (txtEsfuerzoRestante.getValue().toString().trim().equals("") == true || txtEsfuerzoRestante.getValue() == null || !Utilities.isNumeric(txtEsfuerzoRestante.getValue().toString().trim())) {
				throw new Exception("Por favor ingrese el esfuerzo restante, recuerde que este campo es de valor numerico");
			}

			if (somEstadoArtefacto.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione un estado para el artefacto");
			}
			if (somPrioridadesArtefacto.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione una prioridad para el artefacto");
			}
			if (somTipoArtefacto.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione un tipo de artefacto");
			}
			if (somArtefactoActivo.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione un tipo de artefacto");
			}
			if (somDesarrolladores.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione un Desarrollador para el artefacto");
			}
			if (somInteres.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione un interés ");
			}
			
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			log.info("Usuario codigo= " + vtUsuarioActual.getUsuaCodigo());

			artefactoSeleccionado.setTitulo(txtNombre.getValue().toString());
			artefactoSeleccionado.setDescripcion(txtDescripcion.getValue().toString());
			artefactoSeleccionado.setEsfuerzoEstimado(Integer.parseInt(txtEsfuerzoEstimado.getValue().toString().trim() ));
			artefactoSeleccionado.setEsfuerzoRestante(Integer.parseInt(txtEsfuerzoRestante.getValue().toString().trim() ));
			artefactoSeleccionado.setEsfuerzoReal(Integer.parseInt(txtEsfuerzoReal.getValue().toString().trim() ));
			artefactoSeleccionado.setOrigen(txtOrigen.getValue().toString());
			artefactoSeleccionado.setPuntos(Integer.parseInt(txtPuntos.getValue().toString().trim()));
			artefactoSeleccionado.setActivo(somArtefactoActivo.getValue().toString().trim());
			
			artefactoSeleccionado.setFechaModificacion(new Date());
			artefactoSeleccionado.setUsuModificador(vtUsuarioActual.getUsuaCodigo());

			VtEstado vtEstado = businessDelegatorView.getVtEstado(Long.parseLong(somEstadoArtefacto.getValue().toString().trim()));
			artefactoSeleccionado.setVtEstado(vtEstado);

			VtTipoArtefacto vtTipoArtefacto = businessDelegatorView.getVtTipoArtefacto(Long.parseLong(somTipoArtefacto.getValue().toString().trim()));
			artefactoSeleccionado.setVtTipoArtefacto(vtTipoArtefacto);

			VtPrioridad vtPrioridad = businessDelegatorView.getVtPrioridad(Long.parseLong(somPrioridadesArtefacto.getValue().toString().trim()));
			artefactoSeleccionado.setVtPrioridad(vtPrioridad);
			artefactoSeleccionado.setVtSprint(sprintSeleccionado);
			artefactoSeleccionado.setVtPilaProducto(backlogSeleccionado);
			
			VtInteres vtInteres = businessDelegatorView.getVtInteres(Long.parseLong(somInteres.getValue().toString().trim()));
			VtUsuario vtUsuario = businessDelegatorView.getVtUsuario(Long.parseLong(somDesarrolladores.getValue().toString().trim()));				
				
			businessDelegatorView.updateVtArtefacto(artefactoSeleccionado);			
			asignarDesarrollador(artefactoSeleccionado, vtInteres, vtUsuario);					

			FacesUtils.addInfoMessage("El artefacto se ha modificado con exito");	

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

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
		FacesUtils.putinSession("sprintSeleccionado", sprintSeleccionado);
		return "/XHTML/TreeTable.xhtml";

	}

	public String sprintAction(){
		FacesUtils.putinSession("sprintSeleccionado", sprintSeleccionado);
		return "/XHTML/listaSprint.xhtml";
	}

	public String crearArtefactoAction(){
		return "/XHTML/CrearArtefactos.xhtml";
	}
	
	public String modificarArtefactoAction() throws Exception{
		
		usuarioArtefacto = businessDelegatorView.findUsuarioArtefactoByArtefacto(artefactoSeleccionado);
		
		if (artefactoSeleccionado.getActivo().equals("S")) {
			FacesUtils.putinSession("artefactoSeleccionado", artefactoSeleccionado);
			FacesUtils.putinSession("usuarioArtefacto", usuarioArtefacto);
			return "/XHTML/modificarArtefactos.xhtml";
		}
		else{
			FacesUtils.addErrorMessage("El artefacto esta inactivo");
			return "";
		}
		
	}
	
	public String volverArtefactoAction(){
		return "/XHTML/listarArtefactos.xhtml";
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
