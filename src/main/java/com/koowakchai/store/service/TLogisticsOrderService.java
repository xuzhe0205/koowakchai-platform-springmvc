package com.koowakchai.store.service;

import com.koowakchai.hibernate.entity.TLogisticsCompanyEntity;

import java.util.List;

public interface TLogisticsOrderService {

    public void addShippingOrders(List<Long> orderIds, int logisticsComapnyId) throws Exception;

}
