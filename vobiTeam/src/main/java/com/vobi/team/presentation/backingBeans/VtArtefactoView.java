package com.vobi.team.presentation.backingBeans;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtEstado;
import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtPrioridad;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtSprint;
import com.vobi.team.modelo.VtTipoArtefacto;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;



@ManagedBean
@ViewScoped
public class VtArtefactoView {

	public final static Logger log=LoggerFactory.getLogger(VtArtefactoView.class);


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

	private CommandButton btnModificar;

	private CommandButton btnLimpiar;

	//////////////////////////////////////////////////////////////


	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private VtArtefacto artefactoSeleccionado;
	private List<VtArtefacto> losArtefactos;

	private VtSprint sprintSeleccionado;
	private VtPilaProducto backlogSeleccionado;

	@PostConstruct
	public void init(){

		try {
			sprintSeleccionado = (VtSprint) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sprintSeleccionado");
			backlogSeleccionado = (VtPilaProducto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("backlogSeleccionado");
		} catch (Exception e) {
			log.info(e.getMessage());
		}

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
			sprintSeleccionado = (VtSprint) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sprintSeleccionado");
			if (sprintSeleccionado!=null) {
				losArtefactos = businessDelegatorView.findArtefactosBySpring(sprintSeleccionado);
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

	public void actualizarLista() throws Exception {

		sprintSeleccionado = (VtSprint) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sprintSeleccionado");
		btnCArtefacto.setDisabled(false);
		if (sprintSeleccionado!=null) {
			losArtefactos = businessDelegatorView.findArtefactosBySpring(sprintSeleccionado);
		}

	}


	public void crearAction() {

		try {
			if (txtCrearNombre.getValue().toString().trim().equals("") == true || txtCrearNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (txtCrearDescripcion.getValue().toString().trim().equals("") == true || txtCrearDescripcion.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (txtCrearOrigen.getValue().toString().trim().equals("") == true || txtCrearOrigen.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (txtCrearPuntos.getValue().toString().trim().equals("") == true || txtCrearPuntos.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (txtCrearEsfuerzoEstimado.getValue().toString().trim().equals("") == true || txtCrearEsfuerzoEstimado.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (txtCrearEsfuerzoReal.getValue().toString().trim().equals("") == true || txtCrearEsfuerzoReal.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (txtCrearEsfuerzoRestante.getValue().toString().trim().equals("") == true || txtCrearEsfuerzoRestante.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
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

			VtArtefacto vtArtefacto = new VtArtefacto();

			vtArtefacto.setTitulo(txtCrearNombre.getValue().toString());
			vtArtefacto.setDescripcion(txtCrearDescripcion.getValue().toString());
			vtArtefacto.setEsfuerzoEstimado(Integer.parseInt(txtCrearEsfuerzoEstimado.getValue().toString().trim() ));
			vtArtefacto.setEsfuerzoRestante(Integer.parseInt(txtCrearEsfuerzoRestante.getValue().toString().trim() ));
			vtArtefacto.setEsfuerzoReal(Integer.parseInt(txtCrearEsfuerzoReal.getValue().toString().trim() ));
			vtArtefacto.setOrigen(txtCrearOrigen.getValue().toString());
			vtArtefacto.setPuntos(Integer.parseInt(txtCrearPuntos.getValue().toString().trim()));
			vtArtefacto.setActivo("S");

			Date fecha = new Date();

			vtArtefacto.setFechaCreacion(fecha);
			vtArtefacto.setFechaModificacion(fecha);
			vtArtefacto.setUsuCreador(1L);
			vtArtefacto.setUsuModificador(1L);

			VtEstado vtEstado = businessDelegatorView.getVtEstado(Long.parseLong(somCrearEstadoArtefacto.getValue().toString().trim()));

			vtArtefacto.setVtEstado(vtEstado);

			VtTipoArtefacto vtTipoArtefacto = businessDelegatorView.getVtTipoArtefacto(Long.parseLong(somCrearTipoArtefacto.getValue().toString().trim()));

			vtArtefacto.setVtTipoArtefacto(vtTipoArtefacto);

			VtPrioridad vtPrioridad = businessDelegatorView.getVtPrioridad(Long.parseLong(somCrearPrioridadesArtefacto.getValue().toString().trim()));

			vtArtefacto.setVtPrioridad(vtPrioridad);

			vtArtefacto.setVtSprint(sprintSeleccionado);

			vtArtefacto.setVtPilaProducto(backlogSeleccionado);

			businessDelegatorView.saveVtArtefacto(vtArtefacto);

			FacesUtils.addInfoMessage("El artefacto se ha creado con exito");	

			limpiarCrearAction();
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

		//	btnCrear.setDisabled(true);
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
		int valorTipoArtefacto = Integer.parseInt(somTipoArtefacto.getValue().toString().trim());

		if (valorTipoArtefacto == 1 || valorTipoArtefacto==4) {

			txtEsfuerzoEstimado.setDisabled(false);

		}else if (valorTipoArtefacto == 2 || valorTipoArtefacto==3) {
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
	

	public void hidratarArtefactoMod() {
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

		}else {
			log.info("No se ha seleccionado ningún artefacto");
		}

	}

	public void modificarAction() {

		try {
			if (txtCrearNombre.getValue().toString().trim().equals("") == true || txtCrearNombre.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (txtCrearDescripcion.getValue().toString().trim().equals("") == true || txtCrearDescripcion.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (txtCrearOrigen.getValue().toString().trim().equals("") == true || txtCrearOrigen.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (txtCrearPuntos.getValue().toString().trim().equals("") == true || txtCrearPuntos.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (txtCrearEsfuerzoEstimado.getValue().toString().trim().equals("") == true || txtCrearEsfuerzoEstimado.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (txtCrearEsfuerzoReal.getValue().toString().trim().equals("") == true || txtCrearEsfuerzoReal.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
			}
			if (txtCrearEsfuerzoRestante.getValue().toString().trim().equals("") == true || txtCrearEsfuerzoRestante.getValue() == null) {
				throw new Exception("Por favor llene todos los campos");
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

			artefactoSeleccionado.setTitulo(txtCrearNombre.getValue().toString());
			artefactoSeleccionado.setDescripcion(txtCrearDescripcion.getValue().toString());
			artefactoSeleccionado.setEsfuerzoEstimado(Integer.parseInt(txtCrearEsfuerzoEstimado.getValue().toString().trim() ));
			artefactoSeleccionado.setEsfuerzoRestante(Integer.parseInt(txtCrearEsfuerzoRestante.getValue().toString().trim() ));
			artefactoSeleccionado.setEsfuerzoReal(Integer.parseInt(txtCrearEsfuerzoReal.getValue().toString().trim() ));
			artefactoSeleccionado.setOrigen(txtCrearOrigen.getValue().toString());
			artefactoSeleccionado.setPuntos(Integer.parseInt(txtCrearPuntos.getValue().toString().trim()));
			artefactoSeleccionado.setActivo(somArtefactoActivo.getValue().toString().trim());

			Date fecha = new Date();


			artefactoSeleccionado.setFechaModificacion(fecha);

			artefactoSeleccionado.setUsuModificador(1L);

			VtEstado vtEstado = businessDelegatorView.getVtEstado(Long.parseLong(somCrearEstadoArtefacto.getValue().toString().trim()));

			artefactoSeleccionado.setVtEstado(vtEstado);

			VtTipoArtefacto vtTipoArtefacto = businessDelegatorView.getVtTipoArtefacto(Long.parseLong(somCrearTipoArtefacto.getValue().toString().trim()));

			artefactoSeleccionado.setVtTipoArtefacto(vtTipoArtefacto);

			VtPrioridad vtPrioridad = businessDelegatorView.getVtPrioridad(Long.parseLong(somCrearPrioridadesArtefacto.getValue().toString().trim()));

			artefactoSeleccionado.setVtPrioridad(vtPrioridad);

			artefactoSeleccionado.setVtSprint(sprintSeleccionado);

			artefactoSeleccionado.setVtPilaProducto(backlogSeleccionado);

			businessDelegatorView.updateVtArtefacto(artefactoSeleccionado);

			FacesUtils.addInfoMessage("El artefacto se ha modificado con exito");	

			hidratarArtefactoMod();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

}
