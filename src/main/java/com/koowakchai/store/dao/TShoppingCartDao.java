package com.koowakchai.store.dao;

import com.koowakchai.store.entity.TShoppingCartEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TShoppingCartDao {
    public void saveOrUpdateCart(TShoppingCartEntity tShoppingCartEntity) throws Exception;
}
