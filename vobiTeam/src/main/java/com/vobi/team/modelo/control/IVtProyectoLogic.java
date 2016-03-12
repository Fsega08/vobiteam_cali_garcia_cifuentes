package com.vobi.team.modelo.control;

import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.dto.VtProyectoDTO;

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
public interface IVtProyectoLogic {
    public List<VtProyecto> getVtProyecto() throws Exception;

    /**
         * Save an new VtProyecto entity
         */
    public void saveVtProyecto(VtProyecto entity) throws Exception;

    /**
         * Delete an existing VtProyecto entity
         *
         */
    public void deleteVtProyecto(VtProyecto entity) throws Exception;

    /**
        * Update an existing VtProyecto entity
        *
        */
    public void updateVtProyecto(VtProyecto entity) throws Exception;

    /**
         * Load an existing VtProyecto entity
         *
         */
    public VtProyecto getVtProyecto(Long proyCodigo) throws Exception;

    public List<VtProyecto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtProyecto> findPageVtProyecto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtProyecto() throws Exception;

    public List<VtProyectoDTO> getDataVtProyecto() throws Exception;
    
    public List<VtProyecto> findProyectsByEnterpriseIdentification
	(VtEmpresa vtEmpresa) throws Exception;
}
