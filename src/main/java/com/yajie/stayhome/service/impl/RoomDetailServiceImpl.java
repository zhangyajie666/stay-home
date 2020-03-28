package com.yajie.stayhome.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yajie.stayhome.mapper.RoomDetailMapper;
import com.yajie.stayhome.model.RoomDetail;
import com.yajie.stayhome.service.RoomDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lenovo
 */
@Service
public class RoomDetailServiceImpl implements RoomDetailService {

    @Autowired
    private RoomDetailMapper roomDetailMapper;

    @Override
    public List<RoomDetail> getRoomDetail() {
        return roomDetailMapper.select();
    }

    @Override
    public PageInfo<RoomDetail> getRoomDetailForPage(int pageNo, int pageSize, String type) {
        PageHelper.startPage(pageNo, pageSize);
        List<RoomDetail> roomDetailList;
        if (type == null || "".equals(type)) {
            roomDetailList = getRoomDetail();
        } else {
            roomDetailList = getRoomDetailByType(type);
        }
        PageInfo<RoomDetail> pageInfo = new PageInfo<>(roomDetailList);
        return pageInfo;
    }

    @Override
    public List<RoomDetail> getRoomDetailByType(String type) {
        return roomDetailMapper.selectByType(type);
    }

    @Override
    public RoomDetail getRoomDetailById(Integer id) {
        return roomDetailMapper.selectById(id);
    }

    @Override
    public Integer getRoomDetailCount() {
        return roomDetailMapper.selectCount();
    }

    @Override
    public Integer modifyRoomDetailById(RoomDetail roomDetail) {
        return roomDetailMapper.updateById(roomDetail);
    }

    @Override
    public Integer removeRoomDetailById(Integer id) {
        return roomDetailMapper.deleteById(id);
    }

    @Override
    public Integer removeRoomDetailByList(List<Integer> list) {
        return roomDetailMapper.deleteByList(list);
    }

    @Override
    public Integer addRoomDetail(RoomDetail roomDetail) {
        return roomDetailMapper.insert(roomDetail);
    }

    @Override
    public List<RoomDetail> getRoomDetailListByOwnerId(Integer ownerId) {
        return roomDetailMapper.selectByOwnerId(ownerId);
    }

    @Override
    public void addVisitingTimesById(Integer id) {
        roomDetailMapper.updateVisitingTimesById(id);
    }

    @Override
    public List<Integer> getVisitingTimes(Integer ownerId) {
        return roomDetailMapper.selectVisitingTimes(ownerId);
    }

    @Override
    public List<Integer> getNumberOfRoomCheckIn(Integer ownerId) {
        return roomDetailMapper.selectNumberOfRoomCheckIn(ownerId);
    }
}
