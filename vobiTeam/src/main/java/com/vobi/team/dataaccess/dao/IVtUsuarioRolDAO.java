package com.vobi.team.dataaccess.dao;

import java.util.List;

import com.vobi.team.dataaccess.api.Dao;
import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.VtUsuarioRol;


/**
* Interface for   VtUsuarioRolDAO.
*
*/
public interface IVtUsuarioRolDAO extends Dao<VtUsuarioRol, Long> {
	
	public VtUsuarioRol findUsuarioRolByUsuarioAndRol(Long usuarioCodigo, Long rolCodigo);

	public Long rolMasBajoPorUsuario(VtUsuario vtUsuario);

	public List<VtUsuarioRol> ListaDesarrolladoresVortexYClientesDeOtraEmpresa(VtEmpresa vtEmpresa);
}
