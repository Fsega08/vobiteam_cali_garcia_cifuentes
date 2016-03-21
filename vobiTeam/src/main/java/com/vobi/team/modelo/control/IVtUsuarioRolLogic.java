package com.vobi.team.modelo.control;

import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.VtUsuarioRol;
import com.vobi.team.modelo.dto.VtUsuarioRolDTO;

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
public interface IVtUsuarioRolLogic {
    public List<VtUsuarioRol> getVtUsuarioRol() throws Exception;

    /**
         * Save an new VtUsuarioRol entity
         */
    public void saveVtUsuarioRol(VtUsuarioRol entity) throws Exception;

    /**
         * Delete an existing VtUsuarioRol entity
         *
         */
    public void deleteVtUsuarioRol(VtUsuarioRol entity)
        throws Exception;

    /**
        * Update an existing VtUsuarioRol entity
        *
        */
    public void updateVtUsuarioRol(VtUsuarioRol entity)
        throws Exception;

    /**
         * Load an existing VtUsuarioRol entity
         *
         */
    public VtUsuarioRol getVtUsuarioRol(Long usroCodigo)
        throws Exception;

    public List<VtUsuarioRol> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtUsuarioRol> findPageVtUsuarioRol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtUsuarioRol() throws Exception;

    public List<VtUsuarioRolDTO> getDataVtUsuarioRol()
        throws Exception;
    
    public List<VtUsuarioRol> findUsuarioRolbyUsuario(VtUsuario usuario)throws Exception;
    
    public VtUsuarioRol findUsuarioRolByUsuarioAndRol(Long usuarioCodigo, Long rolCodigo);
	
}
