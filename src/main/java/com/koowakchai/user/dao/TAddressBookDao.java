package com.koowakchai.user.dao;

import com.koowakchai.hibernate.entity.TAddressBookEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TAddressBookDao {

    public void saveOrUpdateAddress(TAddressBookEntity tAddressBookEntity) throws Exception;
    public TAddressBookEntity getTAddressBookEntity(long addrId) throws Exception;
}
