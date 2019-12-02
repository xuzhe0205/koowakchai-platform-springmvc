package com.koowakchai.errand.dao;

import com.koowakchai.hibernate.entity.TDeliverymanEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TDeliverymanDao {
    public void addTDeliverymanEntity(TDeliverymanEntity tDeliverymanEntity) throws Exception;
    public void updateDeliverymanInfo(long deliverymanId, String status) throws Exception;
    public void assignProcurementServiceOrder(long deliverymanId, int assignedOrderId, String errandType) throws Exception;
}
