package com.koowakchai.store.dao;

import com.koowakchai.store.entity.TBusinessEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TBusinessDao {
    public List<TBusinessEntity> getProductTypes(int subtypeId) throws Exception;

    public List<String> getAllBusinessTypes() throws Exception;

    public void saveOrUpdateTBusinessType(TBusinessEntity tBusinessTypeEntity) throws Exception;

}
