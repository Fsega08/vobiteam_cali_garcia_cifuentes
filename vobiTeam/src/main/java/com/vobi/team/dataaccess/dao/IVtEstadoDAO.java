package com.vobi.team.dataaccess.dao;

import java.util.List;

import com.vobi.team.dataaccess.api.Dao;
import com.vobi.team.modelo.VtEstado;


/**
* Interface for   VtEstadoDAO.
*
*/
public interface IVtEstadoDAO extends Dao<VtEstado, Long> {

	public List<VtEstado> consultarEstadosParaDesarrollador();
}
