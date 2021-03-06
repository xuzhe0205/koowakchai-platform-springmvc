package com.koowakchai.store.dao;

import com.koowakchai.hibernate.entity.TShoppingCartEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TShoppingCartDao {
    public void saveOrUpdateCart(TShoppingCartEntity tShoppingCartEntity) throws Exception;
    public List<TShoppingCartEntity> getCartItem(long userId) throws Exception;
    public TShoppingCartEntity getTShoppingCartEntity(int cartEntityId) throws Exception;
    public void deleteTShoppingCartEntity(int cartEntityId) throws Exception;
}
