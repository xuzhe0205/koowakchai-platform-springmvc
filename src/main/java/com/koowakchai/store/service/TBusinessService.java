package com.koowakchai.store.service;

import com.koowakchai.store.entity.TBusinessEntity;

import java.util.List;

public interface TBusinessService {
    public List<TBusinessEntity> getProductTypes(int subtypeId) throws Exception;

    public List<String> getAllBusinessTypes() throws Exception;

    public void saveOrUpdateTBusinessType(TBusinessEntity tBusinessTypeEntity) throws Exception;
}
