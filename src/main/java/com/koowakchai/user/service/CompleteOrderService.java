package com.koowakchai.user.service;


public interface CompleteOrderService {

    public void updateTTotalOrderEntity(long orderId, long userId, long addrId, long paymentId) throws Exception;

}
