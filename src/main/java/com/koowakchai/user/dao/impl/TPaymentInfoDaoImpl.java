package com.koowakchai.user.dao.impl;

import com.koowakchai.hibernate.entity.TPaymentInfoEntity;
import com.koowakchai.user.dao.TPaymentInfoDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TPaymentInfoDaoImpl implements TPaymentInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTPaymentInfoEntity(TPaymentInfoEntity tPaymentInfoEntity) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tPaymentInfoEntity);
    }

}
