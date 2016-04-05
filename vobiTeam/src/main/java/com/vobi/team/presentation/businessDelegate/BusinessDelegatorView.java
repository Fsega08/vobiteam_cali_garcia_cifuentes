package com.vobi.team.presentation.businessDelegate;

import com.vobi.team.modelo.VtArchivo;
import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtEstado;
import com.vobi.team.modelo.VtHistoriaArtefacto;
import com.vobi.team.modelo.VtInteres;
import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtPrioridad;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtProyectoUsuario;
import com.vobi.team.modelo.VtRol;
import com.vobi.team.modelo.VtSprint;
import com.vobi.team.modelo.VtTipoArtefacto;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.VtUsuarioArtefacto;
import com.vobi.team.modelo.VtUsuarioRol;
import com.vobi.team.modelo.control.IVtArchivoLogic;
import com.vobi.team.modelo.control.IVtArtefactoLogic;
import com.vobi.team.modelo.control.IVtEmpresaLogic;
import com.vobi.team.modelo.control.IVtEstadoLogic;
import com.vobi.team.modelo.control.IVtHistoriaArtefactoLogic;
import com.vobi.team.modelo.control.IVtInteresLogic;
import com.vobi.team.modelo.control.IVtPilaProductoLogic;
import com.vobi.team.modelo.control.IVtPrioridadLogic;
import com.vobi.team.modelo.control.IVtProyectoLogic;
import com.vobi.team.modelo.control.IVtProyectoUsuarioLogic;
import com.vobi.team.modelo.control.IVtRolLogic;
import com.vobi.team.modelo.control.IVtSprintLogic;
import com.vobi.team.modelo.control.IVtTipoArtefactoLogic;
import com.vobi.team.modelo.control.IVtUsuarioArtefactoLogic;
import com.vobi.team.modelo.control.IVtUsuarioLogic;
import com.vobi.team.modelo.control.IVtUsuarioRolLogic;
import com.vobi.team.modelo.control.VtArchivoLogic;
import com.vobi.team.modelo.control.VtArtefactoLogic;
import com.vobi.team.modelo.control.VtEmpresaLogic;
import com.vobi.team.modelo.control.VtEstadoLogic;
import com.vobi.team.modelo.control.VtHistoriaArtefactoLogic;
import com.vobi.team.modelo.control.VtInteresLogic;
import com.vobi.team.modelo.control.VtPilaProductoLogic;
import com.vobi.team.modelo.control.VtPrioridadLogic;
import com.vobi.team.modelo.control.VtProyectoLogic;
import com.vobi.team.modelo.control.VtProyectoUsuarioLogic;
import com.vobi.team.modelo.control.VtRolLogic;
import com.vobi.team.modelo.control.VtSprintLogic;
import com.vobi.team.modelo.control.VtTipoArtefactoLogic;
import com.vobi.team.modelo.control.VtUsuarioArtefactoLogic;
import com.vobi.team.modelo.control.VtUsuarioLogic;
import com.vobi.team.modelo.control.VtUsuarioRolLogic;
import com.vobi.team.modelo.dto.VtArchivoDTO;
import com.vobi.team.modelo.dto.VtArtefactoDTO;
import com.vobi.team.modelo.dto.VtEmpresaDTO;
import com.vobi.team.modelo.dto.VtEstadoDTO;
import com.vobi.team.modelo.dto.VtHistoriaArtefactoDTO;
import com.vobi.team.modelo.dto.VtInteresDTO;
import com.vobi.team.modelo.dto.VtPilaProductoDTO;
import com.vobi.team.modelo.dto.VtPrioridadDTO;
import com.vobi.team.modelo.dto.VtProyectoDTO;
import com.vobi.team.modelo.dto.VtProyectoUsuarioDTO;
import com.vobi.team.modelo.dto.VtRolDTO;
import com.vobi.team.modelo.dto.VtSprintDTO;
import com.vobi.team.modelo.dto.VtTipoArtefactoDTO;
import com.vobi.team.modelo.dto.VtUsuarioArtefactoDTO;
import com.vobi.team.modelo.dto.VtUsuarioDTO;
import com.vobi.team.modelo.dto.VtUsuarioRolDTO;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.sql.*;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* Use a Business Delegate to reduce coupling between presentation-tier clients and business services.
* The Business Delegate hides the underlying implementation details of the business service, such as lookup and access details of the EJB architecture.
*
* The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides,
* the implementation of the business services. Using a Business Delegate reduces the coupling between presentation-tier clients and
* the system's business services. Depending on the implementation strategy, the Business Delegate may shield clients from possible
* volatility in the implementation of the business service API. Potentially, this reduces the number of changes that must be made to the
* presentation-tier client code when the business service API or its underlying implementation changes.
*
* However, interface methods in the Business Delegate may still require modification if the underlying business service API changes.
* Admittedly, though, it is more likely that changes will be made to the business service rather than to the Business Delegate.
*
* Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront work in return
* for future gains. However, using this pattern or its strategies results in only a small amount of additional upfront work and provides
* considerable benefits. The main benefit is hiding the details of the underlying service. For example, the client can become transparent
* to naming and lookup services. The Business Delegate also handles the exceptions from the business services, such as java.rmi.Remote
* exceptions, Java Messages Service (JMS) exceptions and so on. The Business Delegate may intercept such service level exceptions and
* generate application level exceptions instead. Application level exceptions are easier to handle by the clients, and may be user friendly.
* The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a service failure without
* exposing the client to the problem until it is determined that the problem is not resolvable. These gains present a compelling reason to
* use the pattern.
*
* Another benefit is that the delegate may cache results and references to remote business services. Caching can significantly improve performance,
* because it limits unnecessary and potentially costly round trips over the network.
*
* A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the underlying implementation
* details of the business service lookup code. The Lookup Service may be written as part of the Delegate, but we recommend that it be
* implemented as a separate component, as outlined in the Service Locator pattern (See "Service Locator" on page 368.)
*
* When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the two.
* This one-to-one relationship exists because logic that might have been encapsulated in a Business Delegate relating to its interaction
* with multiple business services (creating a one-to-many relationship) will often be factored back into a Session Facade.
*
* Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the presentation and the
* business tiers.
*
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("BusinessDelegatorView")
public class BusinessDelegatorView implements IBusinessDelegatorView {
    private static final Logger log = LoggerFactory.getLogger(BusinessDelegatorView.class);
    @Autowired
    private IVtArchivoLogic vtArchivoLogic;
    @Autowired
    private IVtArtefactoLogic vtArtefactoLogic;
    @Autowired
    private IVtEmpresaLogic vtEmpresaLogic;
    @Autowired
    private IVtEstadoLogic vtEstadoLogic;
    @Autowired
    private IVtHistoriaArtefactoLogic vtHistoriaArtefactoLogic;
    @Autowired
    private IVtInteresLogic vtInteresLogic;
    @Autowired
    private IVtPilaProductoLogic vtPilaProductoLogic;
    @Autowired
    private IVtPrioridadLogic vtPrioridadLogic;
    @Autowired
    private IVtProyectoLogic vtProyectoLogic;
    @Autowired
    private IVtProyectoUsuarioLogic vtProyectoUsuarioLogic;
    @Autowired
    private IVtRolLogic vtRolLogic;
    @Autowired
    private IVtSprintLogic vtSprintLogic;
    @Autowired
    private IVtTipoArtefactoLogic vtTipoArtefactoLogic;
    @Autowired
    private IVtUsuarioLogic vtUsuarioLogic;
    @Autowired
    private IVtUsuarioArtefactoLogic vtUsuarioArtefactoLogic;
    @Autowired
    private IVtUsuarioRolLogic vtUsuarioRolLogic;

