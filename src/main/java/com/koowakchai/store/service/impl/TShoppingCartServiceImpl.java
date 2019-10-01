package com.koowakchai.store.service.impl;

import com.koowakchai.store.dao.TShoppingCartDao;
import com.koowakchai.store.entity.TShoppingCartEntity;
import com.koowakchai.store.service.TShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TShoppingCartServiceImpl implements TShoppingCartService {

    @Autowired
    private TShoppingCartDao tShoppingCartDao;

    public void saveOrUpdateCart(int userId, int subtypeId, int productId, int quantity) throws Exception{
        TShoppingCartEntity tShoppingCartEntity = new TShoppingCartEntity();
        tShoppingCartEntity.setProductId(productId);
        tShoppingCartEntity.setQuantity(quantity);
        tShoppingCartEntity.setUserId(userId);
        tShoppingCartEntity.setProductSubtypeId(subtypeId);
        tShoppingCartDao.saveOrUpdateCart(tShoppingCartEntity);
    }
}
