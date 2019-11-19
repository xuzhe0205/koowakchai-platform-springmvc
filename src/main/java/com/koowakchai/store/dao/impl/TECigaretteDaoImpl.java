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

    @Override
    public TECigaretteEntity getTECigaCartItemEntity(int productId) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createSQLQuery("select * from t_e_cigarette where t_e_cigarette.id = :productId").addEntity(TECigaretteEntity.class);
        TECigaretteEntity teCigaretteEntity = (TECigaretteEntity) query.setParameter("productId", productId).getSingleResult();
        return teCigaretteEntity;
    }

    @Override
    public List<TECigaretteEntity> getTEcigaEntitySorted(String sortKey) throws Exception{
        Session session =  sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TECigaretteEntity> cr = cb.createQuery(TECigaretteEntity.class);
        Root<TECigaretteEntity> root = cr.from(TECigaretteEntity.class);
        cr.select(root).orderBy(cb.desc(root.get(sortKey)));
        Query<TECigaretteEntity> query = session.createQuery(cr);
        List<TECigaretteEntity> teCigaretteEntityList= query.getResultList();
        return teCigaretteEntityList;
    }


}
