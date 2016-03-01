/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tai.solr.dao.impl;

import it.tai.solr.dao.ProbeDAO;
import it.tai.solr.dao.entities.SolrSummaryEntity;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author francesco
 */
public class ProbeDAOImpl extends HibernateDaoSupport implements ProbeDAO {

    private static final Logger logger = LoggerFactory.getLogger(ProbeDAOImpl.class);

    private SessionFactory sessionFactory;

    @Override
    public SolrSummaryEntity findById(long id) {
        return (SolrSummaryEntity) getHibernateTemplate().get(SolrSummaryEntity.class, id);
    }

    @Override
    public List<SolrSummaryEntity> findAll() {
        return (List<SolrSummaryEntity>) getHibernateTemplate().find("from it.tai.solr.dao.entities.SolrSummaryEntity");
    }

    @Override
    public void save(SolrSummaryEntity solrSummaryEntity) {
        getHibernateTemplate().save(solrSummaryEntity);
    }

    @Override
    public void update(SolrSummaryEntity solrSummaryEntity) {
        getHibernateTemplate().update(solrSummaryEntity);
    }

    @Override
    public void delete(SolrSummaryEntity solrSummaryEntity) {
        getHibernateTemplate().update(solrSummaryEntity);
    }

    @Override
    public SolrSummaryEntity getLastEntry() {

        return (SolrSummaryEntity) getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("FROM it.tai.solr.dao.entities.SolrSummaryEntity ORDER BY registeredAt DESC");
                query.setMaxResults(1);
                
                return query.list().get(0);
            }
        });
        
    }

    @Override
    public void shutdown() {
        getHibernateTemplate().getSessionFactory().openSession().createSQLQuery("SHUTDOWN").executeUpdate();
    }

    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
