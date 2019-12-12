package com.koowakchai.user.service.impl;

import com.koowakchai.hibernate.entity.TAddressBookEntity;
import com.koowakchai.hibernate.entity.TPaymentInfoEntity;
import com.koowakchai.hibernate.entity.TTotalOrderEntity;
import com.koowakchai.store.dao.TECigaretteDao;
import com.koowakchai.store.dao.TLiquorDao;
import com.koowakchai.store.dao.TTotalOrderDao;
import com.koowakchai.user.dao.TAddressBookDao;
import com.koowakchai.user.dao.TPaymentInfoDao;
import com.koowakchai.user.service.CompleteOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class CompleteOrderServiceImpl implements CompleteOrderService {

    @Autowired
    private TTotalOrderDao tTotalOrderDao;

    @Autowired
    private TAddressBookDao tAddressBookDao;

    @Autowired
    private TPaymentInfoDao tPaymentInfoDao;

    @Autowired
    private TECigaretteDao teCigaretteDao;

    @Autowired
    private TLiquorDao tLiquorDao;

    @Override
    public void updateTTotalOrderEntity(long orderId, long userId, long addrId, long paymentId) throws Exception{

        TTotalOrderEntity tTotalOrderEntity = tTotalOrderDao.getTTotalOrderEntity(orderId);

        TAddressBookEntity tAddressBookEntity = tAddressBookDao.getTAddressBookEntity(addrId);
        String shippingAddr = tAddressBookEntity.getFullAddr();
        String recipientName = tAddressBookEntity.getRecipient();

        TPaymentInfoEntity tPaymentInfoEntity = tPaymentInfoDao.getPaymentInfoEntity(paymentId);
//        String paymentMethod = tPaymentInfoEntity.getMethod();
//        String outTradeNumber = "";
//        if (paymentMethod.equals("alipay")){
//            outTradeNumber  = "some Alipay payment code";
//        }

        String paymentMethod = "Credit Card";
        String outTradeNumber = "";

        double totalCost =  tTotalOrderEntity.getPrice()*tTotalOrderEntity.getQuantity()+tTotalOrderEntity.getShipping();

        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        Timestamp ts = Timestamp.valueOf(currentTime) ;

        String status = "paid";

        tTotalOrderDao.updateTotalOrderEntity(orderId, userId, addrId, paymentId, totalCost, ts, recipientName, paymentMethod, shippingAddr, outTradeNumber, status);

        if (tTotalOrderEntity.getSubtypeId() == 1){
            teCigaretteDao.reduceTECigaEntity(tTotalOrderEntity.getProductId(), tTotalOrderEntity.getQuantity());
        }
        else if (tTotalOrderEntity.getSubtypeId() == 2){
            tLiquorDao.reduceTLiquorEntity(tTotalOrderEntity.getProductId(), tTotalOrderEntity.getQuantity());
        }

    }




}
