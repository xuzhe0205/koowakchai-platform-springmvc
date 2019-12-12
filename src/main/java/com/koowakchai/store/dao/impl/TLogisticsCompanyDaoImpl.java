package com.koowakchai.store.dao.impl;

import com.koowakchai.hibernate.entity.TLogisticsCompanyEntity;
import com.koowakchai.store.dao.TLogisticsCompanyDao;
import com.koowakchai.store.dao.TLogisticsOrderDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TLogisticsCompanyDaoImpl implements TLogisticsCompanyDao {

    @Autowired
    private SessionFactory sessionFactory;

    public TLogisticsCompanyEntity getTLogisticsCompanyEntity(int logisticsCompanyId) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TLogisticsCompanyEntity> cr = cb.createQuery(TLogisticsCompanyEntity.class);
        Root<TLogisticsCompanyEntity> root = cr.from(TLogisticsCompanyEntity.class);
        cr.select(root).where(cb.equal(root.get("id"),logisticsCompanyId));
        Query<TLogisticsCompanyEntity> query = session.createQuery(cr);
        TLogisticsCompanyEntity tLogisticsCompanyEntity = query.getSingleResult();
        return tLogisticsCompanyEntity;
    }

    @Override
    public List<TLogisticsCompanyEntity> getTLogisticsCompanyEntity() throws Exception {
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createSQLQuery("select * from t_logistics_company").addEntity(TLogisticsCompanyEntity.class);
        List<TLogisticsCompanyEntity> tLogisticsCompanyEntityList = query.getResultList();
        return tLogisticsCompanyEntityList;
    }
}
