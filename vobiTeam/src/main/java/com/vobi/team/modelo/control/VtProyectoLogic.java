package com.vobi.team.modelo.control;

import com.vobi.team.dataaccess.dao.*;
import com.vobi.team.exceptions.*;
import com.vobi.team.modelo.*;
import com.vobi.team.modelo.dto.VtProyectoDTO;
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


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("VtProyectoLogic")
public class VtProyectoLogic implements IVtProyectoLogic {
    private static final Logger log = LoggerFactory.getLogger(VtProyectoLogic.class);

    /**
     * DAO injected by Spring that manages VtProyecto entities
     *
     */
    @Autowired
    private IVtProyectoDAO vtProyectoDAO;

    /**
    * DAO injected by Spring that manages VtPilaProducto entities
    *
    */
    @Autowired
    private IVtPilaProductoDAO vtPilaProductoDAO;

    /**
    * DAO injected by Spring that manages VtProyectoUsuario entities
    *
    */
    @Autowired
    private IVtProyectoUsuarioDAO vtProyectoUsuarioDAO;
    
    @Autowired
    private IVtPilaProductoLogic vtPilaProductoLogic;

    /**
    * Logic injected by Spring that manages VtEmpresa entities
    *
    */
    @Autowired
    IVtEmpresaLogic logicVtEmpresa1;

