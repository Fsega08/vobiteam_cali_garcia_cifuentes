package com.vobi.team.dataaccess.dao;

import com.vobi.team.dataaccess.api.HibernateDaoImpl;
import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtProyectoUsuario;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;


/**
 * A data access object (DAO) providing persistence and search support for
 * VtArtefacto entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.VtArtefacto
 */
@Scope("singleton")
@Repository("VtArtefactoDAO")
public class VtArtefactoDAO extends HibernateDaoImpl<VtArtefacto, Long>
    implements IVtArtefactoDAO {
    private static final Logger log = LoggerFactory.getLogger(VtArtefactoDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IVtArtefactoDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IVtArtefactoDAO) ctx.getBean("VtArtefactoDAO");
    }
    
    
	@Override
	public List<VtArtefacto> findArtefactosVaciosPorBacklog(Long backlogId) {
		Query query = getSession().getNamedQuery("consultarArtefactosPorPilaProductoSinSprintAsignado");
		query.setParameter("pilaCodigo", backlogId);

		List<VtArtefacto> artefactos = (List<VtArtefacto>) query.list();
		return artefactos;
	}
    
    @Override
	public List<VtArtefacto> findArtefactosBySprintAndEstado(Long spriCodigo, Long estaCodigo) {
		Query query = getSession().getNamedQuery("consultarArtefactosPorSprintYEstado");
		query.setParameter("spriCodigo", spriCodigo);
		query.setParameter("estaCodigo", estaCodigo);

		List<VtArtefacto> artefactos = (List<VtArtefacto>) query.list();
		return artefactos;
	}
    
    @Override
  	public Long totalEsfuerzoEstimadoArtefactoPorSprint(Long spriCodigo) {
  		Query query = getSession().getNamedQuery("sumatoriaEsfuerzoEstimadoDeArtefactoPorSprint");
  		query.setParameter("spriCodigo", spriCodigo);
  		
  		Object esfuerzo = query.uniqueResult();
  		
  		Long suma = (Long) esfuerzo;
  		
  		return suma;

  	}
    
}
