package com.koowakchai.user.dao.impl;

import com.koowakchai.hibernate.entity.TUserEntity;
import com.koowakchai.hibernate.entity.TUserRoleEntity;
import com.koowakchai.user.dao.TUserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class TUserDaoImpl implements TUserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insertLoginToken(TUserEntity tUserEntity) throws Exception {

    }

    @Override
    public List<Object> getUserInfo(String username, String password, String roleName) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createSQLQuery("select t_user.*, t_user_role.role_name from t_user join t_user_role " +
                "on t_user.id = t_user_role.user_id where username = :username and password = :password and role_name = :roleName");
        List<Object> userInfoEntity = query.setParameter("username", username).setParameter("password", password).setParameter("roleName", roleName).getResultList();

        return userInfoEntity;
    }

    @Override
    public Long getUserId (String email) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        NativeQuery query = session.createSQLQuery("select t_user.id from t_user where t_user.email = :email");
        List<Object> idList = query.setParameter("email", email).getResultList();
        Long userId = ((BigInteger)idList.get(0)).longValue();
        return userId;
    }

    @Override
    public void saveOrUpdateTUser(TUserEntity tUserEntity) throws Exception{
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(tUserEntity);
        tx.commit();
        session.close();
    }

    @Override
    public void saveOrUpdateTUserRole(TUserRoleEntity tUserRoleEntity) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(tUserRoleEntity);
    }
}
