package com.yajie.stayhome.mapper;

import com.yajie.stayhome.model.RoomDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Lenovo
 */
@Mapper
public interface RoomDetailMapper {

    @Select("select * from room_detail")
    @Results({
            @Result(property = "arrivalTime", column = "arrival_time"),
            @Result(property = "departureTime", column = "departure_time")
    })
    List<RoomDetail> select();

    @Select("select rd.* from room_detail rd JOIN room_type rt ON rd.type=rt.id WHERE rt.type=#{type}")
    @Results({
            @Result(property = "arrivalTime", column = "arrival_time"),
            @Result(property = "departureTime", column = "departure_time")
    })
    List<RoomDetail> selectByType(String type);

    @Select("select * from room_detail where id = #{id}")
    @Results({
            @Result(property = "arrivalTime", column = "arrival_time"),
            @Result(property = "departureTime", column = "departure_time")
    })
    RoomDetail selectById(Integer id);

    @Select("select count(*) from room_detail")
    Integer selectCount();

    @UpdateProvider(type = RoomDetailDynamicSqlProvider.class, method = "updateRoomDetail")
    Integer updateById(RoomDetail roomDetail);

    @Delete("delete from room_detail where id = #{id}")
    Integer deleteById(Integer id);

    @Delete("<script>" +
            "delete from room_detail where id in " +
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    Integer deleteByList(List<Integer> list);

    @Insert("insert into room_detail (owner_id, introduce, price, capacity, size, " +
            "arrival_time, departure_time, type, picture) values(#{ownerId}, #{introduce}," +
            " #{price}, #{capacity}, #{size}, #{arrivalTime}, #{departureTime}, #{type}, #{picture})")
    Integer insert(RoomDetail roomDetail);

    @Select("select * from room_detail where owner_id = #{ownerId}")
    @Results({
            @Result(property = "arrivalTime", column = "arrival_time"),
            @Result(property = "departureTime", column = "departure_time")
    })
    List<RoomDetail> selectByOwnerId(Integer ownerId);

    @Update("update room_detail set visiting_times = visiting_times + 1 where id = #{id}")
    void updateVisitingTimesById(Integer id);

    @Select("select visiting_times from room_detail where owner_id = #{ownerId}")
    List<Integer> selectVisitingTimes(Integer ownerId);

    @Select("SELECT IFNULL(t.num,0) FROM room_detail r LEFT JOIN (\n" +
            "SELECT r.id id, COUNT(*) num FROM room_detail r JOIN `order` o ON r.id=o.room_id GROUP BY r.id\n" +
            ") t ON r.id=t.id WHERE r.owner_id = #{ownerId} GROUP BY r.id")
    List<Integer> selectNumberOfRoomCheckIn(Integer ownerId);
}
