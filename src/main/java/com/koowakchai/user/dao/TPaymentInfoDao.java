package com.koowakchai.user.dao;

import com.koowakchai.hibernate.entity.TPaymentInfoEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TPaymentInfoDao {

    public void addTPaymentInfoEntity(TPaymentInfoEntity tPaymentInfoEntity) throws Exception;
    public TPaymentInfoEntity getPaymentInfoEntity(long paymentId) throws Exception;
}
