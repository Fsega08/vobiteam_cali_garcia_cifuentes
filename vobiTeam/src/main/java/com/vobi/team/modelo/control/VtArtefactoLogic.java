package com.vobi.team.modelo.control;

import com.vobi.team.dataaccess.dao.*;
import com.vobi.team.exceptions.*;
import com.vobi.team.modelo.*;
import com.vobi.team.modelo.dto.VtArtefactoDTO;
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
@Service("VtArtefactoLogic")
public class VtArtefactoLogic implements IVtArtefactoLogic {
    private static final Logger log = LoggerFactory.getLogger(VtArtefactoLogic.class);

    /**
     * DAO injected by Spring that manages VtArtefacto entities
     *
     */
    @Autowired
    private IVtArtefactoDAO vtArtefactoDAO;

    /**
    * DAO injected by Spring that manages VtArchivo entities
    *
    */
    @Autowired
    private IVtArchivoDAO vtArchivoDAO;

    /**
    * DAO injected by Spring that manages VtHistoriaArtefacto entities
    *
    */
    @Autowired
    private IVtHistoriaArtefactoDAO vtHistoriaArtefactoDAO;

    /**
    * DAO injected by Spring that manages VtUsuarioArtefacto entities
    *
    */
    @Autowired
    private IVtUsuarioArtefactoDAO vtUsuarioArtefactoDAO;

    /**
    * Logic injected by Spring that manages VtEstado entities
    *
    */
    @Autowired
    IVtEstadoLogic logicVtEstado1;

    /**
    * Logic injected by Spring that manages VtPilaProducto entities
    *
    */
    @Autowired
    IVtPilaProductoLogic logicVtPilaProducto2;

    /**
    * Logic injected by Spring that manages VtPrioridad entities
    *
    */
    @Autowired
    IVtPrioridadLogic logicVtPrioridad3;

    /**
    * Logic injected by Spring that manages VtSprint entities
    *
    */
    @Autowired
    IVtSprintLogic logicVtSprint4;

    /**
    * Logic injected by Spring that manages VtTipoArtefacto entities
    *
    */
    @Autowired
    IVtTipoArtefactoLogic logicVtTipoArtefacto5;

