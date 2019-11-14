package com.koowakchai.store.dao.impl;

import com.koowakchai.hibernate.entity.TShoppingCartEntity;
import com.koowakchai.store.dao.TShoppingCartDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TShoppingCartDaoImpl implements TShoppingCartDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveOrUpdateCart(TShoppingCartEntity tShoppingCartEntity) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tShoppingCartEntity);
    }
}
