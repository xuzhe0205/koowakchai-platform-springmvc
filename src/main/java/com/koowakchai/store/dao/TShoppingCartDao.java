package com.koowakchai.store.dao;

import com.koowakchai.store.entity.TShoppingCartEntity;

public interface TShoppingCartDao {
    public void saveOrUpdateCart(TShoppingCartEntity tShoppingCartEntity) throws Exception;
}
