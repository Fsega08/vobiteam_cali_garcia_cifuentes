package com.vobi.team.presentation.backingBeans;

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
import org.primefaces.component.password.Password;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.exceptions.ZMessManager;
import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtRol;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.VtUsuarioRol;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;
import com.vobi.team.utilities.Utilities;



@ManagedBean
@ViewScoped
public class VtUsuarioView {

	public final static Logger log=LoggerFactory.getLogger(VtUsuarioView.class);


	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;	

	private List<VtUsuario> losUsuarios;
	private VtUsuario usuarioSeleccionado;

	////////////Crear Usuario
	private InputText txtNombreC;
	private InputText txtLoginC;
	private Password txtPasswordC;
	private Password txtPasswordC2;

	private CommandButton btnGenerar;
	private CommandButton btnManual;

	private CommandButton btnCrear;
	private CommandButton btnCrearUsuario;
	private CommandButton btnLimpiarC;

	private SelectOneMenu somEmpresasC;
	private List<SelectItem> lasEmpresas;

	////////////Modificar Usuario
	private InputText txtNombreM;
	private InputText txtLoginM;
	private Password txtPasswordM;
	private Password txtPasswordM2;

	private CommandButton btnGenerarM;
	private CommandButton btnManualM;

	private CommandButton btnModificar;
	private CommandButton btnLimpiarM;

	private SelectOneMenu somEmpresasM;
	private SelectOneMenu somEstado;	
	private List<SelectItem> lasEmpresasM;

	////// PickList
	private DualListModel<VtRol> pickListRol;
	private List<VtRol> rolSource;
	private List<VtRol> rolTarget;

	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();


	@PostConstruct
	public void init(){
		try {
			usuarioSeleccionado = businessDelegatorView.getVtUsuario(1L);
			rolSource = businessDelegatorView.getRolesNoAsignados(usuarioSeleccionado);
			rolTarget = businessDelegatorView.getRolesAsignados(usuarioSeleccionado);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		pickListRol = new DualListModel<VtRol>(rolSource, rolTarget);
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}
	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public InputText getTxtNombreC() {
		return txtNombreC;
	}
	public void setTxtNombreC(InputText txtNombreC) {
		this.txtNombreC = txtNombreC;
	}
	public InputText getTxtLoginC() {
		return txtLoginC;
	}
	public void setTxtLoginC(InputText txtLoginC) {
		this.txtLoginC = txtLoginC;
	}
	public Password getTxtPasswordC() {
		return txtPasswordC;
	}
	public void setTxtPasswordC(Password txtPasswordC) {
		this.txtPasswordC = txtPasswordC;
	}	
	public Password getTxtPasswordC2() {
		return txtPasswordC2;
	}
	public void setTxtPasswordC2(Password txtPasswordC2) {
		this.txtPasswordC2 = txtPasswordC2;
	}
	public CommandButton getBtnCrear() {
		return btnCrear;
	}
	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}
	public CommandButton getBtnCrearUsuario() {
		return btnCrearUsuario;
	}
	public void setBtnCrearUsuario(CommandButton btnCrearUsuario) {
		this.btnCrearUsuario = btnCrearUsuario;
	}
	public CommandButton getBtnLimpiarC() {
		return btnLimpiarC;
	}
	public void setBtnLimpiarC(CommandButton btnLimpiarC) {
		this.btnLimpiarC = btnLimpiarC;
	}

