package com.koowakchai.store.service;

public interface TShoppingCartService {
    public void saveOrUpdateCart(long userId, int subtypeId, int productId, int quantity) throws Exception;
//    public void getCartItem(int)
}
