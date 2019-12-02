package com.koowakchai.travel.service.impl;

import com.koowakchai.hibernate.entity.TDriverEntity;
import com.koowakchai.hibernate.entity.TUserEntity;
import com.koowakchai.travel.dao.TDriverDao;
import com.koowakchai.travel.service.TDriverService;
import com.koowakchai.user.dao.TUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TDriverServiceImpl implements TDriverService {

    @Autowired
    private TDriverDao tDriverDao;

    @Autowired
    private TUserDao tUserDao;

    @Override
    public Map<String, String> getDriverInfo(long driverId) throws Exception {
        TDriverEntity tDriverEntity = tDriverDao.getTDriverEntity(driverId);
        TUserEntity tUserEntity = tUserDao.getTUserEntity(driverId);
        Map<String, String> driverInfo = new HashMap<>();
        driverInfo.put("driver username", tUserEntity.getUsername());
        driverInfo.put("driver gender", tUserEntity.getGender());
        driverInfo.put("vehicle made", tDriverEntity.getVehicleMake());
        driverInfo.put("vehicle model", tDriverEntity.getVehicleModel());
        driverInfo.put("vehicle year",  Integer.toString(tDriverEntity.getVehcleYear()));
        driverInfo.put("vehicle class", tDriverEntity.getVehicleClass());
        driverInfo.put("rating", Double.toString(tDriverEntity.getRating()));
        driverInfo.put("trip count", Integer.toString(tDriverEntity.getTripCount()));
        return driverInfo;
    }

    @Override
    public void addDriverInfo(long userId, String driverLicense, String vehicleMake, String vehicleModel, String vehicleYear, String vehicleClass) throws Exception {
        TDriverEntity tDriverEntity = new TDriverEntity();
        tDriverEntity.setDriverLicense(driverLicense);
        tDriverEntity.setStatus("rest");
        tDriverEntity.setUserId(userId);
        tDriverEntity.setVehcleYear(Integer.valueOf(vehicleYear));
        tDriverEntity.setVehicleClass(vehicleClass);
        tDriverEntity.setVehicleMake(vehicleMake);
        tDriverEntity.setVehicleModel(vehicleModel);
        tDriverEntity.setRating(0);
        tDriverEntity.setTripCount(0);
        tDriverDao.addTDriverEntity(tDriverEntity);
    }
}
