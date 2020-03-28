package com.yajie.stayhome.service;

import com.github.pagehelper.PageInfo;
import com.yajie.stayhome.model.RoomDetail;

import java.util.List;

/**
 * @author Lenovo
 */
public interface RoomDetailService {

    /**
     * 获取所有的RoomDetail
     * @return
     */
    List<RoomDetail> getRoomDetail();

    /**
     * 根据页码和每页大小获取RoomDetail
     * @param pageNo
     * @param pageSize
     * @return oomDetail
     */
    PageInfo<RoomDetail> getRoomDetailForPage(int pageNo, int pageSize, String type);

    /**
     * 根据房间类型查找RoomDetail
     * @param type
     * @return
     */
    List<RoomDetail> getRoomDetailByType(String type);

    /**
     *
     * @param id
     * @return
     */
    RoomDetail getRoomDetailById(Integer id);

    /**
     * 获取房源数量
     * @return
     */
    Integer getRoomDetailCount();

    /**
     * 根据id修改RoomDetail信息
     * @param roomDetail
     */
    Integer modifyRoomDetailById(RoomDetail roomDetail);

    /**
     * 根据id删除RoomDetail
     * @param id
     * @return
     */
    Integer removeRoomDetailById(Integer id);

    /**
     * 删除id list中的roomDetail
     * @param list
     * @return
     */
    Integer removeRoomDetailByList(List<Integer> list);

    /**
     * 添加RoomDetail
     * @param roomDetail
     * @return
     */
    Integer addRoomDetail(RoomDetail roomDetail);

    /**
     * 根据ownerId查找所有房源细节
     * @param ownerId
     * @return
     */
    List<RoomDetail> getRoomDetailListByOwnerId(Integer ownerId);

    /**
     * 通过房间id增加访问次数
     * @param id 房间号
     */
    void addVisitingTimesById(Integer id);

    /**
     * 根据ownerId获取所有房源访问次数
     * @param ownerId 房主id
     * @return 房源访问次数
     */
    List<Integer> getVisitingTimes(Integer ownerId);

    /**
     * 根据ownerId获取所有房源入住次数
     * @param ownerId
     * @return
     */
    List<Integer> getNumberOfRoomCheckIn(Integer ownerId);
}
