package com.koowakchai.store.service;


import com.koowakchai.hibernate.entity.TTotalOrderEntity;

import java.util.List;
import java.util.Map;

public interface TTotalOrderService {

    public long addTTotalOrderEntity(int cartEntityId, String remark) throws Exception;

    public Map<String, List<TTotalOrderEntity>> getTTotalOrderEntityList() throws Exception;

}
