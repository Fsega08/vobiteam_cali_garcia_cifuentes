package com.vobi.team.modelo.control;

import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.dto.VtEmpresaDTO;

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
public interface IVtEmpresaLogic {
    public List<VtEmpresa> getVtEmpresa() throws Exception;

    /**
         * Save an new VtEmpresa entity
         */
    public void saveVtEmpresa(VtEmpresa entity) throws Exception;

    /**
         * Delete an existing VtEmpresa entity
         *
         */
    public void deleteVtEmpresa(VtEmpresa entity) throws Exception;

    /**
        * Update an existing VtEmpresa entity
        *
        */
    public void updateVtEmpresa(VtEmpresa entity) throws Exception;

    /**
         * Load an existing VtEmpresa entity
         *
         */
    public VtEmpresa getVtEmpresa(Long emprCodigo) throws Exception;

    public List<VtEmpresa> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtEmpresa> findPageVtEmpresa(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtEmpresa() throws Exception;

    public List<VtEmpresaDTO> getDataVtEmpresa() throws Exception;
    
    public VtEmpresa findByEnterpriseIdentificacion(String identificacion) throws Exception;
}
