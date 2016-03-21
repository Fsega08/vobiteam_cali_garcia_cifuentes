package com.vobi.team.dataaccess.dao;

import com.vobi.team.dataaccess.api.HibernateDaoImpl;
import com.vobi.team.modelo.VtUsuarioRol;

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
 * VtUsuarioRol entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.VtUsuarioRol
 */
@Scope("singleton")
@Repository("VtUsuarioRolDAO")
public class VtUsuarioRolDAO extends HibernateDaoImpl<VtUsuarioRol, Long>
    implements IVtUsuarioRolDAO {
    private static final Logger log = LoggerFactory.getLogger(VtUsuarioRolDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IVtUsuarioRolDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IVtUsuarioRolDAO) ctx.getBean("VtUsuarioRolDAO");
    }

	@Override
	public VtUsuarioRol findUsuarioRolByUsuarioAndRol(Long usuarioCodigo, Long rolCodigo) {
		Query query = getSession().getNamedQuery("consultarUsuarioRolPorUsuarioYRol");
		query.setParameter("usuaCodigo", usuarioCodigo);
		query.setParameter("rolCodigo", rolCodigo);
		
		VtUsuarioRol usuarioRol = (VtUsuarioRol) query.uniqueResult();
		return usuarioRol;
	}
}
