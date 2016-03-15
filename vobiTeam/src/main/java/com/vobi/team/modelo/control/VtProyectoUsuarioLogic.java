package com.vobi.team.modelo.control;

import com.vobi.team.dataaccess.dao.*;
import com.vobi.team.exceptions.*;
import com.vobi.team.modelo.*;
import com.vobi.team.modelo.dto.VtProyectoUsuarioDTO;
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
@Service("VtProyectoUsuarioLogic")
public class VtProyectoUsuarioLogic implements IVtProyectoUsuarioLogic {
    private static final Logger log = LoggerFactory.getLogger(VtProyectoUsuarioLogic.class);

    /**
     * DAO injected by Spring that manages VtProyectoUsuario entities
     *
     */
    @Autowired
    private IVtProyectoUsuarioDAO vtProyectoUsuarioDAO;

    /**
    * Logic injected by Spring that manages VtProyecto entities
    *
    */
    @Autowired
    IVtProyectoLogic logicVtProyecto1;

    /**
    * Logic injected by Spring that manages VtUsuario entities
    *
    */
    @Autowired
    IVtUsuarioLogic logicVtUsuario2;

    @Transactional(readOnly = true)
    public List<VtProyectoUsuario> getVtProyectoUsuario()
        throws Exception {
        log.debug("finding all VtProyectoUsuario instances");

        List<VtProyectoUsuario> list = new ArrayList<VtProyectoUsuario>();

        try {
            list = vtProyectoUsuarioDAO.findAll();
        } catch (Exception e) {
            log.error("finding all VtProyectoUsuario failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "VtProyectoUsuario");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveVtProyectoUsuario(VtProyectoUsuario entity)
        throws Exception {
        log.debug("saving VtProyectoUsuario instance");

        try {
            if (entity.getVtProyecto() == null) {
                throw new ZMessManager().new ForeignException("vtProyecto");
            }

            if (entity.getVtUsuario() == null) {
                throw new ZMessManager().new ForeignException("vtUsuario");
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

//            if (entity.getPrusCodigo() == null) {
//                throw new ZMessManager().new EmptyFieldException("prusCodigo");
//            }

            if (entity.getVtProyecto().getProyCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "proyCodigo_VtProyecto");
            }

            if (entity.getVtUsuario().getUsuaCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuaCodigo_VtUsuario");
            }

//            if (getVtProyectoUsuario(entity.getPrusCodigo()) != null) {
//                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
//            }
            
            List<VtProyectoUsuario> losProyectosUsuarios = findProyectoUsuarioPorProyecto(entity.getVtProyecto());
            
            if(losProyectosUsuarios != null){
            	for (VtProyectoUsuario vtProyectoUsuario : losProyectosUsuarios) {
                	if(entity.getVtUsuario().getUsuaCodigo().equals(vtProyectoUsuario.getVtUsuario().getUsuaCodigo())){
                		throw new Exception("El usuario ya fue asignado al proyecto ");
        			}
    			} 
            }
            
             

            vtProyectoUsuarioDAO.save(entity);

            log.debug("save VtProyectoUsuario successful");
        } catch (Exception e) {
            log.error("save VtProyectoUsuario failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteVtProyectoUsuario(VtProyectoUsuario entity)
        throws Exception {
        log.debug("deleting VtProyectoUsuario instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "VtProyectoUsuario");
        }

        if (entity.getPrusCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("prusCodigo");
        }

        try {
        	//No borra, se actualiza
        	entity.setActivo("N");
            vtProyectoUsuarioDAO.update(entity);

            log.debug("delete VtProyectoUsuario successful");
        } catch (Exception e) {
            log.error("delete VtProyectoUsuario failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateVtProyectoUsuario(VtProyectoUsuario entity)
        throws Exception {
        log.debug("updating VtProyectoUsuario instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "VtProyectoUsuario");
            }

            if (entity.getVtProyecto() == null) {
                throw new ZMessManager().new ForeignException("vtProyecto");
            }

            if (entity.getVtUsuario() == null) {
                throw new ZMessManager().new ForeignException("vtUsuario");
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

            if (entity.getPrusCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("prusCodigo");
            }

            if (entity.getVtProyecto().getProyCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "proyCodigo_VtProyecto");
            }

            if (entity.getVtUsuario().getUsuaCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuaCodigo_VtUsuario");
            }

            vtProyectoUsuarioDAO.update(entity);

            log.debug("update VtProyectoUsuario successful");
        } catch (Exception e) {
            log.error("update VtProyectoUsuario failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<VtProyectoUsuarioDTO> getDataVtProyectoUsuario()
        throws Exception {
        try {
            List<VtProyectoUsuario> vtProyectoUsuario = vtProyectoUsuarioDAO.findAll();

            List<VtProyectoUsuarioDTO> vtProyectoUsuarioDTO = new ArrayList<VtProyectoUsuarioDTO>();

            for (VtProyectoUsuario vtProyectoUsuarioTmp : vtProyectoUsuario) {
                VtProyectoUsuarioDTO vtProyectoUsuarioDTO2 = new VtProyectoUsuarioDTO();

                vtProyectoUsuarioDTO2.setPrusCodigo(vtProyectoUsuarioTmp.getPrusCodigo());
                vtProyectoUsuarioDTO2.setActivo((vtProyectoUsuarioTmp.getActivo() != null)
                    ? vtProyectoUsuarioTmp.getActivo() : null);
                vtProyectoUsuarioDTO2.setFechaCreacion(vtProyectoUsuarioTmp.getFechaCreacion());
                vtProyectoUsuarioDTO2.setFechaModificacion(vtProyectoUsuarioTmp.getFechaModificacion());
                vtProyectoUsuarioDTO2.setUsuCreador((vtProyectoUsuarioTmp.getUsuCreador() != null)
                    ? vtProyectoUsuarioTmp.getUsuCreador() : null);
                vtProyectoUsuarioDTO2.setUsuModificador((vtProyectoUsuarioTmp.getUsuModificador() != null)
                    ? vtProyectoUsuarioTmp.getUsuModificador() : null);
                vtProyectoUsuarioDTO2.setProyCodigo_VtProyecto((vtProyectoUsuarioTmp.getVtProyecto()
                                                                                    .getProyCodigo() != null)
                    ? vtProyectoUsuarioTmp.getVtProyecto().getProyCodigo() : null);
                vtProyectoUsuarioDTO2.setUsuaCodigo_VtUsuario((vtProyectoUsuarioTmp.getVtUsuario()
                                                                                   .getUsuaCodigo() != null)
                    ? vtProyectoUsuarioTmp.getVtUsuario().getUsuaCodigo() : null);
                vtProyectoUsuarioDTO.add(vtProyectoUsuarioDTO2);
            }

            return vtProyectoUsuarioDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VtProyectoUsuario getVtProyectoUsuario(Long prusCodigo)
        throws Exception {
        log.debug("getting VtProyectoUsuario instance");

        VtProyectoUsuario entity = null;

        try {
            entity = vtProyectoUsuarioDAO.findById(prusCodigo);
        } catch (Exception e) {
            log.error("get VtProyectoUsuario failed", e);
            throw new ZMessManager().new FindingException("VtProyectoUsuario");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<VtProyectoUsuario> findPageVtProyectoUsuario(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<VtProyectoUsuario> entity = null;

        try {
            entity = vtProyectoUsuarioDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "VtProyectoUsuario Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberVtProyectoUsuario() throws Exception {
        Long entity = null;

        try {
            entity = vtProyectoUsuarioDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "VtProyectoUsuario Count");
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
    public List<VtProyectoUsuario> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<VtProyectoUsuario> list = new ArrayList<VtProyectoUsuario>();
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
            list = vtProyectoUsuarioDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = true)
	public List<VtProyectoUsuario> findProyectoUsuarioPorUsuario(VtProyecto proyecto) throws Exception {
		
		List<VtProyectoUsuario> proyectosUsuario = new ArrayList<VtProyectoUsuario>();
		
		try {
			Object[] variables = {"vtProyecto.proyCodigo", false, proyecto.getProyCodigo(), "="};
			proyectosUsuario = findByCriteria(variables, null, null);
			
			
		} catch (Exception e) {
			log.info(e.getMessage(), e);
			throw new Exception("No se encontro proyectos_usuarios con ese ID de Proyecto");
		}
		
		return (proyectosUsuario != null && !proyectosUsuario.isEmpty()
    			? proyectosUsuario : null);
	}
    
    @Transactional(readOnly = true)
	public List<VtProyectoUsuario> findProyectoUsuarioPorProyecto(VtProyecto proyecto) throws Exception {
		
		List<VtProyectoUsuario> proyectosUsuario = new ArrayList<VtProyectoUsuario>();
		
		try {
			Object[] variables = {"vtProyecto.proyCodigo", false, proyecto.getProyCodigo(), "="};
			proyectosUsuario = findByCriteria(variables, null, null);
			
			
		} catch (Exception e) {
			log.info(e.getMessage(), e);
			throw new Exception("No se encontro proyectos_usuarios con ese ID de Proyecto");
		}
		
		return (proyectosUsuario != null && !proyectosUsuario.isEmpty()
    			? proyectosUsuario : null);
	}

	@Override
	 @Transactional(readOnly = true)
	public VtProyectoUsuario findProyectoUsuarioByProyectoAndUsuario(Long proyectoId, Long usuarioId) {
		return vtProyectoUsuarioDAO.findProyectoUsuarioByProyectoAndUsuario(proyectoId, usuarioId);
	}


    
    
    
}
