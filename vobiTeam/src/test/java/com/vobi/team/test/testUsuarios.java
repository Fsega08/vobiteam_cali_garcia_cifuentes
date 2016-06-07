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

import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtProyectoUsuario;
import com.vobi.team.modelo.VtSprint;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.VtUsuarioArtefacto;
import com.vobi.team.modelo.VtUsuarioRol;
import com.vobi.team.modelo.control.IVtArtefactoLogic;
import com.vobi.team.modelo.control.IVtEmpresaLogic;
import com.vobi.team.modelo.control.IVtInteresLogic;
import com.vobi.team.modelo.control.IVtProyectoLogic;
import com.vobi.team.modelo.control.IVtProyectoUsuarioLogic;
import com.vobi.team.modelo.control.IVtRolLogic;
import com.vobi.team.modelo.control.IVtSprintLogic;
import com.vobi.team.modelo.control.IVtUsuarioArtefactoLogic;
import com.vobi.team.modelo.control.IVtUsuarioLogic;
import com.vobi.team.modelo.control.IVtUsuarioRolLogic;
import com.vobi.team.modelo.dto.VtArtefactoDTO;
import com.vobi.team.service.mail.IMailService;
import com.vobi.team.utilities.Utilities;

import hirondelle.date4j.DateTime;

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
	
	@Autowired
	private IVtEmpresaLogic vtEmpresaLogic;
	
	@Autowired
	private IMailService mail;
	
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
		//		VtUsuario vtUsuario = vtUsuarioLogic.getVtUsuario(4L);
		//
		//		List<VtUsuarioRol> usuarioRol = vtUsuarioRolLogic.findUsuarioRolbyUsuario(vtUsuario);
		//
		//		for (VtUsuarioRol vtUsuarioRol : usuarioRol) {
		//			log.info("Codigo: "+ vtUsuarioRol.getUsroCodigo());
		//			log.info("Usuario: "+ vtUsuarioRol.getVtUsuario().getNombre());
		//			log.info("Rol: "+ vtUsuarioRol.getVtRol().getRolNombre()+"\n");
		//		}
	}

	//	@Test
	public void testH()throws Exception{
		//		VtRol vtRol = vtRolLogic.getVtRol(2L);
		//		List<VtUsuario> losUsuarios = new ArrayList<VtUsuario>();
		//		List<VtUsuarioRol> usuarioRol = vtUsuarioRolLogic.findUsuarioRolbyRol(vtRol);		
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
		//		VtUsuarioArtefacto usuarioArtefacto = vtUsuarioArtefactoLogic.findUsuarioArtefactoByArtefacto(vtArtefacto);
		//			
		//		log.info("Código: "+usuarioArtefacto.getUsuartCodigo());
	}

	//	@Test
	public void testJ() throws Exception {

		//		VtUsuario vtUsuario = vtUsuarioLogic.findUsuarioByLogin("fsega08@gmail.com");
		//		log.info("clave= " + vtUsuario.getClave());
		//		vtUsuarioLogic.nuevoUsuario(vtUsuario);
		//		log.info("clave= " + vtUsuario.getClave());
	}	

	//	@Test
	public void testK() throws Exception{
		//		VtUsuario vtUsuario = vtUsuarioLogic.getVtUsuario(3L);
		//		List<VtUsuarioRol> vtUsuarioRoles = vtUsuarioRolLogic.findUsuarioRolbyUsuario(vtUsuario);
		//		int permisos = vtRolLogic.getVtRol().size();
		//		
		//		log.info("permisosPre= " + permisos);
		//		
		//		for (VtUsuarioRol vtUsuarioRol : vtUsuarioRoles) {
		//			log.info("Codigo: "+ vtUsuarioRol.getUsroCodigo());
		//			log.info("Usuario: "+ vtUsuarioRol.getVtUsuario().getNombre());
		//			log.info("Rol: "+ vtUsuarioRol.getVtRol().getRolNombre()+"\n");
		//		}
		//
		//		for (VtUsuarioRol vtUsuarioRol : vtUsuarioRoles) {
		//			if (vtUsuarioRol.getVtRol().getRolCodigo() == 3L) {
		//				if (permisos > vtUsuarioRol.getVtRol().getRolCodigo()) {
		//					permisos = 3;
		//				}
		//			}else if (vtUsuarioRol.getVtRol().getRolCodigo() == 2L) {
		//				if (permisos > vtUsuarioRol.getVtRol().getRolCodigo()) {
		//					permisos = 2;
		//				}
		//			}else if (vtUsuarioRol.getVtRol().getRolCodigo() == 1L) {
		//				if (permisos > vtUsuarioRol.getVtRol().getRolCodigo()) {
		//					permisos = 1;
		//				}
		//			}
		//		}
		//		log.info("permisos= " + permisos);
	}

