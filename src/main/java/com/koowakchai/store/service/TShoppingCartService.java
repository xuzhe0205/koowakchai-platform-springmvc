package com.koowakchai.store.service;

public interface TShoppingCartService {
    public void saveOrUpdateCart(int userId, int subtypeId, int productId, int quantity) throws Exception;
}
