package com.koowakchai.user.dao.impl;

import com.koowakchai.store.dao.TTotalOrderDao;
import com.koowakchai.user.dao.CompleteOrderDao;
import com.koowakchai.user.dao.TAddressBookDao;
import com.koowakchai.user.dao.TPaymentInfoDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CompleteOrderDaoImpl implements CompleteOrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private TTotalOrderDao tTotalOrderDao;

    @Autowired
    private TAddressBookDao tAddressBookDao;

    @Autowired
    private TPaymentInfoDao tPaymentInfoDao;

    @Override
    public void updateTotalOrderEntity(long orderId, long userId, long addrId, long paymentId) throws Exception{





    }

}
