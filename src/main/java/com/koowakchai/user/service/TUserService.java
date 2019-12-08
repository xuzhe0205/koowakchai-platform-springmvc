package com.koowakchai.user.service;

import com.koowakchai.hibernate.entity.TUserEntity;

import java.util.List;

public interface TUserService {
    public List<Object> getUserInfo(String username, String password, String roleName) throws Exception;
    public Long addTUserEntity(String username, String password, String email, String dob, String gender) throws Exception;
    public void saveOrUpdateTUserRole(String roleName, String email) throws Exception;
    public void saveOrUpdateTUserEntity(long userId, String userUrl, String email, String userPhone) throws Exception;
    public List<TUserEntity> getDriverByStatus(String status);
    public void updateUserRegion(long userId, String region) throws Exception;
    public List<TUserEntity> getDeliverymanByStatus(String status);

}
