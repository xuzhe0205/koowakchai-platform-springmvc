package com.koowakchai.store.dao;

import com.koowakchai.hibernate.entity.TLiquorEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TLiquorDao {

    public TLiquorEntity getTLiquorCartItemEntity(int productId) throws Exception;

    public List<TLiquorEntity> getTLiquorEntitySorted(String sortKey, int pageNumber, int pageSize) throws Exception;

    public void reduceTLiquorEntity(int id, int quantity) throws Exception;

    public void deleteTLiquorEntity(int productId) throws Exception;

    public List<TLiquorEntity> searchTLiquorEntityList(String keyword) throws Exception;
}
