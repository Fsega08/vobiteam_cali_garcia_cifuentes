package com.vobi.team.modelo.control;

import com.vobi.team.dataaccess.dao.*;
import com.vobi.team.exceptions.*;
import com.vobi.team.modelo.*;
import com.vobi.team.modelo.dto.VtSprintDTO;
import com.vobi.team.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * @author Zathura Code Generator http://zathuracode.org/
 * www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("VtSprintLogic")
public class VtSprintLogic implements IVtSprintLogic {
	private static final Logger log = LoggerFactory.getLogger(VtSprintLogic.class);

	/**
	 * DAO injected by Spring that manages VtSprint entities
	 *
	 */
	@Autowired
	private IVtSprintDAO vtSprintDAO;

	/**
	 * DAO injected by Spring that manages VtArtefacto entities
	 *
	 */
	@Autowired
	private IVtArtefactoDAO vtArtefactoDAO;

	/**
	 * Logic injected by Spring that manages VtPilaProducto entities
	 *
	 */
	@Autowired
	IVtPilaProductoLogic logicVtPilaProducto1;

	@Transactional(readOnly = true)
	public List<VtSprint> getVtSprint() throws Exception {
		log.debug("finding all VtSprint instances");

		List<VtSprint> list = new ArrayList<VtSprint>();

		try {
			list = vtSprintDAO.findAll();
		} catch (Exception e) {
			log.error("finding all VtSprint failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL +
					"VtSprint");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveVtSprint(VtSprint entity) throws Exception {
		log.debug("saving VtSprint instance");

		try {
			if (entity.getVtPilaProducto() == null) {
				throw new ZMessManager().new ForeignException("vtPilaProducto");
			}

			if (entity.getActivo() == null) {
				throw new ZMessManager().new EmptyFieldException("activo");
			}

			if ((entity.getActivo() != null) &&
					(Utilities.checkWordAndCheckWithlength(entity.getActivo(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException("activo");
			}

			if (entity.getFechaCreacion() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"fechaCreacion");
			}

			if (entity.getNombre() == null) {
				throw new ZMessManager().new EmptyFieldException("nombre");
			}

			if ((entity.getNombre() != null) &&
					(Utilities.checkWordAndCheckWithlength(entity.getNombre(),
							255) == false)) {
				throw new ZMessManager().new NotValidFormatException("nombre");
			}

			if ((entity.getObjetivo() != null) &&
					(Utilities.checkWordAndCheckWithlength(
							entity.getObjetivo(), 255) == false)) {
				throw new ZMessManager().new NotValidFormatException("objetivo");
			}

			//            if (entity.getSpriCodigo() == null) {
			//                throw new ZMessManager().new EmptyFieldException("spriCodigo");
			//            }

			if (entity.getUsuCreador() == null) {
				throw new ZMessManager().new EmptyFieldException("usuCreador");
			}

			if (entity.getVtPilaProducto().getPilaCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"pilaCodigo_VtPilaProducto");
			}

			//            if (getVtSprint(entity.getSpriCodigo()) != null) {
			//                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
			//            }
			
			
			Date fechaActual = new Date();
			
			if (entity.getFechaInicio().before(fechaActual) || entity.getFechaFin().before(fechaActual)){
				throw new Exception("Fechas Incorrectas");
			}

			vtSprintDAO.save(entity);

			log.debug("save VtSprint successful");
		} catch (Exception e) {
			log.error("save VtSprint failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteVtSprint(VtSprint entity) throws Exception {
		log.debug("deleting VtSprint instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("VtSprint");
		}

		if (entity.getSpriCodigo() == null) {
			throw new ZMessManager().new EmptyFieldException("spriCodigo");
		}

		List<VtArtefacto> vtArtefactos = null;

		try {
			vtArtefactos = vtArtefactoDAO.findByProperty("vtSprint.spriCodigo",
					entity.getSpriCodigo());

			if (Utilities.validationsList(vtArtefactos) == true) {
				throw new ZMessManager().new DeletingException("vtArtefactos");
			}

			vtSprintDAO.delete(entity);

			log.debug("delete VtSprint successful");
		} catch (Exception e) {
			log.error("delete VtSprint failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateVtSprint(VtSprint entity) throws Exception {
		log.debug("updating VtSprint instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("VtSprint");
			}

			if (entity.getVtPilaProducto() == null) {
				throw new ZMessManager().new ForeignException("vtPilaProducto");
			}

			if (entity.getActivo() == null) {
				throw new ZMessManager().new EmptyFieldException("activo");
			}

			if ((entity.getActivo() != null) &&
					(Utilities.checkWordAndCheckWithlength(entity.getActivo(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException("activo");
			}

			if (entity.getFechaCreacion() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"fechaCreacion");
			}

			if (entity.getNombre() == null) {
				throw new ZMessManager().new EmptyFieldException("nombre");
			}

			if ((entity.getNombre() != null) &&
					(Utilities.checkWordAndCheckWithlength(entity.getNombre(),
							255) == false)) {
				throw new ZMessManager().new NotValidFormatException("nombre");
			}

			if ((entity.getObjetivo() != null) &&
					(Utilities.checkWordAndCheckWithlength(
							entity.getObjetivo(), 255) == false)) {
				throw new ZMessManager().new NotValidFormatException("objetivo");
			}

			if (entity.getSpriCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException("spriCodigo");
			}

			if (entity.getUsuCreador() == null) {
				throw new ZMessManager().new EmptyFieldException("usuCreador");
			}

			if (entity.getVtPilaProducto().getPilaCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"pilaCodigo_VtPilaProducto");
			}

			vtSprintDAO.update(entity);

			log.debug("update VtSprint successful");
		} catch (Exception e) {
			log.error("update VtSprint failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<VtSprintDTO> getDataVtSprint() throws Exception {
		try {
			List<VtSprint> vtSprint = vtSprintDAO.findAll();

			List<VtSprintDTO> vtSprintDTO = new ArrayList<VtSprintDTO>();

			for (VtSprint vtSprintTmp : vtSprint) {
				VtSprintDTO vtSprintDTO2 = new VtSprintDTO();

				vtSprintDTO2.setSpriCodigo(vtSprintTmp.getSpriCodigo());
				vtSprintDTO2.setActivo((vtSprintTmp.getActivo() != null)
						? vtSprintTmp.getActivo() : null);
				vtSprintDTO2.setFechaCreacion(vtSprintTmp.getFechaCreacion());
				vtSprintDTO2.setFechaFin(vtSprintTmp.getFechaFin());
				vtSprintDTO2.setFechaInicio(vtSprintTmp.getFechaInicio());
				vtSprintDTO2.setFechaModificacion(vtSprintTmp.getFechaModificacion());
				vtSprintDTO2.setNombre((vtSprintTmp.getNombre() != null)
						? vtSprintTmp.getNombre() : null);
				vtSprintDTO2.setObjetivo((vtSprintTmp.getObjetivo() != null)
						? vtSprintTmp.getObjetivo() : null);
				vtSprintDTO2.setUsuCreador((vtSprintTmp.getUsuCreador() != null)
						? vtSprintTmp.getUsuCreador() : null);
				vtSprintDTO2.setUsuModificador((vtSprintTmp.getUsuModificador() != null)
						? vtSprintTmp.getUsuModificador() : null);
				vtSprintDTO2.setPilaCodigo_VtPilaProducto((vtSprintTmp.getVtPilaProducto()
						.getPilaCodigo() != null)
						? vtSprintTmp.getVtPilaProducto().getPilaCodigo() : null);
				vtSprintDTO.add(vtSprintDTO2);
			}

			return vtSprintDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public VtSprint getVtSprint(Long spriCodigo) throws Exception {
		log.debug("getting VtSprint instance");

		VtSprint entity = null;

		try {
			entity = vtSprintDAO.findById(spriCodigo);
		} catch (Exception e) {
			log.error("get VtSprint failed", e);
			throw new ZMessManager().new FindingException("VtSprint");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<VtSprint> findPageVtSprint(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception {
		List<VtSprint> entity = null;

		try {
			entity = vtSprintDAO.findPage(sortColumnName, sortAscending,
					startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("VtSprint Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberVtSprint() throws Exception {
		Long entity = null;

		try {
			entity = vtSprintDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("VtSprint Count");
		} finally {
		}

		return entity;
	}

	/**
	 *
	 * @param varibles
	 *            este arreglo debera tener:
	 *
	 * [0] = String variable = (String) varibles[i]; representa como se llama la
	 * variable en el pojo
	 *
	 * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
	 * valor necesita o no ''(comillas simples)usado para campos de tipo string
	 *
	 * [2] = Object value = varibles[i + 2]; representa el valor que se va a
	 * buscar en la BD
	 *
	 * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
	 * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
	 * en este campo iria el tipo de comparador que quiero si es = o <>
	 *
	 * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
	 * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
	 * que se le ingresen en los otros 4
	 *
	 *
	 * @param variablesBetween
	 *
	 * la diferencia son estas dos posiciones
	 *
	 * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
	 * a ser buscada en un rango
	 *
	 * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
	 *
	 * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
	 * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
	 *
	 * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
	 * ejemplo: a comparator1 1 and a < 5
	 *
	 * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
	 * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
	 * 5) *
	 * @param variablesBetweenDates(en
	 *            este caso solo para mysql)
	 *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
	 *            una fecha
	 *
	 * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
	 * dates)
	 *
	 * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
	 * dates)
	 *
	 * esto hace un between entre las dos fechas.
	 *
	 * @return lista con los objetos que se necesiten
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<VtSprint> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception {
		List<VtSprint> list = new ArrayList<VtSprint>();
		String where = new String();
		String tempWhere = new String();

		if (variables != null) {
			for (int i = 0; i < variables.length; i++) {
				if ((variables[i] != null) && (variables[i + 1] != null) &&
						(variables[i + 2] != null) &&
						(variables[i + 3] != null)) {
					String variable = (String) variables[i];
					Boolean booVariable = (Boolean) variables[i + 1];
					Object value = variables[i + 2];
					String comparator = (String) variables[i + 3];

					if (booVariable.booleanValue()) {
						tempWhere = (tempWhere.length() == 0)
								? ("(model." + variable + " " + comparator + " \'" +
										value + "\' )")
										: (tempWhere + " AND (model." + variable + " " +
												comparator + " \'" + value + "\' )");
					} else {
						tempWhere = (tempWhere.length() == 0)
								? ("(model." + variable + " " + comparator + " " +
										value + " )")
										: (tempWhere + " AND (model." + variable + " " +
												comparator + " " + value + " )");
					}
				}

				i = i + 3;
			}
		}

		if (variablesBetween != null) {
			for (int j = 0; j < variablesBetween.length; j++) {
				if ((variablesBetween[j] != null) &&
						(variablesBetween[j + 1] != null) &&
						(variablesBetween[j + 2] != null) &&
						(variablesBetween[j + 3] != null) &&
						(variablesBetween[j + 4] != null)) {
					String variable = (String) variablesBetween[j];
					Object value = variablesBetween[j + 1];
					Object value2 = variablesBetween[j + 2];
					String comparator1 = (String) variablesBetween[j + 3];
					String comparator2 = (String) variablesBetween[j + 4];
					tempWhere = (tempWhere.length() == 0)
							? ("(" + value + " " + comparator1 + " " + variable +
									" and " + variable + " " + comparator2 + " " + value2 +
									" )")
									: (tempWhere + " AND (" + value + " " + comparator1 +
											" " + variable + " and " + variable + " " +
											comparator2 + " " + value2 + " )");
				}

				j = j + 4;
			}
		}

		if (variablesBetweenDates != null) {
			for (int k = 0; k < variablesBetweenDates.length; k++) {
				if ((variablesBetweenDates[k] != null) &&
						(variablesBetweenDates[k + 1] != null) &&
						(variablesBetweenDates[k + 2] != null)) {
					String variable = (String) variablesBetweenDates[k];
					Object object1 = variablesBetweenDates[k + 1];
					Object object2 = variablesBetweenDates[k + 2];
					String value = null;
					String value2 = null;

					try {
						Date date1 = (Date) object1;
						Date date2 = (Date) object2;
						value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
						value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
					} catch (Exception e) {
						list = null;
						throw e;
					}

					tempWhere = (tempWhere.length() == 0)
							? ("(model." + variable + " between \'" + value +
									"\' and \'" + value2 + "\')")
									: (tempWhere + " AND (model." + variable +
											" between \'" + value + "\' and \'" + value2 + "\')");
				}

				k = k + 2;
			}
		}

		if (tempWhere.length() == 0) {
			where = null;
		} else {
			where = "(" + tempWhere + ")";
		}

		try {
			list = vtSprintDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = true)
	public List<VtSprint> findSprintByBacklog(VtPilaProducto vtBacklog) 
			throws Exception{
		log.info("Entro al metodo para buscar los sprints");

		List<VtSprint> vtSprints = new ArrayList<>();

		try {
			Object[] variables = {"vtPilaProducto.pilaCodigo", false, vtBacklog.getPilaCodigo(), "="};

			vtSprints = findByCriteria(variables, null, null);

		} catch (Exception e) {
			log.info(e.getMessage(), e);
			throw new Exception("No se encontro la sprint por ese Id");
		}

		return (vtSprints != null && !vtSprints.isEmpty()
				? vtSprints : null);
	}



}
