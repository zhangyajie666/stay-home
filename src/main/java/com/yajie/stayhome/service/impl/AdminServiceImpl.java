package com.yajie.stayhome.service.impl;

import com.yajie.stayhome.mapper.AdminMapper;
import com.yajie.stayhome.model.Admin;
import com.yajie.stayhome.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lenovo
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin getAdminByUsernameAndPassword(String username, String password) {
        return adminMapper.selectByUsernameAndPassword(username, password);
    }
}
