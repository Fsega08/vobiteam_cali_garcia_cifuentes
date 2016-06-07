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

import org.primefaces.component.inputmask.InputMask;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.modelo.VtArchivo;
import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtEstado;
import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtProgresoArtefacto;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtProyectoUsuario;
import com.vobi.team.modelo.VtSprint;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.VtUsuarioArtefacto;
import com.vobi.team.modelo.dto.VtArtefactoDTO;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;
import com.vobi.team.utilities.Utilities;


@ManagedBean
@ViewScoped
public class VtDesarrolladorView {
	public final static Logger log=LoggerFactory.getLogger(VtDesarrolladorView.class);

	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private VtProyecto proyectoSeleccionado;
	private VtArtefacto artefactoSeleccionado;	

	private SelectOneMenu somProyectos;
	private List<SelectItem> losProyectos;
	
	private SelectOneMenu somBacklogs;
	private List<SelectItem> losBacklogs;

	private List<VtArtefacto> losArtefactos;
	
	private StreamedContent file;
	private VtArchivo archivoSeleccionado;
	private List<VtArchivo> losArchivos;

	//.......................Dialog Progreso..................

	

	private InputText txtNombreArtefacto;
	
	private InputMask txtEsfuerzoRestante;
	private InputMask txtEsfuerzoReal;
	private InputMask txtPuntos;
	private InputMask txtTiempoEstimado;
	
	private InputTextarea txtDescripcion;
	
	private SelectOneMenu somEstadoArtefacto;
	private List<SelectItem> losEstadosArtefactos;

	//.......................................................
	
	private List<VtProgresoArtefacto> losProgresos;
	
	//.......................................................
	
	


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

	public InputText getTxtNombreArtefacto() {
		return txtNombreArtefacto;
	}

