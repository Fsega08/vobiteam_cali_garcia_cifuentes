package com.vobi.team.modelo.control;

import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.dto.VtPilaProductoDTO;

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
public interface IVtPilaProductoLogic {
    public List<VtPilaProducto> getVtPilaProducto() throws Exception;

    /**
         * Save an new VtPilaProducto entity
         */
    public void saveVtPilaProducto(VtPilaProducto entity)
        throws Exception;

    /**
         * Delete an existing VtPilaProducto entity
         *
         */
    public void deleteVtPilaProducto(VtPilaProducto entity)
        throws Exception;

    /**
        * Update an existing VtPilaProducto entity
        *
        */
    public void updateVtPilaProducto(VtPilaProducto entity)
        throws Exception;

    /**
         * Load an existing VtPilaProducto entity
         *
         */
    public VtPilaProducto getVtPilaProducto(Long pilaCodigo)
        throws Exception;

    public List<VtPilaProducto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtPilaProducto> findPageVtPilaProducto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtPilaProducto() throws Exception;

    public List<VtPilaProductoDTO> getDataVtPilaProducto()
        throws Exception;
    
    public List<VtPilaProducto> findBacklogByProyecto(VtProyecto vtProyecto) throws Exception;
}
