package com.vobi.team.modelo.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vobi.team.dataaccess.dao.IVtUsuarioRolDAO;
import com.vobi.team.exceptions.ZMessManager;
import com.vobi.team.modelo.VtRol;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.VtUsuarioRol;
import com.vobi.team.modelo.dto.VtUsuarioRolDTO;
import com.vobi.team.utilities.Utilities;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("VtUsuarioRolLogic")
public class VtUsuarioRolLogic implements IVtUsuarioRolLogic {
    private static final Logger log = LoggerFactory.getLogger(VtUsuarioRolLogic.class);

    /**
     * DAO injected by Spring that manages VtUsuarioRol entities
     *
     */
    @Autowired
    private IVtUsuarioRolDAO vtUsuarioRolDAO;

    /**
    * Logic injected by Spring that manages VtRol entities
    *
    */
    @Autowired
    IVtRolLogic logicVtRol1;

    /**
    * Logic injected by Spring that manages VtUsuario entities
    *
    */
    @Autowired
    IVtUsuarioLogic logicVtUsuario2;

    @Transactional(readOnly = true)
    public List<VtUsuarioRol> getVtUsuarioRol() throws Exception {
        log.debug("finding all VtUsuarioRol instances");

        List<VtUsuarioRol> list = new ArrayList<VtUsuarioRol>();

        try {
            list = vtUsuarioRolDAO.findAll();
        } catch (Exception e) {
            log.error("finding all VtUsuarioRol failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "VtUsuarioRol");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveVtUsuarioRol(VtUsuarioRol entity) throws Exception {
        log.debug("saving VtUsuarioRol instance");

        try {
            if (entity.getVtRol() == null) {
                throw new ZMessManager().new ForeignException("vtRol");
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

//            if (entity.getUsroCodigo() == null) {
//                throw new ZMessManager().new EmptyFieldException("usroCodigo");
//            }

            if (entity.getUsuCreador() == null) {
                throw new ZMessManager().new EmptyFieldException("usuCreador");
            }

            if (entity.getVtRol().getRolCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "rolCodigo_VtRol");
            }

            if (entity.getVtUsuario().getUsuaCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuaCodigo_VtUsuario");
            }

//            if (getVtUsuarioRol(entity.getUsroCodigo()) != null) {
//                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
//            }

            vtUsuarioRolDAO.save(entity);

            log.debug("save VtUsuarioRol successful");
        } catch (Exception e) {
            log.error("save VtUsuarioRol failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteVtUsuarioRol(VtUsuarioRol entity)
        throws Exception {
        log.debug("deleting VtUsuarioRol instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("VtUsuarioRol");
        }

        if (entity.getUsroCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("usroCodigo");
        }

        try {
        	entity.setActivo("N");
        	
            vtUsuarioRolDAO.update(entity);

            log.debug("delete VtUsuarioRol successful");
        } catch (Exception e) {
            log.error("delete VtUsuarioRol failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateVtUsuarioRol(VtUsuarioRol entity)
        throws Exception {
        log.debug("updating VtUsuarioRol instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("VtUsuarioRol");
            }

            if (entity.getVtRol() == null) {
                throw new ZMessManager().new ForeignException("vtRol");
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

            if (entity.getUsroCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("usroCodigo");
            }

            if (entity.getUsuCreador() == null) {
                throw new ZMessManager().new EmptyFieldException("usuCreador");
            }

            if (entity.getVtRol().getRolCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "rolCodigo_VtRol");
            }

            if (entity.getVtUsuario().getUsuaCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuaCodigo_VtUsuario");
            }

            vtUsuarioRolDAO.update(entity);

            log.debug("update VtUsuarioRol successful");
        } catch (Exception e) {
            log.error("update VtUsuarioRol failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<VtUsuarioRolDTO> getDataVtUsuarioRol()
        throws Exception {
        try {
            List<VtUsuarioRol> vtUsuarioRol = vtUsuarioRolDAO.findAll();

            List<VtUsuarioRolDTO> vtUsuarioRolDTO = new ArrayList<VtUsuarioRolDTO>();

            for (VtUsuarioRol vtUsuarioRolTmp : vtUsuarioRol) {
                VtUsuarioRolDTO vtUsuarioRolDTO2 = new VtUsuarioRolDTO();

                vtUsuarioRolDTO2.setUsroCodigo(vtUsuarioRolTmp.getUsroCodigo());
                vtUsuarioRolDTO2.setActivo((vtUsuarioRolTmp.getActivo() != null)
                    ? vtUsuarioRolTmp.getActivo() : null);
                vtUsuarioRolDTO2.setFechaCreacion(vtUsuarioRolTmp.getFechaCreacion());
                vtUsuarioRolDTO2.setFechaModificacion(vtUsuarioRolTmp.getFechaModificacion());
                vtUsuarioRolDTO2.setUsuCreador((vtUsuarioRolTmp.getUsuCreador() != null)
                    ? vtUsuarioRolTmp.getUsuCreador() : null);
                vtUsuarioRolDTO2.setUsuModificador((vtUsuarioRolTmp.getUsuModificador() != null)
                    ? vtUsuarioRolTmp.getUsuModificador() : null);
                vtUsuarioRolDTO2.setRolCodigo_VtRol((vtUsuarioRolTmp.getVtRol()
                                                                    .getRolCodigo() != null)
                    ? vtUsuarioRolTmp.getVtRol().getRolCodigo() : null);
                vtUsuarioRolDTO2.setUsuaCodigo_VtUsuario((vtUsuarioRolTmp.getVtUsuario()
                                                                         .getUsuaCodigo() != null)
                    ? vtUsuarioRolTmp.getVtUsuario().getUsuaCodigo() : null);
                vtUsuarioRolDTO.add(vtUsuarioRolDTO2);
            }

            return vtUsuarioRolDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public VtUsuarioRol getVtUsuarioRol(Long usroCodigo)
        throws Exception {
        log.debug("getting VtUsuarioRol instance");

        VtUsuarioRol entity = null;

        try {
            entity = vtUsuarioRolDAO.findById(usroCodigo);
        } catch (Exception e) {
            log.error("get VtUsuarioRol failed", e);
            throw new ZMessManager().new FindingException("VtUsuarioRol");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<VtUsuarioRol> findPageVtUsuarioRol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<VtUsuarioRol> entity = null;

        try {
            entity = vtUsuarioRolDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("VtUsuarioRol Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberVtUsuarioRol() throws Exception {
        Long entity = null;

        try {
            entity = vtUsuarioRolDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("VtUsuarioRol Count");
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
    public List<VtUsuarioRol> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<VtUsuarioRol> list = new ArrayList<VtUsuarioRol>();
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
            list = vtUsuarioRolDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = true)
	public List<VtUsuarioRol> findUsuarioRolbyUsuario(VtUsuario usuario) throws Exception {
		
    	List<VtUsuarioRol> rolesUsuario = new ArrayList<VtUsuarioRol>();
    	
    	try {
    		Object[] variables = {"vtUsuario.usuaCodigo", false, usuario.getUsuaCodigo(), "="};
    		rolesUsuario = findByCriteria(variables, null, null);
    		
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Exception("No se encontro roles de ese Usuario");
		}	
    	
		return (rolesUsuario != null && !rolesUsuario.isEmpty()
    			? rolesUsuario : null);
	}

    @Transactional(readOnly = true)
	public VtUsuarioRol findUsuarioRolByUsuarioAndRol(Long usuarioCodigo, Long rolCodigo) {
		return vtUsuarioRolDAO.findUsuarioRolByUsuarioAndRol(usuarioCodigo, rolCodigo);
	}

    @Transactional(readOnly = true)
	public List<VtUsuarioRol> findUsuarioRolbyRol(VtRol rol) throws Exception {
    	List<VtUsuarioRol> usuariosRol = new ArrayList<VtUsuarioRol>();
    	
    	try {
    		Object[] variables = {"vtRol.rolCodigo", false, rol.getRolCodigo(), "="};
    		usuariosRol = findByCriteria(variables, null, null);
    		
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Exception("No se encontro Usuarios con ese Rol");
		}
    	
    	return (usuariosRol != null && !usuariosRol.isEmpty()
    			? usuariosRol : null);
	}
}
