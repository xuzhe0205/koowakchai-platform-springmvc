package com.koowakchai.errand.service.impl;

import com.koowakchai.common.distribution.AutoDispatchTaskThread;
import com.koowakchai.common.distribution.GenerateTask;
import com.koowakchai.errand.dao.TProcurementServiceOrderDao;
import com.koowakchai.errand.service.TProcurementServiceOrderService;
import com.koowakchai.hibernate.entity.TProcurementServiceOrderEntity;
import com.koowakchai.errand.dao.TDeliverymanDao;
import com.koowakchai.hibernate.entity.TUserEntity;
import com.koowakchai.user.dao.TUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TProcurementServiceOrderServiceImpl implements TProcurementServiceOrderService {

    @Autowired
    private TProcurementServiceOrderDao tProcurementServiceOrderDao;

    @Autowired
    private TUserDao tUserDao;

    @Autowired
    private TDeliverymanDao tDeliverymanDao;

    @Autowired
    private GenerateTask generateTask;

    @Autowired
    private AutoDispatchTaskThread autoDispatchTaskThread;

    @Override
    public void addProcurementServiceOrder(long userId, String deliveryAddress, String purchaseAddress, String items, String deliveryTime, double tips, double deliveryFee) throws Exception {
        TUserEntity tUserEntity = tUserDao.getTUserEntity(userId);
        String region = tUserEntity.getRegion();
        TProcurementServiceOrderEntity tProcurementServiceOrderEntity = new TProcurementServiceOrderEntity();
        tProcurementServiceOrderEntity.setDeliveryAddress(deliveryAddress);
        tProcurementServiceOrderEntity.setDeliveryFee(deliveryFee);
        tProcurementServiceOrderEntity.setDeliveryTime(deliveryTime);
        tProcurementServiceOrderEntity.setItems(items);
        tProcurementServiceOrderEntity.setStatus("requested");
        tProcurementServiceOrderEntity.setTips(tips);
        tProcurementServiceOrderEntity.setRegion(region);
        tProcurementServiceOrderEntity.setPurchaseAddress(purchaseAddress);
        tProcurementServiceOrderEntity.setCustomerId(userId);
        tProcurementServiceOrderDao.addProcurementServiceOrder(tProcurementServiceOrderEntity);
        generateTask.setRole("deliveryman");

        generateTask.setErrandType("procurement service");

        autoDispatchTaskThread.run();

    }

    @Override
    public void updateProcurementServiceOrder(long deliverymanId, double itemsPrice, int procurementOrderId) throws Exception {
        tProcurementServiceOrderDao.updateProcurementServiceOrder(deliverymanId, itemsPrice, procurementOrderId);
        tDeliverymanDao.updateDeliverymanInfo(deliverymanId, "onRoute");
    }

    @Override
    public TProcurementServiceOrderEntity getAssignedProcurementOrder(long deliverymanId) throws Exception {
        return tProcurementServiceOrderDao.getAssignedProcurementOrder(deliverymanId);
    }
}
