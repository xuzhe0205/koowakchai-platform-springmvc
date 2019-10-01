package com.koowakchai.store.dao;

import com.koowakchai.store.entity.TBusinessEntity;

import java.util.List;

public interface TBusinessDao {
    public List<TBusinessEntity> getProductTypes(int subtypeId) throws Exception;

    public List<String> getAllBusinessTypes() throws Exception;

    public void saveOrUpdateTBusinessType(TBusinessEntity tBusinessTypeEntity) throws Exception;

}
