package com.koowakchai.store.dao.impl;

import com.koowakchai.hibernate.entity.TECigaretteEntity;
import com.koowakchai.store.dao.TECigaretteDao;
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
public class TECigaretteDaoImpl implements TECigaretteDao {
    @Autowired
    private SessionFactory sessionFactory;

//    private int pageNumber = 1;
//    private int pageSize = 5;

    @Override
    public TECigaretteEntity getTECigaCartItemEntity(int productId) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createSQLQuery("select * from t_e_cigarette where t_e_cigarette.id = :productId").addEntity(TECigaretteEntity.class);
        TECigaretteEntity teCigaretteEntity = (TECigaretteEntity) query.setParameter("productId", productId).getSingleResult();
        return teCigaretteEntity;
    }

    @Override
    public List<TECigaretteEntity> getTEcigaEntitySorted(String sortKey, int pageNumber, int pageSize) throws Exception{

        Session session =  sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TECigaretteEntity> cr = cb.createQuery(TECigaretteEntity.class);
        Root<TECigaretteEntity> root = cr.from(TECigaretteEntity.class);
        cr.select(root).orderBy(cb.desc(root.get(sortKey)));
        Query<TECigaretteEntity> query = session.createQuery(cr);
        query.setFirstResult((pageNumber - 1)*pageSize);
        query.setMaxResults(pageSize);
        List<TECigaretteEntity> teCigaretteEntityList= query.getResultList();
        return teCigaretteEntityList;
    }

    @Override
    public void reduceTECigaEntity(int id, int quantity) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TECigaretteEntity> cr = cb.createQuery(TECigaretteEntity.class);
        Root<TECigaretteEntity> root = cr.from(TECigaretteEntity.class);
        cr.select(root).where(cb.equal(root.get("id"),id));
        Query<TECigaretteEntity> query = session.createQuery(cr);
        TECigaretteEntity teCigaretteEntity = query.getSingleResult();
        int stock = teCigaretteEntity.getStock();
        stock = stock - quantity;
        teCigaretteEntity.setStock(stock);
        session.saveOrUpdate(teCigaretteEntity);
    }

    @Override
    public void deleteProduct(int productId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        TECigaretteEntity teCigaretteEntity = new TECigaretteEntity();
        teCigaretteEntity.setId(productId);
        session.delete(teCigaretteEntity);
    }

    @Override
    public List<TECigaretteEntity> searchTECigaretteEntityList(String keyword) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createSQLQuery("select * from t_e_cigarette where t_e_cigarette.name like CONCAT('%', :keyword, '%') or t_e_cigarette.brand like CONCAT('%', :keyword, '%') or t_e_cigarette.flavour like CONCAT('%', :keyword, '%')").addEntity(TECigaretteEntity.class);
        List<TECigaretteEntity> teCigaretteEntityList = query.setParameter("keyword", keyword).getResultList();
        return teCigaretteEntityList;
    }


}
