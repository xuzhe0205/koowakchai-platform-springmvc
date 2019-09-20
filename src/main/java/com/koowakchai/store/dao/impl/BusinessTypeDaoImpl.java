package com.koowakchai.store.dao.impl;

import com.koowakchai.store.dao.BusinessTypeDao;
import com.koowakchai.store.entity.BusinessTypeEntity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BusinessTypeDaoImpl implements BusinessTypeDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<BusinessTypeEntity> getBusinessTypes() throws Exception{
        Session session = sessionFactory.getCurrentSession();
        SQLQuery query = session.createSQLQuery("select * from business_type").addEntity(BusinessTypeEntity.class);
        List<BusinessTypeEntity> businessTypeEntityList = query.list();
        return businessTypeEntityList;
    }

    @Override
    public void saveOrUpdateBusinessType(BusinessTypeEntity businessTypeEntity) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(businessTypeEntity);
    }
}
