package com.yajie.stayhome.mapper;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author Lenovo
 */
public class UserDynamicSqlProvider {

    public String selectWithParam(Map<String, String> map) {
        return new SQL() {
            {
                SELECT("*");
                FROM("user");
                if (map.get("username") != null && !("".equals(map.get("username")))) {
                    WHERE("username = #{username}");
                }
                if (map.get("start") != null && !("".equals(map.get("start")))) {
                    WHERE("create_time >= #{start}");
                }
                if (map.get("end") != null && !("".equals(map.get("end")))) {
                    WHERE("create_time <= #{end}");
                }
            }
        }.toString();
    }
}
