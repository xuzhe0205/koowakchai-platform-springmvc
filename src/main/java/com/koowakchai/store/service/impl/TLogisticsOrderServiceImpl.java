package com.koowakchai.store.service.impl;

import com.koowakchai.common.email.AnnexEMailService;
import com.koowakchai.hibernate.entity.TLogisticsCompanyEntity;
import com.koowakchai.hibernate.entity.TLogisticsOrderEntity;
import com.koowakchai.hibernate.entity.TTotalOrderEntity;
import com.koowakchai.store.dao.TLogisticsCompanyDao;
import com.koowakchai.store.dao.TLogisticsOrderDao;
import com.koowakchai.store.dao.TTotalOrderDao;
import com.koowakchai.store.service.StoreEmailService;
import com.koowakchai.store.service.TLogisticsOrderService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TLogisticsOrderServiceImpl implements TLogisticsOrderService {

    @Autowired
    private TLogisticsCompanyDao tLogisticsCompanyDao;

    @Autowired
    private TLogisticsOrderDao tLogisticsOrderDao;

    @Autowired
    private TTotalOrderDao tTotalOrderDao;

    @Autowired
    private StoreEmailService storeEmailService;

    @Override
    public void addShippingOrders(List<Long> orderIds, int logisticsComapnyId) throws  Exception {
        String trackingNumber = RandomStringUtils.randomAlphanumeric(12).toUpperCase();
        Set<TTotalOrderEntity> tTotalOrderEntitySet =  new HashSet<>();
        TLogisticsCompanyEntity tLogisticsCompanyEntity = tLogisticsCompanyDao.getTLogisticsCompanyEntity(logisticsComapnyId);
        for (long orderId : orderIds){
//            TLogisticsOrderEntity tLogisticsOrderEntity = new TLogisticsOrderEntity();
//            tLogisticsOrderEntity.setLogisticsCompany(tLogisticsCompanyEntity.getCompanyName());
//            tLogisticsOrderEntity.setStaffPhone(tLogisticsCompanyEntity.getStaffPhone());
//            tLogisticsOrderEntity.setTrackingNumber(trackingNumber);
//            long logisticsId = tLogisticsOrderDao.addShippingOrders(tLogisticsOrderEntity);
            Date dt = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt);
            Timestamp ts = Timestamp.valueOf(currentTime) ;
            tTotalOrderDao.updateShippingTotalOrderEntity(orderId, trackingNumber, ts, "shipped");
            tTotalOrderEntitySet.add(tTotalOrderDao.getTTotalOrderEntity(orderId));

        }
        TLogisticsOrderEntity tLogisticsOrderEntity = new TLogisticsOrderEntity();
        tLogisticsOrderEntity.settTotalOrderEntitySet(tTotalOrderEntitySet);
        tLogisticsOrderEntity.setTrackingNumber(trackingNumber);
        tLogisticsOrderEntity.setLogisticsCompany(tLogisticsCompanyEntity.getCompanyName());
        tLogisticsOrderEntity.setStaffPhone(tLogisticsCompanyEntity.getStaffPhone());
        tLogisticsOrderDao.addShippingOrders(tLogisticsOrderEntity);
        storeEmailService.sendConfirmationEmail(orderIds);
    }

}
