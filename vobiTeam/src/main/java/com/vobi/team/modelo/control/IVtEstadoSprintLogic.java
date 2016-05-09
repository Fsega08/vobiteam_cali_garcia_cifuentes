package com.vobi.team.modelo.control;

import com.vobi.team.modelo.VtEstadoSprint;
import com.vobi.team.modelo.dto.VtEstadoSprintDTO;

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
public interface IVtEstadoSprintLogic {
    public List<VtEstadoSprint> getVtEstadoSprint() throws Exception;

    /**
         * Save an new VtEstadoSprint entity
         */
    public void saveVtEstadoSprint(VtEstadoSprint entity)
        throws Exception;

    /**
         * Delete an existing VtEstadoSprint entity
         *
         */
    public void deleteVtEstadoSprint(VtEstadoSprint entity)
        throws Exception;

    /**
        * Update an existing VtEstadoSprint entity
        *
        */
    public void updateVtEstadoSprint(VtEstadoSprint entity)
        throws Exception;

    /**
         * Load an existing VtEstadoSprint entity
         *
         */
    public VtEstadoSprint getVtEstadoSprint(Long estsprCodigo)
        throws Exception;

    public List<VtEstadoSprint> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtEstadoSprint> findPageVtEstadoSprint(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtEstadoSprint() throws Exception;

    public List<VtEstadoSprintDTO> getDataVtEstadoSprint()
        throws Exception;
}
