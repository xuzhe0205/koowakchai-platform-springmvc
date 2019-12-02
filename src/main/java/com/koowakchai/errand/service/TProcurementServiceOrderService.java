package com.koowakchai.errand.service;

import com.koowakchai.hibernate.entity.TProcurementServiceOrderEntity;

public interface TProcurementServiceOrderService {
    public void addProcurementServiceOrder(long userId, String deliveryAddress, String purchaseAddress, String items, String deliveryTime, double tips, double deliveryFee) throws Exception;

    public void updateProcurementServiceOrder(long deliverymanId, double itemsPrice, int procurementOrderId) throws Exception;

//    public void assignProcurementServiceOrder(long deliverymanId, int pickupDropoffOrderId) throws Exception;

    public TProcurementServiceOrderEntity getAssignedProcurementOrder(long deliverymanId) throws Exception;

}
