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
import com.vobi.team.modelo.VtProgresoArtefacto;
import com.vobi.team.modelo.VtSprint;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.control.IVtArtefactoLogic;
import com.vobi.team.modelo.control.IVtPilaProductoLogic;
import com.vobi.team.modelo.control.IVtProgresoArtefactoLogic;
import com.vobi.team.modelo.control.IVtSprintLogic;
import com.vobi.team.modelo.control.IVtUsuarioLogic;
import com.vobi.team.modelo.dto.VtSprintDTO;

import hirondelle.date4j.DateTime;
import hirondelle.date4j.DateTime.DayOverflow;






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
	 
	 @Autowired
	 private IVtProgresoArtefactoLogic vtProgresoArtefactoLogic;
	 
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
		VtSprint sprintSeleccionado = sprintLogic.getVtSprint(1L);
//		List<VtArtefacto> losArtefactos = vtArtefactoLogic.findArtefactosBySpring(sprintSeleccionado);
		
		Long totalEsfuerzo = vtArtefactoLogic.totalEsfuerzoEstimadoArtefactoPorSprint(sprintSeleccionado.getSpriCodigo());
		
		Long totalDias = (sprintSeleccionado.getFechaFin().getTime() - sprintSeleccionado.getFechaInicio().getTime());
		totalDias = TimeUnit.MILLISECONDS.toDays(totalDias);
		
		DateTime diaInicio = new DateTime(""+sprintSeleccionado.getFechaInicio());
		
		diaInicio = DateTime.forDateOnly(diaInicio.getYear(), diaInicio.getMonth(), diaInicio.getDay());
				
		log.info("esf= " + totalEsfuerzo);
	
		for (int i = 0; i <= totalDias; i++) {
			Long tiempoDedicadoDia = vtProgresoArtefactoLogic.sumatoriaTiempoDedicadoPorSprintFecha(sprintSeleccionado.getSpriCodigo(), diaInicio.plusDays(i));

//			if (i==1) {
//				tiempoDedicadoDia = totalEsfuerzo - tiempoDedicadoDia; 
//			}else {
//				tiempoDedicadoDia = vtProgresoArtefactoLogic.sumatoriaTiempoDedicadoPorSprintFecha(sprintSeleccionado.getSpriCodigo(), diaInicio.plusDays(i-1)) - tiempoDedicadoDia;
//			}
			
			
			
			log.info("fecha= " + diaInicio.plusDays(i));
			log.info("tiempoDia= " + tiempoDedicadoDia);
		}
		
	}	
	
	@Test    
	public void testD() throws Exception {
		
		VtSprint vtSprint = vtSprintLogic.getVtSprint(2L);
		
		VtSprintDTO vtSprintDTO = vtSprintLogic.getDataVtSprintDTO(vtSprint);
		
		log.info("Nombre= " + vtSprintDTO.getNombre());
		log.info("Estado= " + vtSprintDTO.getEstsprCodigo_VtEstadoSprint());
		log.info("Usuario creador= " + vtSprintDTO.getUsuCreador());
	}	
	
	@Test    
	public void testE() throws Exception {
		
		VtUsuario vtUsuario = vtUsuarioLogic.getVtUsuario(1L);
		
		VtArtefacto vtArtefacto = vtArtefactoLogic.getVtArtefacto(1L);
		
		vtUsuarioLogic.correoReporteCliente(vtUsuario, vtArtefacto);
		
	}
	
	@Test    
	public void testF() throws Exception {
		
		List<VtUsuario> usu= vtUsuarioLogic.findVortexRolAdmin();
		
		for (VtUsuario vtUsuario : usu) {
			log.info(""+vtUsuario.getNombre());
			log.info(""+vtUsuario.getUsuaCodigo());
			log.info(""+vtUsuario.getLogin());
		}
	}

}
