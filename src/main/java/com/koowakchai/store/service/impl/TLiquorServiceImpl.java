package com.koowakchai.store.service.impl;

import com.koowakchai.hibernate.entity.TLiquorEntity;
import com.koowakchai.store.dao.TLiquorDao;
import com.koowakchai.store.service.TLiquorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TLiquorServiceImpl implements TLiquorService {

    @Autowired
    private TLiquorDao tLiquorDao;

    @Override
    public List<TLiquorEntity> getTLiquorEntitySorted(String sortKey) throws Exception{
        List<TLiquorEntity> tLiquorEntityList = tLiquorDao.getTLiquorEntitySorted(sortKey);
        return tLiquorEntityList;
    }
}
