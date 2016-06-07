package com.vobi.team.presentation.backingBeans;

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

import com.vobi.team.modelo.VtRol;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;



@ManagedBean
@ViewScoped
public class MenuModelView {
	public final static Logger log=LoggerFactory.getLogger(MenuModelView.class);

	@ManagedProperty(value="#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private MenuModel menuModel;

	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();
	
	private Long permisos;
	
	@PostConstruct
	public void init() {
		try {
			menuModel = new DefaultMenuModel();
			
			permisos = (Long) FacesUtils.getfromSession("permisos");
			
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
		
		DefaultMenuItem dashboard = new DefaultMenuItem("Dashboard");
		dashboard.setOutcome("/cliente/dashboard.xhtml");
		dashboard.setId("sm_dashboard");
		dashboard.setIcon("icon-home");
		menuModel.addElement(dashboard);
		
		DefaultMenuItem menuProyectos = new DefaultMenuItem("Ver Proyectos");
		menuProyectos.setOutcome("/cliente/TreeTableCliente.xhtml");
		menuProyectos.setId("sm_TreeCliente");
		menuProyectos.setIcon("icon-indent-right");
		menuModel.addElement(menuProyectos);
		
		DefaultSubMenu orientacion = new DefaultSubMenu("Orientación del menu");
		
		DefaultMenuItem izquierdaItem = new DefaultMenuItem("Izquierda");
		izquierdaItem.setIcon("icon-align-left");
		izquierdaItem.setId("sm_ltr");
		izquierdaItem.setOnclick("$('body').removeClass('ui-sentinel-rtl');return false;");
		
		DefaultMenuItem derechaItem = new DefaultMenuItem("Derecha");
		derechaItem.setIcon("icon-align-right");
		derechaItem.setId("sm_rtl");
		derechaItem.setOnclick("$('body').addClass('ui-sentinel-rtl');return false;");

		orientacion.setId("sm_orientation");
		orientacion.setIcon("icon-align-right");
		orientacion.addElement(derechaItem);
		orientacion.addElement(izquierdaItem);
		menuModel.addElement(orientacion);
	}

	private void menuDesarrollador() throws Exception {
		
		VtUsuario vtUsuario = businessDelegatorView.findUsuarioByLogin(usuarioActual);
		VtRol vtRol = businessDelegatorView.getVtRol(3L);
		Boolean desarrolladorCliente = businessDelegatorView.findUsuarioRolByRolAndUser(vtUsuario, vtRol);

		
		DefaultMenuItem dashboard = new DefaultMenuItem("Dashboard");
		dashboard.setOutcome("/desarrollador/dashboard.xhtml");
		dashboard.setId("sm_dashboard");
		dashboard.setIcon("icon-home");
		menuModel.addElement(dashboard);
		
		//Proyectos Desarrollor 
		DefaultMenuItem menuProyectos = new DefaultMenuItem("Ver Proyectos");
		menuProyectos.setOutcome("/desarrollador/tableDesarrollador.xhtml");
		menuProyectos.setId("sm_Proyectos");
		menuProyectos.setIcon("icon-indent-right");
		menuModel.addElement(menuProyectos);
		
		if (desarrolladorCliente == true) {
			DefaultMenuItem menuProyectosCliente = new DefaultMenuItem("Ver Proyectos Como Cliente");
			menuProyectosCliente.setOutcome("/cliente/TreeTableCliente.xhtml");
			menuProyectosCliente.setId("sm_TreeCliente");
			menuProyectosCliente.setIcon("icon-indent-right");
			menuModel.addElement(menuProyectosCliente);
		}
		
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

	private void menuAdministrador() throws Exception {
		
		VtUsuario vtUsuario = businessDelegatorView.findUsuarioByLogin(usuarioActual);
		VtRol vtRol = businessDelegatorView.getVtRol(2L);
		Boolean adminDesarrollador = businessDelegatorView.findUsuarioRolByRolAndUser(vtUsuario, vtRol);
		
		DefaultMenuItem dashboard = new DefaultMenuItem("Dashboard");
		dashboard.setOutcome("/XHTML/dashboard.xhtml");
		dashboard.setId("sm_dashboard");
		dashboard.setIcon("icon-home");
		menuModel.addElement(dashboard);
		
		DefaultSubMenu empresaSubmenu = new DefaultSubMenu("Empresa");		
		DefaultMenuItem empresaItem = new DefaultMenuItem("Lista de Empresas");

		empresaItem.setOutcome("/XHTML/TreeTable.xhtml");
		empresaItem.setIcon("icon-list");
		empresaItem.setId("sm_DashboardEmpresa");
		empresaItem.setContainerStyleClass("layout-menubar-active");

		empresaSubmenu.setId("sm_Empresa");
		empresaSubmenu.setIcon("icon-building-filled");
		empresaSubmenu.addElement(empresaItem);
		menuModel.addElement(empresaSubmenu);
		
		if (adminDesarrollador == true) {
			DefaultMenuItem menuProyectos = new DefaultMenuItem("Ver Proyectos");
			menuProyectos.setOutcome("/desarrollador/tableDesarrollador.xhtml");
			menuProyectos.setId("sm_Proyectos");
			menuProyectos.setIcon("icon-indent-right");
			menuModel.addElement(menuProyectos);
		}
		
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

		//Item Estado Sprint
		DefaultMenuItem estadoSprintItem = new DefaultMenuItem("Gestión Estados del Sprint");
		estadoSprintItem.setOutcome("/XHTML/listaEstadoSprint.xhtml");
		estadoSprintItem.setIcon("icon-th-list-outline");
		estadoSprintItem.setId("sm_vtEstadoSprint");

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
		propiedadesSM.addElement(estadoSprintItem);
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