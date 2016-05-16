package com.vobi.team.dataaccess.dao;

import com.vobi.team.dataaccess.api.Dao;
import com.vobi.team.modelo.VtProgresoArtefacto;

import hirondelle.date4j.DateTime;


/**
* Interface for   VtProgresoArtefactoDAO.
*
*/
public interface IVtProgresoArtefactoDAO extends Dao<VtProgresoArtefacto, Long> {

	Long sumatoriaTiempoDedicadoPorSprintFecha(Long spriCodigo, String fecha);
}
