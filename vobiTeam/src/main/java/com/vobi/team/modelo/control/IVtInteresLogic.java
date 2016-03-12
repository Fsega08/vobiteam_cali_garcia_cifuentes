package com.vobi.team.modelo.control;

import com.vobi.team.modelo.VtInteres;
import com.vobi.team.modelo.dto.VtInteresDTO;

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
public interface IVtInteresLogic {
    public List<VtInteres> getVtInteres() throws Exception;

    /**
         * Save an new VtInteres entity
         */
    public void saveVtInteres(VtInteres entity) throws Exception;

    /**
         * Delete an existing VtInteres entity
         *
         */
    public void deleteVtInteres(VtInteres entity) throws Exception;

    /**
        * Update an existing VtInteres entity
        *
        */
    public void updateVtInteres(VtInteres entity) throws Exception;

    /**
         * Load an existing VtInteres entity
         *
         */
    public VtInteres getVtInteres(Long inteCodigo) throws Exception;

    public List<VtInteres> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtInteres> findPageVtInteres(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtInteres() throws Exception;

    public List<VtInteresDTO> getDataVtInteres() throws Exception;
}
