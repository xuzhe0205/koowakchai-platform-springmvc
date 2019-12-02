package com.koowakchai.errand.service.impl;

import com.koowakchai.common.distribution.AutoDispatchTaskThread;
import com.koowakchai.common.distribution.GenerateTask;
import com.koowakchai.errand.dao.TDeliverymanDao;
import com.koowakchai.errand.dao.TPickupDropoffOrderDao;
import com.koowakchai.errand.service.TPickupDropoffOrderService;
import com.koowakchai.hibernate.entity.TPickupDropoffOrderEntity;
import com.koowakchai.hibernate.entity.TUserEntity;
import com.koowakchai.user.dao.TUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TPickupDropoffOrderServiceImpl implements TPickupDropoffOrderService {

    @Autowired
    private TPickupDropoffOrderDao tPickupDropoffOrderDao;

    @Autowired
    private TUserDao tUserDao;

    @Autowired
    private TDeliverymanDao tDeliverymanDao;

    @Autowired
    private GenerateTask generateTask;

    @Autowired
    private AutoDispatchTaskThread autoDispatchTaskThread;

    @Override
    public void addPickupDropoffOrder(long userId, String pickupAddress, String dropoffAddress, String itemsType, double itemsWeight, double tips, String remarks, double deliveryFee, String deliveryTime) throws Exception {
        TUserEntity tUserEntity = tUserDao.getTUserEntity(userId);
        TPickupDropoffOrderEntity tPickupDropoffOrderEntity = new TPickupDropoffOrderEntity();
        tPickupDropoffOrderEntity.setDeliveryFee(deliveryFee);
        tPickupDropoffOrderEntity.setDeliveryTime(deliveryTime);
        tPickupDropoffOrderEntity.setDropoffAddress(pickupAddress);
        tPickupDropoffOrderEntity.setPickupAddress(pickupAddress);
        tPickupDropoffOrderEntity.setItemsType(itemsType);
        tPickupDropoffOrderEntity.setItemsWeight(itemsWeight);
        tPickupDropoffOrderEntity.setTips(tips);
        tPickupDropoffOrderEntity.setRemarks(remarks);
        tPickupDropoffOrderEntity.setRegion(tUserEntity.getRegion());
        tPickupDropoffOrderEntity.setStatus("requested");
        tPickupDropoffOrderEntity.setCustomerId(userId);
        tPickupDropoffOrderDao.addPickupDropoffOrder(tPickupDropoffOrderEntity);

        generateTask.setRole("deliveryman");

        generateTask.setErrandType("pickup dropoff");

        autoDispatchTaskThread.run();

    }

    @Override
    public void updatePickupDropoffOrder(long userId, int pickupDropoffOrderId) throws Exception {
        tPickupDropoffOrderDao.updatePickupDropoffOrder(userId, pickupDropoffOrderId);
        tDeliverymanDao.updateDeliverymanInfo(userId, "onRoute");
    }

    @Override
    public TPickupDropoffOrderEntity getAssignedPickupDropoffOrder(long deliverymanId) throws Exception {
        return tPickupDropoffOrderDao.getAssignedPickupDropoffOrder(deliverymanId);
    }
}
