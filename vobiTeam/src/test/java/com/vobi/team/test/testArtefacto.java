package com.vobi.team.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtPilaProducto;

import com.vobi.team.modelo.VtSprint;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.control.IVtArtefactoLogic;
import com.vobi.team.modelo.control.IVtPilaProductoLogic;

import com.vobi.team.modelo.control.IVtSprintLogic;
import com.vobi.team.modelo.control.IVtUsuarioLogic;






@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class testArtefacto {
	
	private final static Logger log=LoggerFactory.getLogger(testArtefacto.class);
	
	 @Autowired
	 private IVtPilaProductoLogic vtPilaProductoLogic;
	 
	 @Autowired
	 private IVtArtefactoLogic vtArtefactoLogic;
	 
	 @Autowired
	 private IVtSprintLogic sprintLogic;
	 
	 @Autowired
	private IVtUsuarioLogic vtUsuarioLogic;
	 
	 @Autowired
	 private IVtSprintLogic vtSprintLogic;
	 
	@Test
	public void testA() throws Exception {
		VtPilaProducto vtPilaProducto = vtPilaProductoLogic.getVtPilaProducto(1L);
		
		log.info("pila producto= " + vtPilaProducto.getNombre());

		List<VtArtefacto> losArtefactos = vtArtefactoLogic.findArtefactosVaciosPorBacklog(vtPilaProducto.getPilaCodigo());
		
		for (VtArtefacto vtArtefacto : losArtefactos) {
			log.info("nombre= " + vtArtefacto.getTitulo());		
		}
		
		
	}	
	
	@Test
	public void testB() throws Exception {
		
		VtSprint vtSprint = sprintLogic.getVtSprint(1L);
		
		List<VtArtefacto> losArtefactos = new ArrayList<>();
		try {
			losArtefactos = vtArtefactoLogic.findArtefactosBySpring(vtSprint);

		} catch (Exception e) {
			log.info(e.getMessage());
		}
				
		for (VtArtefacto vtArtefacto : losArtefactos) {
			log.info("nombre= " + vtArtefacto.getTitulo());
			
		}
		
		
	}	
	
	
	
	@Test
	public void testC() throws Exception {
		
	
		
	}	
	
	    
	

}
