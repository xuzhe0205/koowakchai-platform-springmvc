package com.koowakchai.store.dao;

import com.koowakchai.hibernate.entity.TBusinessSubtypeEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TBusinessSubtypeDao {
    public TBusinessSubtypeEntity getTBusinessSubtypeEntity(int subtypeId) throws Exception;
}
