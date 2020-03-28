package com.yajie.stayhome.service;

import com.yajie.stayhome.model.Admin;

/**
 * @author Lenovo
 */
public interface AdminService {

    /**
     * 根据用户名获取admin
     * @param username
     * @param password
     * @return
     */
    Admin getAdminByUsernameAndPassword(String username, String password);
}
