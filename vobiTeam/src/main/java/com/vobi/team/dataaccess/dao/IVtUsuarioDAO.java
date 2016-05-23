package com.vobi.team.dataaccess.dao;

import java.util.List;

import com.vobi.team.dataaccess.api.Dao;
import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtUsuario;


/**
* Interface for   VtUsuarioDAO.
*
*/
public interface IVtUsuarioDAO extends Dao<VtUsuario, Long> {

	List<VtUsuario> ListaDesarrolladoresVortexYClientesDeOtraEmpresa(VtEmpresa vtEmpresa);
}
