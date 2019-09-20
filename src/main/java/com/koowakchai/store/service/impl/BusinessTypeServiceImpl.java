package com.koowakchai.store.service.impl;

import com.koowakchai.store.dao.BusinessTypeDao;
import com.koowakchai.store.entity.BusinessTypeEntity;
import com.koowakchai.store.service.BusinessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BusinessTypeServiceImpl implements BusinessTypeService {

    @Autowired
    private BusinessTypeDao businessTypeDao;

    public List<BusinessTypeEntity> getBusinessTypes() throws Exception{
        return businessTypeDao.getBusinessTypes();
    }

    public void saveOrUpdateBusinessType(BusinessTypeEntity businessTypeEntity) throws Exception{
        businessTypeDao.saveOrUpdateBusinessType(businessTypeEntity);
    }
}
