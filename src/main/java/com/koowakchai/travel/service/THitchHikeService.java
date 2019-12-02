package com.koowakchai.travel.service;

import com.koowakchai.hibernate.entity.TTravelOrderEntity;

import java.util.List;

public interface THitchHikeService {
    public void addHitchHikeOrderEntity(long passengerId, String pickupTime, String pickupLocation, String dropoffLocation, double price, int numberPassenger) throws Exception;
    public double getHitchHikePrice(long passengerId, String pickupTime, String pickupLocation, String dropoffLocation, double distance, int numberPassenger) throws Exception;
    public void processHitchHikingOrder(long driverId, long passengerId, long travelOrderId) throws Exception;
    public List<TTravelOrderEntity> getHitchHikeOrder() throws Exception;
    public void assignHitchHikeOrder(long driverId, long travelId) throws Exception;

}
