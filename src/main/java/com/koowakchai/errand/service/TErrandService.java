package com.koowakchai.errand.service;

public interface TErrandService {
    public double checkErrandFee(double distance, double weight, String errandType, String deliveryTime) throws Exception;
}
