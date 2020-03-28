package com.yajie.stayhome.service;

import com.yajie.stayhome.model.User;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
public interface UserService {

    /**
     * 根据username获取User信息
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 根据username和password获取User信息
     * @param username
     * @param password
     * @return
     */
    User getUserByUsernameAndPassword(String username, String password);

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);

    /**
     * 根据指定参数获取User集合
     * @return
     */
    List<User> getUserWithParam(Map<String, String> map);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 根据用户名修改用户信息
     * @param user
     * @return
     */
    Integer modifyUserByUsername(User user);

    /**
     * 根据id删除用户信息
     * @param id
     * @return
     */
    Integer removeUserById(Integer id);

    /**
     * 根据List中的id删除用户
     * @param list
     * @return
     */
    Integer removeUserByList(List<Integer> list);

    /**
     * 获取用户数
     * @return
     */
    Integer getUserCount();
}
