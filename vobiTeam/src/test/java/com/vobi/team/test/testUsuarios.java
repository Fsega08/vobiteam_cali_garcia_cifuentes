package com.vobi.team.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtProyectoUsuario;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.control.IVtProyectoLogic;
import com.vobi.team.modelo.control.IVtProyectoUsuarioLogic;
import com.vobi.team.modelo.control.IVtUsuarioLogic;



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
	 
	
	//@Test
	public void testA() throws Exception {
		
//		VtProyecto vtProyecto = vtProyectoLogic.getVtProyecto(1L);
//		
//		List<VtUsuario> losUsuarios = vtUsuarioLogic.getVtUsuarioNoAsignados(vtProyecto);
//		
//		for (VtUsuario vtUsuario : losUsuarios) {
//			log.info("Nombre: "+vtUsuario.getNombre());
//			log.info("Codigo: "+vtUsuario.getUsuaCodigo()+"\n");
//		}	
		
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
	
	@Test
	public void testD() throws Exception {
		VtProyecto vtProyecto = vtProyectoLogic.getVtProyecto(3L);
		VtUsuario vtUsuario = vtUsuarioLogic.getVtUsuario(1L);
		
		VtProyectoUsuario proyectoUsuario = vtProyectoUsuarioLogic.findProyectoUsuarioByProyectoAndUsuario(vtProyecto.getProyCodigo(), vtUsuario.getUsuaCodigo());
		
		
		log.info(""+proyectoUsuario.getPrusCodigo());
		
		
	}	

}
