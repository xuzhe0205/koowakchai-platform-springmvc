package com.koowakchai.store.service;

import com.koowakchai.hibernate.entity.TLiquorEntity;

import java.util.List;

public interface TLiquorService {
    public List<TLiquorEntity> getTLiquorEntitySorted(String sortKey, int pageNumber, int pageSize) throws Exception;

    public void deleteTLiquorEntity(int productId) throws Exception;

    public List<TLiquorEntity> searchTLiquorEntityList(String keyword) throws Exception;
}
