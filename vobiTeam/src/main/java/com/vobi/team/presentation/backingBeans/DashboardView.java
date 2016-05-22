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

import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtRol;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.VtUsuarioArtefacto;
import com.vobi.team.modelo.VtUsuarioRol;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;
import com.vobi.team.utilities.Utilities;



@ManagedBean
@ViewScoped
public class DashboardView {

	public final static Logger log=LoggerFactory.getLogger(DashboardView.class);


	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;	
	private List<VtRol> usuarioRol;

	private List<VtUsuarioArtefacto> usuarioArtefactos;
	private int cantidadArtefactos;
	private int empresas;
	private int proyectos;

	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();

	private VtUsuario usuSesion;

	@PostConstruct
	public void init(){
		try {
			usuSesion = businessDelegatorView.findUsuarioByLogin(usuarioActual);
			usuarioArtefactos = businessDelegatorView.findUsuarioArtefactoByUsuarios(usuSesion);
			cantidadArtefactos = usuarioArtefactos.size();
			usuarioRol = businessDelegatorView.getRolesAsignados(usuSesion);

			empresas = businessDelegatorView.getVtEmpresa().size();
			proyectos = businessDelegatorView.getVtProyecto().size();



		} catch (Exception e) {
			//log.error(e.getMessage());
		}


	}

	public VtUsuario getUsuSesion() {
		return usuSesion;
	}

	public int getCantidadArtefactos() {
		return cantidadArtefactos;
	}

	public void setCantidadArtefactos(int cantidadArtefactos) {
		this.cantidadArtefactos = cantidadArtefactos;
	}

	public int getEmpresas() {
		return empresas;
	}

	public void setEmpresas(int empresas) {
		this.empresas = empresas;
	}

	public int getProyectos() {
		return proyectos;
	}

	public void setProyectos(int proyectos) {
		this.proyectos = proyectos;
	}

	public void setUsuSesion(VtUsuario usuSesion) {
		this.usuSesion = usuSesion;
	}

	public List<VtUsuarioArtefacto> getUsuarioArtefactos() {
		return usuarioArtefactos;
	}

	public void setUsuarioArtefactos(List<VtUsuarioArtefacto> usuarioArtefactos) {
		this.usuarioArtefactos = usuarioArtefactos;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public List<VtRol> getUsuarioRol() {

		try {
			if(usuSesion != null){
				usuarioRol = businessDelegatorView.getRolesAsignados(usuSesion);
			}

		} catch (Exception e) {
			//		log.error(e.getMessage());
		}

		return usuarioRol;
	}

	public void setUsuarioRol(List<VtRol> usuarioRol) {
		this.usuarioRol = usuarioRol;
	}

	public String sprintAction(){
		try {
			usuarioRol = businessDelegatorView.getRolesAsignados(usuSesion);
			for (VtRol vtRol : usuarioRol) {
				if (vtRol.getRolCodigo()==1L) {
					return "/XHTML/TreeTable.xhtml";
				}
				// Aqui se colocara mostrar artefactos del desarrollador para
				// que juegue
				if (vtRol.getRolCodigo()==2L) {
					return "/dashboard.xhtml";
				}
			}

			return "";
		} catch (Exception e) {
			log.info(e.getMessage());
			return "";
		}

	}

	public String retornarDashboard() {
		Long permisos = (Long) FacesUtils.getfromSession("permisos");

		if (permisos == 1L) {
			return "/XHTML/dashboard.xhtml";
		}else if (permisos == 2L) {
			return "/desarrollador/dashboard.xhtml";
		}else if (permisos == 3L) {
			return "/cliente/dashboard.xhtml";
		}else {
			return "";
		}
	}
}
