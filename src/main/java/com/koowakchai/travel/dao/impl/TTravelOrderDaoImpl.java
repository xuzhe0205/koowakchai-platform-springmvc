package com.koowakchai.travel.dao.impl;

import com.koowakchai.hibernate.entity.TTravelOrderEntity;
import com.koowakchai.travel.dao.TTravelOrderDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TTravelOrderDaoImpl implements TTravelOrderDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public TTravelOrderEntity getTravelOrders(long driverId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createSQLQuery("select t_travel_order.* from t_travel_order join t_driver on t_travel_order.driver_id = t_driver.user_id where t_travel_order.status = 'requested' and t_travel_order.driver_id = :driverId").addEntity(TTravelOrderEntity.class);
        TTravelOrderEntity tTravelOrderEntity = (TTravelOrderEntity) query.setParameter("driverId", driverId).getSingleResult();
        return tTravelOrderEntity;
    }
}
