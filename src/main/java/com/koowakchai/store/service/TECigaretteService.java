package com.koowakchai.store.service;

import com.koowakchai.hibernate.entity.TECigaretteEntity;

import java.util.List;

public interface TECigaretteService {

    public List<TECigaretteEntity> getTEcigaEntitySorted(String sortKey, int pageNumber, int pageSize) throws Exception;

    public void deleteTECigaEntity(int productId) throws Exception;

    public List<TECigaretteEntity> searchTECigaretteEntityList(String keyword) throws Exception;
}
