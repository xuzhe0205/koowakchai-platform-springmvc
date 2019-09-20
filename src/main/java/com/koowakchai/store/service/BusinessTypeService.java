package com.koowakchai.store.service;

import com.koowakchai.store.entity.BusinessTypeEntity;

import java.util.List;

public interface BusinessTypeService {
    public List<BusinessTypeEntity> getBusinessTypes() throws Exception;

    public void saveOrUpdateBusinessType(BusinessTypeEntity businessTypeEntity) throws Exception;
}
