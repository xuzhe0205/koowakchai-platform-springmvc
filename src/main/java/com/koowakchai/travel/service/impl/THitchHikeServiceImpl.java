package com.koowakchai.travel.service.impl;

import com.koowakchai.common.distribution.AutoDispatchTaskThread;
import com.koowakchai.common.distribution.GenerateTask;
import com.koowakchai.hibernate.entity.TTravelOrderEntity;
import com.koowakchai.travel.dao.THitchHikeOrderDao;
import com.koowakchai.travel.service.THitchHikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class THitchHikeServiceImpl implements THitchHikeService {

    @Autowired
    private THitchHikeOrderDao tHitchHikeOrderDao;

    @Autowired
    private AutoDispatchTaskThread autoDispatchTaskThread;

    @Autowired
    private GenerateTask generateTask;

    @Override
    public void addHitchHikeOrderEntity(long passengerId, String pickupTime, String pickupLocation, String dropoffLocation, double price, int numberPassenger) throws Exception {
        TTravelOrderEntity tTravelOrderEntity = new TTravelOrderEntity();
        tTravelOrderEntity.setPassengerId(passengerId);
        tTravelOrderEntity.setNumPassenger((byte)numberPassenger);
        tTravelOrderEntity.setPickupLocation(pickupLocation);
        tTravelOrderEntity.setDropoffLocation(dropoffLocation);
        tTravelOrderEntity.setTravelType("hitch hike");
        tTravelOrderEntity.setPrice(price);
        tTravelOrderEntity.setPickupTime(Timestamp.valueOf(pickupTime));

        tHitchHikeOrderDao.addHitchHikeOrderEntity(tTravelOrderEntity);

        generateTask.setRole("driver");

        generateTask.setTravelType("hitch hike");

        generateTask.run();

        autoDispatchTaskThread.run();

    }

    @Override
    public double getHitchHikePrice(long passengerId, String pickupTime, String pickupLocation, String dropoffLocation, double distance, int numberPassenger) throws Exception {
        double startPrice = 6.0;
        double distanceRate = 0.942;
        String[] dateAndTime = pickupTime.split(" ");
        String time =  dateAndTime[1];
        String strPattern = "^0+";
        int hour = Integer.valueOf(time.substring(0,2).replaceAll(strPattern,""));
        if ((hour > 7 && hour < 9) || (hour > 12 && hour < 14) || (hour > 16 && hour < 19)){
            distanceRate = 1.413;
        }
        double price = startPrice + distanceRate * distance;

        return price;
    }

    @Override
    public void processHitchHikingOrder(long driverId, long passengerId, long travelOrderId) throws Exception {
        tHitchHikeOrderDao.updateHitchHikeOrderEntity(travelOrderId, driverId, "accepted");
    }

    @Override
    public List<TTravelOrderEntity> getHitchHikeOrder() throws Exception {
        return tHitchHikeOrderDao.getTravelOrderEntityByType("hitch hike");
    }

    @Override
    public void assignHitchHikeOrder(long driverId, long travelId) throws Exception {
        tHitchHikeOrderDao.assignAirportRideOrder(driverId, travelId);
    }
}
