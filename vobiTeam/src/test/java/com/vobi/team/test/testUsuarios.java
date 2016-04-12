package com.vobi.team.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.control.IVtArtefactoLogic;
import com.vobi.team.modelo.control.IVtInteresLogic;
import com.vobi.team.modelo.control.IVtProyectoLogic;
import com.vobi.team.modelo.control.IVtProyectoUsuarioLogic;
import com.vobi.team.modelo.control.IVtRolLogic;
import com.vobi.team.modelo.control.IVtSprintLogic;
import com.vobi.team.modelo.control.IVtUsuarioArtefactoLogic;
import com.vobi.team.modelo.control.IVtUsuarioLogic;
import com.vobi.team.modelo.control.IVtUsuarioRolLogic;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class testUsuarios {

	private final static Logger log=LoggerFactory.getLogger(testUsuarios.class);

	@Autowired
	private IVtUsuarioLogic vtUsuarioLogic;

	@Autowired
	private IVtProyectoUsuarioLogic vtProyectoUsuarioLogic;

	@Autowired
	private IVtProyectoLogic vtProyectoLogic;

	@Autowired
	private IVtSprintLogic vtSprintLogic;

	@Autowired
	private IVtUsuarioRolLogic vtUsuarioRolLogic;

	@Autowired
	private IVtRolLogic vtRolLogic;	 

	@Autowired
	private IVtInteresLogic vtInteresLogic;

	@Autowired
	private IVtUsuarioArtefactoLogic vtUsuarioArtefactoLogic;

	@Autowired
	private IVtArtefactoLogic vtArtefactoLogic;

	//@Test
	public void testA() throws Exception {

//				VtProyecto vtProyecto = vtProyectoLogic.getVtProyecto(1L);
//				
//				List<VtUsuario> losUsuarios = vtUsuarioLogic.getVtUsuarioNoAsignados(vtProyecto);
//				
//				for (VtUsuario vtUsuario : losUsuarios) {
//					log.info("Nombre: "+vtUsuario.getNombre());
//					log.info("Codigo: "+vtUsuario.getUsuaCodigo()+"\n");
//				}	

	}

	//@Test
	public void testB() throws Exception {

		//		VtUsuario vtUsuario = vtUsuarioLogic.getVtUsuario(1L);
		//		String usuarioString = vtUsuario.toString();
		//		if(usuarioString.equals(vtUsuario.toString())){
		//			log.info("Puede ser");
		//		}

	}

	//	@Test
	public void testC() throws Exception {
		//		
		//		VtProyecto vtProyecto = vtProyectoLogic.getVtProyecto(1L);
		//		
		//		List<VtUsuario> losUsuarios = vtUsuarioLogic.getVtUsuarioAsignados(vtProyecto);			
		//		VtUsuario usuarioNuevo = vtUsuarioLogic.getVtUsuario(1L);		
		//		
		//		for (VtUsuario vtUsuario : losUsuarios) {
		//			if(vtUsuario.getUsuaCodigo().equals(usuarioNuevo.getUsuaCodigo())){
		//				log.info("Ya se asigno");
		//			}
		//							
		//		}		
	}

	//	@Test
	public void testD() throws Exception {
		//		VtProyecto vtProyecto = vtProyectoLogic.getVtProyecto(3L);
		//		VtUsuario vtUsuario = vtUsuarioLogic.getVtUsuario(1L);
		//		
		//		VtProyectoUsuario proyectoUsuario = vtProyectoUsuarioLogic.findProyectoUsuarioByProyectoAndUsuario(vtProyecto.getProyCodigo(), vtUsuario.getUsuaCodigo());
		//				
		//		log.info(""+proyectoUsuario.getPrusCodigo());


	}
	//@Test
	public void testF() throws Exception {

		//		VtSprint sprint = vtSprintLogic.getVtSprint(16L);
		//		
		//		log.info("Fecha Inicio antes del Cambio: "+sprint.getFechaInicio().toString());
		//		log.info("Fecha Fin antes del Cambio :"+sprint.getFechaFin().toString());
		//		
		//		Calendar cal = Calendar.getInstance();
		//		cal.set(2016, Calendar.MARCH, 19);
		//		
		//		Date nuevaFechaInicio = cal.getTime();
		//		
		//		cal.set(2016, Calendar.FEBRUARY, 8);
		//		Date nuevaFechaFin = cal.getTime();
		//		
		//		log.info("Nueva fecha de inicio: "+ nuevaFechaInicio.toString());
		//		log.info("Nueva fecha de fin: "+ nuevaFechaFin.toString());
		//		
		//		sprint.setFechaInicio(nuevaFechaInicio);
		//		sprint.setFechaFin(nuevaFechaFin);
		//		
		//		vtSprintLogic.updateVtSprint(sprint);

	}

	//	@Test
	public void testG()throws Exception{
		//		VtUsuario vtUsuario = vtUsuarioLogic.getVtUsuario(1L);

		//		List<VtUsuarioRol> usuarioRol = vtUsuarioRolLogic.findUsuarioRolbyUsuario(vtUsuario);
		//		
		//		for (VtUsuarioRol vtUsuarioRol : usuarioRol) {
		//			log.info("Codigo: "+ vtUsuarioRol.getUsroCodigo());
		//			log.info("Usuario: "+ vtUsuarioRol.getVtUsuario().getNombre());
		//			log.info("Rol: "+ vtUsuarioRol.getVtRol().getRolNombre()+"\n");
		//		}

		//		VtUsuarioRol usuarioRol = vtUsuarioRolLogic.findUsuarioRolByUsuarioAndRol(vtUsuario.getUsuaCodigo(), 1L);
		//		assertNotNull(usuarioRol);
		//
		//		log.info("Codigo: "+ usuarioRol.getUsroCodigo());
		//		log.info("Usuario: "+ usuarioRol.getVtUsuario().getNombre());
		//		log.info("Rol: "+ usuarioRol.getVtRol().getRolNombre()+"\n");

	}

	//	@Test
	public void testH()throws Exception{
		//		VtRol vtRol = vtRolLogic.getVtRol(2L);
		//		List<VtUsuario> losUsuarios = new ArrayList<VtUsuario>();
		//		List<VtUsuarioRol> usuarioRol = vtUsuarioRolLogic.findUsuarioRolbyRol(vtRol);
		//		
		//		
		//		for (VtUsuarioRol vtUsuarioRol : usuarioRol) {
		//			log.info("Codigo: "+ vtUsuarioRol.getUsroCodigo());
		//			log.info("Usuario: "+ vtUsuarioRol.getVtUsuario().getNombre());
		//			log.info("Rol: "+ vtUsuarioRol.getVtRol().getRolNombre()+"\n");
		//			losUsuarios.add(vtUsuarioRol.getVtUsuario());
		//		}
		//		
		//		for (VtUsuario vtUsuario : losUsuarios) {
		//			log.info("Nombre: "+vtUsuario.getNombre());
		//			log.info("Codigo: "+vtUsuario.getUsuaCodigo()+"\n");
		//		}
	}
	//	@Test
	public void testI()throws Exception{

		//		VtArtefacto vtArtefacto = vtArtefactoLogic.getVtArtefacto(22L);
		//		
		//		VtUsuarioArtefacto usuarioArtefacto = vtUsuarioArtefactoLogic.findUsuarioArtefactoByArtefacto(vtArtefacto);
		//			
		//		log.info("Código: "+usuarioArtefacto.getUsuartCodigo());
	}

	@Test
	public void testJ() throws Exception {
			
		VtUsuario vtUsuario = vtUsuarioLogic.findUsuarioByLogin("fsega08@gmail.com");
			
		log.info("clave= " + vtUsuario.getClave());
			
		vtUsuarioLogic.nuevoUsuario(vtUsuario);
			
		log.info("clave= " + vtUsuario.getClave());
			
			
	}	

}
