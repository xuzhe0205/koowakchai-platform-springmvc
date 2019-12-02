package com.koowakchai.user.dao;

import com.koowakchai.hibernate.entity.TUserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TUserDao {

    public void insertLoginToken(TUserEntity tUserEntity) throws Exception;

    public List<Object> getUserInfo(String username, String password, String roleName) throws Exception;

    public Long getUserId (String email) throws Exception;

    public Long addTUserEntity(TUserEntity tUserEntity) throws Exception;

    public void saveOrUpdateTUserEntity(long userId, String userUrl, String gender, String userPhone) throws Exception;

    public TUserEntity getTUserEntity(long userId) throws Exception;

    public List<TUserEntity> getDriverByStatus(String status);

    public void updateUserRegion(long userId, String region) throws Exception;

    public List<TUserEntity> getDeliverymanByStatus(String status);

}
