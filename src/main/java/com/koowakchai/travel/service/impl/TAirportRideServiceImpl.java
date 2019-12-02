package com.koowakchai.travel.service.impl;

import com.koowakchai.common.distribution.AutoDispatchTaskThread;
import com.koowakchai.common.distribution.GenerateTask;
import com.koowakchai.hibernate.entity.TAirportInfoEntity;
import com.koowakchai.hibernate.entity.TTravelOrderEntity;
import com.koowakchai.travel.dao.TAirportRideDao;
import com.koowakchai.travel.service.TAirportRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TAirportRideServiceImpl implements TAirportRideService {

    @Autowired
    private TAirportRideDao tAirportRideDao;

    @Autowired
    private AutoDispatchTaskThread autoDispatchTaskThread;

    @Autowired
    private GenerateTask generateTask;

    @Override
    public List<TAirportInfoEntity> getAirportInfoByCondition(String city, String airport) throws Exception {

        return tAirportRideDao.getTAirportEntityByCondition(city, airport);
    }

    @Override
    public void addAirportRideOrder(int airportInfoId, long userId, String pickupTime, String pickupLocation, String dropoffLocation, double price, int numberPassenger, String flightNumber) throws Exception {
        TTravelOrderEntity tTravelOrderEntity = new TTravelOrderEntity();
        tTravelOrderEntity.setPassengerId(userId);
        tTravelOrderEntity.setTravelType("airport ride");
        tTravelOrderEntity.setPickupTime(Timestamp.valueOf(pickupTime));
        tTravelOrderEntity.setPrice(price);
        tTravelOrderEntity.setPickupLocation(pickupLocation);
        tTravelOrderEntity.setDropoffLocation(dropoffLocation);
        tTravelOrderEntity.setStatus("requested");
        tTravelOrderEntity.setPickupLocation(pickupTime);
        tTravelOrderEntity.setNumPassenger((byte)numberPassenger);
        tTravelOrderEntity.setFlightNumber(flightNumber);
        tTravelOrderEntity.setAirportInfoId(airportInfoId);

        tAirportRideDao.addAirportRideOrder(tTravelOrderEntity);

        generateTask.setRole("driver");

        generateTask.setTravelType("airport ride");

        autoDispatchTaskThread.run();
    }

    @Override
    public double getAirportRidePrice(long passengerId, String pickupTime, String pickupLocation, String dropoffLocation, double distance) {
        double startPrice = 28;
        double distanceRate = 1.094;
        String[] dateAndTime = pickupTime.split(" ");
        String time =  dateAndTime[1];
        String strPattern = "^0+";
        int hour = Integer.valueOf(time.substring(0,2).replaceAll(strPattern,""));
        if ((hour > 7 && hour < 9) || (hour > 12 && hour < 14) || (hour > 16 && hour < 19)){
            distanceRate = 1.406;
        }
        double price = startPrice + distanceRate * distance;
        return price;
    }

    @Override
    public void processAirportRideOrder(long driverId, long passengerId, long travelOrderId) throws Exception {
        tAirportRideDao.updateAirportRideOrderEntity(travelOrderId, driverId, "accepted");

    }

    @Override
    public void assignAirportRideOrder(long driverId, long travelOrderId) throws Exception {
        tAirportRideDao.assignAirportRideOrder(driverId, travelOrderId);
    }

    @Override
    public List<TTravelOrderEntity> getAirportRideOrder() throws Exception {
        return tAirportRideDao.getTravelOrderEntityByType("airport ride");
    }
}
