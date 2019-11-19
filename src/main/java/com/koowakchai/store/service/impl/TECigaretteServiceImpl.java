package com.koowakchai.store.service.impl;

import com.koowakchai.hibernate.entity.TECigaretteEntity;
import com.koowakchai.store.dao.TECigaretteDao;
import com.koowakchai.store.service.TECigaretteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TECigaretteServiceImpl implements TECigaretteService {

    @Autowired
    private TECigaretteDao teCigaretteDao;

    @Override
    public List<TECigaretteEntity> getTEcigaEntitySorted(String sortKey) throws Exception{
        List<TECigaretteEntity> teCigaretteEntityList = teCigaretteDao.getTEcigaEntitySorted(sortKey);
        return teCigaretteEntityList;
    }
}
