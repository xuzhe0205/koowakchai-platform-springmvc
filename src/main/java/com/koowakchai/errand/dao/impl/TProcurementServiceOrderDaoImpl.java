package com.koowakchai.errand.dao.impl;

import com.koowakchai.errand.dao.TProcurementServiceOrderDao;
import com.koowakchai.hibernate.entity.TProcurementServiceOrderEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TProcurementServiceOrderDaoImpl implements TProcurementServiceOrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addProcurementServiceOrder(TProcurementServiceOrderEntity tProcurementServiceOrderEntity) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tProcurementServiceOrderEntity);
    }

    @Override
    public void updateProcurementServiceOrder(long deliverymanId, double itemsPrice, int procurementOrderId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        TProcurementServiceOrderEntity tProcurementServiceOrderEntity = session.get(TProcurementServiceOrderEntity.class, procurementOrderId);
        tProcurementServiceOrderEntity.setDeliverymanId(deliverymanId);
        tProcurementServiceOrderEntity.setStatus("accepted");
        tProcurementServiceOrderEntity.setItemsPrice(itemsPrice);
        session.saveOrUpdate(tProcurementServiceOrderEntity);
    }

    @Override
    public List<TProcurementServiceOrderEntity> getTProcurementServiceOrderList() throws Exception {
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createSQLQuery("select * from t_procurement_service_order where t_procurement_service_order.status = 'requested' and t_procurement_service_order.deliverymanId is null").addEntity(TProcurementServiceOrderEntity.class);
        List<TProcurementServiceOrderEntity> tProcurementServiceOrderEntityList = query.getResultList();
        return tProcurementServiceOrderEntityList;
    }

    @Override
    public void assignProcurementServiceOrder(long deliverymanId, int procurementOrderId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        TProcurementServiceOrderEntity tProcurementServiceOrderEntity = session.get(TProcurementServiceOrderEntity.class, procurementOrderId);
        tProcurementServiceOrderEntity.setDeliverymanId(deliverymanId);
        session.saveOrUpdate(tProcurementServiceOrderEntity);
    }

    @Override
    public TProcurementServiceOrderEntity getAssignedProcurementOrder(long deliverymanId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createSQLQuery("select t_procurement_service_order.* from t_procurement_service_order join t_deliveryman on t_procurement_service_order.id = t_deliveryman.assigned_order_id where t_deliveryman.errand_type = 'procurement service'").addEntity(TProcurementServiceOrderEntity.class);

        TProcurementServiceOrderEntity tProcurementServiceOrderEntity = (TProcurementServiceOrderEntity) query.getSingleResult();
        return tProcurementServiceOrderEntity;
    }
}
