package com.koowakchai.travel.dao.impl;

import com.koowakchai.hibernate.entity.TDriverEntity;
import com.koowakchai.travel.dao.TDriverDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class TDriverDaoImpl implements TDriverDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public TDriverEntity getTDriverEntity(long driverId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TDriverEntity> cr = cb.createQuery(TDriverEntity.class);
        Root<TDriverEntity> root = cr.from(TDriverEntity.class);
        cr.select(root).where(cb.equal(root.get("userId"),driverId));
        Query<TDriverEntity> query = session.createQuery(cr);
        TDriverEntity tDriverEntity = query.getSingleResult();
        return tDriverEntity;
    }

    @Override
    public void addTDriverEntity(TDriverEntity tDriverEntity) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tDriverEntity);
    }

    @Override
    public void updateTDriverInfoEntity(long driverId, long assignedTripId) throws Exception {
//        Session session = sessionFactory.getCurrentSession();
//        TDriverEntity tDriverEntity = session.get(TDriverEntity.class, driverId);
//        tDriverEntity.setAssignedTripId(assignedTripId);
//        session.saveOrUpdate(tDriverEntity);
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TDriverEntity> cr = cb.createQuery(TDriverEntity.class);
        Root<TDriverEntity> root = cr.from(TDriverEntity.class);
        cr.select(root).where(cb.equal(root.get("userId"),driverId));
        Query<TDriverEntity> query = session.createQuery(cr);
        TDriverEntity tDriverEntity = query.getSingleResult();
        tDriverEntity.setAssignedTripId(assignedTripId);
        int tripCount = tDriverEntity.getTripCount();
        tripCount++;
        tDriverEntity.setTripCount(tripCount);
        session.saveOrUpdate(tDriverEntity);
    }
}
