package com.koowakchai.store.dao.impl;

import com.koowakchai.hibernate.entity.TTotalOrderEntity;
import com.koowakchai.store.dao.TTotalOrderDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;

@Repository
public class TTotalOrderDaoImpl implements TTotalOrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long addTTotalOrderEntity(TTotalOrderEntity tTotalOrderEntity) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.save(tTotalOrderEntity);
        return tTotalOrderEntity.getId();
    }

    @Override
    public void updateTotalOrderEntity(long orderId, long userId, long addrId, long paymentId, double totalCost, Timestamp paidDatetime, String recipientName, String paymentMethod, String shippingAddr, String outTradeNumber, String status) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        TTotalOrderEntity tTotalOrderEntity = session.get(TTotalOrderEntity.class, orderId);
        tTotalOrderEntity.setAddrId(addrId);
        tTotalOrderEntity.setStatus(status);
        tTotalOrderEntity.setPaymentId(paymentId);
        tTotalOrderEntity.setPaidDatetime(paidDatetime);
        tTotalOrderEntity.setRecipientName(recipientName);
        tTotalOrderEntity.setShippingAddr(shippingAddr);
        tTotalOrderEntity.setPaymentMethod(paymentMethod);
        tTotalOrderEntity.setOutTradeNumber(outTradeNumber);
        tTotalOrderEntity.setPricePaid(totalCost);
        session.saveOrUpdate(tTotalOrderEntity);
    }

    @Override
    public TTotalOrderEntity getTTotalOrderEntity(long orderId) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TTotalOrderEntity> cr = cb.createQuery(TTotalOrderEntity.class);
        Root<TTotalOrderEntity> root = cr.from(TTotalOrderEntity.class);
        cr.select(root).where(cb.equal(root.get("id"),orderId));
        Query<TTotalOrderEntity> query = session.createQuery(cr);
        TTotalOrderEntity tTotalOrderEntity = query.getSingleResult();
        return tTotalOrderEntity;
    }

    @Override
    public void updateShippingTotalOrderEntity(long orderId, String trackingNumber, Timestamp shippingDate, String status) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        TTotalOrderEntity tTotalOrderEntity = session.get(TTotalOrderEntity.class, orderId);
        tTotalOrderEntity.setShipDatetime(shippingDate);
        tTotalOrderEntity.setTrackingNumber(trackingNumber);
        tTotalOrderEntity.setStatus(status);
        session.saveOrUpdate(tTotalOrderEntity);
    }

}
