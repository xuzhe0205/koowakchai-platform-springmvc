package com.koowakchai.user.service;

import java.util.List;

public interface TUserService {
    public List<Object> getUserInfo(String username, String password, String roleName) throws Exception;
    public void addTUserEntity(String username, String password, String email, String dob) throws Exception;
    public void saveOrUpdateTUserRole(String roleName, String email) throws Exception;
    public void saveOrUpdateTUserEntity(long userId, String userUrl, String gender, String userPhone) throws Exception;

}
