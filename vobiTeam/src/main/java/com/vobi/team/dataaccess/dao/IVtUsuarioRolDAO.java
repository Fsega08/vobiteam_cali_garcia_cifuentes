package com.vobi.team.dataaccess.dao;

import com.vobi.team.dataaccess.api.Dao;
import com.vobi.team.modelo.VtUsuarioRol;


/**
* Interface for   VtUsuarioRolDAO.
*
*/
public interface IVtUsuarioRolDAO extends Dao<VtUsuarioRol, Long> {
	
	public VtUsuarioRol findUsuarioRolByUsuarioAndRol(Long usuarioCodigo, Long rolCodigo);
}
