package com.koowakchai.user.dao;

import com.koowakchai.hibernate.entity.TUserEntity;
import com.koowakchai.hibernate.entity.TUserRoleEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TUserDao {

    public void insertLoginToken(TUserEntity tUserEntity) throws Exception;

    public List<Object> getUserInfo(String username, String password, String roleName) throws Exception;

    public Long getUserId (String email) throws Exception;

    public void saveOrUpdateTUser(TUserEntity tUserEntity) throws Exception;

    public void saveOrUpdateTUserRole(TUserRoleEntity tUserRoleEntity) throws Exception;
}
