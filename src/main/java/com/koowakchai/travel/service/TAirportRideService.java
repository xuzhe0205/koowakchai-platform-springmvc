package com.koowakchai.travel.service;

import com.koowakchai.hibernate.entity.TAirportInfoEntity;
import com.koowakchai.hibernate.entity.TTravelOrderEntity;

import java.util.List;

public interface TAirportRideService {
    public List<TAirportInfoEntity> getAirportInfoByCondition(String city, String airport) throws Exception;

    public void addAirportRideOrder(int airportInfoId, long userId, String pickupTime, String pickupLocation, String dropoffLocation, double price, int numberPassenger, String flightNumber) throws Exception;

    public double getAirportRidePrice(long passengerId, String pickupTime, String pickupLocation, String dropoffLocation, double distance);

    public void processAirportRideOrder(long driverId, long passengerId, long travelOrderId) throws Exception;

    public void assignAirportRideOrder(long driverId, long travelId) throws Exception;

    public List<TTravelOrderEntity> getAirportRideOrder() throws Exception;

}
