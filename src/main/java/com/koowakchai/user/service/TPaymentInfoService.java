package com.koowakchai.user.service;

public interface TPaymentInfoService {

    public void addTPaymentInfoEntity(long userId, String method, String cardNum, String zipcode, String cvv, String expDate) throws Exception;

}
