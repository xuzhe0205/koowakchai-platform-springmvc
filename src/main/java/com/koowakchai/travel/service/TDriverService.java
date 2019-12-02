package com.koowakchai.travel.service;

import java.util.Map;

public interface TDriverService {
    public Map<String, String> getDriverInfo(long driverId) throws Exception;
    public void addDriverInfo(long userId, String driverLicense, String vehicleMake, String vehicleModel, String vehicleYear, String vehicleClass) throws  Exception;
}
