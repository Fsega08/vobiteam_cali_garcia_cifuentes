package com.vobi.team.dataaccess.dao;

import java.util.List;

import com.vobi.team.dataaccess.api.Dao;
import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtSprint;


/**
* Interface for   VtSprintDAO.
*
*/
public interface IVtSprintDAO extends Dao<VtSprint, Long> {

	public List<VtSprint> findSprintEstadoActivo(VtPilaProducto vtPilaProducto);
}
