package com.yajie.stayhome.controller;

import com.yajie.stayhome.model.RoomDetail;
import com.yajie.stayhome.model.User;
import com.yajie.stayhome.service.OrderService;
import com.yajie.stayhome.service.RoomDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @author Lenovo
 */
@Controller
public class RoomController {

    @Autowired
    private RoomDetailService roomDetailService;

    @Autowired
    private OrderService orderService;

    @GetMapping("room_detail")
    public String roomDetail(@RequestParam("id") int id, Model model) {
        roomDetailService.addVisitingTimesById(id);
        RoomDetail roomDetail = roomDetailService.getRoomDetailById(id);
        model.addAttribute("roomDetail", roomDetail);

        return "room_detail";
    }

    @GetMapping("room_list")
    public String roomList(@RequestParam(value="pageNo",defaultValue="1", required = false)int pageNo,
                           @RequestParam(value="pageSize",defaultValue="4", required = false)int pageSize,
                           @RequestParam(value = "room_type", required = false) String type,
                           Model model) {
//        PageInfo<RoomDetail> page = roomDetailService.getRoomDetailForPage(pageNo, pageSize, type);
//        model.addAttribute("page", page);
        List<RoomDetail> roomDetailList = null;
        if (type == null || "".equals(type)) {
            roomDetailList = roomDetailService.getRoomDetail();
        } else {
            roomDetailList = roomDetailService.getRoomDetailByType(type);
        }
        model.addAttribute("roomDetailList", roomDetailList);
        return "room_list";
    }

    @PostMapping("book_room")
    public String bookRoom(@RequestParam("arrivalDate") String arrivalDate,
                           @RequestParam("departureDate") String departureDate,
                           @RequestParam("adultNumber") Integer adultNumber,
                           @RequestParam("childrenNumber") Integer childrenNumber,
                           @RequestParam("roomId") Integer roomId,
                           Model model,
                           HttpServletRequest request) {
        RoomDetail roomDetail = roomDetailService.getRoomDetailById(roomId);
        model.addAttribute("arrivalDate", arrivalDate);
        model.addAttribute("departureDate", departureDate);
        model.addAttribute("adultNumber", adultNumber);
        model.addAttribute("childrenNumber", childrenNumber);
        model.addAttribute("roomDetail", roomDetail);
        request.getSession().setAttribute("arrivalDate", arrivalDate);
        request.getSession().setAttribute("departureDate", departureDate);
        request.getSession().setAttribute("adultNumber", adultNumber);
        request.getSession().setAttribute("childrenNumber", childrenNumber);
        request.getSession().setAttribute("roomDetail", roomDetail);
        return "reservation";
    }

    @GetMapping("book_confirmation")
    public String bookConfirmation(HttpServletRequest request) throws ParseException {
        String arrivalDate = (String) request.getSession().getAttribute("arrivalDate");
        String departureDate = (String) request.getSession().getAttribute("departureDate");
        Integer adultNumber = (Integer) request.getSession().getAttribute("adultNumber");
        Integer childrenNumber = (Integer) request.getSession().getAttribute("childrenNumber");
        RoomDetail roomDetail = (RoomDetail) request.getSession().getAttribute("roomDetail");
        User user = (User) request.getSession().getAttribute("user");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //Integer totalDay = Integer.parseInt(departureDate.substring(8))-Integer.parseInt(arrivalDate.substring(8));
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(format.parse(departureDate));
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(format.parse(arrivalDate));
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        int totalDay=day1-day2;
        Integer total = roomDetail.getPrice()*totalDay;
        orderService.addOrder(user.getId(), format.parse(arrivalDate), format.parse(departureDate),
                adultNumber, childrenNumber, roomDetail.getId(), total);
        return "redirect:book_success";
    }

    @GetMapping("book_success")
    public String success() {
        return "book_success";
    }

}

