package com.koowakchai.store.service;

import com.koowakchai.hibernate.entity.TLiquorEntity;

import java.util.List;

public interface TLiquorService {
    public List<TLiquorEntity> getTLiquorEntitySorted(String sortKey) throws Exception;
}
