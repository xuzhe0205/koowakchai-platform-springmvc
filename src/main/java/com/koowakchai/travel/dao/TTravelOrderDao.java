package com.koowakchai.travel.dao;

import com.koowakchai.hibernate.entity.TTravelOrderEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TTravelOrderDao {
    public TTravelOrderEntity getTravelOrders(long driverId) throws Exception;
}
