package com.koowakchai.user.dao.impl;

import com.koowakchai.hibernate.entity.TUserRoleEntity;
import com.koowakchai.user.dao.TUserRoleDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TUserRoleDaoImpl implements TUserRoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveOrUpdateTUserRole(TUserRoleEntity tUserRoleEntity) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tUserRoleEntity);
    }
}
