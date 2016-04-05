package com.vobi.team.modelo.control;

import com.vobi.team.modelo.VtUsuarioArtefacto;
import com.vobi.team.modelo.dto.VtUsuarioArtefactoDTO;

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
public interface IVtUsuarioArtefactoLogic {
	public List<VtUsuarioArtefacto> getVtUsuarioArtefacto()
			throws Exception;

	/**
	 * Save an new VtUsuarioArtefacto entity
	 */
	public void saveVtUsuarioArtefacto(VtUsuarioArtefacto entity)
			throws Exception;

	/**
	 * Delete an existing VtUsuarioArtefacto entity
	 *
	 */
	public void deleteVtUsuarioArtefacto(VtUsuarioArtefacto entity)
			throws Exception;

	/**
	 * Update an existing VtUsuarioArtefacto entity
	 *
	 */
	public void updateVtUsuarioArtefacto(VtUsuarioArtefacto entity)
			throws Exception;

	/**
	 * Load an existing VtUsuarioArtefacto entity
	 *
	 */
	public VtUsuarioArtefacto getVtUsuarioArtefacto(Long usuartCodigo)
			throws Exception;

	public List<VtUsuarioArtefacto> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception;

	public List<VtUsuarioArtefacto> findPageVtUsuarioArtefacto(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberVtUsuarioArtefacto() throws Exception;

	public List<VtUsuarioArtefactoDTO> getDataVtUsuarioArtefacto()
			throws Exception;

	public VtUsuarioArtefacto findUsuarioArtefactoByUsuarioArtefactoInteres(Long usuarioCodigo, Long arteCodigo, Long inteCodigo) throws Exception;
}
