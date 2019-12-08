package com.koowakchai.user.service;

public interface TPaymentInfoService {

    public void addTPaymentInfoEntity(long userId, String cardholderName, String cardNum, String zipcode, String cvv, String expDate) throws Exception;

}