	public void setTxtNombreArtefacto(InputText txtNombreArtefacto) {
		this.txtNombreArtefacto = txtNombreArtefacto;
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
					if (proyectoUsuario.getActivo().equals("S")) {
						losProyectos.add(new SelectItem(proyectoUsuario.getVtProyecto().getProyCodigo(), proyectoUsuario.getVtProyecto().getNombre()));
					}
					
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
	

	public SelectOneMenu getSomBacklogs() {
		return somBacklogs;
	}

	public void setSomBacklogs(SelectOneMenu somBacklogs) {
		this.somBacklogs = somBacklogs;
	}

	public List<SelectItem> getLosBacklogs() {
		
		try {
			if(losBacklogs == null){
				losBacklogs = new ArrayList<SelectItem>();	
				
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return losBacklogs;
	}

	public void setLosBacklogs(List<SelectItem> losBacklogs) {
		this.losBacklogs = losBacklogs;
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
	
	

	public InputMask getTxtTiempoEstimado() {
		return txtTiempoEstimado;
	}

	public void setTxtTiempoEstimado(InputMask txtTiempoEstimado) {
		this.txtTiempoEstimado = txtTiempoEstimado;
	}

	public InputTextarea getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(InputTextarea txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}	

	public InputMask getTxtEsfuerzoRestante() {
		return txtEsfuerzoRestante;
	}

	public void setTxtEsfuerzoRestante(InputMask txtEsfuerzoRestante) {
		this.txtEsfuerzoRestante = txtEsfuerzoRestante;
	}

	public InputMask getTxtEsfuerzoReal() {
		return txtEsfuerzoReal;
	}

	public void setTxtEsfuerzoReal(InputMask txtEsfuerzoReal) {
		this.txtEsfuerzoReal = txtEsfuerzoReal;
	}

	public InputMask getTxtPuntos() {
		return txtPuntos;
	}

	public void setTxtPuntos(InputMask txtPuntos) {
		this.txtPuntos = txtPuntos;
	}
	
	public List<SelectItem> getLosEstadosArtefactos() {
		try {
			if (losEstadosArtefactos==null) {
				List<VtEstado> listaEstadosArtefactos = businessDelegatorView.consultarEstadosParaDesarrollador();
				losEstadosArtefactos = new ArrayList<SelectItem>();
				for (VtEstado vtEstado : listaEstadosArtefactos) {
					if (vtEstado.getActivo().equals("S")) {
						losEstadosArtefactos.add(new SelectItem(vtEstado.getEstaCodigo(), vtEstado.getNombre()));
					}
					
				}
				if (artefactoSeleccionado.getVtEstado().getEstaCodigo() != 2L || artefactoSeleccionado.getVtEstado().getEstaCodigo() != 3L) {
					losEstadosArtefactos.add(new SelectItem(artefactoSeleccionado.getVtEstado().getEstaCodigo(), artefactoSeleccionado.getVtEstado().getNombre()));
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
	
	public VtArchivo getArchivoSeleccionado() {
		return archivoSeleccionado;
	}

	public void setArchivoSeleccionado(VtArchivo archivoSeleccionado) {
		this.archivoSeleccionado = archivoSeleccionado;
	}

	public List<VtArchivo> getLosArchivos() throws Exception {
		if (artefactoSeleccionado!=null) {

			losArchivos = businessDelegatorView.findArchivosByArtefactos(artefactoSeleccionado);

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

	//---------------------------------Fin de los Gets y Sets--------------------------------------------------------

	public void proyectoListener() throws Exception{
		String codigoProyecto = somProyectos.getValue().toString().trim();

		VtProyecto proyecto = businessDelegatorView.getVtProyecto(Long.parseLong(codigoProyecto));

		if(!codigoProyecto.equals("-1") ){			
			
			losBacklogs = new ArrayList<SelectItem>();
			losArtefactos = new ArrayList<VtArtefacto>();
			
			List<VtPilaProducto> vtPilaProductos =  businessDelegatorView.findBacklogByProyecto(proyecto);
			
			for (VtPilaProducto vtPilaProducto : vtPilaProductos) {
				if (vtPilaProducto.getActivo().equals("S")) {
					losBacklogs.add(new SelectItem(vtPilaProducto.getPilaCodigo(), vtPilaProducto.getNombre()));
				}
			}
			
			
		}else{
			losBacklogs = new ArrayList<SelectItem>();
			losArtefactos = new ArrayList<VtArtefacto>();
		}
	}
	
	public void backlogListener() throws Exception{
		String codigoBacklog = somBacklogs.getValue().toString().trim();

		VtPilaProducto vtPilaProducto = businessDelegatorView.getVtPilaProducto(Long.parseLong(codigoBacklog));

		if(!codigoBacklog.equals("-1") ){			

			for (VtUsuarioArtefacto usuarioArtefacto : desarrollador.getVtUsuarioArtefactos()) {				

				if(vtPilaProducto.getPilaCodigo() == usuarioArtefacto.getVtArtefacto().getVtPilaProducto().getPilaCodigo()){
					losArtefactos.add(usuarioArtefacto.getVtArtefacto());
				}
			}
		}else{
			losArtefactos = new ArrayList<VtArtefacto>();
		}
	}
	
	public void artefactoSeleccionadoAction(){
		try {
			VtSprint vtSprint = new VtSprint();
			VtArtefactoDTO artefactoDTO = businessDelegatorView.getVtArtefactoDTO(artefactoSeleccionado);
			
			
			if (artefactoSeleccionado.getVtSprint() == null) {
				throw new Exception("El artefacto no se encuentra en un sprint");
			}else {
				vtSprint = businessDelegatorView.getVtSprint(artefactoSeleccionado.getVtSprint().getSpriCodigo());
				
			}
			
			if (vtSprint.getVtEstadoSprint().getEstsprCodigo() == 1L ) {
				throw new Exception("El sprint todavia no ha iniciado");
			}else if (vtSprint.getVtEstadoSprint().getEstsprCodigo() == 3L) {
				throw new Exception("El sprint ya ha terminado");
			}else if(artefactoSeleccionado.getActivo().equals("N")) {
				throw new Exception("El artefacto esta inactivo");
			}
			
			
			if (vtSprint.getVtEstadoSprint().getEstsprCodigo() == 2L && artefactoSeleccionado.getActivo().equals("S")) {
				
				somEstadoArtefacto.setValue(artefactoDTO.getEstaCodigo_VtEstado());
				txtNombreArtefacto.setValue(artefactoDTO.getTitulo());
				txtEsfuerzoReal.setValue(artefactoDTO.getEsfuerzoRealConvertido());
				txtEsfuerzoRestante.setValue(artefactoDTO.getEsfuerzoRestanteConvertido());
				txtPuntos.setValue(artefactoDTO.getPuntosConvertido());
				
				RequestContext.getCurrentInstance().execute("PF('dlgProgresArtefacto').show();");
			}
			
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		
		
	}
	
	public void historiaProgresoAction(){
		getLosProgresos();
	}

	public void crearAction() {

		try {
			if (somEstadoArtefacto.getValue().toString().trim().equals("-1") == true) {
				throw new Exception("Seleccione un estado para el artefacto");
			}
			
			if (txtDescripcion.getValue().toString().trim().equals("") == true || txtDescripcion.getValue() == null) {
				throw new Exception("Por favor ingrese la descripción");
			}

			if (txtTiempoEstimado.getValue().toString().trim().equals("") == true || txtTiempoEstimado.getValue() == null) {
				throw new Exception("Por favor ingrese el esfuerzo estimado, recuerde este campo solo acepta numeros");
			}

			VtUsuario vtUsuario = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			VtProgresoArtefacto vtProgresoArtefacto =  new VtProgresoArtefacto();		

			vtProgresoArtefacto.setTiempoDedicado(Utilities.pasarFormatoHoraAInteger(txtTiempoEstimado.getValue().toString().trim()));			
			vtProgresoArtefacto.setEsfuerzoRestante(Utilities.pasarFormatoHoraAInteger(txtTiempoEstimado.getValue().toString().trim()) - (Utilities.pasarFormatoHoraAInteger(txtTiempoEstimado.getValue().toString().trim())));			
			vtProgresoArtefacto.setPuntos(Utilities.pasarFormatoHoraAInteger(txtPuntos.getValue().toString().trim()));			
			vtProgresoArtefacto.setEsfuerzoReal(Utilities.pasarFormatoHoraAInteger(txtEsfuerzoReal.getValue().toString().trim())+ vtProgresoArtefacto.getTiempoDedicado());			
			vtProgresoArtefacto.setDescripcion(txtDescripcion.getValue().toString());			
			vtProgresoArtefacto.setFechaCreacion(new Date());			
			vtProgresoArtefacto.setFechaModificacion(new Date());			
			vtProgresoArtefacto.setUsuCreador(vtUsuario.getUsuaCodigo());			
			vtProgresoArtefacto.setUsuModificador(vtUsuario.getUsuaCodigo());			
			vtProgresoArtefacto.setActivo("S");			
			vtProgresoArtefacto.setVtArtefacto(artefactoSeleccionado);

			artefactoSeleccionado.setEsfuerzoRestante(vtProgresoArtefacto.getEsfuerzoRestante());			
			artefactoSeleccionado.setEsfuerzoReal(vtProgresoArtefacto.getEsfuerzoReal());
			long estado = Long.parseLong(somEstadoArtefacto.getValue().toString());
			VtEstado vtEstado = businessDelegatorView.getVtEstado(estado);
			artefactoSeleccionado.setVtEstado(vtEstado);
			
			businessDelegatorView.saveVtProgresoArtefacto(vtProgresoArtefacto);			
			businessDelegatorView.updateVtArtefacto(artefactoSeleccionado);

			FacesUtils.addInfoMessage("El progreso fue agregado con exito");
			
			artefactoSeleccionadoAction();
			backlogListener();

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void limpiarAction() {
		txtDescripcion.resetValue();
		txtTiempoEstimado.resetValue();
	}
	// Crear Artefacto //
	
	public String crearArtefactoAction() throws NumberFormatException, Exception{
		
		String codigoProyecto = somProyectos.getValue().toString().trim();		
		String codigoBacklog = somBacklogs.getValue().toString().trim();
		
		if(!codigoProyecto.equals("-1") &&  !codigoBacklog.equals("-1")){

			VtPilaProducto vtPilaProducto = businessDelegatorView.getVtPilaProducto(Long.parseLong(codigoBacklog));
			

			FacesUtils.putinSession("backlogSeleccionado", vtPilaProducto);
			
			return "/desarrollador/CrearArtefactosDesarrollador.xhtml";
		}else{
			FacesUtils.addErrorMessage("No se ha especificado un proyecto o una pila de productos validos");
		}
		
		
		return "";
	}
	
	// HISTORIAL DEL ARTEFACTO //

	public void artefactoSesionAction() throws Exception {
		FacesUtils.putinSession("artefactoSeleccionado",artefactoSeleccionado);
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

}
