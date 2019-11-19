package com.koowakchai.user.service;

import com.koowakchai.hibernate.entity.TAddressBookEntity;

public interface TAddressBookService {
    public void saveOrUpdateAddress(long userId, String recipientName, String recipientPhone, String recipientAddr) throws Exception;
}
