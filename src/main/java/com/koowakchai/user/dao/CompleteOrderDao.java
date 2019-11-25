package com.koowakchai.user.dao;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CompleteOrderDao {

   public void updateTotalOrderEntity(long orderId, long userId, long addrId, long paymentId) throws Exception;

}
