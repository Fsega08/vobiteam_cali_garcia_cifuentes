package com.vobi.team.dataaccess.dao;


import com.vobi.team.dataaccess.api.Dao;
import com.vobi.team.modelo.VtProyectoUsuario;


/**
* Interface for   VtProyectoUsuarioDAO.
*
*/
public interface IVtProyectoUsuarioDAO extends Dao<VtProyectoUsuario, Long> {
	
	
	
	public VtProyectoUsuario findProyectoUsuarioByProyectoAndUsuario(Long proyectoId, Long usuarioId);
	
}
