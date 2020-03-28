package com.yajie.stayhome.mapper;

import com.yajie.stayhome.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Lenovo
 */
@Mapper
public interface RoleMapper {

    @Select("select * from role where `name` = #{name}")
    Role selectByName(String name);
}
