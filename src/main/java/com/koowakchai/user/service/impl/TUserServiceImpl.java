package com.koowakchai.user.service.impl;

import com.koowakchai.hibernate.entity.TUserEntity;
import com.koowakchai.hibernate.entity.TUserRoleEntity;
import com.koowakchai.user.dao.TUserDao;
import com.koowakchai.user.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TUserServiceImpl implements TUserService {

    @Autowired
    private TUserDao tUserDao;

    @Override
    public List<Object> getUserInfo(String username, String password, String roleName)throws Exception {
        return tUserDao.getUserInfo(username, password, roleName);
    }

    @Override
    public void addTUserEntity(String username, String password, String email, String dob) throws Exception{
        TUserEntity tUserEntity = new TUserEntity();
        tUserEntity.setDob(dob);
        tUserEntity.setEmail(email);
        tUserEntity.setPassword(password);
        tUserEntity.setUsername(username);
        tUserDao.addTUserEntity(tUserEntity);
    }

    @Override
    public void saveOrUpdateTUserRole(String roleName, String email) throws Exception{
        int roleId = 0;
        switch (roleName){
            case "deliveryman":
                roleId = 1;
                break;
            case "driver":
                roleId = 2;
                break;
            case "customer":
                roleId = 3;
                break;
            case "store staff":
                roleId = 4;
                break;
        }
        Long userId = tUserDao.getUserId(email);

        TUserRoleEntity tUserRoleEntity = new TUserRoleEntity();
        tUserRoleEntity.setUserId(userId);
        tUserRoleEntity.setRoleId(roleId);
        tUserRoleEntity.setRoleName(roleName);

        tUserDao.saveOrUpdateTUserRole(tUserRoleEntity);

    }

    @Override
    public void saveOrUpdateTUserEntity(long userId, String userUrl, String gender, String userPhone) throws Exception{
        tUserDao.saveOrUpdateTUserEntity(userId, userUrl, gender, userPhone);
    }
}
