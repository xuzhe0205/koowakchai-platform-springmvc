package com.koowakchai.store.service;

import com.koowakchai.hibernate.entity.TECigaretteEntity;

import java.util.List;

public interface TECigaretteService {

    public List<TECigaretteEntity> getTEcigaEntitySorted(String sortKey) throws Exception;
}
