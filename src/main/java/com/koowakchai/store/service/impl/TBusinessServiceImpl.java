package com.koowakchai.store.service.impl;

import com.koowakchai.store.dao.TBusinessDao;
import com.koowakchai.store.entity.TBusinessEntity;
import com.koowakchai.store.service.TBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TBusinessServiceImpl implements TBusinessService {

    @Autowired
    private TBusinessDao tBusinessDao;

    @Override
    public List<String> getAllBusinessTypes() throws Exception{
        return tBusinessDao.getAllBusinessTypes();
    }

    @Override
    public List<TBusinessEntity> getProductTypes(int subtypeId) throws Exception{
        return tBusinessDao.getProductTypes(subtypeId);
    }

    @Override
    public void saveOrUpdateTBusinessType(TBusinessEntity tBusinessEntity) throws Exception{
        tBusinessDao.saveOrUpdateTBusinessType(tBusinessEntity);
    }
}
