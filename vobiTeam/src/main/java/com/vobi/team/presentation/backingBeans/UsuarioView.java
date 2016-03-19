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
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.exceptions.ZMessManager;
import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;
import com.vobi.team.utilities.Utilities;



@ViewScoped
@ManagedBean(name = "usuarioView")
public class UsuarioView {
	
	
	private InputText txtNombre;
	private InputText txtLogin;
	private Password txtPassword;
	private SelectOneMenu somEstado;
	private SelectOneMenu somEmpresas;
	private CommandButton btnGenerar;
    private CommandButton btnCrear;
    private CommandButton btnModificar;
    private CommandButton btnBorrar;
    private CommandButton btnLimpiar;
    
    private final static Logger log=LoggerFactory.getLogger(UsuarioView.class);
    private List<SelectItem> lasEmpresas;
    
    private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;   
    
       
    public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}
	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}
	public InputText getTxtNombre() {
		return txtNombre;
	}	
	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}
	public InputText getTxtLogin() {
		return txtLogin;
	}
	public void setTxtLogin(InputText txtLogin) {
		this.txtLogin = txtLogin;
	}
	public Password getTxtPassword() {
		return txtPassword;
	}
	public void setTxtPassword(Password txtPassword) {
		this.txtPassword = txtPassword;
	}		
	public SelectOneMenu getSomEstado() {
		return somEstado;
	}
	public void setSomEstado(SelectOneMenu somEstado) {
		this.somEstado = somEstado;
	}
	public CommandButton getBtnGenerar() {
		return btnGenerar;
	}
	public void setBtnGenerar(CommandButton btnGenerar) {
		this.btnGenerar = btnGenerar;
	}
	public CommandButton getBtnCrear() {
		return btnCrear;
	}
	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}
	public CommandButton getBtnModificar() {
		return btnModificar;
	}
	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}
	public CommandButton getBtnBorrar() {
		return btnBorrar;
	}
	public void setBtnBorrar(CommandButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}
	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}
	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}    
	
	public SelectOneMenu getSomEmpresas() {
		return somEmpresas;
	}
	public void setSomEmpresas(SelectOneMenu somEmpresas) {
		this.somEmpresas = somEmpresas;
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
	public String crearAction()throws Exception
    { 
    	try 
    	{    		
    		String nombre = txtNombre.getValue().toString().trim();
    		String login = txtLogin.getValue().toString().trim();
    		String clave = txtPassword.getValue().toString().trim();
    		String empresa = somEmpresas.getValue().toString().trim();
    		String estado = somEstado.getValue().toString().trim();    		
    		
    		if (nombre==null || nombre.trim().equals("")){
				throw new Exception("Debe ingresar el nombre");
			}			
			if (empresa.equals("-1")){
				throw new Exception("Debe ingresar la empresa");
			}
			if (estado.equals("-1")){
				throw new Exception("Debe ingresar la empresa");
			}
			if (login==null || login.trim().equals("")){
				throw new Exception("Debe ingresar el login");
			}
			if (clave==null || clave.trim().equals("")){
				throw new Exception("Debe ingresar la clave");
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
			vtUsuario.setActivo(estado);
			
			VtEmpresa vtEmpresa = businessDelegatorView.getVtEmpresa(empresaID);
			vtUsuario.setVtEmpresa(vtEmpresa);
			
			businessDelegatorView.saveVtUsuario(vtUsuario);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
    		
    		
		} 
    	catch (Exception e) 
    	{
			FacesUtils.addErrorMessage(e.getMessage());
		}
    	
    	
    	
    	return "";
    }
    
    public String modificarAction()throws Exception
    {  	    	
    	try 
    	{
    		String nombre = txtNombre.getValue().toString().trim();
    		String login = txtLogin.getValue().toString().trim();
    		String clave = txtPassword.getValue().toString().trim();
    		String empresa = somEmpresas.getValue().toString().trim();
    		String estado = somEstado.getValue().toString().trim();
    		
    		if (nombre==null || nombre.trim().equals("")){
				throw new Exception("Debe ingresar el nombre");
			}			
			if (empresa.equals("-1")){
				throw new Exception("Debe ingresar la empresa");
			}
			if (estado.equals("-1")){
				throw new Exception("Debe ingresar la empresa");
			}
			if (login==null || login.trim().equals("")){
				throw new Exception("Debe ingresar el login");
			}
			if (clave==null || clave.trim().equals("")){
				throw new Exception("Debe ingresar la clave");
			}	
    		
			long empresaID;			
			try {
				empresaID = Long.parseLong(empresa);
			} catch (Exception e) {
				throw new Exception("Problemas con el Id de la Empresa");
			}
			
			VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);
			    		
    		VtUsuario vtUsuario = businessDelegatorView.findUsuarioByLogin(login);
    		vtUsuario.setFechaModificacion(new Date());
    		vtUsuario.setClave(clave);
    		vtUsuario.setNombre(nombre);    		
    		vtUsuario.setUsuModificador(vtUsuarioActual.getUsuaCodigo());
    		vtUsuario.setActivo(estado);
    		VtEmpresa vtEmpresa = businessDelegatorView.getVtEmpresa(empresaID);
			vtUsuario.setVtEmpresa(vtEmpresa);
    		
    		
    		businessDelegatorView.updateVtUsuario(vtUsuario);    		
    		FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);    		
		} 
    	catch (Exception e) 
    	{			
			FacesUtils.addErrorMessage(e.getMessage());
		}
    	
    	
    	return "";
    }
	
    public String borrarAction()throws Exception
    { 

    	try 
    	{
    		String login = txtLogin.getValue().toString().trim();
    		
    		VtUsuario vtUsuarioActual = businessDelegatorView.findUsuarioByLogin(usuarioActual);
    		VtUsuario vtUsuario = businessDelegatorView.findUsuarioByLogin(login);
    		
    		vtUsuario.setUsuModificador(vtUsuarioActual.getUsuaCodigo());
    		
    		businessDelegatorView.deleteVtUsuario(vtUsuario);    		
    		FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
    		limpiarAction();
		} 
    	catch (Exception e) 
    	{			
			FacesUtils.addErrorMessage(e.getMessage());
		}
    	
    	
    	
    	return "";
    }
    
    public String generarAction()throws Exception
    {
    	try 
    	{
    		String pass = Utilities.getPassword();
    		txtPassword.setValue(pass);
    		FacesUtils.addInfoMessage("La contraseña generada es: "+pass);
		} 
    	catch (Exception e) 
    	{			
			FacesUtils.addErrorMessage(e.getMessage());
		}
    	
    	
    	
    	return "";
    }
	
    public String limpiarAction()
    { 
		txtLogin.resetValue();
		txtLogin.setDisabled(false);
    	txtNombre.resetValue();
    	txtPassword.resetValue();
    	somEstado.setValue("-1");
    	somEmpresas.setValue("-1");
    	
    	
    	btnCrear.setDisabled(false);
    	btnModificar.setDisabled(false);
		btnBorrar.setDisabled(false);
    	
    	return "";
    }
	
	public void loginListener()throws Exception
	{
		VtUsuario vtUsuario = null;
		
		try 
		{
			vtUsuario = businessDelegatorView.findUsuarioByLogin(txtLogin.getValue().toString().trim());			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		if(vtUsuario==null)
		{
			somEmpresas.setDisabled(false);
			
	    	btnCrear.setDisabled(false);
	    	btnModificar.setDisabled(true);
			btnBorrar.setDisabled(true);

		}else
		{
			txtLogin.setValue(vtUsuario.getLogin());
			txtLogin.setDisabled(true);
			txtNombre.setValue(vtUsuario.getNombre());
			txtPassword.setValue(vtUsuario.getClave());
			somEstado.setValue(vtUsuario.getActivo());
			somEmpresas.setValue(vtUsuario.getVtEmpresa().getEmprCodigo());
			
			
			btnCrear.setDisabled(true);
	    	btnModificar.setDisabled(false);
			btnBorrar.setDisabled(false);
		}
		
		
	}


}
