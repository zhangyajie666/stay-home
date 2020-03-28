package com.yajie.stayhome.mapper;

import com.yajie.stayhome.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    @Results({
            @Result(column = "create_time", property = "createTime")
    })
    User selectByUsername(String username);

    @Select("select * from user where username = #{username} and password = #{password}")
    @Results({
            @Result(column = "create_time", property = "createTime")
    })
    User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("select * from user where id = #{id}")
    @Results({
            @Result(column = "create_time", property = "createTime")
    })
    User selectById(Integer id);

    @Insert("insert into user (username, phone, email, password, role, create_time) values" +
            " (#{username}, #{phone}, #{email}, #{password}, #{role}, #{createTime})")
    void insert(User user);

    @SelectProvider(type = UserDynamicSqlProvider.class, method = "selectWithParam")
    @Results({
            @Result(column = "create_time", property = "createTime")
    })
    List<User> selectWithParam(Map<String, String> map);

    @Update("update user set " +
            "phone = #{phone}, email = #{email}, role = #{role}, password = #{password} " +
            "where username = #{username}")
    Integer updateByUsername(User user);

    @Delete("delete from user where id = #{id}")
    Integer deleteById(Integer id);

    @Delete("<script>" +
                "delete from user where id in " +
                "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>" +
                    "#{item}" +
                "</foreach>" +
            "</script>")
    Integer deleteByList(List<Integer> list);

    @Select("select count(*) from `user`")
    Integer selectCount();
}
