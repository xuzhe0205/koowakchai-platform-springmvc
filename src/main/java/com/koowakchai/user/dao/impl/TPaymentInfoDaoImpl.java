package com.koowakchai.user.dao.impl;

import com.koowakchai.hibernate.entity.TPaymentInfoEntity;
import com.koowakchai.user.dao.TPaymentInfoDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class TPaymentInfoDaoImpl implements TPaymentInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTPaymentInfoEntity(TPaymentInfoEntity tPaymentInfoEntity) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tPaymentInfoEntity);
    }

    @Override
    public TPaymentInfoEntity getPaymentInfoEntity(long paymentId) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TPaymentInfoEntity> cr = cb.createQuery(TPaymentInfoEntity.class);
        Root<TPaymentInfoEntity> root = cr.from(TPaymentInfoEntity.class);
        cr.select(root).where(cb.equal(root.get("id"),paymentId));
        Query<TPaymentInfoEntity> query = session.createQuery(cr);
        TPaymentInfoEntity tPaymentInfoEntity = query.getSingleResult();
        return tPaymentInfoEntity;
    }
}
