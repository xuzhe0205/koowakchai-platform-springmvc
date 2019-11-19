package com.koowakchai.store.dao.impl;

import com.koowakchai.hibernate.entity.TShoppingCartEntity;
import com.koowakchai.store.dao.TShoppingCartDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TShoppingCartDaoImpl implements TShoppingCartDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveOrUpdateCart(TShoppingCartEntity tShoppingCartEntity) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tShoppingCartEntity);
    }

    @Override
    public List<TShoppingCartEntity> getCartItem(long userId) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createSQLQuery("select * from t_shopping_cart where t_shopping_cart.user_id = :userId").addEntity(TShoppingCartEntity.class);
        List<TShoppingCartEntity> tShoppingCartEntityList = query.setParameter("userId", userId).getResultList();
        return tShoppingCartEntityList;

    }
}
