package com.koowakchai.travel.dao.impl;

import com.koowakchai.hibernate.entity.TTravelOrderEntity;
import com.koowakchai.travel.dao.THitchHikeOrderDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class THitchHikeOrderDaoImpl implements THitchHikeOrderDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addHitchHikeOrderEntity(TTravelOrderEntity tTravelOrderEntity) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tTravelOrderEntity);
    }

    @Override
    public void updateHitchHikeOrderEntity(Long travelOrderId, Long driverId, String status) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        TTravelOrderEntity tTravelOrderEntity = session.get(TTravelOrderEntity.class, travelOrderId);
        tTravelOrderEntity.setDriverId(driverId);
        tTravelOrderEntity.setStatus(status);
        session.saveOrUpdate(tTravelOrderEntity);
    }

    @Override
    public List<TTravelOrderEntity> getTravelOrderEntityByType(String type) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createSQLQuery("select * from t_travel_order where t_travel_order.travel_type = :type and t_travel_order.status = 'requested' and t_travel_order.driver_id is null").addEntity(TTravelOrderEntity.class);
        List<TTravelOrderEntity> tTravelOrderEntityList = query.setParameter("type", type).getResultList();
        return tTravelOrderEntityList;
    }

    @Override
    public void assignAirportRideOrder(long driverId, long travelOrderId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        TTravelOrderEntity tTravelOrderEntity = session.get(TTravelOrderEntity.class, travelOrderId);
        tTravelOrderEntity.setDriverId(driverId);
        session.saveOrUpdate(tTravelOrderEntity);
    }
}
