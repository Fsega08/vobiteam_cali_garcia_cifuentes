package com.vobi.team.dataaccess.dao;

import java.util.List;

import com.vobi.team.dataaccess.api.Dao;
import com.vobi.team.modelo.VtArtefacto;


/**
* Interface for   VtArtefactoDAO.
*
*/
public interface IVtArtefactoDAO extends Dao<VtArtefacto, Long> {

	List<VtArtefacto> findArtefactosVaciosPorBacklog(Long backlogId);
	List<VtArtefacto> findArtefactosVaciosPorBacklogYDesarrollador(Long backlogId);
	
	List<VtArtefacto> findArtefactosBySprintAndEstado(Long spriCodigo, Long estaCodigo);

	Long totalEsfuerzoEstimadoArtefactoPorSprint(Long spriCodigo);
	List<VtArtefacto> findArtefactosBySprint(Long spriCodigo);
}