    public List<VtArchivo> getVtArchivo() throws Exception {
        return vtArchivoLogic.getVtArchivo();
    }

    public void saveVtArchivo(VtArchivo entity) throws Exception {
        vtArchivoLogic.saveVtArchivo(entity);
    }

    public void deleteVtArchivo(VtArchivo entity) throws Exception {
        vtArchivoLogic.deleteVtArchivo(entity);
    }

    public void updateVtArchivo(VtArchivo entity) throws Exception {
        vtArchivoLogic.updateVtArchivo(entity);
    }

    public VtArchivo getVtArchivo(Long archCodigo) throws Exception {
        VtArchivo vtArchivo = null;

        try {
            vtArchivo = vtArchivoLogic.getVtArchivo(archCodigo);
        } catch (Exception e) {
            throw e;
        }

        return vtArchivo;
    }

    public List<VtArchivo> findByCriteriaInVtArchivo(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return vtArchivoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<VtArchivo> findPageVtArchivo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return vtArchivoLogic.findPageVtArchivo(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberVtArchivo() throws Exception {
        return vtArchivoLogic.findTotalNumberVtArchivo();
    }

    public List<VtArchivoDTO> getDataVtArchivo() throws Exception {
        return vtArchivoLogic.getDataVtArchivo();
    }

    public List<VtArtefacto> getVtArtefacto() throws Exception {
        return vtArtefactoLogic.getVtArtefacto();
    }

    public void saveVtArtefacto(VtArtefacto entity) throws Exception {
        vtArtefactoLogic.saveVtArtefacto(entity);
    }

    public void deleteVtArtefacto(VtArtefacto entity) throws Exception {
        vtArtefactoLogic.deleteVtArtefacto(entity);
    }

    public void updateVtArtefacto(VtArtefacto entity) throws Exception {
        vtArtefactoLogic.updateVtArtefacto(entity);
    }

    public VtArtefacto getVtArtefacto(Long arteCodigo)
        throws Exception {
        VtArtefacto vtArtefacto = null;

        try {
            vtArtefacto = vtArtefactoLogic.getVtArtefacto(arteCodigo);
        } catch (Exception e) {
            throw e;
        }

        return vtArtefacto;
    }

    public List<VtArtefacto> findByCriteriaInVtArtefacto(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return vtArtefactoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<VtArtefacto> findPageVtArtefacto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return vtArtefactoLogic.findPageVtArtefacto(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberVtArtefacto() throws Exception {
        return vtArtefactoLogic.findTotalNumberVtArtefacto();
    }

    public List<VtArtefactoDTO> getDataVtArtefacto() throws Exception {
        return vtArtefactoLogic.getDataVtArtefacto();
    }

    public List<VtEmpresa> getVtEmpresa() throws Exception {
        return vtEmpresaLogic.getVtEmpresa();
    }

    public void saveVtEmpresa(VtEmpresa entity) throws Exception {
        vtEmpresaLogic.saveVtEmpresa(entity);
    }

    public void deleteVtEmpresa(VtEmpresa entity) throws Exception {
        vtEmpresaLogic.deleteVtEmpresa(entity);
    }

    public void updateVtEmpresa(VtEmpresa entity) throws Exception {
        vtEmpresaLogic.updateVtEmpresa(entity);
    }
    
	@Override
	public VtEmpresa findByEnterpriseIdentificacion(String identificacion) throws Exception {
		return vtEmpresaLogic.findByEnterpriseIdentificacion(identificacion);
	}
    
    public VtEmpresa getVtEmpresa(Long emprCodigo) throws Exception {
        VtEmpresa vtEmpresa = null;

        try {
            vtEmpresa = vtEmpresaLogic.getVtEmpresa(emprCodigo);
        } catch (Exception e) {
            throw e;
        }

        return vtEmpresa;
    }

    public List<VtEmpresa> findByCriteriaInVtEmpresa(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return vtEmpresaLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<VtEmpresa> findPageVtEmpresa(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return vtEmpresaLogic.findPageVtEmpresa(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberVtEmpresa() throws Exception {
        return vtEmpresaLogic.findTotalNumberVtEmpresa();
    }

    public List<VtEmpresaDTO> getDataVtEmpresa() throws Exception {
        return vtEmpresaLogic.getDataVtEmpresa();
    }

    public List<VtEstado> getVtEstado() throws Exception {
        return vtEstadoLogic.getVtEstado();
    }

    public void saveVtEstado(VtEstado entity) throws Exception {
        vtEstadoLogic.saveVtEstado(entity);
    }

    public void deleteVtEstado(VtEstado entity) throws Exception {
        vtEstadoLogic.deleteVtEstado(entity);
    }

    public void updateVtEstado(VtEstado entity) throws Exception {
        vtEstadoLogic.updateVtEstado(entity);
    }

    public VtEstado getVtEstado(Long estaCodigo) throws Exception {
        VtEstado vtEstado = null;

        try {
            vtEstado = vtEstadoLogic.getVtEstado(estaCodigo);
        } catch (Exception e) {
            throw e;
        }

        return vtEstado;
    }

    public List<VtEstado> findByCriteriaInVtEstado(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return vtEstadoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<VtEstado> findPageVtEstado(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return vtEstadoLogic.findPageVtEstado(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberVtEstado() throws Exception {
        return vtEstadoLogic.findTotalNumberVtEstado();
    }

    public List<VtEstadoDTO> getDataVtEstado() throws Exception {
        return vtEstadoLogic.getDataVtEstado();
    }

    public List<VtHistoriaArtefacto> getVtHistoriaArtefacto()
        throws Exception {
        return vtHistoriaArtefactoLogic.getVtHistoriaArtefacto();
    }

    public void saveVtHistoriaArtefacto(VtHistoriaArtefacto entity)
        throws Exception {
        vtHistoriaArtefactoLogic.saveVtHistoriaArtefacto(entity);
    }

    public void deleteVtHistoriaArtefacto(VtHistoriaArtefacto entity)
        throws Exception {
        vtHistoriaArtefactoLogic.deleteVtHistoriaArtefacto(entity);
    }

    public void updateVtHistoriaArtefacto(VtHistoriaArtefacto entity)
        throws Exception {
        vtHistoriaArtefactoLogic.updateVtHistoriaArtefacto(entity);
    }

    public VtHistoriaArtefacto getVtHistoriaArtefacto(Long historiaCodigo)
        throws Exception {
        VtHistoriaArtefacto vtHistoriaArtefacto = null;

        try {
            vtHistoriaArtefacto = vtHistoriaArtefactoLogic.getVtHistoriaArtefacto(historiaCodigo);
        } catch (Exception e) {
            throw e;
        }

        return vtHistoriaArtefacto;
    }

    public List<VtHistoriaArtefacto> findByCriteriaInVtHistoriaArtefacto(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return vtHistoriaArtefactoLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<VtHistoriaArtefacto> findPageVtHistoriaArtefacto(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return vtHistoriaArtefactoLogic.findPageVtHistoriaArtefacto(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberVtHistoriaArtefacto() throws Exception {
        return vtHistoriaArtefactoLogic.findTotalNumberVtHistoriaArtefacto();
    }

    public List<VtHistoriaArtefactoDTO> getDataVtHistoriaArtefacto()
        throws Exception {
        return vtHistoriaArtefactoLogic.getDataVtHistoriaArtefacto();
    }

    public List<VtInteres> getVtInteres() throws Exception {
        return vtInteresLogic.getVtInteres();
    }

    public void saveVtInteres(VtInteres entity) throws Exception {
        vtInteresLogic.saveVtInteres(entity);
    }

    public void deleteVtInteres(VtInteres entity) throws Exception {
        vtInteresLogic.deleteVtInteres(entity);
    }

    public void updateVtInteres(VtInteres entity) throws Exception {
        vtInteresLogic.updateVtInteres(entity);
    }

    public VtInteres getVtInteres(Long inteCodigo) throws Exception {
        VtInteres vtInteres = null;

        try {
            vtInteres = vtInteresLogic.getVtInteres(inteCodigo);
        } catch (Exception e) {
            throw e;
        }

        return vtInteres;
    }

    public List<VtInteres> findByCriteriaInVtInteres(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return vtInteresLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<VtInteres> findPageVtInteres(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return vtInteresLogic.findPageVtInteres(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberVtInteres() throws Exception {
        return vtInteresLogic.findTotalNumberVtInteres();
    }

    public List<VtInteresDTO> getDataVtInteres() throws Exception {
        return vtInteresLogic.getDataVtInteres();
    }

    public List<VtPilaProducto> getVtPilaProducto() throws Exception {
        return vtPilaProductoLogic.getVtPilaProducto();
    }

    public void saveVtPilaProducto(VtPilaProducto entity)
        throws Exception {
        vtPilaProductoLogic.saveVtPilaProducto(entity);
    }

    public void deleteVtPilaProducto(VtPilaProducto entity)
        throws Exception {
        vtPilaProductoLogic.deleteVtPilaProducto(entity);
    }

    public void updateVtPilaProducto(VtPilaProducto entity)
        throws Exception {
        vtPilaProductoLogic.updateVtPilaProducto(entity);
    }

    public VtPilaProducto getVtPilaProducto(Long pilaCodigo)
        throws Exception {
        VtPilaProducto vtPilaProducto = null;

        try {
            vtPilaProducto = vtPilaProductoLogic.getVtPilaProducto(pilaCodigo);
        } catch (Exception e) {
            throw e;
        }

        return vtPilaProducto;
    }

    public List<VtPilaProducto> findByCriteriaInVtPilaProducto(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return vtPilaProductoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<VtPilaProducto> findPageVtPilaProducto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return vtPilaProductoLogic.findPageVtPilaProducto(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberVtPilaProducto() throws Exception {
        return vtPilaProductoLogic.findTotalNumberVtPilaProducto();
    }

    public List<VtPilaProductoDTO> getDataVtPilaProducto()
        throws Exception {
        return vtPilaProductoLogic.getDataVtPilaProducto();
    }

    public List<VtPrioridad> getVtPrioridad() throws Exception {
        return vtPrioridadLogic.getVtPrioridad();
    }

    public void saveVtPrioridad(VtPrioridad entity) throws Exception {
        vtPrioridadLogic.saveVtPrioridad(entity);
    }

    public void deleteVtPrioridad(VtPrioridad entity) throws Exception {
        vtPrioridadLogic.deleteVtPrioridad(entity);
    }

    public void updateVtPrioridad(VtPrioridad entity) throws Exception {
        vtPrioridadLogic.updateVtPrioridad(entity);
    }

    public VtPrioridad getVtPrioridad(Long prioCodigo)
        throws Exception {
        VtPrioridad vtPrioridad = null;

        try {
            vtPrioridad = vtPrioridadLogic.getVtPrioridad(prioCodigo);
        } catch (Exception e) {
            throw e;
        }

        return vtPrioridad;
    }

    public List<VtPrioridad> findByCriteriaInVtPrioridad(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return vtPrioridadLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<VtPrioridad> findPageVtPrioridad(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return vtPrioridadLogic.findPageVtPrioridad(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberVtPrioridad() throws Exception {
        return vtPrioridadLogic.findTotalNumberVtPrioridad();
    }

    public List<VtPrioridadDTO> getDataVtPrioridad() throws Exception {
        return vtPrioridadLogic.getDataVtPrioridad();
    }

    public List<VtProyecto> getVtProyecto() throws Exception {
        return vtProyectoLogic.getVtProyecto();
    }

    public void saveVtProyecto(VtProyecto entity) throws Exception {
        vtProyectoLogic.saveVtProyecto(entity);
    }

    public void deleteVtProyecto(VtProyecto entity) throws Exception {
        vtProyectoLogic.deleteVtProyecto(entity);
    }

    public void updateVtProyecto(VtProyecto entity) throws Exception {
        vtProyectoLogic.updateVtProyecto(entity);
    }

    public VtProyecto getVtProyecto(Long proyCodigo) throws Exception {
        VtProyecto vtProyecto = null;

        try {
            vtProyecto = vtProyectoLogic.getVtProyecto(proyCodigo);
        } catch (Exception e) {
            throw e;
        }

        return vtProyecto;
    }

    public List<VtProyecto> findByCriteriaInVtProyecto(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return vtProyectoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<VtProyecto> findPageVtProyecto(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return vtProyectoLogic.findPageVtProyecto(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberVtProyecto() throws Exception {
        return vtProyectoLogic.findTotalNumberVtProyecto();
    }

    public List<VtProyectoDTO> getDataVtProyecto() throws Exception {
        return vtProyectoLogic.getDataVtProyecto();
    }

    public List<VtProyectoUsuario> getVtProyectoUsuario()
        throws Exception {
        return vtProyectoUsuarioLogic.getVtProyectoUsuario();
    }

    public void saveVtProyectoUsuario(VtProyectoUsuario entity)
        throws Exception {
        vtProyectoUsuarioLogic.saveVtProyectoUsuario(entity);
    }

    public void deleteVtProyectoUsuario(VtProyectoUsuario entity)
        throws Exception {
        vtProyectoUsuarioLogic.deleteVtProyectoUsuario(entity);
    }

    public void updateVtProyectoUsuario(VtProyectoUsuario entity)
        throws Exception {
        vtProyectoUsuarioLogic.updateVtProyectoUsuario(entity);
    }

    public VtProyectoUsuario getVtProyectoUsuario(Long prusCodigo)
        throws Exception {
        VtProyectoUsuario vtProyectoUsuario = null;

        try {
            vtProyectoUsuario = vtProyectoUsuarioLogic.getVtProyectoUsuario(prusCodigo);
        } catch (Exception e) {
            throw e;
        }

        return vtProyectoUsuario;
    }

    public List<VtProyectoUsuario> findByCriteriaInVtProyectoUsuario(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return vtProyectoUsuarioLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<VtProyectoUsuario> findPageVtProyectoUsuario(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return vtProyectoUsuarioLogic.findPageVtProyectoUsuario(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberVtProyectoUsuario() throws Exception {
        return vtProyectoUsuarioLogic.findTotalNumberVtProyectoUsuario();
    }

    public List<VtProyectoUsuarioDTO> getDataVtProyectoUsuario()
        throws Exception {
        return vtProyectoUsuarioLogic.getDataVtProyectoUsuario();
    }

    public List<VtRol> getVtRol() throws Exception {
        return vtRolLogic.getVtRol();
    }

    public void saveVtRol(VtRol entity) throws Exception {
        vtRolLogic.saveVtRol(entity);
    }

    public void deleteVtRol(VtRol entity) throws Exception {
        vtRolLogic.deleteVtRol(entity);
    }

    public void updateVtRol(VtRol entity) throws Exception {
        vtRolLogic.updateVtRol(entity);
    }

    public VtRol getVtRol(Long rolCodigo) throws Exception {
        VtRol vtRol = null;

        try {
            vtRol = vtRolLogic.getVtRol(rolCodigo);
        } catch (Exception e) {
            throw e;
        }

        return vtRol;
    }

    public List<VtRol> findByCriteriaInVtRol(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return vtRolLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<VtRol> findPageVtRol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return vtRolLogic.findPageVtRol(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberVtRol() throws Exception {
        return vtRolLogic.findTotalNumberVtRol();
    }

    public List<VtRolDTO> getDataVtRol() throws Exception {
        return vtRolLogic.getDataVtRol();
    }

    public List<VtSprint> getVtSprint() throws Exception {
        return vtSprintLogic.getVtSprint();
    }

    public void saveVtSprint(VtSprint entity) throws Exception {
        vtSprintLogic.saveVtSprint(entity);
    }

    public void deleteVtSprint(VtSprint entity) throws Exception {
        vtSprintLogic.deleteVtSprint(entity);
    }

    public void updateVtSprint(VtSprint entity) throws Exception {
        vtSprintLogic.updateVtSprint(entity);
    }

    public VtSprint getVtSprint(Long spriCodigo) throws Exception {
        VtSprint vtSprint = null;

        try {
            vtSprint = vtSprintLogic.getVtSprint(spriCodigo);
        } catch (Exception e) {
            throw e;
        }

        return vtSprint;
    }

    public List<VtSprint> findByCriteriaInVtSprint(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return vtSprintLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<VtSprint> findPageVtSprint(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return vtSprintLogic.findPageVtSprint(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberVtSprint() throws Exception {
        return vtSprintLogic.findTotalNumberVtSprint();
    }

    public List<VtSprintDTO> getDataVtSprint() throws Exception {
        return vtSprintLogic.getDataVtSprint();
    }

    public List<VtTipoArtefacto> getVtTipoArtefacto() throws Exception {
        return vtTipoArtefactoLogic.getVtTipoArtefacto();
    }

    public void saveVtTipoArtefacto(VtTipoArtefacto entity)
        throws Exception {
        vtTipoArtefactoLogic.saveVtTipoArtefacto(entity);
    }

    public void deleteVtTipoArtefacto(VtTipoArtefacto entity)
        throws Exception {
        vtTipoArtefactoLogic.deleteVtTipoArtefacto(entity);
    }

    public void updateVtTipoArtefacto(VtTipoArtefacto entity)
        throws Exception {
        vtTipoArtefactoLogic.updateVtTipoArtefacto(entity);
    }

    public VtTipoArtefacto getVtTipoArtefacto(Long tparCodigo)
        throws Exception {
        VtTipoArtefacto vtTipoArtefacto = null;

        try {
            vtTipoArtefacto = vtTipoArtefactoLogic.getVtTipoArtefacto(tparCodigo);
        } catch (Exception e) {
            throw e;
        }

        return vtTipoArtefacto;
    }

    public List<VtTipoArtefacto> findByCriteriaInVtTipoArtefacto(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return vtTipoArtefactoLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<VtTipoArtefacto> findPageVtTipoArtefacto(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return vtTipoArtefactoLogic.findPageVtTipoArtefacto(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberVtTipoArtefacto() throws Exception {
        return vtTipoArtefactoLogic.findTotalNumberVtTipoArtefacto();
    }

    public List<VtTipoArtefactoDTO> getDataVtTipoArtefacto()
        throws Exception {
        return vtTipoArtefactoLogic.getDataVtTipoArtefacto();
    }

    public List<VtUsuario> getVtUsuario() throws Exception {
        return vtUsuarioLogic.getVtUsuario();
    }

    public void saveVtUsuario(VtUsuario entity) throws Exception {
        vtUsuarioLogic.saveVtUsuario(entity);
    }

    public void deleteVtUsuario(VtUsuario entity) throws Exception {
        vtUsuarioLogic.deleteVtUsuario(entity);
    }

    public void updateVtUsuario(VtUsuario entity) throws Exception {
        vtUsuarioLogic.updateVtUsuario(entity);
    }

    public VtUsuario getVtUsuario(Long usuaCodigo) throws Exception {
        VtUsuario vtUsuario = null;

        try {
            vtUsuario = vtUsuarioLogic.getVtUsuario(usuaCodigo);
        } catch (Exception e) {
            throw e;
        }

        return vtUsuario;
    }

    public List<VtUsuario> findByCriteriaInVtUsuario(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return vtUsuarioLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<VtUsuario> findPageVtUsuario(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return vtUsuarioLogic.findPageVtUsuario(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberVtUsuario() throws Exception {
        return vtUsuarioLogic.findTotalNumberVtUsuario();
    }

    public List<VtUsuarioDTO> getDataVtUsuario() throws Exception {
        return vtUsuarioLogic.getDataVtUsuario();
    }

    public List<VtUsuarioArtefacto> getVtUsuarioArtefacto()
        throws Exception {
        return vtUsuarioArtefactoLogic.getVtUsuarioArtefacto();
    }

    public void saveVtUsuarioArtefacto(VtUsuarioArtefacto entity)
        throws Exception {
        vtUsuarioArtefactoLogic.saveVtUsuarioArtefacto(entity);
    }

    public void deleteVtUsuarioArtefacto(VtUsuarioArtefacto entity)
        throws Exception {
        vtUsuarioArtefactoLogic.deleteVtUsuarioArtefacto(entity);
    }

    public void updateVtUsuarioArtefacto(VtUsuarioArtefacto entity)
        throws Exception {
        vtUsuarioArtefactoLogic.updateVtUsuarioArtefacto(entity);
    }

    public VtUsuarioArtefacto getVtUsuarioArtefacto(Long usuartCodigo)
        throws Exception {
        VtUsuarioArtefacto vtUsuarioArtefacto = null;

        try {
            vtUsuarioArtefacto = vtUsuarioArtefactoLogic.getVtUsuarioArtefacto(usuartCodigo);
        } catch (Exception e) {
            throw e;
        }

        return vtUsuarioArtefacto;
    }

    public List<VtUsuarioArtefacto> findByCriteriaInVtUsuarioArtefacto(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return vtUsuarioArtefactoLogic.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<VtUsuarioArtefacto> findPageVtUsuarioArtefacto(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return vtUsuarioArtefactoLogic.findPageVtUsuarioArtefacto(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberVtUsuarioArtefacto() throws Exception {
        return vtUsuarioArtefactoLogic.findTotalNumberVtUsuarioArtefacto();
    }

    public List<VtUsuarioArtefactoDTO> getDataVtUsuarioArtefacto()
        throws Exception {
        return vtUsuarioArtefactoLogic.getDataVtUsuarioArtefacto();
    }

    public List<VtUsuarioRol> getVtUsuarioRol() throws Exception {
        return vtUsuarioRolLogic.getVtUsuarioRol();
    }

    public void saveVtUsuarioRol(VtUsuarioRol entity) throws Exception {
        vtUsuarioRolLogic.saveVtUsuarioRol(entity);
    }

    public void deleteVtUsuarioRol(VtUsuarioRol entity)
        throws Exception {
        vtUsuarioRolLogic.deleteVtUsuarioRol(entity);
    }

    public void updateVtUsuarioRol(VtUsuarioRol entity)
        throws Exception {
        vtUsuarioRolLogic.updateVtUsuarioRol(entity);
    }

    public VtUsuarioRol getVtUsuarioRol(Long usroCodigo)
        throws Exception {
        VtUsuarioRol vtUsuarioRol = null;

        try {
            vtUsuarioRol = vtUsuarioRolLogic.getVtUsuarioRol(usroCodigo);
        } catch (Exception e) {
            throw e;
        }

        return vtUsuarioRol;
    }

    public List<VtUsuarioRol> findByCriteriaInVtUsuarioRol(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return vtUsuarioRolLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<VtUsuarioRol> findPageVtUsuarioRol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return vtUsuarioRolLogic.findPageVtUsuarioRol(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberVtUsuarioRol() throws Exception {
        return vtUsuarioRolLogic.findTotalNumberVtUsuarioRol();
    }

    public List<VtUsuarioRolDTO> getDataVtUsuarioRol()
        throws Exception {
        return vtUsuarioRolLogic.getDataVtUsuarioRol();
    }

	@Override
	public List<VtProyecto> findProyectsByEnterpriseIdentification(VtEmpresa vtEmpresa) throws Exception {
		return vtProyectoLogic.findProyectsByEnterpriseIdentification(vtEmpresa);
	}
	
	@Override
	public boolean autenticarUsuario(String login, String password) throws Exception {
		return vtUsuarioLogic.autenticarUsuario(login, password);
	}

	@Override
	public VtUsuario findUsuarioByLogin(String login) throws Exception {
		return vtUsuarioLogic.findUsuarioByLogin(login);
	}

	@Override
	public List<VtProyectoUsuario> findProyectoUsuarioPorProyecto(VtProyecto proyecto) throws Exception {		
		return vtProyectoUsuarioLogic.findProyectoUsuarioPorProyecto(proyecto);
	}

	@Override
	public List<VtUsuario> getVtUsuarioNoAsignados(VtProyecto proyecto) throws Exception {
		return vtUsuarioLogic.getVtUsuarioNoAsignados(proyecto);
	}

	@Override
	public List<VtUsuario> getVtUsuarioAsignados(VtProyecto proyecto) throws Exception {
		return vtUsuarioLogic.getVtUsuarioAsignados(proyecto);
	}

	@Override
	public List<VtPilaProducto> findBacklogByProyecto(VtProyecto vtProyecto) throws Exception {
		return vtPilaProductoLogic.findBacklogByProyecto(vtProyecto);
	}

	@Override
	public List<VtSprint> findSprintByBacklog(VtPilaProducto vtBacklog) throws Exception {
		return vtSprintLogic.findSprintByBacklog(vtBacklog);
	}

	@Override
	public List<VtArtefacto> findArtefactosBySpring(VtSprint vtSprint) throws Exception {
		return vtArtefactoLogic.findArtefactosBySpring(vtSprint);
	}

	@Override
	public VtProyectoUsuario findProyectoUsuarioByProyectoAndUsuario(Long proyectoId, Long usuarioId) {
		return vtProyectoUsuarioLogic.findProyectoUsuarioByProyectoAndUsuario(proyectoId, usuarioId);
	}

	@Override
	public List<VtArtefacto> findArtefactosVaciosPorBacklog(Long backlogId) {
		return vtArtefactoLogic.findArtefactosVaciosPorBacklog(backlogId);
	}

	@Override
	public List<VtHistoriaArtefacto> findHistoriaByArtefacto(VtArtefacto vtArtefacto) throws Exception {
		return vtHistoriaArtefactoLogic.findHistoriaByArtefacto(vtArtefacto);
	}

	@Override
	public List<VtArchivo> findArchivosByArtefactos(VtArtefacto vtArtefacto) throws Exception {
		return vtArchivoLogic.findArchivosByArtefactos(vtArtefacto);
	}

	@Override
	public List<VtUsuarioRol> findUsuarioRolbyUsuario(VtUsuario usuario) throws Exception {
		return vtUsuarioRolLogic.findUsuarioRolbyUsuario(usuario);
	}

	@Override
	public VtUsuarioRol findUsuarioRolByUsuarioAndRol(Long usuarioCodigo, Long rolCodigo) {
		return vtUsuarioRolLogic.findUsuarioRolByUsuarioAndRol(usuarioCodigo, rolCodigo);
	}

	@Override
	public List<VtRol> getRolesAsignados(VtUsuario usuario) throws Exception {
		return vtRolLogic.getRolesAsignados(usuario);
	}

	@Override
	public List<VtRol> getRolesNoAsignados(VtUsuario usuario) throws Exception {
		return vtRolLogic.getRolesNoAsignados(usuario);
	}

	@Override
	public List<VtUsuarioRol> findUsuarioRolbyRol(VtRol rol) throws Exception {
		return vtUsuarioRolLogic.findUsuarioRolbyRol(rol);
	}

	@Override
	public List<VtUsuario> getVtUsuarioDesarrolladores() throws Exception {
		return vtUsuarioLogic.getVtUsuarioDesarrolladores();
	}

	@Override
	public VtUsuarioArtefacto findUsuarioArtefactoByUsuarioArtefactoInteres(Long usuarioCodigo, Long arteCodigo,
			Long inteCodigo) throws Exception {
		return vtUsuarioArtefactoLogic.findUsuarioArtefactoByUsuarioArtefactoInteres(usuarioCodigo, arteCodigo, inteCodigo);
	}

	@Override
	public VtUsuarioArtefacto findUsuarioArtefactoByArtefacto(VtArtefacto artefacto) throws Exception {
		return vtUsuarioArtefactoLogic.findUsuarioArtefactoByArtefacto(artefacto);
	}
	
	
	
	
}
