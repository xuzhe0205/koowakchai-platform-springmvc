package com.koowakchai.store.dao.impl;

import com.koowakchai.hibernate.entity.TBusinessSubtypeEntity;
import com.koowakchai.store.dao.TBusinessSubtypeDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class TBusinessSubtypeDaoImpl implements TBusinessSubtypeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public TBusinessSubtypeEntity getTBusinessSubtypeEntity(int subtypeId) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TBusinessSubtypeEntity> cr = cb.createQuery(TBusinessSubtypeEntity.class);
        Root<TBusinessSubtypeEntity> root = cr.from(TBusinessSubtypeEntity.class);
        cr.select(root).where(cb.equal(root.get("id"),subtypeId));
        Query<TBusinessSubtypeEntity> query = session.createQuery(cr);
        TBusinessSubtypeEntity tBusinessSubtypeEntity = query.getSingleResult();
        return tBusinessSubtypeEntity;
    }

}
