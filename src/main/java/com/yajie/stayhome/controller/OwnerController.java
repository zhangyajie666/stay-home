package com.yajie.stayhome.controller;

import com.yajie.stayhome.model.*;
import com.yajie.stayhome.service.OrderService;
import com.yajie.stayhome.service.RoomDetailService;
import com.yajie.stayhome.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Lenovo
 */
@Controller
public class OwnerController {

    @Autowired
    private RoomDetailService roomDetailService;

    @Autowired
    private OrderService orderService;

    @Value("${picture-upload-project-path}")
    private String pictureUploadProjectPath;

    @GetMapping("owner-index")
    public String ownerIndex() {
        return "owner-index";
    }

    @GetMapping("owner-chart")
    public String ownerWelcome() {
        return "owner-chart";
    }

    @GetMapping("owner-room-list")
    public String roomList(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        List<RoomDetail> roomDetailList = roomDetailService.getRoomDetailListByOwnerId(user.getId());
        model.addAttribute("roomDetailList", roomDetailList);
        model.addAttribute("listSize", roomDetailList.size());
        return "owner-room-list";
    }

    @GetMapping("owner-room-edit")
    public String roomEdit(@RequestParam("id") Integer id, Model model) {
        RoomDetail roomDetail = roomDetailService.getRoomDetailById(id);
        model.addAttribute("roomDetail", roomDetail);
        return "owner-room-edit";
    }

