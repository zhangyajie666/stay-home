package com.yajie.stayhome.controller;

import com.yajie.stayhome.model.Order;
import com.yajie.stayhome.model.User;
import com.yajie.stayhome.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author Lenovo
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("order_list")
    public String orderList(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        List<Order> orderList = orderService.getOrderByUserId(user.getId());
        model.addAttribute("orderList", orderList);
        return "order_list";
    }

    @GetMapping("order_cancel")
    public String orderCancel(@RequestParam("id") String id) {
        Date arrivalTimeByOrderID = orderService.getArrivalTimeByOrderID(id);
        if(arrivalTimeByOrderID==null){
            orderService.removeOrderById(id);
        }else {

        }

        return "redirect:order_list";
    }

    @GetMapping("order_comment")
    public String orderComment(@RequestParam("id") String id,
                               HttpServletRequest request) {
        request.getSession().setAttribute("orderId", id);
        return "order_comment";
    }

    @PostMapping("order_comment")
    public String doOrderComment(@RequestParam("message") String message,
                               HttpServletRequest request) {
        String id = (String) request.getSession().getAttribute("orderId");
        orderService.modifyCommentById(message, id);
        request.getSession().removeAttribute("orderId");
        return "redirect:order_list";
    }
}
