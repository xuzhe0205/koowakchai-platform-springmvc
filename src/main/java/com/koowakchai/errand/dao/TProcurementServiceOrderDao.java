package com.koowakchai.errand.dao;

import com.koowakchai.hibernate.entity.TProcurementServiceOrderEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TProcurementServiceOrderDao {
    public void addProcurementServiceOrder(TProcurementServiceOrderEntity tProcurementServiceOrderEntity) throws Exception;
    public void updateProcurementServiceOrder(long deliverymanId, double itemsPrice, int procurementOrderId) throws Exception;
    public List<TProcurementServiceOrderEntity> getTProcurementServiceOrderList() throws Exception;
    public void assignProcurementServiceOrder(long deliverymanId, int procurementOrderId) throws Exception;
    public TProcurementServiceOrderEntity getAssignedProcurementOrder(long deliverymanId) throws Exception;

}
