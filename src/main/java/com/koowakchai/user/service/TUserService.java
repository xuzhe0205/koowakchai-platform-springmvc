package com.koowakchai.user.service;

import java.util.List;

public interface TUserService {
    public List<Object> getUserInfo(String username, String password, String roleName) throws Exception;
    public void saveOrUpdateTUser(String username, String password, String email, String dob) throws Exception;
    public void saveOrUpdateTUserRole(String roleName, String email) throws Exception;

}