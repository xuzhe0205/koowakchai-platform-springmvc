package com.koowakchai.user.service.impl;

import com.koowakchai.hibernate.entity.TPaymentInfoEntity;
import com.koowakchai.user.dao.TPaymentInfoDao;
import com.koowakchai.user.service.TPaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TPaymentInfoServiceImpl implements TPaymentInfoService {

    @Autowired
    private TPaymentInfoDao tPaymentInfoDao;

    @Override
    public void addTPaymentInfoEntity(long userId, String method, String cardNum, String zipcode, String cvv, String expDate) throws Exception{
        TPaymentInfoEntity tPaymentInfoEntity = new TPaymentInfoEntity();
        tPaymentInfoEntity.setCardNum(cardNum);
        tPaymentInfoEntity.setCvv(cvv);
        tPaymentInfoEntity.setUserId(userId);
        tPaymentInfoEntity.setZipcode(zipcode);
        tPaymentInfoEntity.setMethod(method);
        tPaymentInfoEntity.setExpDate(expDate);
        tPaymentInfoDao.addTPaymentInfoEntity(tPaymentInfoEntity);
    }

}
