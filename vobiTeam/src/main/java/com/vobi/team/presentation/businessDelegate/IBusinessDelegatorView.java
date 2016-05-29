package com.vobi.team.presentation.businessDelegate;

import java.util.List;

import javax.mail.MessagingException;

import com.vobi.team.modelo.VtArchivo;
import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtEstado;
import com.vobi.team.modelo.VtEstadoSprint;
import com.vobi.team.modelo.VtHistoriaArtefacto;
import com.vobi.team.modelo.VtInteres;
import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtPrioridad;
import com.vobi.team.modelo.VtProgresoArtefacto;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtProyectoUsuario;
import com.vobi.team.modelo.VtRol;
import com.vobi.team.modelo.VtSprint;
import com.vobi.team.modelo.VtTipoArtefacto;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.VtUsuarioArtefacto;
import com.vobi.team.modelo.VtUsuarioRol;
import com.vobi.team.modelo.dto.VtArchivoDTO;
import com.vobi.team.modelo.dto.VtArtefactoDTO;
import com.vobi.team.modelo.dto.VtEmpresaDTO;
import com.vobi.team.modelo.dto.VtEstadoDTO;
import com.vobi.team.modelo.dto.VtEstadoSprintDTO;
import com.vobi.team.modelo.dto.VtHistoriaArtefactoDTO;
import com.vobi.team.modelo.dto.VtInteresDTO;
import com.vobi.team.modelo.dto.VtPilaProductoDTO;
import com.vobi.team.modelo.dto.VtPrioridadDTO;
import com.vobi.team.modelo.dto.VtProgresoArtefactoDTO;
import com.vobi.team.modelo.dto.VtProyectoDTO;
import com.vobi.team.modelo.dto.VtProyectoUsuarioDTO;
import com.vobi.team.modelo.dto.VtRolDTO;
import com.vobi.team.modelo.dto.VtSprintDTO;
import com.vobi.team.modelo.dto.VtTipoArtefactoDTO;
import com.vobi.team.modelo.dto.VtUsuarioArtefactoDTO;
import com.vobi.team.modelo.dto.VtUsuarioDTO;
import com.vobi.team.modelo.dto.VtUsuarioRolDTO;

import hirondelle.date4j.DateTime;


/**
 * @author Zathura Code Generator http://zathuracode.org/
 * www.zathuracode.org
 *
 */
public interface IBusinessDelegatorView {

	public List<VtUsuario> getVtUsuarioAsignados(VtProyecto proyecto) throws Exception;

	public void recuperarContrasena(VtUsuario vtUsuario) throws Exception;

	public List<VtArchivo> getVtArchivo() throws Exception;

	public void saveVtArchivo(VtArchivo entity) throws Exception;

	public void deleteVtArchivo(VtArchivo entity) throws Exception;

	public void updateVtArchivo(VtArchivo entity) throws Exception;

	public VtArchivo getVtArchivo(Long archCodigo) throws Exception;

