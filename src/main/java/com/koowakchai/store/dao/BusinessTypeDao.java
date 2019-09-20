package com.koowakchai.store.dao;

import com.koowakchai.store.entity.BusinessTypeEntity;

import java.util.List;

public interface BusinessTypeDao {
    public List<BusinessTypeEntity> getBusinessTypes() throws Exception;

    public void saveOrUpdateBusinessType(BusinessTypeEntity businessTypeEntity) throws Exception;
}
