package com.koowakchai.travel.dao.impl;

import com.koowakchai.hibernate.entity.TAirportInfoEntity;
import com.koowakchai.hibernate.entity.TDriverEntity;
import com.koowakchai.hibernate.entity.TTravelOrderEntity;
import com.koowakchai.travel.dao.TAirportRideDao;
import com.koowakchai.travel.dao.TDriverDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TAirportRideDaoImpl implements TAirportRideDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private TDriverDao tDriverDao;

    @Override
    public List<TAirportInfoEntity> getTAirportEntityByCondition(String city, String airport) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TAirportInfoEntity> cr = cb.createQuery(TAirportInfoEntity.class);
        Root<TAirportInfoEntity> root = cr.from(TAirportInfoEntity.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        List<TAirportInfoEntity> tAirportInfoEntityList = new ArrayList<>();
        if (city.equals("") && airport.equals("")){
            tAirportInfoEntityList = session.createQuery("SELECT ai FROM TAirportInfoEntity ai", TAirportInfoEntity.class).getResultList();
        }
        else{
            predicates.add(cb.equal(root.get("city"), city));
            if (!city.equals("") && !airport.equals("")){
                predicates.add(cb.equal(root.get("airport"), airport));
            }
            cr.select(root).where(cb.and(predicates.toArray(new Predicate[] {})));
            Query<TAirportInfoEntity> query = session.createQuery(cr);
            tAirportInfoEntityList = query.getResultList();
        }


        return tAirportInfoEntityList;
    }

    @Override
    public void addAirportRideOrder(TTravelOrderEntity tTravelOrderEntity) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tTravelOrderEntity);
    }

    @Override
    public void updateAirportRideOrderEntity(Long travelOrderId, Long driverId, String status) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        TTravelOrderEntity tTravelOrderEntity = session.get(TTravelOrderEntity.class, travelOrderId);
        tTravelOrderEntity.setDriverId(driverId);
        tTravelOrderEntity.setStatus(status);
        TDriverEntity tDriverEntity = tDriverDao.getTDriverEntity(driverId);
        tDriverEntity.setStatus("onRoute");
        session.saveOrUpdate(tTravelOrderEntity);
        session.saveOrUpdate(tDriverEntity);
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