    @Transactional(readOnly = true)
    public List<VtArtefacto> getVtArtefacto() throws Exception {
        log.debug("finding all VtArtefacto instances");

        List<VtArtefacto> list = new ArrayList<VtArtefacto>();

        try {
            list = vtArtefactoDAO.findAll();
        } catch (Exception e) {
            log.error("finding all VtArtefacto failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "VtArtefacto");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveVtArtefacto(VtArtefacto entity) throws Exception {
        log.debug("saving VtArtefacto instance");

        try {
            if (entity.getVtEstado() == null) {
                throw new ZMessManager().new ForeignException("vtEstado");
            }

            if (entity.getVtPilaProducto() == null) {
                throw new ZMessManager().new ForeignException("vtPilaProducto");
            }

            if (entity.getVtPrioridad() == null) {
                throw new ZMessManager().new ForeignException("vtPrioridad");
            }

            if (entity.getVtSprint() == null) {
                throw new ZMessManager().new ForeignException("vtSprint");
            }

            if (entity.getVtTipoArtefacto() == null) {
                throw new ZMessManager().new ForeignException("vtTipoArtefacto");
            }

            if (entity.getActivo() == null) {
                throw new ZMessManager().new EmptyFieldException("activo");
            }

            if ((entity.getActivo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getActivo(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException("activo");
            }

//            if (entity.getArteCodigo() == null) {
//                throw new ZMessManager().new EmptyFieldException("arteCodigo");
//            }

            if ((entity.getDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcion(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcion");
            }

            if (entity.getFechaCreacion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "fechaCreacion");
            }

            if ((entity.getOrigen() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getOrigen(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("origen");
            }

            if (entity.getTitulo() == null) {
                throw new ZMessManager().new EmptyFieldException("titulo");
            }

            if ((entity.getTitulo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getTitulo(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("titulo");
            }

            if (entity.getUsuCreador() == null) {
                throw new ZMessManager().new EmptyFieldException("usuCreador");
            }

            if (entity.getVtEstado().getEstaCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "estaCodigo_VtEstado");
            }

            if (entity.getVtPilaProducto().getPilaCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "pilaCodigo_VtPilaProducto");
            }

            if (entity.getVtPrioridad().getPrioCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "prioCodigo_VtPrioridad");
            }

            if (entity.getVtSprint().getSpriCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "spriCodigo_VtSprint");
            }

            if (entity.getVtTipoArtefacto().getTparCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "tparCodigo_VtTipoArtefacto");
            }

//            if (getVtArtefacto(entity.getArteCodigo()) != null) {
//                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
//            }

            vtArtefactoDAO.save(entity);

            log.debug("save VtArtefacto successful");
        } catch (Exception e) {
            log.error("save VtArtefacto failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteVtArtefacto(VtArtefacto entity) throws Exception {
        log.debug("deleting VtArtefacto instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("VtArtefacto");
        }

        if (entity.getArteCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("arteCodigo");
        }

        List<VtArchivo> vtArchivos = null;
        List<VtHistoriaArtefacto> vtHistoriaArtefactos = null;
        List<VtUsuarioArtefacto> vtUsuarioArtefactos = null;

        try {
            vtArchivos = vtArchivoDAO.findByProperty("vtArtefacto.arteCodigo",
                    entity.getArteCodigo());

            if (Utilities.validationsList(vtArchivos) == true) {
                throw new ZMessManager().new DeletingException("vtArchivos");
            }

            vtHistoriaArtefactos = vtHistoriaArtefactoDAO.findByProperty("vtArtefacto.arteCodigo",
                    entity.getArteCodigo());

            if (Utilities.validationsList(vtHistoriaArtefactos) == true) {
                throw new ZMessManager().new DeletingException(
                    "vtHistoriaArtefactos");
            }

            vtUsuarioArtefactos = vtUsuarioArtefactoDAO.findByProperty("vtArtefacto.arteCodigo",
                    entity.getArteCodigo());

            if (Utilities.validationsList(vtUsuarioArtefactos) == true) {
                throw new ZMessManager().new DeletingException(
                    "vtUsuarioArtefactos");
            }

            vtArtefactoDAO.delete(entity);

            log.debug("delete VtArtefacto successful");
        } catch (Exception e) {
            log.error("delete VtArtefacto failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateVtArtefacto(VtArtefacto entity) throws Exception {
        log.debug("updating VtArtefacto instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("VtArtefacto");
            }

            if (entity.getVtEstado() == null) {
                throw new ZMessManager().new ForeignException("vtEstado");
            }

            if (entity.getVtPilaProducto() == null) {
                throw new ZMessManager().new ForeignException("vtPilaProducto");
            }

            if (entity.getVtPrioridad() == null) {
                throw new ZMessManager().new ForeignException("vtPrioridad");
            }

            if (entity.getVtSprint() == null) {
                throw new ZMessManager().new ForeignException("vtSprint");
            }

            if (entity.getVtTipoArtefacto() == null) {
                throw new ZMessManager().new ForeignException("vtTipoArtefacto");
            }

            if (entity.getActivo() == null) {
                throw new ZMessManager().new EmptyFieldException("activo");
            }

            if ((entity.getActivo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getActivo(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException("activo");
            }

            if (entity.getArteCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("arteCodigo");
            }

            if ((entity.getDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcion(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcion");
            }

            if (entity.getFechaCreacion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "fechaCreacion");
            }

            if ((entity.getOrigen() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getOrigen(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("origen");
            }

            if (entity.getTitulo() == null) {
                throw new ZMessManager().new EmptyFieldException("titulo");
            }

            if ((entity.getTitulo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getTitulo(),
                        255) == false)) {
                throw new ZMessManager().new NotValidFormatException("titulo");
            }

            if (entity.getUsuCreador() == null) {
                throw new ZMessManager().new EmptyFieldException("usuCreador");
            }

            if (entity.getVtEstado().getEstaCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "estaCodigo_VtEstado");
            }

            if (entity.getVtPilaProducto().getPilaCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "pilaCodigo_VtPilaProducto");
            }

            if (entity.getVtPrioridad().getPrioCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "prioCodigo_VtPrioridad");
            }

            if (entity.getVtSprint().getSpriCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "spriCodigo_VtSprint");
            }

            if (entity.getVtTipoArtefacto().getTparCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "tparCodigo_VtTipoArtefacto");
            }

            vtArtefactoDAO.update(entity);

            log.debug("update VtArtefacto successful");
        } catch (Exception e) {
            log.error("update VtArtefacto failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<VtArtefactoDTO> getDataVtArtefacto() throws Exception {
        try {
            List<VtArtefacto> vtArtefacto = vtArtefactoDAO.findAll();

            List<VtArtefactoDTO> vtArtefactoDTO = new ArrayList<VtArtefactoDTO>();

            for (VtArtefacto vtArtefactoTmp : vtArtefacto) {
                VtArtefactoDTO vtArtefactoDTO2 = new VtArtefactoDTO();

                vtArtefactoDTO2.setArteCodigo(vtArtefactoTmp.getArteCodigo());
                vtArtefactoDTO2.setActivo((vtArtefactoTmp.getActivo() != null)
                    ? vtArtefactoTmp.getActivo() : null);
                vtArtefactoDTO2.setDescripcion((vtArtefactoTmp.getDescripcion() != null)
                    ? vtArtefactoTmp.getDescripcion() : null);
                vtArtefactoDTO2.setEsfuerzoEstimado((vtArtefactoTmp.getEsfuerzoEstimado() != null)
                    ? vtArtefactoTmp.getEsfuerzoEstimado() : null);
                vtArtefactoDTO2.setEsfuerzoReal((vtArtefactoTmp.getEsfuerzoReal() != null)
                    ? vtArtefactoTmp.getEsfuerzoReal() : null);
                vtArtefactoDTO2.setEsfuerzoRestante((vtArtefactoTmp.getEsfuerzoRestante() != null)
                    ? vtArtefactoTmp.getEsfuerzoRestante() : null);
                vtArtefactoDTO2.setFechaCreacion(vtArtefactoTmp.getFechaCreacion());
                vtArtefactoDTO2.setFechaModificacion(vtArtefactoTmp.getFechaModificacion());
                vtArtefactoDTO2.setOrigen((vtArtefactoTmp.getOrigen() != null)
                    ? vtArtefactoTmp.getOrigen() : null);
                vtArtefactoDTO2.setPuntos((vtArtefactoTmp.getPuntos() != null)
                    ? vtArtefactoTmp.getPuntos() : null);
                vtArtefactoDTO2.setTitulo((vtArtefactoTmp.getTitulo() != null)
                    ? vtArtefactoTmp.getTitulo() : null);
                vtArtefactoDTO2.setUsuCreador((vtArtefactoTmp.getUsuCreador() != null)
                    ? vtArtefactoTmp.getUsuCreador() : null);
                vtArtefactoDTO2.setUsuModificador((vtArtefactoTmp.getUsuModificador() != null)
                    ? vtArtefactoTmp.getUsuModificador() : null);
                vtArtefactoDTO2.setEstaCodigo_VtEstado((vtArtefactoTmp.getVtEstado()
                                                                      .getEstaCodigo() != null)
                    ? vtArtefactoTmp.getVtEstado().getEstaCodigo() : null);
                vtArtefactoDTO2.setPilaCodigo_VtPilaProducto((vtArtefactoTmp.getVtPilaProducto()
                                                                            .getPilaCodigo() != null)
                    ? vtArtefactoTmp.getVtPilaProducto().getPilaCodigo() : null);
                vtArtefactoDTO2.setPrioCodigo_VtPrioridad((vtArtefactoTmp.getVtPrioridad()
                                                                         .getPrioCodigo() != null)
                    ? vtArtefactoTmp.getVtPrioridad().getPrioCodigo() : null);
                vtArtefactoDTO2.setSpriCodigo_VtSprint((vtArtefactoTmp.getVtSprint()
                                                                      .getSpriCodigo() != null)
                    ? vtArtefactoTmp.getVtSprint().getSpriCodigo() : null);
                vtArtefactoDTO2.setTparCodigo_VtTipoArtefacto((vtArtefactoTmp.getVtTipoArtefacto()
                                                                             .getTparCodigo() != null)
                    ? vtArtefactoTmp.getVtTipoArtefacto().getTparCodigo() : null);
                vtArtefactoDTO.add(vtArtefactoDTO2);
            }

            return vtArtefactoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VtArtefacto getVtArtefacto(Long arteCodigo)
        throws Exception {
        log.debug("getting VtArtefacto instance");

        VtArtefacto entity = null;

        try {
            entity = vtArtefactoDAO.findById(arteCodigo);
        } catch (Exception e) {
            log.error("get VtArtefacto failed", e);
            throw new ZMessManager().new FindingException("VtArtefacto");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<VtArtefacto> findPageVtArtefacto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<VtArtefacto> entity = null;

        try {
            entity = vtArtefactoDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("VtArtefacto Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberVtArtefacto() throws Exception {
        Long entity = null;

        try {
            entity = vtArtefactoDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("VtArtefacto Count");
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
    public List<VtArtefacto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<VtArtefacto> list = new ArrayList<VtArtefacto>();
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
            list = vtArtefactoDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
