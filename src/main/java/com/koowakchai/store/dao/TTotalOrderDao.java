package com.koowakchai.store.dao;

import com.koowakchai.hibernate.entity.TTotalOrderEntity;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Transactional
public interface TTotalOrderDao {

    public long addTTotalOrderEntity(TTotalOrderEntity tTotalOrderEntity) throws Exception;

//    public void updateTotalOrderEntity(TTotalOrderEntity tTotalOrderEntity) throws Exception;
    public void updateTotalOrderEntity(long orderId, long userId, long addrId, long paymentId, double totalCost, Timestamp paidDatetime, String recipientName, String paymentMethod, String shippingAddr, String outTradeNumber, String status) throws Exception;

    public TTotalOrderEntity getTTotalOrderEntity(long orderId) throws Exception;

    public void updateShippingTotalOrderEntity(long orderId, String trackingNumber, Timestamp shippingDate, String status) throws Exception;

}
