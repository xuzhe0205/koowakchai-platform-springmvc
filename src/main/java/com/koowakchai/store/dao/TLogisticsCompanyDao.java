package com.koowakchai.store.dao;

import com.koowakchai.hibernate.entity.TLogisticsCompanyEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TLogisticsCompanyDao {

    public TLogisticsCompanyEntity getTLogisticsCompanyEntity(int logisticsCompanyId) throws Exception;

}
