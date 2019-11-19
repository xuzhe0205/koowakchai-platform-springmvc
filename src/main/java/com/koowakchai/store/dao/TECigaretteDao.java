package com.koowakchai.store.dao;

import com.koowakchai.hibernate.entity.TECigaretteEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TECigaretteDao {


    public TECigaretteEntity getTECigaCartItemEntity(int productId) throws Exception;

    public List<TECigaretteEntity> getTEcigaEntitySorted(String sortKey) throws Exception;
}
