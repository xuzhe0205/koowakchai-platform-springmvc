package com.koowakchai.user.dao.impl;

import com.koowakchai.hibernate.entity.TAddressBookEntity;
import com.koowakchai.user.dao.TAddressBookDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TAddressBookDaoImpl implements TAddressBookDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveOrUpdateAddress(TAddressBookEntity tAddressBookEntity) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tAddressBookEntity);
    }

}
