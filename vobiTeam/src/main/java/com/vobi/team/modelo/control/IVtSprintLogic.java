package com.vobi.team.modelo.control;

import com.vobi.team.modelo.VtEstadoSprint;
import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtSprint;
import com.vobi.team.modelo.dto.VtSprintDTO;

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
public interface IVtSprintLogic {
    public List<VtSprint> getVtSprint() throws Exception;

    /**
         * Save an new VtSprint entity
         */
    public void saveVtSprint(VtSprint entity) throws Exception;

    /**
         * Delete an existing VtSprint entity
         *
         */
    public void deleteVtSprint(VtSprint entity) throws Exception;

    /**
        * Update an existing VtSprint entity
        *
        */
    public void updateVtSprint(VtSprint entity) throws Exception;

    /**
         * Load an existing VtSprint entity
         *
         */
    public VtSprint getVtSprint(Long spriCodigo) throws Exception;

    public List<VtSprint> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtSprint> findPageVtSprint(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVtSprint() throws Exception;

    public List<VtSprintDTO> getDataVtSprint() throws Exception;
    
    public List<VtSprint> findSprintByBacklog(VtPilaProducto vtBacklog) 
			throws Exception;
    public List<VtSprint> findSprintEnCursoByBacklog(VtPilaProducto vtBacklog, VtEstadoSprint vtEstadoSprint) 
			throws Exception;
    public VtSprintDTO getDataVtSprintDTO(VtSprint vtSprint) throws Exception;
    
    public Boolean sprintActivoEnLaMismaPila(VtPilaProducto  vtPilaProducto);
    
    public VtSprint buscarSprintActivoEnLaMismaPila(VtPilaProducto  vtPilaProducto);
}
