package com.koowakchai.user.service.impl;

import com.koowakchai.common.distribution.GenerateTask;
import com.koowakchai.hibernate.entity.TUserEntity;
import com.koowakchai.hibernate.entity.TUserRoleEntity;
import com.koowakchai.errand.dao.TDeliverymanDao;
import com.koowakchai.user.dao.TUserDao;
import com.koowakchai.user.dao.TUserRoleDao;
import com.koowakchai.user.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TUserServiceImpl implements TUserService {

    @Autowired
    private TUserDao tUserDao;

    @Autowired
    private TUserRoleDao tUserRoleDao;

    @Autowired
    private GenerateTask generateTask;

    @Autowired
    private TDeliverymanDao tDeliverymanDao;

    @Override
    public List<Object> getUserInfo(String username, String password, String roleName)throws Exception {
        return tUserDao.getUserInfo(username, password, roleName);
    }

    @Override
    public Long addTUserEntity(String username, String password, String email, String dob, String gender) throws Exception{
        TUserEntity tUserEntity = new TUserEntity();
        tUserEntity.setDob(dob);
        tUserEntity.setEmail(email);
        tUserEntity.setPassword(password);
        tUserEntity.setUsername(username);
        tUserEntity.setGender(gender);
        return(tUserDao.addTUserEntity(tUserEntity));
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

        tUserRoleDao.saveOrUpdateTUserRole(tUserRoleEntity);

    }

    @Override
    public void saveOrUpdateTUserEntity(long userId, String userUrl, String email, String userPhone) throws Exception{
        tUserDao.saveOrUpdateTUserEntity(userId, userUrl, email, userPhone);
    }

    @Override
    public List<TUserEntity> getDriverByStatus(String status){
        try{
//            generateTask.setRole();
//            generateTask.run();
            return tUserDao.getDriverByStatus(status);
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public void updateUserRegion(long userId, String region) throws Exception{
        tUserDao.updateUserRegion(userId, region);
    }

    @Override
    public List<TUserEntity> getDeliverymanByStatus(String status) {
        return tUserDao.getDeliverymanByStatus(status);
    }
}
