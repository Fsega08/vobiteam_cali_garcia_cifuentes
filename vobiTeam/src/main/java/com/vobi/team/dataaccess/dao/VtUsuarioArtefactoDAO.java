package com.vobi.team.dataaccess.dao;

import com.vobi.team.dataaccess.api.HibernateDaoImpl;
import com.vobi.team.modelo.VtUsuarioArtefacto;

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
 * VtUsuarioArtefacto entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.VtUsuarioArtefacto
 */
@Scope("singleton")
@Repository("VtUsuarioArtefactoDAO")
public class VtUsuarioArtefactoDAO extends HibernateDaoImpl<VtUsuarioArtefacto, Long>
    implements IVtUsuarioArtefactoDAO {
    private static final Logger log = LoggerFactory.getLogger(VtUsuarioArtefactoDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IVtUsuarioArtefactoDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IVtUsuarioArtefactoDAO) ctx.getBean("VtUsuarioArtefactoDAO");
    }

	@Override
	public VtUsuarioArtefacto findUsuarioArtefactoByUsuarioArtefactoInteres(Long usuarioCodigo, Long arteCodigo,
			Long inteCodigo) {
		Query query = getSession().getNamedQuery("consultarUsuarioArtefactoPorUsuarioInteresArtefacto");
		query.setParameter("usuaCodigo", usuarioCodigo);
		query.setParameter("inteCodigo", inteCodigo);
		query.setParameter("arteCodigo", arteCodigo);
		
		VtUsuarioArtefacto vtUsuarioArtefacto = (VtUsuarioArtefacto) query.uniqueResult();
		return vtUsuarioArtefacto;
	}
}
