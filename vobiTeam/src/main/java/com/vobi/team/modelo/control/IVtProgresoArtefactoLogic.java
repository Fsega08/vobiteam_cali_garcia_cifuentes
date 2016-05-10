package com.vobi.team.modelo.control;

import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtProgresoArtefacto;
import com.vobi.team.modelo.dto.VtProgresoArtefactoDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IVtProgresoArtefactoLogic {
    public List<VtProgresoArtefacto> getVtProgresoArtefacto()
        throws Exception;

    /**
         * Save an new VtProgresoArtefacto entity
         */
    public void saveVtProgresoArtefacto(VtProgresoArtefacto entity)
        throws Exception;

    /**
         * Delete an existing VtProgresoArtefacto entity
         *
         */
    public void deleteVtProgresoArtefacto(VtProgresoArtefacto entity)
        throws Exception;

    /**
        * Update an existing VtProgresoArtefacto entity
        *
        */
    public void updateVtProgresoArtefacto(VtProgresoArtefacto entity)
        throws Exception;

    /**
         * Load an existing VtProgresoArtefacto entity
         *
         */
    public VtProgresoArtefacto getVtProgresoArtefacto(Long proartCodigo)
        throws Exception;

    public List<VtProgresoArtefacto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtProgresoArtefacto> findPageVtProgresoArtefacto(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberVtProgresoArtefacto() throws Exception;

    public List<VtProgresoArtefactoDTO> getDataVtProgresoArtefacto()
        throws Exception;
    
    public List<VtProgresoArtefacto> findProgresoArtefactosPorArtefactos(VtArtefacto vtArtefacto) throws Exception;
}
