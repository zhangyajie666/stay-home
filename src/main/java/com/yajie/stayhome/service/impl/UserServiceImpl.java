package com.yajie.stayhome.service.impl;

import com.yajie.stayhome.mapper.UserMapper;
import com.yajie.stayhome.model.User;
import com.yajie.stayhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username, password);
    }

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public List<User> getUserWithParam(Map<String, String> map) {
        return userMapper.selectWithParam(map);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public Integer modifyUserByUsername(User user) {
        return userMapper.updateByUsername(user);
    }

    @Override
    public Integer removeUserById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public Integer removeUserByList(List<Integer> list) {
        return userMapper.deleteByList(list);
    }

    @Override
    public Integer getUserCount() {
        return userMapper.selectCount();
    }

}
