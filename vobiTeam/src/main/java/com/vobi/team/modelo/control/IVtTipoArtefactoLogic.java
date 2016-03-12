package com.vobi.team.modelo.control;

import com.vobi.team.modelo.VtTipoArtefacto;
import com.vobi.team.modelo.dto.VtTipoArtefactoDTO;

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
public interface IVtTipoArtefactoLogic {
    public List<VtTipoArtefacto> getVtTipoArtefacto() throws Exception;

    /**
         * Save an new VtTipoArtefacto entity
         */
    public void saveVtTipoArtefacto(VtTipoArtefacto entity)
        throws Exception;

    /**
         * Delete an existing VtTipoArtefacto entity
         *
         */
    public void deleteVtTipoArtefacto(VtTipoArtefacto entity)
        throws Exception;

    /**
        * Update an existing VtTipoArtefacto entity
        *
        */
    public void updateVtTipoArtefacto(VtTipoArtefacto entity)
        throws Exception;

    /**
         * Load an existing VtTipoArtefacto entity
         *
         */
    public VtTipoArtefacto getVtTipoArtefacto(Long tparCodigo)
        throws Exception;

    public List<VtTipoArtefacto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtTipoArtefacto> findPageVtTipoArtefacto(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberVtTipoArtefacto() throws Exception;

    public List<VtTipoArtefactoDTO> getDataVtTipoArtefacto()
        throws Exception;
}