//	@Test
	public void testL() throws Exception{
//		VtSprint sprint = vtSprintLogic.getVtSprint(2L);
//
//		for (VtArtefacto artefacto : sprint.getVtArtefactos()) {
//			log.info(""+artefacto.getTitulo());
//		}
	}
	
	@Test
	public void testM() throws Exception{
//		VtUsuario vtUsuario = vtUsuarioLogic.getVtUsuario(1L);
//		
//		log.info("Permiso= "+ vtUsuarioRolLogic.rolMasBajoPorUsuario(vtUsuario));
//		
	}
	
	@Test
	public void testN() throws Exception{
//		VtEmpresa vtEmpresa = vtEmpresaLogic.getVtEmpresa(2L);
//		
//		List<VtUsuarioRol> losUsuRoles = vtUsuarioRolLogic.ListaDesarrolladoresVortexYClientesDeOtraEmpresa(vtEmpresa);
//		
//		for (VtUsuarioRol vtUsuarioRol : losUsuRoles) {
//			log.info(""+vtUsuarioRol.getVtUsuario().getNombre());
//			log.info(""+vtUsuarioRol.getVtUsuario().getVtEmpresa().getNombre());
//			log.info(""+vtUsuarioRol.getVtRol().getRolNombre());
//		}
	}
	
	@Test
	public void testO() throws Exception{
//		for (int i = 0; i < 5; i++) { 
//
//			String asunto = "Bienvenido a VobiTeam";
//			
//			String cuerpo = "Sr@,Sebastián  le damos la bienvenida a VobiTeam " + '\n' 
//							+ "Esperamos que la aplicación sea de su agrado, momentaneamente su cuenta es: " + '\n' + '\n' +
//							"Usuario o Login= Sebastián"  + '\n' +
//							"Contraseña= " + 1234 + '\n' + '\n' 
//							+ "Perteneciente a la empresa= SEGA "   
//							+ '\n' + '\n'  
//							+ "Por motivos de seguridad trate de ingresar lo mas pronto posible "
//							+ "y hacer cambio de su contraseña." + '\n'
//							+ "Le deseamos un excelente día." + '\n'  + '\n' ;
//			
//			
//			mail.send("fsega08@gmail.com" , asunto, cuerpo);
//
//		}
		
	}	
	
//	@Test
	public void testP() throws Exception{
//		int mins = Utilities.pasarFormatoHoraAInteger("55:00");
//		log.info(""+mins);
//		
//		String hours = Utilities.pasarIntAFormatoHoraMinuto(mins);
//		log.info(hours);
	}
	
	@Test
	public void testQ() throws Exception{
//		VtArtefacto vtArtefacto = vtArtefactoLogic.getVtArtefacto(27L);
//		VtArtefactoDTO artefactoDTO = vtArtefactoLogic.getVtArtefactoDTO(vtArtefacto);
//		
//		log.info(artefactoDTO.getEsfuerzoEstimadoConvertido());
	}
	
	
	@Test
	public void testR() throws Exception{
//		VtArtefacto vtArtefacto = vtArtefactoLogic.getVtArtefacto(27L);
//		
//		
//		log.info(""+vtArtefacto.getVtPrioridad().getNombre());
	}
	
	@Test
	public void testS() throws Exception{
		VtSprint sprint = vtSprintLogic.getVtSprint(13L);
		
		if (sprint.getVtEstadoSprint().getEstsprCodigo().equals(2L) && vtSprintLogic.findSprintEnCursoByBacklog(sprint.getVtPilaProducto(), sprint.getVtEstadoSprint()).size()>1) {
			
			log.info("Si");
			
        }
		
		
	}

}
