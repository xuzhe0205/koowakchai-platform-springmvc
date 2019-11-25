package com.koowakchai.store.service;

import java.util.List;

public interface StoreEmailService {
    public Boolean sendConfirmationEmail(List<Long> ordersIds) throws Exception;
}
