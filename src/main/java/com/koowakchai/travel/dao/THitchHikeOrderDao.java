package com.koowakchai.travel.dao;

import com.koowakchai.hibernate.entity.TTravelOrderEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface THitchHikeOrderDao {

    public void addHitchHikeOrderEntity(TTravelOrderEntity tTravelOrderEntity) throws Exception;

    public void updateHitchHikeOrderEntity(Long travelOrderId, Long driverId, String status) throws Exception;

    public List<TTravelOrderEntity> getTravelOrderEntityByType(String type) throws Exception;

    public void assignAirportRideOrder(long driverId, long travelOrderId) throws Exception;


}
