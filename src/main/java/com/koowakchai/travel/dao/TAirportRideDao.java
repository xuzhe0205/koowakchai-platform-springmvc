package com.koowakchai.travel.dao;

import com.koowakchai.hibernate.entity.TAirportInfoEntity;
import com.koowakchai.hibernate.entity.TTravelOrderEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TAirportRideDao {

    public List<TAirportInfoEntity> getTAirportEntityByCondition(String city, String airport) throws Exception;

    public void addAirportRideOrder(TTravelOrderEntity tTravelOrderEntity) throws Exception;

    public void updateAirportRideOrderEntity(Long travelOrderId, Long driverId, String status) throws Exception;

    public List<TTravelOrderEntity> getTravelOrderEntityByType(String type) throws Exception;

    public void assignAirportRideOrder(long driverId, long travelOrderId) throws Exception;
}
