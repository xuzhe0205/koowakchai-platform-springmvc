package com.koowakchai.store.service.impl;

import com.koowakchai.common.email.AnnexEMailService;
import com.koowakchai.hibernate.entity.TTotalOrderEntity;
import com.koowakchai.store.dao.TTotalOrderDao;
import com.koowakchai.store.service.StoreEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreEmailServiceImpl implements StoreEmailService {

    @Autowired
    private AnnexEMailService annexEMailService;

    @Autowired
    private TTotalOrderDao tTotalOrderDao;


    @Async
    @Override
    public Boolean sendConfirmationEmail(List<Long> ordersIds) throws Exception {
        List<Object> orderObjectList = new ArrayList<>();
        for (long orderId : ordersIds){
            TTotalOrderEntity tTotalOrderEntity = tTotalOrderDao.getTTotalOrderEntity(orderId);
            orderObjectList.add(tTotalOrderEntity);
        }
        boolean isEmailSent = annexEMailService.sendEmail(1, orderObjectList);
        System.out.println("Email has successfully sent to purchasing customer!!!");
        return isEmailSent;
    }
}
