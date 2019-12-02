package com.koowakchai.errand.dao.impl;

import com.koowakchai.errand.dao.TPickupDropoffOrderDao;
import com.koowakchai.hibernate.entity.TPickupDropoffOrderEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TPickupDropoffOrderDaoImpl implements TPickupDropoffOrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPickupDropoffOrder(TPickupDropoffOrderEntity tPickupDropoffOrderEntity) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tPickupDropoffOrderEntity);
    }

    @Override
    public void updatePickupDropoffOrder(long userId, int pickupDropooffOrderId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        TPickupDropoffOrderEntity tPickupDropoffOrderEntity = session.get(TPickupDropoffOrderEntity.class, pickupDropooffOrderId);
        tPickupDropoffOrderEntity.setDeliverymanId(userId);
        tPickupDropoffOrderEntity.setStatus("accepted");
        session.saveOrUpdate(tPickupDropoffOrderEntity);
    }

    @Override
    public List<TPickupDropoffOrderEntity> getPickupDropoffOrder() throws Exception {
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createSQLQuery("select * from t_pickup_dropoff_order where t_pickup_dropoff_order.status = 'requested' and t_pickup_dropoff_order.deliverymanId is null").addEntity(TPickupDropoffOrderEntity.class);
        List<TPickupDropoffOrderEntity> tPickupDropoffOrderEntityList = query.getResultList();
        return tPickupDropoffOrderEntityList;
    }

    @Override
    public TPickupDropoffOrderEntity getAssignedPickupDropoffOrder(long deliverymanId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createSQLQuery("select t_pickup_dropoff_order.* from t_pickup_dropoff_order join t_deliveryman on t_pickup_dropoff_order.id = t_deliveryman.assigned_order_id where t_deliveryman.errand_type = 'pickup dropoff'").addEntity(TPickupDropoffOrderEntity.class);

        TPickupDropoffOrderEntity tPickupDropoffOrderEntity = (TPickupDropoffOrderEntity) query.getSingleResult();
        return tPickupDropoffOrderEntity;
    }

}
