package com.yajie.stayhome.mapper;

import com.yajie.stayhome.model.Order;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author Lenovo
 */
@Mapper
public interface OrderMapper {
    @Insert("insert into `order`(id, create_time, user_id, arrival_date, departure_date," +
            "adult_number, children_number, room_id, total) VALUES(#{id}, #{createTime}, #{userId}, " +
            "#{arrivalDate}, #{departureDate}, #{adultNumber}, #{childrenNumber}, #{roomId},#{total})")
    void insert(@Param("id") String id,
                @Param("createTime") Date createTime,
                @Param("userId") Integer userId,
                @Param("arrivalDate") Date arrivalDate,
                @Param("departureDate") Date departureDate,
                @Param("adultNumber") Integer adultNumber,
                @Param("childrenNumber") Integer childrenNumber,
                @Param("roomId") Integer roomId,
                @Param("total") Integer total);

    @Select("select * from `order` where user_id = #{userId} ORDER BY create_time DESC")
    @Results({
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "user", column = "user_id",
                    one = @One(select = "com.yajie.stayhome.mapper.UserMapper.selectById")),
            @Result(property = "arrivalDate", column = "arrival_date"),
            @Result(property = "departureDate", column = "departure_date"),
            @Result(property = "adultNumber", column = "adult_number"),
            @Result(property = "childrenNumber", column = "children_number"),
            @Result(property = "roomDetail", column = "room_id",
                    one = @One(select = "com.yajie.stayhome.mapper.RoomDetailMapper.selectById")),
            @Result(property = "comment", column = "comment"),
            @Result(property = "arrivalTime", column = "arrival_time"),
            @Result(property = "departureTime", column = "departure_time")
    })
    List<Order> selectByUserId(Integer userId);

    @Delete("delete from `order` where id = #{id}")
    void deleteById(String id);

    @Update("update `order` set `comment` = #{comment} where id = #{id}")
    void updateCommentById(@Param("comment") String comment,
                           @Param("id") String id);

    @Select("select count(*) from `order`")
    Integer selectCount();

    @Select("SELECT count(*) FROM `order` WHERE `comment` IS NOT NULL")
    Integer selectCommentCount();

    @Select("SELECT o.* FROM `order` o JOIN room_detail r ON o.room_id=r.id " +
            "WHERE r.owner_id=#{ownerId} AND o.arrival_time IS NULL AND o.departure_date > NOW()" +
            " AND o.arrival_date <= NOW() ORDER BY create_time desc")
    @Results({
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "user", column = "user_id",
                    one = @One(select = "com.yajie.stayhome.mapper.UserMapper.selectById")),
            @Result(property = "arrivalDate", column = "arrival_date"),
            @Result(property = "departureDate", column = "departure_date"),
            @Result(property = "adultNumber", column = "adult_number"),
            @Result(property = "childrenNumber", column = "children_number"),
            @Result(property = "roomDetail", column = "room_id",
                    one = @One(select = "com.yajie.stayhome.mapper.RoomDetailMapper.selectById")),
            @Result(property = "comment", column = "comment"),
            @Result(property = "arrivalTime", column = "arrival_time"),
            @Result(property = "departureTime", column = "departure_time")
    })
    List<Order> selectNonArrivalOrderByOwnerId(Integer ownerId);

    @Update("update `order` set arrival_time = #{arrivalTime} where id = #{id}")
    Integer updateArrivalTimeById(@Param("id") String id, @Param("arrivalTime") Date arrivalTime);

    @Select("SELECT o.* FROM `order` o JOIN room_detail r ON o.room_id=r.id " +
            "WHERE r.owner_id = #{ownerId} AND o.arrival_time IS NOT NULL" +
            " AND o.departure_time IS NULL")
    @Results({
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "user", column = "user_id",
                    one = @One(select = "com.yajie.stayhome.mapper.UserMapper.selectById")),
            @Result(property = "arrivalDate", column = "arrival_date"),
            @Result(property = "departureDate", column = "departure_date"),
            @Result(property = "adultNumber", column = "adult_number"),
            @Result(property = "childrenNumber", column = "children_number"),
            @Result(property = "roomDetail", column = "room_id",
                    one = @One(select = "com.yajie.stayhome.mapper.RoomDetailMapper.selectById")),
            @Result(property = "comment", column = "comment"),
            @Result(property = "arrivalTime", column = "arrival_time"),
            @Result(property = "departureTime", column = "departure_time")
    })
    List<Order> selectArrivalOrderByOwnerId(Integer ownerId);

    @Update("update `order` set departure_time = #{departureTime} where id = #{id}")
    Integer updateDepartureTimeById(@Param("id") String id, @Param("departureTime") Date departureTime);

    @Select("SELECT arrival_time FROM `order` WHERE id = #{id}")
    Date getArrivalTimeByOrder(@Param("id") String id);
}
