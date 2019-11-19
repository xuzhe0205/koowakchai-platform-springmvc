package com.koowakchai.user.service.impl;

import com.koowakchai.hibernate.entity.TAddressBookEntity;
import com.koowakchai.user.dao.TAddressBookDao;
import com.koowakchai.user.service.TAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TAddressBookServiceImpl implements TAddressBookService {

    @Autowired
    private TAddressBookDao tAddressBookDao;

    @Override
    public void saveOrUpdateAddress(long userId, String recipientName, String recipientPhone, String recipientAddr) throws Exception{
        TAddressBookEntity tAddressBookEntity = new TAddressBookEntity();
        tAddressBookEntity.setUserId(userId);
        tAddressBookEntity.setFullAddr(recipientAddr);
        tAddressBookEntity.setPhoneNum(recipientPhone);
        tAddressBookEntity.setRecipient(recipientName);
        tAddressBookDao.saveOrUpdateAddress(tAddressBookEntity);
    }
}