	public List<VtArchivo> findByCriteriaInVtArchivo(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception;

	public List<VtArchivo> findPageVtArchivo(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception;

	public Long findTotalNumberVtArchivo() throws Exception;

	public List<VtArchivoDTO> getDataVtArchivo() throws Exception;

	public List<VtArtefacto> getVtArtefacto() throws Exception;

	public List<VtArtefacto> findArtefactosBySpring(VtSprint vtSprint) throws Exception;

	public void saveVtArtefacto(VtArtefacto entity) throws Exception;

	public void deleteVtArtefacto(VtArtefacto entity) throws Exception;

	public void updateVtArtefacto(VtArtefacto entity) throws Exception;

	public VtArtefacto getVtArtefacto(Long arteCodigo)
			throws Exception;

	public List<VtArtefacto> findByCriteriaInVtArtefacto(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception;

	public List<VtArtefacto> findPageVtArtefacto(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception;

	public List<VtArtefacto> findArtefactosVaciosPorBacklog(Long backlogId);

	public Long findTotalNumberVtArtefacto() throws Exception;

	public List<VtArtefactoDTO> getDataVtArtefacto() throws Exception;

	public List<VtEmpresa> getVtEmpresa() throws Exception;

	public void saveVtEmpresa(VtEmpresa entity) throws Exception;

	public void deleteVtEmpresa(VtEmpresa entity) throws Exception;

	public void updateVtEmpresa(VtEmpresa entity) throws Exception;

	public VtEmpresa getVtEmpresa(Long emprCodigo) throws Exception;

	public VtEmpresa findByEnterpriseIdentificacion(String identificacion) throws Exception;

	public List<VtEmpresa> findByCriteriaInVtEmpresa(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception;

	public List<VtEmpresa> findPageVtEmpresa(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception;

	public Long findTotalNumberVtEmpresa() throws Exception;

	public List<VtEmpresaDTO> getDataVtEmpresa() throws Exception;

	public List<VtEstado> getVtEstado() throws Exception;

	public void saveVtEstado(VtEstado entity) throws Exception;

	public void deleteVtEstado(VtEstado entity) throws Exception;

	public void updateVtEstado(VtEstado entity) throws Exception;

	public VtEstado getVtEstado(Long estaCodigo) throws Exception;

	public List<VtEstado> findByCriteriaInVtEstado(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception;

	public List<VtEstado> findPageVtEstado(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception;

	public Long findTotalNumberVtEstado() throws Exception;

	public List<VtEstadoDTO> getDataVtEstado() throws Exception;

	public List<VtHistoriaArtefacto> getVtHistoriaArtefacto()
			throws Exception;

	public void saveVtHistoriaArtefacto(VtHistoriaArtefacto entity)
			throws Exception;

	public void deleteVtHistoriaArtefacto(VtHistoriaArtefacto entity)
			throws Exception;

	public void updateVtHistoriaArtefacto(VtHistoriaArtefacto entity)
			throws Exception;

	public VtHistoriaArtefacto getVtHistoriaArtefacto(Long historiaCodigo)
			throws Exception;

	public List<VtHistoriaArtefacto> findByCriteriaInVtHistoriaArtefacto(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<VtHistoriaArtefacto> findPageVtHistoriaArtefacto(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberVtHistoriaArtefacto() throws Exception;

	public List<VtHistoriaArtefactoDTO> getDataVtHistoriaArtefacto()
			throws Exception;

	public List<VtInteres> getVtInteres() throws Exception;

	public void saveVtInteres(VtInteres entity) throws Exception;

	public void deleteVtInteres(VtInteres entity) throws Exception;

	public void updateVtInteres(VtInteres entity) throws Exception;

	public VtInteres getVtInteres(Long inteCodigo) throws Exception;

	public List<VtInteres> findByCriteriaInVtInteres(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception;

	public List<VtInteres> findPageVtInteres(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception;

	public Long findTotalNumberVtInteres() throws Exception;

	public List<VtInteresDTO> getDataVtInteres() throws Exception;

	public List<VtPilaProducto> getVtPilaProducto() throws Exception;

	public void saveVtPilaProducto(VtPilaProducto entity)
			throws Exception;

	public void deleteVtPilaProducto(VtPilaProducto entity)
			throws Exception;

	public void updateVtPilaProducto(VtPilaProducto entity)
			throws Exception;

	public VtPilaProducto getVtPilaProducto(Long pilaCodigo)
			throws Exception;

	public List<VtPilaProducto> findByCriteriaInVtPilaProducto(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<VtPilaProducto> findPageVtPilaProducto(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception;

	public Long findTotalNumberVtPilaProducto() throws Exception;

	public List<VtPilaProductoDTO> getDataVtPilaProducto()
			throws Exception;

	public List<VtPrioridad> getVtPrioridad() throws Exception;

	public void saveVtPrioridad(VtPrioridad entity) throws Exception;

	public void deleteVtPrioridad(VtPrioridad entity) throws Exception;

	public void updateVtPrioridad(VtPrioridad entity) throws Exception;

	public VtPrioridad getVtPrioridad(Long prioCodigo)
			throws Exception;

	public List<VtPrioridad> findByCriteriaInVtPrioridad(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception;

	public List<VtPrioridad> findPageVtPrioridad(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception;

	public Long findTotalNumberVtPrioridad() throws Exception;

	public List<VtPrioridadDTO> getDataVtPrioridad() throws Exception;

	public List<VtProgresoArtefacto> getVtProgresoArtefacto()
			throws Exception;

	public void saveVtProgresoArtefacto(VtProgresoArtefacto entity)
			throws Exception;

	public void deleteVtProgresoArtefacto(VtProgresoArtefacto entity)
			throws Exception;

	public void updateVtProgresoArtefacto(VtProgresoArtefacto entity)
			throws Exception;

	public VtProgresoArtefacto getVtProgresoArtefacto(Long proartCodigo)
			throws Exception;

	public List<VtProgresoArtefacto> findByCriteriaInVtProgresoArtefacto(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<VtProgresoArtefacto> findPageVtProgresoArtefacto(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberVtProgresoArtefacto() throws Exception;

	public List<VtProgresoArtefactoDTO> getDataVtProgresoArtefacto()
			throws Exception;

	public List<VtProyecto> getVtProyecto() throws Exception;

	public void saveVtProyecto(VtProyecto entity) throws Exception;

	public void deleteVtProyecto(VtProyecto entity) throws Exception;

	public void updateVtProyecto(VtProyecto entity) throws Exception;

	public VtProyecto getVtProyecto(Long proyCodigo) throws Exception;

	public List<VtProyecto> findByCriteriaInVtProyecto(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception;

	public List<VtProyecto> findProyectsByEnterpriseIdentification
	(VtEmpresa vtEmpresa) throws Exception;

	public List<VtProyecto> findPageVtProyecto(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception;

	public Long findTotalNumberVtProyecto() throws Exception;

	public List<VtProyectoDTO> getDataVtProyecto() throws Exception;

	public List<VtProyectoUsuario> getVtProyectoUsuario()
			throws Exception;

	public void saveVtProyectoUsuario(VtProyectoUsuario entity)
			throws Exception;

	public void deleteVtProyectoUsuario(VtProyectoUsuario entity)
			throws Exception;

	public void updateVtProyectoUsuario(VtProyectoUsuario entity)
			throws Exception;

	public VtProyectoUsuario getVtProyectoUsuario(Long prusCodigo)
			throws Exception;

	public List<VtProyectoUsuario> findByCriteriaInVtProyectoUsuario(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<VtProyectoUsuario> findPageVtProyectoUsuario(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberVtProyectoUsuario() throws Exception;

	public List<VtProyectoUsuarioDTO> getDataVtProyectoUsuario()
			throws Exception;

	public List<VtRol> getVtRol() throws Exception;

	public void saveVtRol(VtRol entity) throws Exception;

	public void deleteVtRol(VtRol entity) throws Exception;

	public void updateVtRol(VtRol entity) throws Exception;

	public VtRol getVtRol(Long rolCodigo) throws Exception;

	public List<VtRol> findByCriteriaInVtRol(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception;

	public List<VtRol> findPageVtRol(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception;

	public Long findTotalNumberVtRol() throws Exception;

	public List<VtRolDTO> getDataVtRol() throws Exception;

	public List<VtSprint> getVtSprint() throws Exception;

	public void saveVtSprint(VtSprint entity) throws Exception;

	public void deleteVtSprint(VtSprint entity) throws Exception;

	public void updateVtSprint(VtSprint entity) throws Exception;

	public VtSprint getVtSprint(Long spriCodigo) throws Exception;

	public List<VtSprint> findByCriteriaInVtSprint(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception;

	public List<VtSprint> findSprintByBacklog(VtPilaProducto vtBacklog) 
			throws Exception;

	public List<VtSprint> findPageVtSprint(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception;

	public Long findTotalNumberVtSprint() throws Exception;

	public List<VtSprintDTO> getDataVtSprint() throws Exception;

	public List<VtTipoArtefacto> getVtTipoArtefacto() throws Exception;

	public void saveVtTipoArtefacto(VtTipoArtefacto entity)
			throws Exception;

	public void deleteVtTipoArtefacto(VtTipoArtefacto entity)
			throws Exception;

	public void updateVtTipoArtefacto(VtTipoArtefacto entity)
			throws Exception;

	public VtTipoArtefacto getVtTipoArtefacto(Long tparCodigo)
			throws Exception;

	public List<VtTipoArtefacto> findByCriteriaInVtTipoArtefacto(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<VtTipoArtefacto> findPageVtTipoArtefacto(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberVtTipoArtefacto() throws Exception;

	public List<VtTipoArtefactoDTO> getDataVtTipoArtefacto()
			throws Exception;

	public List<VtUsuario> getVtUsuario() throws Exception;

	public void saveVtUsuario(VtUsuario entity) throws Exception;

	public void deleteVtUsuario(VtUsuario entity) throws Exception;

	public void updateVtUsuario(VtUsuario entity) throws Exception;

	public VtUsuario getVtUsuario(Long usuaCodigo) throws Exception;

	public List<VtUsuario> findByCriteriaInVtUsuario(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception;

	public List<VtUsuario> findPageVtUsuario(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception;

	public Long findTotalNumberVtUsuario() throws Exception;

	public List<VtUsuarioDTO> getDataVtUsuario() throws Exception;

	public List<VtUsuarioArtefacto> getVtUsuarioArtefacto()
			throws Exception;

	public void saveVtUsuarioArtefacto(VtUsuarioArtefacto entity)
			throws Exception;

	public void deleteVtUsuarioArtefacto(VtUsuarioArtefacto entity)
			throws Exception;

	public void updateVtUsuarioArtefacto(VtUsuarioArtefacto entity)
			throws Exception;

	public VtUsuarioArtefacto getVtUsuarioArtefacto(Long usuartCodigo)
			throws Exception;

	public List<VtUsuarioArtefacto> findByCriteriaInVtUsuarioArtefacto(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<VtUsuarioArtefacto> findPageVtUsuarioArtefacto(
			String sortColumnName, boolean sortAscending, int startRow,
			int maxResults) throws Exception;

	public Long findTotalNumberVtUsuarioArtefacto() throws Exception;

	public List<VtUsuarioArtefactoDTO> getDataVtUsuarioArtefacto()
			throws Exception;

	public List<VtUsuarioRol> getVtUsuarioRol() throws Exception;

	public void saveVtUsuarioRol(VtUsuarioRol entity) throws Exception;

	public void deleteVtUsuarioRol(VtUsuarioRol entity)
			throws Exception;

	public void updateVtUsuarioRol(VtUsuarioRol entity)
			throws Exception;

	public VtUsuarioRol getVtUsuarioRol(Long usroCodigo)
			throws Exception;

	public List<VtUsuarioRol> findByCriteriaInVtUsuarioRol(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception;

	public List<VtUsuarioRol> findPageVtUsuarioRol(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception;

	public Long findTotalNumberVtUsuarioRol() throws Exception;

	public List<VtUsuarioRolDTO> getDataVtUsuarioRol()
			throws Exception;

	public boolean autenticarUsuario(String login, String password) throws Exception;

	public VtUsuario findUsuarioByLogin(String login) throws Exception;

	public List<VtProyectoUsuario> findProyectoUsuarioPorProyecto(VtProyecto proyecto) throws Exception;

	public List<VtUsuario> getVtUsuarioNoAsignados(VtProyecto proyecto) throws Exception;

	public List<VtPilaProducto> findBacklogByProyecto(VtProyecto vtProyecto) throws Exception;

	public VtProyectoUsuario findProyectoUsuarioByProyectoAndUsuario(Long proyectoId, Long usuarioId);

	public List<VtHistoriaArtefacto> findHistoriaByArtefacto(VtArtefacto vtArtefacto) throws Exception;

	public List<VtArchivo> findArchivosByArtefactos(VtArtefacto vtArtefacto) throws Exception;

	public List<VtUsuarioRol> findUsuarioRolbyUsuario(VtUsuario usuario)throws Exception;

	public VtUsuarioRol findUsuarioRolByUsuarioAndRol(Long usuarioCodigo, Long rolCodigo);

	public List<VtRol> getRolesAsignados(VtUsuario usuario) throws Exception;

	public List<VtRol> getRolesNoAsignados(VtUsuario usuario) throws Exception;

	public List<VtUsuarioRol> findUsuarioRolbyRol(VtRol rol)throws Exception;

	public List<VtUsuario> getVtUsuarioDesarrolladores() throws Exception;

	public void nuevoUsuario(VtUsuario vtUsuario) throws Exception;

	public VtUsuarioArtefacto findUsuarioArtefactoByUsuarioArtefactoInteres(Long usuarioCodigo, Long arteCodigo, Long inteCodigo) throws Exception;

	public VtUsuarioArtefacto findUsuarioArtefactoByArtefacto(VtArtefacto artefacto) throws Exception;

	public boolean verificarContraseña(VtUsuario vtUsuario, String passActual ,String password1, String password2);

	public void send(String para, String asunto, String cuerpo) throws MessagingException;

	public List<VtUsuarioArtefacto> findUsuarioArtefactoByUsuarios(VtUsuario vtUsuario) throws Exception;

	//---

	public List<VtEstadoSprint> getVtEstadoSprint() throws Exception;

	public void saveVtEstadoSprint(VtEstadoSprint entity)
			throws Exception;

	public void deleteVtEstadoSprint(VtEstadoSprint entity)
			throws Exception;

	public void updateVtEstadoSprint(VtEstadoSprint entity)
			throws Exception;

	public VtEstadoSprint getVtEstadoSprint(Long esspCodigo)
			throws Exception;

	public List<VtEstadoSprint> findByCriteriaInVtEstadoSprint(
			Object[] variables, Object[] variablesBetween,
			Object[] variablesBetweenDates) throws Exception;

	public List<VtEstadoSprint> findPageVtEstadoSprint(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception;

	public Long findTotalNumberVtEstadoSprint() throws Exception;

	public List<VtEstadoSprintDTO> getDataVtEstadoSprint()
			throws Exception;
	
	public List<VtProgresoArtefacto> findProgresoArtefactosPorArtefactos(VtArtefacto vtArtefacto) throws Exception;
	
	public List<VtArtefacto> findArtefactosBySprintAndEstado(Long spriCodigo, Long estaCodigo);

	public Long totalEsfuerzoEstimadoArtefactoPorSprint(Long spriCodigo);
	
	public Long sumatoriaTiempoDedicadoPorSprintFecha(Long spriCodigo, DateTime fecha);
	
	public Long rolMasBajoPorUsuario(VtUsuario vtUsuario);
	
	public List<VtEstado> consultarEstadosParaDesarrollador();
	
	 public VtSprintDTO getDataVtSprintDTO(VtSprint vtSprint) throws Exception;
}
