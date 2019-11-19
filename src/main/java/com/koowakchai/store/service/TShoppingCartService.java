package com.koowakchai.store.service;

import com.koowakchai.hibernate.entity.ShoppingCartItemEntity;

import java.util.List;

public interface TShoppingCartService {
    public void saveOrUpdateCart(long userId, int subtypeId, int productId, int quantity) throws Exception;
    public List<ShoppingCartItemEntity> getCartItem(long userId) throws Exception;
}
