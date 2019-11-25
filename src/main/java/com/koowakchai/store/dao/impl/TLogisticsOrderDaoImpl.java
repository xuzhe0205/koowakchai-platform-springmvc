package com.koowakchai.store.dao.impl;

import com.koowakchai.hibernate.entity.TLogisticsOrderEntity;
import com.koowakchai.store.dao.TLogisticsOrderDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class TLogisticsOrderDaoImpl implements TLogisticsOrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addShippingOrders(TLogisticsOrderEntity tLogisticsOrderEntity) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tLogisticsOrderEntity);
    }

    @Override
    public TLogisticsOrderEntity getTLogisticsOrderEntity(String trackingNumber) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TLogisticsOrderEntity> cr = cb.createQuery(TLogisticsOrderEntity.class);
        Root<TLogisticsOrderEntity> root = cr.from(TLogisticsOrderEntity.class);
        cr.select(root).where(cb.equal(root.get("trackingNumber"),trackingNumber));
        Query<TLogisticsOrderEntity> query = session.createQuery(cr);
        TLogisticsOrderEntity tLogisticsOrderEntity = query.getSingleResult();
        return tLogisticsOrderEntity;
    }

}
