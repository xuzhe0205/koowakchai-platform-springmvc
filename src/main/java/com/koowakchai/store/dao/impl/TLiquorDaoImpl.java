package com.koowakchai.store.dao.impl;

import com.koowakchai.hibernate.entity.TLiquorEntity;
import com.koowakchai.store.dao.TLiquorDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TLiquorDaoImpl implements TLiquorDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public TLiquorEntity getTLiquorCartItemEntity(int productId) throws Exception{
        Session session =  sessionFactory.getCurrentSession();
        NativeQuery query = session.createSQLQuery("select * from t_liquor where t_liquor.id = :productId").addEntity(TLiquorEntity.class);
        TLiquorEntity tLiquorEntity = (TLiquorEntity)query.setParameter("productId", productId).getSingleResult();
        return tLiquorEntity;
    }

    @Override
    public List<TLiquorEntity> getTLiquorEntitySorted(String sortKey, int pageNumber, int pageSize) throws Exception{
        Session session =  sessionFactory.getCurrentSession();
//        NativeQuery query = session.createSQLQuery("select * from t_liquor order by" + ":sortKey" +"desc").addEntity(TLiquorEntity.class);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TLiquorEntity> cr = cb.createQuery(TLiquorEntity.class);
        Root<TLiquorEntity> root = cr.from(TLiquorEntity.class);
        cr.select(root).orderBy(cb.desc(root.get(sortKey)));
        Query<TLiquorEntity> query = session.createQuery(cr);
        query.setFirstResult((pageNumber - 1)*pageSize);
        query.setMaxResults(pageSize);
        List<TLiquorEntity> tLiquorEntityList = query.getResultList();
        return tLiquorEntityList;
    }

    @Override
    public void reduceTLiquorEntity(int id, int quantity) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TLiquorEntity> cr = cb.createQuery(TLiquorEntity.class);
        Root<TLiquorEntity> root = cr.from(TLiquorEntity.class);
        cr.select(root).where(cb.equal(root.get("id"),id));
        Query<TLiquorEntity> query = session.createQuery(cr);
        TLiquorEntity tLiquorEntity = query.getSingleResult();
        int stock = tLiquorEntity.getStock();
        stock = stock - quantity;
        tLiquorEntity.setStock(stock);
        session.saveOrUpdate(tLiquorEntity);
    }

    @Override
    public void deleteTLiquorEntity(int productId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        TLiquorEntity tLiquorEntity = new TLiquorEntity();
        tLiquorEntity.setId(productId);
        session.delete(tLiquorEntity);
    }

    @Override
    public List<TLiquorEntity> searchTLiquorEntityList(String keyword) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createSQLQuery("select * from t_liquor where t_liquor.name like CONCAT('%', :keyword, '%') or t_liquor.made_country like CONCAT('%', :keyword, '%') or t_liquor.category like CONCAT('%', :keyword, '%')").addEntity(TLiquorEntity.class);
        List<TLiquorEntity> tLiquorEntityList = query.setParameter("keyword", keyword).getResultList();
        return tLiquorEntityList;
    }

}
