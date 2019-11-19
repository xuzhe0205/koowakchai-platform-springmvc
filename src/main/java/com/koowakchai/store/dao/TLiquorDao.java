package com.koowakchai.store.dao;

import com.koowakchai.hibernate.entity.TLiquorEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TLiquorDao {

    public TLiquorEntity getTLiquorCartItemEntity(int productId) throws Exception;

    public List<TLiquorEntity> getTLiquorEntitySorted(String sortKey) throws Exception;
}
