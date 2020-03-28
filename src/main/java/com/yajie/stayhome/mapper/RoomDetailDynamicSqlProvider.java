package com.yajie.stayhome.mapper;

import com.yajie.stayhome.model.RoomDetail;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author Lenovo
 */
public class RoomDetailDynamicSqlProvider {

    public String updateRoomDetail(RoomDetail roomDetail) {
        return new SQL() {
            {
                UPDATE("room_detail");
                if (roomDetail.getOwnerId() != null) {
                    SET(" owner_id = #{ownerId} ");
                }
                if (roomDetail.getIntroduce() != null) {
                    SET(" introduce = #{introduce} ");
                }
                if (roomDetail.getPrice() != null) {
                    SET(" price = #{price} ");
                }
                if (roomDetail.getCapacity() != null) {
                    SET(" capacity = #{capacity} ");
                }
                if (roomDetail.getSize() != null) {
                    SET(" size = #{size} ");
                }
                if (roomDetail.getArrivalTime() != null) {
                    SET(" arrival_time = #{arrivalTime} ");
                }
                if (roomDetail.getDepartureTime() != null) {
                    SET(" departure_time = #{departureTime} ");
                }
                if (roomDetail.getType() != null) {
                    SET(" type = #{type} ");
                }
                if (roomDetail.getPicture() != null) {
                    SET(" picture = #{picture} ");
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }
}
