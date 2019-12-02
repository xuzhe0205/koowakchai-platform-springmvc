package com.koowakchai.errand.dao;

import com.koowakchai.hibernate.entity.TPickupDropoffOrderEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TPickupDropoffOrderDao {
    public void addPickupDropoffOrder(TPickupDropoffOrderEntity tPickupDropoffOrderEntity) throws Exception;
    public void updatePickupDropoffOrder(long userId, int pickupDropooffOrderId) throws Exception;
    public List<TPickupDropoffOrderEntity> getPickupDropoffOrder() throws Exception;
    public TPickupDropoffOrderEntity getAssignedPickupDropoffOrder(long deliverymanId) throws Exception;
}
