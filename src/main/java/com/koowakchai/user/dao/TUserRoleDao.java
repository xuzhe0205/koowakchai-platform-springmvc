package com.koowakchai.user.dao;

import com.koowakchai.hibernate.entity.TUserRoleEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TUserRoleDao {
    public void saveOrUpdateTUserRole(TUserRoleEntity tUserRoleEntity) throws Exception;

}
