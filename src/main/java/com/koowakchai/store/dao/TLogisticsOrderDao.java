package com.koowakchai.store.dao;

import com.koowakchai.hibernate.entity.TLogisticsOrderEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TLogisticsOrderDao {


    public void addShippingOrders(TLogisticsOrderEntity tLogisticsOrderEntity) throws Exception;

    public TLogisticsOrderEntity getTLogisticsOrderEntity(String trackingNumbber) throws Exception;
}
