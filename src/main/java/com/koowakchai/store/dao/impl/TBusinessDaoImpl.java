package com.koowakchai.store.dao.impl;

import com.koowakchai.store.dao.TBusinessDao;
import com.koowakchai.hibernate.entity.TBusinessEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TBusinessDaoImpl implements TBusinessDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<String> getAllBusinessTypes() throws Exception{
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createSQLQuery("select distinct business_name from t_business");
        List<String> tBusinessEntityList = query.getResultList();
        return tBusinessEntityList;
    }

    @Override
    public List<TBusinessEntity> getProductTypes(int subtypeId) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createSQLQuery("select * from t_business where subtype_id = :subtypeId").addEntity(TBusinessEntity.class);
        List<TBusinessEntity> tBusinessEntityList = query.setParameter("subtypeId", subtypeId).getResultList();
        return tBusinessEntityList;
    }

    @Override
    public void saveOrUpdateTBusinessType(TBusinessEntity tBusinessEntity) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tBusinessEntity);
    }
}
