package com.vobi.team.modelo.control;

import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtProyectoUsuario;
import com.vobi.team.modelo.dto.VtProyectoUsuarioDTO;

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
public interface IVtProyectoUsuarioLogic {
    public List<VtProyectoUsuario> getVtProyectoUsuario()
        throws Exception;

    /**
         * Save an new VtProyectoUsuario entity
         */
    public void saveVtProyectoUsuario(VtProyectoUsuario entity)
        throws Exception;

    /**
         * Delete an existing VtProyectoUsuario entity
         *
         */
    public void deleteVtProyectoUsuario(VtProyectoUsuario entity)
        throws Exception;

    /**
        * Update an existing VtProyectoUsuario entity
        *
        */
    public void updateVtProyectoUsuario(VtProyectoUsuario entity)
        throws Exception;

    /**
         * Load an existing VtProyectoUsuario entity
         *
         */
    public VtProyectoUsuario getVtProyectoUsuario(Long prusCodigo)
        throws Exception;

    public List<VtProyectoUsuario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VtProyectoUsuario> findPageVtProyectoUsuario(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberVtProyectoUsuario() throws Exception;

    public List<VtProyectoUsuarioDTO> getDataVtProyectoUsuario()
        throws Exception;
    
    public List<VtProyectoUsuario> findProyectoUsuarioPorProyecto(VtProyecto proyecto) throws Exception;
    
    public VtProyectoUsuario findProyectoUsuarioByProyectoAndUsuario(Long proyectoId, Long usuarioId);
}
