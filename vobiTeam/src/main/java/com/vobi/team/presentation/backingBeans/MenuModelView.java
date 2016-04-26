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
import org.springframework.security.core.Authentication;
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
	private DefaultSubMenu subMenu;
	private DefaultMenuItem item;

	private String usuarioActual=SecurityContextHolder.getContext().getAuthentication().getName();

	@PostConstruct
	public void init() throws Exception{
		
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
		
	}

	private void menuCliente() {
		// TODO Auto-generated method stub
		
	}

	private void menuDesarrollador() {
		// TODO Auto-generated method stub
		
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
		
		
		/*
		<p:submenu id="sm_Usuario" label="Usuario" icon="icon-male">
			<p:menuitem id="sm_vtUsuario" value="Lista de Usuarios" icon="icon-list"
				outcome="/XHTML/listaUsuarios" />
		</p:submenu> */
		
		//Usuario
		DefaultSubMenu usuarioSubmenu = new DefaultSubMenu("Usuarios");
		DefaultMenuItem usuarioItem = new DefaultMenuItem("Usuarios");

		usuarioItem.setOutcome("usuarioVista");
		usuarioItem.setIcon("icon-user-add");
		usuarioItem.setId("sm_usuarioVista");

		usuarioSubmenu.setId("sm_Usuarios");
		usuarioSubmenu.setIcon("icon-user-1");
		usuarioSubmenu.addElement(usuarioItem);


		DefaultMenuItem rolItem = new DefaultMenuItem("Roles");
		rolItem.setOutcome("rolVista");
		rolItem.setIcon("icon-users");
		rolItem.setId("sm_rolVista");

		usuarioSubmenu.addElement(rolItem);
		menuModel.addElement(usuarioSubmenu);

		//Pila de producto
		DefaultSubMenu pilaSubmenu = new DefaultSubMenu("Pila de producto");
		DefaultMenuItem pilaItem = new DefaultMenuItem("Gestionar Pila");

		pilaItem.setOutcome("pilaProductoVista");
		pilaItem.setIcon("icon-align-justify");
		pilaItem.setId("sm_pilaProductoVista");

		pilaSubmenu.setId("sm_Pila_Producto");
		pilaSubmenu.setIcon("icon-align-center");
		pilaSubmenu.addElement(pilaItem);
		menuModel.addElement(pilaSubmenu);


		//Sprint
		DefaultSubMenu sprintSubmenu = new DefaultSubMenu("Sprint");
		DefaultMenuItem sprintItem = new DefaultMenuItem("Gestionar Sprint");

		sprintItem.setOutcome("sprintVista");
		sprintItem.setIcon("icon-th-list-1");
		sprintItem.setId("sm_sprintVista");

		sprintSubmenu.setId("sm_Sprint");
		sprintSubmenu.setIcon("icon-book");
		sprintSubmenu.addElement(sprintItem);
		menuModel.addElement(sprintSubmenu);


		//Comportamiento artefacto
		DefaultSubMenu cpArtefactoSubmenu = new DefaultSubMenu("Comportamiento Artefacto");

		//Estado Artefacto
		DefaultMenuItem estadoArtefactoItem = new DefaultMenuItem("Estado Artefacto");

		estadoArtefactoItem.setOutcome("estadoVista");
		estadoArtefactoItem.setIcon("icon-vcard");
		estadoArtefactoItem.setId("sm_estadoVista");
		cpArtefactoSubmenu.addElement(estadoArtefactoItem);

		//Prioridad Artefacto
		DefaultMenuItem prioridadArtefactoItem = new DefaultMenuItem("Prioridad Artefacto");

		prioridadArtefactoItem.setOutcome("prioridadVista");
		prioridadArtefactoItem.setIcon("icon-news");
		prioridadArtefactoItem.setId("sm_prioridadVista");
		cpArtefactoSubmenu.addElement(prioridadArtefactoItem);

		//tipo Artefacto
		DefaultMenuItem tpArtefactoItem = new DefaultMenuItem("Tipo de Artefacto");

		tpArtefactoItem.setOutcome("tipoArtefactoVista");
		tpArtefactoItem.setIcon("icon-calendar-2");
		tpArtefactoItem.setId("sm_tipoArtefactoVista");
		cpArtefactoSubmenu.addElement(tpArtefactoItem);

		cpArtefactoSubmenu.setId("sm_Comportamiento_Artefacto");
		cpArtefactoSubmenu.setIcon("icon-list-alt");

		menuModel.addElement(cpArtefactoSubmenu);

		
	}
	
}