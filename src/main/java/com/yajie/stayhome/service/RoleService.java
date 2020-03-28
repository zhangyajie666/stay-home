package com.yajie.stayhome.service;

import com.yajie.stayhome.model.Role;

/**
 * @author Lenovo
 */
public interface RoleService {

    /**
     * 根据name获取角色
     * @param name
     * @return
     */
    Role getRoleByName(String name);
}
