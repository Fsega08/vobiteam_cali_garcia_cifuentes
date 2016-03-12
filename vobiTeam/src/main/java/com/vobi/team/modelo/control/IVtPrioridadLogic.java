package com.vobi.team.modelo.control;

import com.vobi.team.modelo.VtPrioridad;
import com.vobi.team.modelo.dto.VtPrioridadDTO;

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
public interface IVtPrioridadLogic {
    public List<VtPrioridad> getVtPrioridad() throws Exception;

    /**
         * Save an new VtPrioridad entity
         */
    public void saveVtPrioridad(VtPrioridad entity) throws Exception;

    /**
         * Delete an existing VtPrioridad entity
         *
         */
    public void deleteVtPrioridad(VtPrioridad entity) throws Exception;

    /**
        * Update an existing VtPrioridad entity
        *
        */
    public void updateVtPrioridad(VtPrioridad entity) throws Exception;

    /**
         * Load an existing VtPrioridad entity
         *
         */
    public VtPrioridad getVtPrioridad(Long prioCodigo)
        throws Exception;

    public List<VtPrioridad> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtPrioridad> findPageVtPrioridad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtPrioridad() throws Exception;

    public List<VtPrioridadDTO> getDataVtPrioridad() throws Exception;
}