    @Transactional(readOnly = true)
    public List<VtProyecto> getVtProyecto() throws Exception {
        log.debug("finding all VtProyecto instances");

        List<VtProyecto> list = new ArrayList<VtProyecto>();

        try {
            list = vtProyectoDAO.findAll();
        } catch (Exception e) {
            log.error("finding all VtProyecto failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "VtProyecto");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveVtProyecto(VtProyecto entity) throws Exception {
        log.debug("saving VtProyecto instance");

        try {
            if (entity.getVtEmpresa() == null) {
                throw new ZMessManager().new ForeignException("vtEmpresa");
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

//            if (entity.getProyCodigo() == null) {
//                throw new ZMessManager().new EmptyFieldException("proyCodigo");
//            }

            if (entity.getPublico() == null) {
                throw new ZMessManager().new EmptyFieldException("publico");
            }

            if ((entity.getPublico() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getPublico(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException("publico");
            }

            if (entity.getUsuCreador() == null) {
                throw new ZMessManager().new EmptyFieldException("usuCreador");
            }

            if (entity.getVtEmpresa().getEmprCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "emprCodigo_VtEmpresa");
            }

//            if (getVtProyecto(entity.getProyCodigo()) != null) {
//                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
//            }

            VtPilaProducto vtPilaProducto = new VtPilaProducto();
            
            vtPilaProducto.setNombre(entity.getNombre());
            
            vtPilaProducto.setDescripcion(entity.getDescripcion());
            
            vtPilaProducto.setActivo(entity.getActivo());
            
            vtPilaProducto.setFechaCreacion(entity.getFechaCreacion());
            
            vtPilaProducto.setFechaModificacion(entity.getFechaModificacion());
            
        	vtPilaProducto.setUsuCreador(entity.getUsuCreador());
        	
        	vtPilaProducto.setUsuModificador(entity.getUsuModificador());
        	
        	vtPilaProducto.setVtProyecto(entity);
            
            vtProyectoDAO.save(entity);
            
            vtPilaProductoLogic.saveVtPilaProducto(vtPilaProducto);
            
            
            vtProyectoDAO.save(entity);
            
            log.debug("save VtProyecto successful");
        } catch (Exception e) {
            log.error("save VtProyecto failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteVtProyecto(VtProyecto entity) throws Exception {
        log.debug("deleting VtProyecto instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("VtProyecto");
        }

        if (entity.getProyCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("proyCodigo");
        }

        List<VtPilaProducto> vtPilaProductos = null;
        List<VtProyectoUsuario> vtProyectoUsuarios = null;

        try {
            vtPilaProductos = vtPilaProductoDAO.findByProperty("vtProyecto.proyCodigo",
                    entity.getProyCodigo());

            if (Utilities.validationsList(vtPilaProductos) == true) {
                throw new ZMessManager().new DeletingException(
                    "vtPilaProductos");
            }

            vtProyectoUsuarios = vtProyectoUsuarioDAO.findByProperty("vtProyecto.proyCodigo",
                    entity.getProyCodigo());

            if (Utilities.validationsList(vtProyectoUsuarios) == true) {
                throw new ZMessManager().new DeletingException(
                    "vtProyectoUsuarios");
            }

            vtProyectoDAO.delete(entity);

            log.debug("delete VtProyecto successful");
        } catch (Exception e) {
            log.error("delete VtProyecto failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateVtProyecto(VtProyecto entity) throws Exception {
        log.debug("updating VtProyecto instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("VtProyecto");
            }

            if (entity.getVtEmpresa() == null) {
                throw new ZMessManager().new ForeignException("vtEmpresa");
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

            if (entity.getProyCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("proyCodigo");
            }

            if (entity.getPublico() == null) {
                throw new ZMessManager().new EmptyFieldException("publico");
            }

            if ((entity.getPublico() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getPublico(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException("publico");
            }

            if (entity.getUsuCreador() == null) {
                throw new ZMessManager().new EmptyFieldException("usuCreador");
            }

            if (entity.getVtEmpresa().getEmprCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "emprCodigo_VtEmpresa");
            }
            
            log.info("Llego el proyecto= " + entity.getProyCodigo());
            
            List<VtPilaProducto> losBacklog = vtPilaProductoLogic.findBacklogByProyecto(entity);
            
            for (VtPilaProducto vtPilaProducto : losBacklog) {
				vtPilaProducto.setActivo(entity.getActivo());
				vtPilaProducto.setUsuModificador(entity.getUsuModificador());
				vtPilaProducto.setFechaModificacion(entity.getFechaModificacion());
				vtPilaProductoLogic.updateVtPilaProducto(vtPilaProducto);
			}
            
            vtProyectoDAO.update(entity);

            log.debug("update VtProyecto successful");
        } catch (Exception e) {
            log.error("update VtProyecto failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<VtProyectoDTO> getDataVtProyecto() throws Exception {
        try {
            List<VtProyecto> vtProyecto = vtProyectoDAO.findAll();

            List<VtProyectoDTO> vtProyectoDTO = new ArrayList<VtProyectoDTO>();

            for (VtProyecto vtProyectoTmp : vtProyecto) {
                VtProyectoDTO vtProyectoDTO2 = new VtProyectoDTO();

                vtProyectoDTO2.setProyCodigo(vtProyectoTmp.getProyCodigo());
                vtProyectoDTO2.setActivo((vtProyectoTmp.getActivo() != null)
                    ? vtProyectoTmp.getActivo() : null);
                vtProyectoDTO2.setDescripcion((vtProyectoTmp.getDescripcion() != null)
                    ? vtProyectoTmp.getDescripcion() : null);
                vtProyectoDTO2.setFechaCreacion(vtProyectoTmp.getFechaCreacion());
                vtProyectoDTO2.setFechaModificacion(vtProyectoTmp.getFechaModificacion());
                vtProyectoDTO2.setNombre((vtProyectoTmp.getNombre() != null)
                    ? vtProyectoTmp.getNombre() : null);
                vtProyectoDTO2.setPublico((vtProyectoTmp.getPublico() != null)
                    ? vtProyectoTmp.getPublico() : null);
                vtProyectoDTO2.setUsuCreador((vtProyectoTmp.getUsuCreador() != null)
                    ? vtProyectoTmp.getUsuCreador() : null);
                vtProyectoDTO2.setUsuModificador((vtProyectoTmp.getUsuModificador() != null)
                    ? vtProyectoTmp.getUsuModificador() : null);
                vtProyectoDTO2.setEmprCodigo_VtEmpresa((vtProyectoTmp.getVtEmpresa()
                                                                     .getEmprCodigo() != null)
                    ? vtProyectoTmp.getVtEmpresa().getEmprCodigo() : null);
                vtProyectoDTO.add(vtProyectoDTO2);
            }

            return vtProyectoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VtProyecto getVtProyecto(Long proyCodigo) throws Exception {
        log.debug("getting VtProyecto instance");

        VtProyecto entity = null;

        try {
            entity = vtProyectoDAO.findById(proyCodigo);
        } catch (Exception e) {
            log.error("get VtProyecto failed", e);
            throw new ZMessManager().new FindingException("VtProyecto");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<VtProyecto> findPageVtProyecto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<VtProyecto> entity = null;

        try {
            entity = vtProyectoDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("VtProyecto Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberVtProyecto() throws Exception {
        Long entity = null;

        try {
            entity = vtProyectoDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("VtProyecto Count");
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
    public List<VtProyecto> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<VtProyecto> list = new ArrayList<VtProyecto>();
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
            list = vtProyectoDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
    @Transactional(readOnly = true)
    public List<VtProyecto> findProyectsByEnterpriseIdentification
    	(VtEmpresa vtEmpresa) throws Exception {
    	
    	log.info("Ingreso al metodo findByEnterpriseIdentificacion");
    	
    	List<VtProyecto> vtProyectos = new ArrayList<VtProyecto>();
    	
    	try {
			Object[] variables = {"vtEmpresa.emprCodigo", true, vtEmpresa.getEmprCodigo(), "="};
			
			vtProyectos = findByCriteria(variables, null, null);

		} catch (Exception e) {
			log.info(e.getMessage(), e);
			throw new Exception("No se encontro la empresas en los proyectos por ese Id");
		}
    	
    	return (vtProyectos != null && !vtProyectos.isEmpty()
    			? vtProyectos : null);
    }
    
}
