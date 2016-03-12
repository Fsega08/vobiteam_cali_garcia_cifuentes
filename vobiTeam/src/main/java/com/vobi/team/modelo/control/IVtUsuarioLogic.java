package com.vobi.team.modelo.control;

import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.dto.VtUsuarioDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface IVtUsuarioLogic {
    public List<VtUsuario> getVtUsuario() throws Exception;

    /**
         * Save an new VtUsuario entity
         */
    public void saveVtUsuario(VtUsuario entity) throws Exception;

    /**
         * Delete an existing VtUsuario entity
         *
         */
    public void deleteVtUsuario(VtUsuario entity) throws Exception;

    /**
        * Update an existing VtUsuario entity
        *
        */
    public void updateVtUsuario(VtUsuario entity) throws Exception;

    /**
         * Load an existing VtUsuario entity
         *
         */
    public VtUsuario getVtUsuario(Long usuaCodigo) throws Exception;

    public List<VtUsuario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtUsuario> findPageVtUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtUsuario() throws Exception;

    public List<VtUsuarioDTO> getDataVtUsuario() throws Exception;
    
    public VtUsuario findUsuarioByLogin(String login)throws Exception;
	
	public boolean autenticarUsuario(String login, String password) throws Exception;
	
	public List<VtUsuario> getVtUsuarioNoAsignados(VtProyecto proyecto) throws Exception;
	
	public List<VtUsuario> getVtUsuarioAsignados(VtProyecto proyecto) throws Exception;
}
