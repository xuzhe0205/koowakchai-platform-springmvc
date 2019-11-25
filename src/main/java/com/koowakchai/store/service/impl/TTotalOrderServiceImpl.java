package com.koowakchai.store.service.impl;

import com.koowakchai.common.email.AnnexEMailService;
import com.koowakchai.hibernate.entity.*;
import com.koowakchai.store.dao.*;
import com.koowakchai.store.service.TTotalOrderService;
import com.koowakchai.user.dao.TAddressBookDao;
import com.koowakchai.user.dao.TPaymentInfoDao;
import com.koowakchai.user.dao.TUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TTotalOrderServiceImpl implements TTotalOrderService {

    @Autowired
    private TTotalOrderDao tTotalOrderDao;

    @Autowired
    private TPaymentInfoDao tPaymentInfoDao;

    @Autowired
    private TUserDao tUserDao;

    @Autowired
    private TAddressBookDao tAddressBookDao;

    @Autowired
    private TECigaretteDao teCigaretteDao;

    @Autowired
    private TLiquorDao tLiquorDao;

    @Autowired
    private TBusinessSubtypeDao tBusinessSubtypeDao;

    @Autowired
    private TShoppingCartDao tShoppingCartDao;


    @Override
    public long addTTotalOrderEntity(long cartEntityId, String remark) throws Exception{
        double shipping = 0.0;
        TTotalOrderEntity tTotalOrderEntity = new TTotalOrderEntity();
//        TPaymentInfoEntity tPaymentInfoEntity = tPaymentInfoDao.getPaymentInfoEntity(paymentId);
//        tTotalOrderEntity.setPaymentMethod(tPaymentInfoEntity.getMethod());
//        tTotalOrderEntity.setPaymentId(paymentId);
        TShoppingCartEntity tShoppingCartEntity = tShoppingCartDao.getTShoppingCartEntity(cartEntityId);
        tTotalOrderEntity.setQuantity(tShoppingCartEntity.getQuantity());
        TUserEntity tUserEntity = tUserDao.getTUserEntity(tShoppingCartEntity.getUserId());
        tTotalOrderEntity.setCustomerEmail(tUserEntity.getEmail());
        tTotalOrderEntity.setCustomerName(tUserEntity.getUsername());
        int subtypeId = tShoppingCartEntity.getProductSubtypeId();
        int productId = tShoppingCartEntity.getProductId();
        if (subtypeId == 1){
            TECigaretteEntity teCigaretteEntity = teCigaretteDao.getTECigaCartItemEntity(productId);
            tTotalOrderEntity.setProductId(productId);
            tTotalOrderEntity.setProductName(teCigaretteEntity.getName());
            tTotalOrderEntity.setSubtypeId(1);
            tTotalOrderEntity.setSubtypeName(tBusinessSubtypeDao.getTBusinessSubtypeEntity(subtypeId).getSubBusinessName());
            tTotalOrderEntity.setPrice(teCigaretteEntity.getPrice());
//            tTotalOrderEntity.setPricePaid(shipping+teCigaretteEntity.getPrice());
        }
        else if (subtypeId == 2){
            TLiquorEntity tLiquorEntity = tLiquorDao.getTLiquorCartItemEntity(productId);
            tTotalOrderEntity.setProductId(productId);
            tTotalOrderEntity.setProductName(tLiquorEntity.getName());
            tTotalOrderEntity.setSubtypeId(2);
            tTotalOrderEntity.setSubtypeName(tBusinessSubtypeDao.getTBusinessSubtypeEntity(subtypeId).getSubBusinessName());
            tTotalOrderEntity.setPrice(tLiquorEntity.getPrice());
//            tTotalOrderEntity.setPricePaid(shipping+tLiquorEntity.getPrice());

        }
//        TAddressBookEntity tAddressBookEntity = tAddressBookDao.getTAddressBookEntity(addrId);
//        tTotalOrderEntity.setAddrId(addrId);
//        tTotalOrderEntity.setShippingAddr(tAddressBookEntity.getFullAddr());
//        tTotalOrderEntity.setRecipientName(tAddressBookEntity.getRecipient());
        tTotalOrderEntity.setShipping(shipping);
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        Timestamp ts = Timestamp.valueOf(currentTime) ;
        tTotalOrderEntity.setCreateDatetime(ts);

        tTotalOrderEntity.setStatus("not-paid");
        tTotalOrderEntity.setRemark(remark);
        long orderId = tTotalOrderDao.addTTotalOrderEntity(tTotalOrderEntity);

        return orderId;

    }

//    @Override
//    public void addTTotalOrderEntity(int productId, int subtypeId, long userId, long addrId, long paymentId, long cartEntityId, String remark, String status) throws Exception{
//        double shipping = 0.0;
//        TTotalOrderEntity tTotalOrderEntity = new TTotalOrderEntity();
//        TPaymentInfoEntity tPaymentInfoEntity = tPaymentInfoDao.getPaymentInfoEntity(paymentId);
//        tTotalOrderEntity.setPaymentMethod(tPaymentInfoEntity.getMethod());
//        tTotalOrderEntity.setPaymentId(paymentId);
//        TUserEntity tUserEntity = tUserDao.getTUserEntity(userId);
//        tTotalOrderEntity.setCustomerEmail(tUserEntity.getEmail());
//        tTotalOrderEntity.setCustomerName(tUserEntity.getUsername());
//        if (subtypeId == 1){
//            TECigaretteEntity teCigaretteEntity = teCigaretteDao.getTECigaCartItemEntity(productId);
//            tTotalOrderEntity.setProductId(productId);
//            tTotalOrderEntity.setProductName(teCigaretteEntity.getName());
//            tTotalOrderEntity.setSubtypeId(1);
//            tTotalOrderEntity.setSubtypeName(tBusinessSubtypeDao.getTBusinessSubtypeEntity(subtypeId).getSubBusinessName());
//            tTotalOrderEntity.setPrice(teCigaretteEntity.getPrice());
////            tTotalOrderEntity.setPricePaid(shipping+teCigaretteEntity.getPrice());
//        }
//        else if (subtypeId == 2){
//            TLiquorEntity tLiquorEntity = tLiquorDao.getTLiquorCartItemEntity(productId);
//            tTotalOrderEntity.setProductId(productId);
//            tTotalOrderEntity.setProductName(tLiquorEntity.getName());
//            tTotalOrderEntity.setSubtypeId(2);
//            tTotalOrderEntity.setSubtypeName(tBusinessSubtypeDao.getTBusinessSubtypeEntity(subtypeId).getSubBusinessName());
//            tTotalOrderEntity.setPrice(tLiquorEntity.getPrice());
////            tTotalOrderEntity.setPricePaid(shipping+tLiquorEntity.getPrice());
//
//        }
//        TAddressBookEntity tAddressBookEntity = tAddressBookDao.getTAddressBookEntity(addrId);
//        tTotalOrderEntity.setAddrId(addrId);
//        tTotalOrderEntity.setShippingAddr(tAddressBookEntity.getFullAddr());
//        tTotalOrderEntity.setRecipientName(tAddressBookEntity.getRecipient());
//        tTotalOrderEntity.setRemark(remark);
//        tTotalOrderEntity.setShipping(shipping);
//        Date dt = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentTime = sdf.format(dt);
//        Timestamp ts = Timestamp.valueOf(currentTime) ;
//        tTotalOrderEntity.setCreateDatetime(ts);
//        tTotalOrderEntity.setQuantity(tShoppingCartDao.getTShoppingCartEntity(cartEntityId).getQuantity());
//        tTotalOrderEntity.setStatus("not-paid");
//        tTotalOrderDao.addTTotalOrderEntity(tTotalOrderEntity);
//    }

}
