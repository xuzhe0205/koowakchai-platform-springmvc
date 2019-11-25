package com.koowakchai.user.dao.impl;

import com.koowakchai.hibernate.entity.TAddressBookEntity;
import com.koowakchai.user.dao.TAddressBookDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class TAddressBookDaoImpl implements TAddressBookDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveOrUpdateAddress(TAddressBookEntity tAddressBookEntity) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tAddressBookEntity);
    }

    @Override
    public TAddressBookEntity getTAddressBookEntity(long addrId) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TAddressBookEntity> cr = cb.createQuery(TAddressBookEntity.class);
        Root<TAddressBookEntity> root = cr.from(TAddressBookEntity.class);
        cr.select(root).where(cb.equal(root.get("id"),addrId));
        Query<TAddressBookEntity> query = session.createQuery(cr);
        TAddressBookEntity tBusinessSubtypeEntity = query.getSingleResult();
        return tBusinessSubtypeEntity;
    }

}
