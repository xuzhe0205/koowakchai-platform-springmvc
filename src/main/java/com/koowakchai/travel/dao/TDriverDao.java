package com.koowakchai.travel.dao;

import com.koowakchai.hibernate.entity.TDriverEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TDriverDao {
    public TDriverEntity getTDriverEntity(long driverId) throws Exception;
    public void addTDriverEntity (TDriverEntity tDriverEntity) throws Exception;
    public void updateTDriverInfoEntity(long driverId, long assignedTripId) throws Exception;
}