	public CommandButton getBtnGenerar() {
		return btnGenerar;
	}
	public void setBtnGenerar(CommandButton btnGenerar) {
		this.btnGenerar = btnGenerar;
	}
	public CommandButton getBtnManual() {
		return btnManual;
	}
	public void setBtnManual(CommandButton btnManual) {
		this.btnManual = btnManual;
	}
	public List<VtUsuario> getLosUsuarios() {

		try {
			if(losUsuarios == null){
				losUsuarios = businessDelegatorView.getVtUsuario();
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return losUsuarios;
	}
	public void setLosUsuarios(List<VtUsuario> losUsuarios) {
		this.losUsuarios = losUsuarios;
	}
	public VtUsuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}
	public void setUsuarioSeleccionado(VtUsuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
	public List<SelectItem> getLasEmpresas() {
		try {
			if(lasEmpresas==null){
				List<VtEmpresa> listEmpresas=businessDelegatorView.getVtEmpresa();
				lasEmpresas=new ArrayList<SelectItem>();

				for (VtEmpresa vtEmpresa : listEmpresas) {
					lasEmpresas.add(new SelectItem(vtEmpresa.getEmprCodigo(),vtEmpresa.getNombre()));
				}

			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return lasEmpresas;
	}
	public void setLasEmpresas(List<SelectItem> lasEmpresas) {
		this.lasEmpresas = lasEmpresas;
	}	
	public SelectOneMenu getSomEmpresasC() {
		return somEmpresasC;
	}
	public void setSomEmpresasC(SelectOneMenu somEmpresasC) {
		this.somEmpresasC = somEmpresasC;
	}

	public InputText getTxtNombreM() {
		return txtNombreM;
	}
	public void setTxtNombreM(InputText txtNombreM) {
		this.txtNombreM = txtNombreM;
	}
	public InputText getTxtLoginM() {
		return txtLoginM;
	}
	public void setTxtLoginM(InputText txtLoginM) {
		this.txtLoginM = txtLoginM;
	}
	public Password getTxtPasswordM() {
		return txtPasswordM;
	}
	public void setTxtPasswordM(Password txtPasswordM) {
		this.txtPasswordM = txtPasswordM;
	}
	public Password getTxtPasswordM2() {
		return txtPasswordM2;
	}
	public void setTxtPasswordM2(Password txtPasswordM2) {
		this.txtPasswordM2 = txtPasswordM2;
	}
	public CommandButton getBtnGenerarM() {
		return btnGenerarM;
	}
	public void setBtnGenerarM(CommandButton btnGenerarM) {
		this.btnGenerarM = btnGenerarM;
	}
	public CommandButton getBtnManualM() {
		return btnManualM;
	}
	public void setBtnManualM(CommandButton btnManualM) {
		this.btnManualM = btnManualM;
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
	public SelectOneMenu getSomEmpresasM() {
		return somEmpresasM;
	}
	public void setSomEmpresasM(SelectOneMenu somEmpresasM) {
		this.somEmpresasM = somEmpresasM;
	}
	public SelectOneMenu getSomEstado() {
		return somEstado;
	}
	public void setSomEstado(SelectOneMenu somEstado) {
		this.somEstado = somEstado;
	}
	public List<SelectItem> getLasEmpresasM() throws Exception {

		try {
			if(lasEmpresasM==null){
				List<VtEmpresa> listEmpresas=businessDelegatorView.getVtEmpresa();
				lasEmpresasM=new ArrayList<SelectItem>();

				for (VtEmpresa vtEmpresa : listEmpresas) {
					lasEmpresasM.add(new SelectItem(vtEmpresa.getEmprCodigo(),vtEmpresa.getNombre()));
				}

			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return lasEmpresasM;
	}
	public void setLasEmpresasM(List<SelectItem> lasEmpresasM) {
		this.lasEmpresasM = lasEmpresasM;
	}

	public DualListModel<VtRol> getPickListRol() {
		return pickListRol;
	}

	public void setPickListRol(DualListModel<VtRol> pickListRol) {
		this.pickListRol = pickListRol;
	}

	public List<VtRol> getRolSource() {
		return rolSource;
	}

	public void setRolSource(List<VtRol> rolSource) {
		this.rolSource = rolSource;
	}

	public List<VtRol> getRolTarget() {
		return rolTarget;
	}

	public void setRolTarget(List<VtRol> rolTarget) {
		this.rolTarget = rolTarget;
	}

	public void crearAction() throws Exception{
		try {
			String nombre = txtNombreC.getValue().toString().trim();
			if (nombre==null || nombre.trim().equals("")){
				throw new Exception("Debe ingresar el nombre");
			}
			String login = txtLoginC.getValue().toString().trim();
			if (login==null || login.trim().equals("")){
				throw new Exception("Debe ingresar el login");
			}
			String clave = txtPasswordC.getValue().toString().trim();
			if (clave==null || clave.trim().equals("")){
				throw new Exception("Debe ingresar la clave");
			}
			String clave2 = txtPasswordC2.getValue().toString().trim();
			if (clave2==null || clave2.trim().equals("")){
				throw new Exception("Debe confirmar la clave");
			}
			String empresa = somEmpresasC.getValue().toString().trim(); 		

			if(!clave.equals(clave2)){
				throw new Exception("las Claves no coinciden");
			}
			
				
			
			

			long empresaID;			
			try {
				empresaID = Long.parseLong(empresa);
			} catch (Exception e) {
				throw new Exception("Problemas con el Id de la Empresa");
			}



			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			VtUsuario vtUsuario = new VtUsuario();

			vtUsuario.setNombre(nombre);
			vtUsuario.setLogin(login);
			vtUsuario.setClave(clave);
			vtUsuario.setFechaCreacion(new Date());
			vtUsuario.setFechaModificacion(new Date());
			vtUsuario.setUsuCreador(vtUsuarioActual.getUsuaCodigo());
			vtUsuario.setUsuModificador(vtUsuarioActual.getUsuaCodigo());
			vtUsuario.setActivo("S");

			VtEmpresa vtEmpresa = businessDelegatorView.getVtEmpresa(empresaID);
			vtUsuario.setVtEmpresa(vtEmpresa);

			businessDelegatorView.saveVtUsuario(vtUsuario);
			businessDelegatorView.nuevoUsuario(vtUsuario);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			
			limpiarAction();
			
			losUsuarios = businessDelegatorView.getVtUsuario();

		} 
		catch (Exception e){
			FacesUtils.addErrorMessage(e.getMessage());
			losUsuarios = businessDelegatorView.getVtUsuario();
		}

	}

	public void limpiarAction(){
		txtLoginC.resetValue();
		txtNombreC.resetValue();
		txtPasswordC.resetValue();
		txtPasswordC2.resetValue();
		somEmpresasC.setValue("-1");


		txtPasswordC.setDisabled(true);
		txtPasswordC2.setDisabled(true);

		btnCrear.setDisabled(false);

	}

	public void limpiarActionM(){

		txtLoginM.resetValue();
		txtNombreM.resetValue();
		txtPasswordM.resetValue();
		txtPasswordM2.resetValue();
		somEmpresasM.setValue("-1");
		somEstado.setValue("-1");

		txtPasswordM.setDisabled(true);
		txtPasswordM2.setDisabled(true);

		btnModificar.setDisabled(false);

	}

	public void modificarAction()throws Exception{

		try {
			String nombre = txtNombreM.getValue().toString().trim();
			if (nombre==null || nombre.trim().equals("")){
				throw new Exception("Debe ingresar el nombre");
			}

			String login = txtLoginM.getValue().toString().trim();
			if (login==null || login.trim().equals("")){
				throw new Exception("Debe ingresar el login");
			}
			String clave = txtPasswordM.getValue().toString().trim();
			if (clave==null || clave.trim().equals("")){
				throw new Exception("Debe ingresar la clave");
			}	
			String clave2 = txtPasswordM2.getValue().toString().trim();
			if (clave2==null || clave2.trim().equals("")){
				throw new Exception("Debe confirmar la clave");
			}
			String empresa = somEmpresasM.getValue().toString().trim();
			if (empresa.equals("-1")){
				throw new Exception("Debe ingresar la empresa");
			}
			String estado = somEstado.getValue().toString().trim();
			if (estado.equals("-1")){
				throw new Exception("Debe ingresar la empresa");
			}

			if(!clave.equals(clave2)){
				throw new Exception("las Claves no coinciden");
			}

			long empresaID;			
			try {
				empresaID = Long.parseLong(empresa);
			} catch (Exception e) {
				throw new Exception("Problemas con el Id de la Empresa");
			}

			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);

			VtUsuario vtUsuario = businessDelegatorView.findUsuarioByLogin(usuarioSeleccionado.getLogin());
			vtUsuario.setFechaModificacion(new Date());
			vtUsuario.setClave(clave);
			vtUsuario.setNombre(nombre);
			vtUsuario.setLogin(login);
			vtUsuario.setUsuModificador(vtUsuarioActual.getUsuaCodigo());
			vtUsuario.setActivo(estado);
			VtEmpresa vtEmpresa = businessDelegatorView.getVtEmpresa(empresaID);
			vtUsuario.setVtEmpresa(vtEmpresa);


			businessDelegatorView.updateVtUsuario(vtUsuario);    		
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);  
			losUsuarios = businessDelegatorView.getVtUsuario();
		} 
		catch (Exception e){			
			FacesUtils.addErrorMessage(e.getMessage());
			losUsuarios = businessDelegatorView.getVtUsuario();
		}



	}

	public void modificarListener() {
		VtUsuario usuario = usuarioSeleccionado;

		txtLoginM.setValue(usuario.getLogin());
		txtNombreM.setValue(usuario.getNombre());
		txtPasswordM.setValue(usuario.getClave());
		txtPasswordM2.setValue(usuario.getClave());
		somEstado.setValue(usuario.getActivo());
		somEmpresasM.setValue(usuario.getVtEmpresa().getEmprCodigo());


	}

	public void pickListListener() throws Exception{
		VtUsuario usuario = usuarioSeleccionado;
		try {
			rolSource = businessDelegatorView.getRolesNoAsignados(usuario);
			rolTarget = businessDelegatorView.getRolesAsignados(usuario);

			if (rolSource!=null) {		

				pickListRol.setTarget(rolTarget);
				pickListRol.setSource(rolSource);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}


	}

	public String generarAction()throws Exception	{
		try 		{
			String pass = Utilities.getPassword();
			txtPasswordC.setValue(pass);
			txtPasswordC2.setValue(pass);
			txtPasswordM.setValue(pass);
			txtPasswordM2.setValue(pass);
			FacesUtils.addInfoMessage("La contraseña generada es: "+pass);
		} 
		catch (Exception e) 		{			
			FacesUtils.addErrorMessage(e.getMessage());
		}



		return "";
	}

	public void escogerPass()throws Exception{
		txtPasswordC.setDisabled(false);
		txtPasswordC2.setDisabled(false);
		txtPasswordM.setDisabled(false);
		txtPasswordM2.setDisabled(false);

	}

	public void onTransfer(TransferEvent event) throws Exception {
		StringBuilder builder = new StringBuilder();

		for(Object item : event.getItems()) {
			VtRol vtRol =(VtRol) item;

			builder.append(((VtRol) item).getRolNombre()).append("<br />");

			//true si paso de izquierda a derecha
			if(event.isAdd()){
				asignarRol(vtRol, usuarioSeleccionado);
			}
			if(event.isRemove()){
				removerRol(vtRol, usuarioSeleccionado);
			}
		}

		FacesUtils.addInfoMessage("Roles Transferidos");

	}


	public void asignarRol(VtRol vtRol, VtUsuario vtUsuario )throws Exception{

		try {
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);
			VtUsuarioRol usuarioRol = businessDelegatorView.findUsuarioRolByUsuarioAndRol(vtUsuario.getUsuaCodigo(), vtRol.getRolCodigo());

			if(usuarioRol == null){
				usuarioRol = new VtUsuarioRol();

				usuarioRol.setVtUsuario(vtUsuario);
				usuarioRol.setVtRol(vtRol);
				usuarioRol.setUsuCreador(vtUsuarioActual.getUsuaCodigo());
				usuarioRol.setUsuModificador(vtUsuarioActual.getUsuaCodigo());
				usuarioRol.setFechaCreacion(new Date());
				usuarioRol.setFechaModificacion(new Date());
				usuarioRol.setActivo("S");

				businessDelegatorView.saveVtUsuarioRol(usuarioRol);		

			}else{
				usuarioRol.setFechaModificacion(new Date());
				usuarioRol.setActivo("S");
				usuarioRol.setUsuModificador(vtUsuarioActual.getUsuaCodigo());
				businessDelegatorView.updateVtUsuarioRol(usuarioRol);
			}



		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}



	}

	public void removerRol(VtRol vtRol, VtUsuario vtUsuario )throws Exception{
		try {
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);
			VtUsuarioRol usuarioRol = businessDelegatorView.findUsuarioRolByUsuarioAndRol(vtUsuario.getUsuaCodigo(), vtRol.getRolCodigo());
			
			usuarioRol.setUsuModificador(vtUsuarioActual.getUsuaCodigo());
			usuarioRol.setFechaModificacion(new Date());
			
			businessDelegatorView.deleteVtUsuarioRol(usuarioRol);
			
		
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

}
