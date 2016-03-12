package com.vobi.team.modelo.control;

import com.vobi.team.modelo.VtRol;
import com.vobi.team.modelo.dto.VtRolDTO;

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
public interface IVtRolLogic {
    public List<VtRol> getVtRol() throws Exception;

    /**
         * Save an new VtRol entity
         */
    public void saveVtRol(VtRol entity) throws Exception;

    /**
         * Delete an existing VtRol entity
         *
         */
    public void deleteVtRol(VtRol entity) throws Exception;

    /**
        * Update an existing VtRol entity
        *
        */
    public void updateVtRol(VtRol entity) throws Exception;

    /**
         * Load an existing VtRol entity
         *
         */
    public VtRol getVtRol(Long rolCodigo) throws Exception;

    public List<VtRol> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtRol> findPageVtRol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtRol() throws Exception;

    public List<VtRolDTO> getDataVtRol() throws Exception;
}
