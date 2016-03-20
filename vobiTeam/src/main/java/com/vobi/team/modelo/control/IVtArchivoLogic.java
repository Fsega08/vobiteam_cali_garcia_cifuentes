package com.vobi.team.modelo.control;

import com.vobi.team.modelo.VtArchivo;
import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.dto.VtArchivoDTO;

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
public interface IVtArchivoLogic {
    public List<VtArchivo> getVtArchivo() throws Exception;

    /**
         * Save an new VtArchivo entity
         */
    public void saveVtArchivo(VtArchivo entity) throws Exception;

    /**
         * Delete an existing VtArchivo entity
         *
         */
    public void deleteVtArchivo(VtArchivo entity) throws Exception;

    /**
        * Update an existing VtArchivo entity
        *
        */
    public void updateVtArchivo(VtArchivo entity) throws Exception;

    /**
         * Load an existing VtArchivo entity
         *
         */
    public VtArchivo getVtArchivo(Long archCodigo) throws Exception;

    public List<VtArchivo> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtArchivo> findPageVtArchivo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtArchivo() throws Exception;

    public List<VtArchivoDTO> getDataVtArchivo() throws Exception;
    
    public List<VtArchivo> findArchivosByArtefactos(VtArtefacto vtArtefacto) throws Exception;
}
