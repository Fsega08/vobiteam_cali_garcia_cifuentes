package com.vobi.team.modelo.control;

import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtHistoriaArtefacto;
import com.vobi.team.modelo.dto.VtHistoriaArtefactoDTO;

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
public interface IVtHistoriaArtefactoLogic {
    public List<VtHistoriaArtefacto> getVtHistoriaArtefacto()
        throws Exception;

    /**
         * Save an new VtHistoriaArtefacto entity
         */
    public void saveVtHistoriaArtefacto(VtHistoriaArtefacto entity)
        throws Exception;

    /**
         * Delete an existing VtHistoriaArtefacto entity
         *
         */
    public void deleteVtHistoriaArtefacto(VtHistoriaArtefacto entity)
        throws Exception;

    /**
        * Update an existing VtHistoriaArtefacto entity
        *
        */
    public void updateVtHistoriaArtefacto(VtHistoriaArtefacto entity)
        throws Exception;

    /**
         * Load an existing VtHistoriaArtefacto entity
         *
         */
    public VtHistoriaArtefacto getVtHistoriaArtefacto(Long historiaCodigo)
        throws Exception;

    public List<VtHistoriaArtefacto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtHistoriaArtefacto> findPageVtHistoriaArtefacto(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberVtHistoriaArtefacto() throws Exception;

    public List<VtHistoriaArtefactoDTO> getDataVtHistoriaArtefacto()
        throws Exception;
    
    public List<VtHistoriaArtefacto> findHistoriaByArtefacto(VtArtefacto vtArtefacto) throws Exception;
}
