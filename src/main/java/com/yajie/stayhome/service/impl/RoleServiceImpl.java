package com.yajie.stayhome.service.impl;

import com.yajie.stayhome.mapper.RoleMapper;
import com.yajie.stayhome.model.Role;
import com.yajie.stayhome.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lenovo
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role getRoleByName(String name) {
        return roleMapper.selectByName(name);
    }
}
