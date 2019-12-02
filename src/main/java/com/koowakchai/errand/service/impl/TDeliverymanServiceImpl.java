package com.koowakchai.errand.service.impl;

import com.koowakchai.errand.service.TDeliverymanService;
import com.koowakchai.errand.dao.TDeliverymanDao;
import com.koowakchai.hibernate.entity.TDeliverymanEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TDeliverymanServiceImpl implements TDeliverymanService {
    @Autowired
    private TDeliverymanDao tDeliverymanDao;
    @Override
    public void addTDeliverymanEntity(long userId) throws Exception {
        TDeliverymanEntity tDeliverymanEntity = new TDeliverymanEntity();
        tDeliverymanEntity.setRating((byte)0);
        tDeliverymanEntity.setStatus("rest");
        tDeliverymanEntity.setOrderCount(0);
        tDeliverymanEntity.setUserId(userId);
        tDeliverymanDao.addTDeliverymanEntity(tDeliverymanEntity);
    }
}
