package com.vobi.team.presentation.backingBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.VtUsuarioRol;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;



@ManagedBean
@ViewScoped
public class MenuModelView {
	public final static Logger log=LoggerFactory.getLogger(MenuModelView.class);

	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private MenuModel menuModel;
	
	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();

	@PostConstruct
	public void init() {
		try {
			menuModel = new DefaultMenuModel();
			VtUsuario vtUsuario = businessDelegatorView.findUsuarioByLogin(usuarioActual);
			List<VtUsuarioRol> vtUsuarioRoles = businessDelegatorView.findUsuarioRolbyUsuario(vtUsuario);
			int permisos = businessDelegatorView.getVtRol().size();

			for (VtUsuarioRol vtUsuarioRol : vtUsuarioRoles) {
				if (vtUsuarioRol.getVtRol().getRolCodigo() == 3L) {
					if (permisos > vtUsuarioRol.getVtRol().getRolCodigo()) {
						permisos = 3;
					}
				}else if (vtUsuarioRol.getVtRol().getRolCodigo() == 2L) {
					if (permisos > vtUsuarioRol.getVtRol().getRolCodigo()) {
						permisos = 2;
					}
				}else if (vtUsuarioRol.getVtRol().getRolCodigo() == 1L) {
					if (permisos > vtUsuarioRol.getVtRol().getRolCodigo()) {
						permisos = 1;
					}
				}
			}

			if (permisos == 1) {
				menuAdministrador();
			}else if (permisos == 2) {
				menuDesarrollador();
			}else if (permisos ==3) {
				menuCliente();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		

	}
	
	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}



	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}



	public MenuModel getMenuModel() {
		return menuModel;
	}



	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}



	private void menuCliente() {
		// TODO Auto-generated method stub

	}

	private void menuDesarrollador() {
		DefaultMenuItem menuDashboard = new DefaultMenuItem("Dashboard");
		menuDashboard.setOutcome("dashboard.xhtml");
		menuDashboard.setIcon("icon-home");
		menuDashboard.setId("sm_Dashboard");
		menuModel.addElement(menuDashboard);
		
		DefaultSubMenu orientacion = new DefaultSubMenu("Orientación del menu");		
		
		DefaultMenuItem derechaItem = new DefaultMenuItem("Derecha");
		derechaItem.setIcon("icon-align-right");
		derechaItem.setId("sm_rtl");
		derechaItem.setOnclick("$('body').addClass('ui-sentinel-rtl');return false;");
		
		DefaultMenuItem izquierdaItem = new DefaultMenuItem("Izquierda");
		izquierdaItem.setIcon("icon-align-left");
		izquierdaItem.setId("sm_ltr");
		izquierdaItem.setOnclick("$('body').removeClass('ui-sentinel-rtl');return false;");
		
		orientacion.setId("sm_orientation");
		orientacion.setIcon("icon-align-right");
		orientacion.addElement(derechaItem);
		orientacion.addElement(izquierdaItem);
		menuModel.addElement(orientacion);
		

	}

	private void menuAdministrador() {

		DefaultSubMenu empresaSubmenu = new DefaultSubMenu("Empresa");		
		DefaultMenuItem empresaItem = new DefaultMenuItem("Lista de Empresas");

		empresaItem.setOutcome("/XHTML/TreeTable.xhtml");
		empresaItem.setIcon("icon-list");
		empresaItem.setId("sm_DashboardEmpresa");
		empresaItem.setContainerStyleClass("layout-menubar-active");

		empresaSubmenu.setId("sm_Empresa");
		empresaSubmenu.setIcon("icon-home");
		empresaSubmenu.addElement(empresaItem);
		menuModel.addElement(empresaSubmenu);

		//Usuario
		DefaultSubMenu usuarioSubmenu = new DefaultSubMenu("Usuario");
		DefaultMenuItem usuarioItem = new DefaultMenuItem("Lista de usuarios");

		usuarioItem.setOutcome("/XHTML/listaUsuarios");
		usuarioItem.setIcon("icon-list");
		usuarioItem.setId("sm_vtUsuario");

		usuarioSubmenu.setId("sm_Usuarios");
		usuarioSubmenu.setIcon("icon-male");
		usuarioSubmenu.addElement(usuarioItem);
		menuModel.addElement(usuarioSubmenu);


		//Propiedades del artefacto

		DefaultSubMenu propiedadesSM = new DefaultSubMenu("Otras Propiedades");

		//Item Rol
		DefaultMenuItem rolItem = new DefaultMenuItem("Gestion de Roles");
		rolItem.setOutcome("/XHTML/listaRoles.xhtml");
		rolItem.setIcon("icon-users-2");
		rolItem.setId("sm_vtUsuario");

		//Item Prioridad
		DefaultMenuItem prioridadItem = new DefaultMenuItem("Gestión Prioridades del Artefacto");
		prioridadItem.setOutcome("/XHTML/listaPrioridad.xhtml");
		prioridadItem.setIcon("icon-menu-outline");
		prioridadItem.setId("sm_vtPrioridad");

		//Item Estado
		DefaultMenuItem estadoItem = new DefaultMenuItem("Gestión Estados del Artefacto");
		estadoItem.setOutcome("/XHTML/listaEstado.xhtml");
		estadoItem.setIcon("icon-archive");
		estadoItem.setId("sm_vtEstado");

		//Item Tipo
		DefaultMenuItem tipoItem = new DefaultMenuItem("Gestión Tipos de Artefacto");
		tipoItem.setOutcome("/XHTML/listaTipoArtefacto.xhtml");
		tipoItem.setIcon("icon-clipboard");
		tipoItem.setId("sm_vtTipo");

		//Item Interes
		DefaultMenuItem interesItem = new DefaultMenuItem("Gestión Interes");
		interesItem.setOutcome("/XHTML/listaInteres.xhtml");
		interesItem.setIcon("icon-clipboard");
		interesItem.setId("icon-stackoverflow");
		
		propiedadesSM.setId("sm_PropArte");
		propiedadesSM.setIcon("icon-cog-alt");
		propiedadesSM.addElement(rolItem);
		propiedadesSM.addElement(prioridadItem);
		propiedadesSM.addElement(estadoItem);
		propiedadesSM.addElement(tipoItem);
		propiedadesSM.addElement(interesItem);
		menuModel.addElement(propiedadesSM);
	
		//Orientacion
		
		DefaultSubMenu orientacion = new DefaultSubMenu("Orientación del menu");		
		
		DefaultMenuItem derechaItem = new DefaultMenuItem("Derecha");
		derechaItem.setIcon("icon-align-right");
		derechaItem.setId("sm_rtl");
		derechaItem.setOnclick("$('body').addClass('ui-sentinel-rtl');return false;");
		
		DefaultMenuItem izquierdaItem = new DefaultMenuItem("Izquierda");
		izquierdaItem.setIcon("icon-align-left");
		izquierdaItem.setId("sm_ltr");
		izquierdaItem.setOnclick("$('body').removeClass('ui-sentinel-rtl');return false;");
		
		orientacion.setId("sm_orientation");
		orientacion.setIcon("icon-align-right");
		orientacion.addElement(derechaItem);
		orientacion.addElement(izquierdaItem);
		menuModel.addElement(orientacion);
	}

}