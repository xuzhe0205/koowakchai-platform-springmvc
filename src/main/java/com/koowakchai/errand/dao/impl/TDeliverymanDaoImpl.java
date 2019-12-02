package com.koowakchai.errand.dao.impl;

import com.koowakchai.errand.dao.TDeliverymanDao;
import com.koowakchai.hibernate.entity.TDeliverymanEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class TDeliverymanDaoImpl implements TDeliverymanDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTDeliverymanEntity(TDeliverymanEntity tDeliverymanEntity) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tDeliverymanEntity);
    }

    @Override
    public void updateDeliverymanInfo(long deliverymanId, String status) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TDeliverymanEntity> cr = cb.createQuery(TDeliverymanEntity.class);
        Root<TDeliverymanEntity> root = cr.from(TDeliverymanEntity.class);
        cr.select(root).where(cb.equal(root.get("userId"),deliverymanId));
        Query<TDeliverymanEntity> query = session.createQuery(cr);
        TDeliverymanEntity tDeliverymanEntity = query.getSingleResult();
        tDeliverymanEntity.setStatus(status);
        int orderCount = tDeliverymanEntity.getOrderCount();
        orderCount++;
        tDeliverymanEntity.setOrderCount(orderCount);
        session.saveOrUpdate(tDeliverymanEntity);
    }

    @Override
    public void assignProcurementServiceOrder(long deliverymanId, int assignedOrderId, String errandType) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TDeliverymanEntity> cr = cb.createQuery(TDeliverymanEntity.class);
        Root<TDeliverymanEntity> root = cr.from(TDeliverymanEntity.class);
        cr.select(root).where(cb.equal(root.get("userId"),deliverymanId));
        Query<TDeliverymanEntity> query = session.createQuery(cr);
        TDeliverymanEntity tDeliverymanEntity = query.getSingleResult();
        tDeliverymanEntity.setAssignedOrderId(assignedOrderId);
        tDeliverymanEntity.setErrandType(errandType);
        session.saveOrUpdate(tDeliverymanEntity);
    }
}