    @PostMapping("owner-room-edit")
    @ResponseBody
    public Result<RoomDetail> doRoomEdit(@RequestParam("id") Integer id,
                                         @RequestParam("introduce") String introduce,
                                         @RequestParam("price") Integer price,
                                         @RequestParam("capacity") Integer capacity,
                                         @RequestParam("size") Integer size,
                                         @RequestParam("arrivalTime") String arrivalTime,
                                         @RequestParam("departureTime") String departureTime,
                                         @RequestParam("type") Integer type,
                                         @RequestParam(value = "picture", required = false) MultipartFile picture,
                                         HttpServletRequest request) {
        Result<RoomDetail> result = new Result<>();

        RoomDetail roomDetail = new RoomDetail();
        roomDetail.setId(id);
        roomDetail.setIntroduce(introduce);
        roomDetail.setPrice(price);
        roomDetail.setCapacity(capacity);
        roomDetail.setSize(size);
        roomDetail.setArrivalTime(Time.valueOf(arrivalTime));
        roomDetail.setDepartureTime(Time.valueOf(departureTime));
        roomDetail.setType(type);
        if (picture != null) {
            //获取文件名
            String fileName = picture.getOriginalFilename();
            //获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //重新生成文件名
            fileName = UUID.randomUUID()+suffixName;
            try {
                String path = request.getServletContext().getRealPath("/upload/");
                File realPath = new File(path);
                if (!realPath.exists()) {
                    realPath.mkdirs();
                }
                File file = new File(realPath, fileName);
                picture.transferTo(file);

                FileCopyUtils.copy(file, new File(pictureUploadProjectPath, fileName));
                roomDetail.setPicture("/upload/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                result.setStatus(Constants.HTTP_STATUS_ERROR);
                result.setMsg("图片上传失败");
                result.setData(null);
                return result;
            }
        }
        Integer row = roomDetailService.modifyRoomDetailById(roomDetail);

        if (row == 1) {
            result.setMsg("修改成功");
            result.setStatus(Constants.HTTP_STATUS_OK);
            result.setData(roomDetail);
        } else {
            result.setMsg("修改失败");
            result.setStatus(Constants.HTTP_STATUS_ERROR);
            result.setData(null);
        }
        return result;
    }

    @PostMapping("owner-room-del")
    @ResponseBody
    public Result<RoomDetail> roomDel(@RequestParam(value = "id", required = false) Integer id,
                                      @RequestParam(value = "arr[]", required = false) List<Integer> list) {
        Integer row = null;
        if (id != null) {
            row = roomDetailService.removeRoomDetailById(id);
        } else if (list != null) {
            row = roomDetailService.removeRoomDetailByList(list);
        }

        Result<RoomDetail> result = new Result<>();
        if (row != null) {
            result.setStatus(Constants.HTTP_STATUS_OK);
            result.setMsg("删除成功");
            result.setData(null);
        } else {
            result.setStatus(Constants.HTTP_STATUS_ERROR);
            result.setMsg("删除失败");
            result.setData(null);
        }
        return result;
    }

    @GetMapping("owner-room-add")
    public String roomAdd() {
        return "owner-room-add";
    }

    @PostMapping("owner-room-add")
    @ResponseBody
    public Result<RoomDetail> doRoomAdd(@RequestParam("introduce") String introduce,
                                        @RequestParam("price") Integer price,
                                        @RequestParam("capacity") Integer capacity,
                                        @RequestParam("size") Integer size,
                                        @RequestParam("arrivalTime") String arrivalTime,
                                        @RequestParam("departureTime") String departureTime,
                                        @RequestParam("type") Integer type,
                                        @RequestParam(value = "picture", required = false) MultipartFile picture,
                                        HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Result<RoomDetail> result = new Result<>();
        RoomDetail roomDetail = new RoomDetail();
        roomDetail.setIntroduce(introduce);
        roomDetail.setPrice(price);
        roomDetail.setOwnerId(user.getId());
        roomDetail.setCapacity(capacity);
        roomDetail.setSize(size);
        roomDetail.setArrivalTime(Time.valueOf(arrivalTime));
        roomDetail.setDepartureTime(Time.valueOf(departureTime));
        roomDetail.setType(type);
        if (picture != null) {
            //获取文件名
            String fileName = picture.getOriginalFilename();
            //获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //重新生成文件名
            fileName = UUID.randomUUID()+suffixName;
            try {
                String path = request.getServletContext().getRealPath("/upload/");
                File realPath = new File(path);
                if (!realPath.exists()) {
                    realPath.mkdirs();
                }
                File file = new File(realPath, fileName);
                picture.transferTo(file);
                FileCopyUtils.copy(file, new File(pictureUploadProjectPath, fileName));
                roomDetail.setPicture("/upload/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                result.setStatus(Constants.HTTP_STATUS_ERROR);
                result.setMsg("图片上传失败");
                result.setData(null);
                return result;
            }
        }
        Integer row = roomDetailService.addRoomDetail(roomDetail);
        if (row == 1) {
            result.setMsg("添加成功");
            result.setStatus(Constants.HTTP_STATUS_OK);
            result.setData(roomDetail);
        } else {
            result.setMsg("添加失败");
            result.setStatus(Constants.HTTP_STATUS_ERROR);
            result.setData(null);
        }
        return result;
    }

    @GetMapping("owner-non-arrival-order-list")
    public String ownerNonArrivalOrderList(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        List<Order> orderList = orderService.getNonArrivalOrderByOwnerId(user.getId());
        model.addAttribute("orderList", orderList);
        return "owner-non-arrival-order-list";
    }

    @PostMapping("owner-check-in")
    @ResponseBody
    public Result<Order> checkIn(@RequestParam("id") String id) {
        Integer row = orderService.modifyOrderArrivalTimeById(id, new Date());
        Result<Order> result = new Result<>();
        if (row == 1) {
            result.setStatus(Constants.HTTP_STATUS_OK);
            result.setMsg("入住成功");
            result.setData(null);
        } else {
            result.setStatus(Constants.HTTP_STATUS_ERROR);
            result.setMsg("入住失败");
            result.setData(null);
        }
        return result;
    }

    @GetMapping("owner-arrival-order-list")
    public String ownerArrivalOrderList(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        List<Order> orderList = orderService.getArrivalOrderByOwnerId(user.getId());
        model.addAttribute("orderList", orderList);
        return "owner-arrival-order-list";
    }

    @PostMapping("owner-check-out")
    @ResponseBody
    public Result<Order> checkOut(@RequestParam("id") String id) {
        Integer row = orderService.modifyOrderDepartureTimeById(id, new Date());
        Result<Order> result = new Result<>();
        if (row == 1) {
            result.setStatus(Constants.HTTP_STATUS_OK);
            result.setMsg("退房成功");
            result.setData(null);
        } else {
            result.setStatus(Constants.HTTP_STATUS_ERROR);
            result.setMsg("退房失败");
            result.setData(null);
        }
        return result;
    }

    @GetMapping("owner-chart-data")
    @ResponseBody
    public List<EchartsData> getEchartsData(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        EchartsData data1 = new EchartsData("访问次数", "bar",
                roomDetailService.getVisitingTimes(user.getId()));
        EchartsData data2 = new EchartsData("入住次数", "bar",
                roomDetailService.getNumberOfRoomCheckIn(user.getId()));
        List<EchartsData> list = new ArrayList<>();
        list.add(data1);
        list.add(data2);
        return list;
    }

   /* @GetMapping("owner-chart-data")
    @ResponseBody
    public Map<String,Object> getEchartsData(HttpServletRequest request) {


        User user = (User) request.getSession().getAttribute("user");

        EchartsData data1 = new EchartsData("访问次数", "bar",
                roomDetailService.getVisitingTimes(user.getId()));
        EchartsData data2 = new EchartsData("入住次数", "bar",
                roomDetailService.getNumberOfRoomCheckIn(user.getId()));
        List<EchartsData> list = new ArrayList<>();
        list.add(data1);
        list.add(data2);
        List<String> introduce = new ArrayList<>();
        List<RoomDetail> room = roomDetailService.getRoomDetailListByOwnerId(user.getId());
        for (RoomDetail r: room) {
                introduce.add(r.getIntroduce());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("X",list);
        map.put("Y",introduce);
        return map;
    }*/



}
