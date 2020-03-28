package com.yajie.stayhome.mapper;

import com.yajie.stayhome.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Lenovo
 */
@Mapper
public interface AdminMapper {
    /**
     * 查询admin用户的账户和密码
     * @param username
     * @param  password
     * @return 返回一个admin对象
     *
     */
    @Select("select * from admin where username=#{username} and password = #{password}")
    Admin selectByUsernameAndPassword(@Param("username") String username,
                                      @Param("password") String password);
}
