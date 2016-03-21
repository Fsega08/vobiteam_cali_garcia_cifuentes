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

import com.vobi.team.dataaccess.dao.IVtArtefactoDAO;
import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtProyectoUsuario;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.control.IVtArtefactoLogic;
import com.vobi.team.modelo.control.IVtPilaProductoLogic;
import com.vobi.team.modelo.control.IVtProyectoLogic;
import com.vobi.team.modelo.control.IVtProyectoUsuarioLogic;
import com.vobi.team.modelo.control.IVtUsuarioLogic;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class testArtefacto {
	
	private final static Logger log=LoggerFactory.getLogger(testArtefacto.class);
	
	 @Autowired
	 private IVtPilaProductoLogic vtPilaProductoLogic;
	 
	 @Autowired
	 private IVtArtefactoLogic vtArtefactoLogic;

	@Test
	public void testA() throws Exception {
		VtPilaProducto vtPilaProducto = vtPilaProductoLogic.getVtPilaProducto(1L);
		
		log.info("pila producto= " + vtPilaProducto.getNombre());

		List<VtArtefacto> losArtefactos = vtArtefactoLogic.findArtefactosVaciosPorBacklog(vtPilaProducto.getPilaCodigo());
		
		for (VtArtefacto vtArtefacto : losArtefactos) {
			log.info("nombre= " + vtArtefacto.getTitulo());
			log.info("pila producto " + vtArtefacto.getVtPilaProducto().getPilaCodigo());
			
		}
		
		
	}	

}
