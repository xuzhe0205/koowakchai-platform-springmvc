package com.koowakchai.store.service.impl;

import com.koowakchai.hibernate.entity.ShoppingCartItemEntity;
import com.koowakchai.hibernate.entity.TECigaretteEntity;
import com.koowakchai.hibernate.entity.TLiquorEntity;
import com.koowakchai.hibernate.entity.TShoppingCartEntity;
import com.koowakchai.store.dao.TECigaretteDao;
import com.koowakchai.store.dao.TLiquorDao;
import com.koowakchai.store.dao.TShoppingCartDao;
import com.koowakchai.store.service.TShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TShoppingCartServiceImpl implements TShoppingCartService {

    @Autowired
    private TShoppingCartDao tShoppingCartDao;

    @Autowired
    private TECigaretteDao teCigaretteDao;

    @Autowired
    private TLiquorDao tLiquorDao;

    @Override
    public void saveOrUpdateCart(long userId, int subtypeId, int productId, int quantity) throws Exception{
        TShoppingCartEntity tShoppingCartEntity = new TShoppingCartEntity();
        tShoppingCartEntity.setProductId(productId);
        tShoppingCartEntity.setQuantity(quantity);
        tShoppingCartEntity.setUserId(userId);
        tShoppingCartEntity.setProductSubtypeId(subtypeId);
        tShoppingCartDao.saveOrUpdateCart(tShoppingCartEntity);
    }

    @Override
    public List<ShoppingCartItemEntity> getCartItem(long userId) throws Exception{
        List<TShoppingCartEntity> tShoppingCartEntityList = tShoppingCartDao.getCartItem(userId);
        List<ShoppingCartItemEntity> cartItemList = new ArrayList<>();
        for (TShoppingCartEntity tShoppingCartEntity : tShoppingCartEntityList){
            ShoppingCartItemEntity shoppingCartItemEntity = new ShoppingCartItemEntity();
            if (tShoppingCartEntity.getProductSubtypeId() == 1){
                TECigaretteEntity teCigaretteEntity = teCigaretteDao.getTECigaCartItemEntity(tShoppingCartEntity.getProductId());
                shoppingCartItemEntity.setBrand(teCigaretteEntity.getBrand());
                shoppingCartItemEntity.setCategory(teCigaretteEntity.getCategory());
                shoppingCartItemEntity.setItemName(teCigaretteEntity.getName());
                shoppingCartItemEntity.setPrice(teCigaretteEntity.getPrice());
                shoppingCartItemEntity.setQuantity(tShoppingCartEntity.getQuantity());
                shoppingCartItemEntity.setProductUrl(tShoppingCartEntity.getProductUrl());
                cartItemList.add(shoppingCartItemEntity);
            }
            else if (tShoppingCartEntity.getProductSubtypeId() == 2){
                TLiquorEntity tLiquorEntity = tLiquorDao.getTLiquorCartItemEntity(tShoppingCartEntity.getProductId());
                shoppingCartItemEntity.setQuantity(tShoppingCartEntity.getQuantity());
                shoppingCartItemEntity.setPrice(tLiquorEntity.getPrice());
                shoppingCartItemEntity.setItemName(tLiquorEntity.getName());
                shoppingCartItemEntity.setCategory(tLiquorEntity.getCategory());
                shoppingCartItemEntity.setBrand(tLiquorEntity.getBrand());
                shoppingCartItemEntity.setCategory(tLiquorEntity.getCategory());
                shoppingCartItemEntity.setProductUrl(tShoppingCartEntity.getProductUrl());
                cartItemList.add(shoppingCartItemEntity);
            }
        }
        return cartItemList;
    }
}
