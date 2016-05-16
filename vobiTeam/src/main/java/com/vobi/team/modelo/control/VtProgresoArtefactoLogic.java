package com.vobi.team.modelo.control;

import com.vobi.team.dataaccess.dao.*;
import com.vobi.team.exceptions.*;
import com.vobi.team.modelo.*;
import com.vobi.team.modelo.dto.VtProgresoArtefactoDTO;
import com.vobi.team.utilities.Utilities;

import hirondelle.date4j.DateTime;

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
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("VtProgresoArtefactoLogic")
public class VtProgresoArtefactoLogic implements IVtProgresoArtefactoLogic {
    private static final Logger log = LoggerFactory.getLogger(VtProgresoArtefactoLogic.class);

    /**
     * DAO injected by Spring that manages VtProgresoArtefacto entities
     *
     */
    @Autowired
    private IVtProgresoArtefactoDAO vtProgresoArtefactoDAO;

    /**
    * Logic injected by Spring that manages VtArtefacto entities
    *
    */
    @Autowired
    IVtArtefactoLogic logicVtArtefacto1;

    @Transactional(readOnly = true)
    public List<VtProgresoArtefacto> getVtProgresoArtefacto()
        throws Exception {
        log.debug("finding all VtProgresoArtefacto instances");

        List<VtProgresoArtefacto> list = new ArrayList<VtProgresoArtefacto>();

        try {
            list = vtProgresoArtefactoDAO.findAll();
        } catch (Exception e) {
            log.error("finding all VtProgresoArtefacto failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "VtProgresoArtefacto");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveVtProgresoArtefacto(VtProgresoArtefacto entity)
        throws Exception {
        log.debug("saving VtProgresoArtefacto instance");

        try {
            if (entity.getVtArtefacto() == null) {
                throw new ZMessManager().new ForeignException("vtArtefacto");
            }

            if (entity.getActivo() == null) {
                throw new ZMessManager().new EmptyFieldException("activo");
            }

            if ((entity.getActivo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getActivo(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException("activo");
            }

            if (entity.getDescripcion() == null) {
                throw new ZMessManager().new EmptyFieldException("descripcion");
            }

            if ((entity.getDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcion(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcion");
            }

            if (entity.getEsfuerzoReal() == null) {
                throw new ZMessManager().new EmptyFieldException("esfuerzoReal");
            }

            if (entity.getEsfuerzoRestante() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "esfuerzoRestante");
            }

            if (entity.getFechaCreacion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "fechaCreacion");
            }

//            if (entity.getProartCodigo() == null) {
//                throw new ZMessManager().new EmptyFieldException("proartCodigo");
//            }

            if (entity.getPuntos() == null) {
                throw new ZMessManager().new EmptyFieldException("puntos");
            }

            if (entity.getTiempoDedicado() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "tiempoDedicado");
            }

            if (entity.getUsuCreador() == null) {
                throw new ZMessManager().new EmptyFieldException("usuCreador");
            }

            if (entity.getVtArtefacto().getArteCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "arteCodigo_VtArtefacto");
            }

//            if (getVtProgresoArtefacto(entity.getProartCodigo()) != null) {
//                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
//            }

            vtProgresoArtefactoDAO.save(entity);

            log.debug("save VtProgresoArtefacto successful");
        } catch (Exception e) {
            log.error("save VtProgresoArtefacto failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteVtProgresoArtefacto(VtProgresoArtefacto entity)
        throws Exception {
        log.debug("deleting VtProgresoArtefacto instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "VtProgresoArtefacto");
        }

        if (entity.getProartCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("proartCodigo");
        }

        try {
            vtProgresoArtefactoDAO.delete(entity);

            log.debug("delete VtProgresoArtefacto successful");
        } catch (Exception e) {
            log.error("delete VtProgresoArtefacto failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateVtProgresoArtefacto(VtProgresoArtefacto entity)
        throws Exception {
        log.debug("updating VtProgresoArtefacto instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "VtProgresoArtefacto");
            }

            if (entity.getVtArtefacto() == null) {
                throw new ZMessManager().new ForeignException("vtArtefacto");
            }

            if (entity.getActivo() == null) {
                throw new ZMessManager().new EmptyFieldException("activo");
            }

            if ((entity.getActivo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getActivo(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException("activo");
            }

            if (entity.getDescripcion() == null) {
                throw new ZMessManager().new EmptyFieldException("descripcion");
            }

            if ((entity.getDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcion(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcion");
            }

            if (entity.getEsfuerzoReal() == null) {
                throw new ZMessManager().new EmptyFieldException("esfuerzoReal");
            }

            if (entity.getEsfuerzoRestante() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "esfuerzoRestante");
            }

            if (entity.getFechaCreacion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "fechaCreacion");
            }

            if (entity.getProartCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("proartCodigo");
            }

            if (entity.getPuntos() == null) {
                throw new ZMessManager().new EmptyFieldException("puntos");
            }

            if (entity.getTiempoDedicado() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "tiempoDedicado");
            }

            if (entity.getUsuCreador() == null) {
                throw new ZMessManager().new EmptyFieldException("usuCreador");
            }

            if (entity.getVtArtefacto().getArteCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "arteCodigo_VtArtefacto");
            }

            vtProgresoArtefactoDAO.update(entity);

            log.debug("update VtProgresoArtefacto successful");
        } catch (Exception e) {
            log.error("update VtProgresoArtefacto failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<VtProgresoArtefactoDTO> getDataVtProgresoArtefacto()
        throws Exception {
        try {
            List<VtProgresoArtefacto> vtProgresoArtefacto = vtProgresoArtefactoDAO.findAll();

            List<VtProgresoArtefactoDTO> vtProgresoArtefactoDTO = new ArrayList<VtProgresoArtefactoDTO>();

            for (VtProgresoArtefacto vtProgresoArtefactoTmp : vtProgresoArtefacto) {
                VtProgresoArtefactoDTO vtProgresoArtefactoDTO2 = new VtProgresoArtefactoDTO();

                vtProgresoArtefactoDTO2.setProartCodigo(vtProgresoArtefactoTmp.getProartCodigo());
                vtProgresoArtefactoDTO2.setActivo((vtProgresoArtefactoTmp.getActivo() != null)
                    ? vtProgresoArtefactoTmp.getActivo() : null);
                vtProgresoArtefactoDTO2.setDescripcion((vtProgresoArtefactoTmp.getDescripcion() != null)
                    ? vtProgresoArtefactoTmp.getDescripcion() : null);
                vtProgresoArtefactoDTO2.setEsfuerzoReal((vtProgresoArtefactoTmp.getEsfuerzoReal() != null)
                    ? vtProgresoArtefactoTmp.getEsfuerzoReal() : null);
                vtProgresoArtefactoDTO2.setEsfuerzoRestante((vtProgresoArtefactoTmp.getEsfuerzoRestante() != null)
                    ? vtProgresoArtefactoTmp.getEsfuerzoRestante() : null);
                vtProgresoArtefactoDTO2.setFechaCreacion(vtProgresoArtefactoTmp.getFechaCreacion());
                vtProgresoArtefactoDTO2.setFechaModificacion(vtProgresoArtefactoTmp.getFechaModificacion());
                vtProgresoArtefactoDTO2.setPuntos((vtProgresoArtefactoTmp.getPuntos() != null)
                    ? vtProgresoArtefactoTmp.getPuntos() : null);
                vtProgresoArtefactoDTO2.setTiempoDedicado((vtProgresoArtefactoTmp.getTiempoDedicado() != null)
                    ? vtProgresoArtefactoTmp.getTiempoDedicado() : null);
                vtProgresoArtefactoDTO2.setUsuCreador((vtProgresoArtefactoTmp.getUsuCreador() != null)
                    ? vtProgresoArtefactoTmp.getUsuCreador() : null);
                vtProgresoArtefactoDTO2.setUsuModificador((vtProgresoArtefactoTmp.getUsuModificador() != null)
                    ? vtProgresoArtefactoTmp.getUsuModificador() : null);
                vtProgresoArtefactoDTO2.setArteCodigo_VtArtefacto((vtProgresoArtefactoTmp.getVtArtefacto()
                                                                                         .getArteCodigo() != null)
                    ? vtProgresoArtefactoTmp.getVtArtefacto().getArteCodigo()
                    : null);
                vtProgresoArtefactoDTO.add(vtProgresoArtefactoDTO2);
            }

            return vtProgresoArtefactoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VtProgresoArtefacto getVtProgresoArtefacto(Long proartCodigo)
        throws Exception {
        log.debug("getting VtProgresoArtefacto instance");

        VtProgresoArtefacto entity = null;

        try {
            entity = vtProgresoArtefactoDAO.findById(proartCodigo);
        } catch (Exception e) {
            log.error("get VtProgresoArtefacto failed", e);
            throw new ZMessManager().new FindingException("VtProgresoArtefacto");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<VtProgresoArtefacto> findPageVtProgresoArtefacto(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<VtProgresoArtefacto> entity = null;

        try {
            entity = vtProgresoArtefactoDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "VtProgresoArtefacto Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberVtProgresoArtefacto() throws Exception {
        Long entity = null;

        try {
            entity = vtProgresoArtefactoDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "VtProgresoArtefacto Count");
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
    public List<VtProgresoArtefacto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<VtProgresoArtefacto> list = new ArrayList<VtProgresoArtefacto>();
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
            list = vtProgresoArtefactoDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
	@Transactional(readOnly=true)
	public List<VtProgresoArtefacto> findProgresoArtefactosPorArtefactos(VtArtefacto vtArtefacto) throws Exception{

		List<VtProgresoArtefacto> losProgresosArtefactos = new ArrayList<VtProgresoArtefacto>();

		try {
			Object[] variables = {"vtArtefacto.arteCodigo", false, vtArtefacto.getArteCodigo(),"="};

			losProgresosArtefactos = findByCriteria(variables, null, null);
		} catch (Exception e) {
			throw new Exception("No se encontro artefactos por el Id del sprint");
		}

		return (losProgresosArtefactos != null && !losProgresosArtefactos.isEmpty()
				? losProgresosArtefactos : losProgresosArtefactos);
	}

	@Transactional(readOnly=true)
	public Long sumatoriaTiempoDedicadoPorSprintFecha(Long spriCodigo, DateTime fecha) {
		
		Long suma = vtProgresoArtefactoDAO.sumatoriaTiempoDedicadoPorSprintFecha(spriCodigo, fecha.toString().trim());

		
		return (suma != null && suma != 0
				? suma : 0L);
	}
}
