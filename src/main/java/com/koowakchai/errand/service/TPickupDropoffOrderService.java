package com.koowakchai.errand.service;

import com.koowakchai.hibernate.entity.TPickupDropoffOrderEntity;

public interface TPickupDropoffOrderService {

    public void addPickupDropoffOrder(long userId, String pickupAddress, String dropoffAddress, String itemsType, double itemsWeight, double tips, String remarks, double deliveryFee, String deliveryTimex) throws Exception;

    public void updatePickupDropoffOrder(long userId, int pickupDropoffOrderId) throws Exception;

    public TPickupDropoffOrderEntity getAssignedPickupDropoffOrder(long deliverymanId) throws Exception;


}
