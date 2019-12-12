package com.koowakchai.store.service.impl;

import com.koowakchai.hibernate.entity.TLogisticsCompanyEntity;
import com.koowakchai.store.dao.TLogisticsCompanyDao;
import com.koowakchai.store.service.TLogisticsCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TLogisticsCompanyServiceImpl implements TLogisticsCompanyService {

    @Autowired
    private TLogisticsCompanyDao tLogisticsCompanyDao;

    @Override
    public List<TLogisticsCompanyEntity> getTLogisticsCompanyEntity() throws Exception {
        return tLogisticsCompanyDao.getTLogisticsCompanyEntity();
    }
}
