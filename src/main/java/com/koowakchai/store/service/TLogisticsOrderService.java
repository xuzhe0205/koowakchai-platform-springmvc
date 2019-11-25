package com.koowakchai.store.service;

import java.util.List;

public interface TLogisticsOrderService {

    public void addShippingOrders(List<Long> orderIds, int logisticsComapnyId) throws Exception;

}
