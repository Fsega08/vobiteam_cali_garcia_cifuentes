package com.vobi.team.modelo.control;

import com.vobi.team.modelo.VtEstado;
import com.vobi.team.modelo.dto.VtEstadoDTO;

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
public interface IVtEstadoLogic {
    public List<VtEstado> getVtEstado() throws Exception;

    /**
         * Save an new VtEstado entity
         */
    public void saveVtEstado(VtEstado entity) throws Exception;

    /**
         * Delete an existing VtEstado entity
         *
         */
    public void deleteVtEstado(VtEstado entity) throws Exception;

    /**
        * Update an existing VtEstado entity
        *
        */
    public void updateVtEstado(VtEstado entity) throws Exception;

    /**
         * Load an existing VtEstado entity
         *
         */
    public VtEstado getVtEstado(Long estaCodigo) throws Exception;

    public List<VtEstado> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtEstado> findPageVtEstado(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtEstado() throws Exception;

    public List<VtEstadoDTO> getDataVtEstado() throws Exception;
    
	public List<VtEstado> consultarEstadosParaDesarrollador();
}
