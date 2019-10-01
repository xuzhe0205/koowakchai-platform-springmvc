package com.koowakchai.store.service;

import com.koowakchai.store.entity.TShoppingCartEntity;

public interface TShoppingCartService {
    public void saveOrUpdateCart(int userId, int subtypeId, int productId, int quantity) throws Exception;
}
