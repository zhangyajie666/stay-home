package com.yajie.stayhome.controller;

import com.yajie.stayhome.model.Admin;
import com.yajie.stayhome.model.Result;
import com.yajie.stayhome.model.RoomDetail;
import com.yajie.stayhome.model.User;
import com.yajie.stayhome.service.AdminService;
import com.yajie.stayhome.service.OrderService;
import com.yajie.stayhome.service.RoomDetailService;
import com.yajie.stayhome.service.UserService;
import com.yajie.stayhome.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Lenovo
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RoomDetailService roomDetailService;

    @Value("${picture-upload-project-path}")
    private String pictureUploadProjectPath;

    @GetMapping("/admin-login")
    public String login() {
        return "admin-login";
    }

    @PostMapping("admin-login")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpServletRequest request,
                          Model model) {
        Admin admin = adminService.getAdminByUsernameAndPassword(username, password);

        if (admin != null) {
            request.getSession().setAttribute("admin", admin);
            request.getSession().setAttribute("userCount", userService.getUserCount());
            request.getSession().setAttribute("orderCount", orderService.getOrderCount());
            request.getSession().setAttribute("orderCommentCount", orderService.getOrderCommentCount());
            request.getSession().setAttribute("roomDetailCount", roomDetailService.getRoomDetailCount());
            return "redirect:admin-index";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "admin-login";
        }
    }

    @GetMapping("admin-logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("admin");
        return "redirect:admin-login";
    }

    @GetMapping("admin-index")
    public String index() {
        return "admin-index";
    }

    @GetMapping("admin-welcome")
    public String welcome() {
        return "admin-welcome";
    }

    @GetMapping("admin-user-list")
    public String userList(@RequestParam(value = "start", required = false) String start,
                           @RequestParam(value = "end", required = false) String end,
                           @RequestParam(value = "username", required = false) String username,
                           Model model) {
        Map<String, String> map = new HashMap<>();
        if (start != null && !("".equals(start))) {
            map.put("start", start + " 00:00:00");
        }
        if (end != null && !("".equals(end))) {
            map.put("end", end + " 23:59:59");
        }
        if (username != null && !("".equals(username))) {
            map.put("username", username);
        }
        List<User> userList = userService.getUserWithParam(map);
        model.addAttribute("userList", userList);
        model.addAttribute("userNumber", userList.size());
        return "admin-user-list";
    }

    @GetMapping("admin-user-edit")
    public String userEdit(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin-user-edit";
    }

    @PostMapping("admin-user-edit")
    @ResponseBody
    public ResponseEntity<?> doUserEdit(@RequestParam("username") String username,
                                        @RequestParam("phone") String phone,
                                        @RequestParam("email") String email,
                                        @RequestParam("role") String role,
                                        @RequestParam("pass") String pass,
                                        @RequestParam("repass") String repass) {

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole("旅客".equals(role) ? 1 : 2);
        user.setPassword(pass);
        Integer row = userService.modifyUserByUsername(user);
        if (row != 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("admin-user-del")
    @ResponseBody
    public ResponseEntity<?> userDel(@RequestParam(value = "id", required = false) Integer id,
                                     @RequestParam(value = "arr[]", required = false) List<Integer> list) {
        Integer result = null;
        if (id != null) {
            result = userService.removeUserById(id);
        } else if (list != null) {
            result = userService.removeUserByList(list);
        }
        if (result != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("admin-user-add")
    public String userAdd() {
        return "admin-user-add";
    }

    @PostMapping("admin-user-add")
    @ResponseBody
    public Result<User> doUserAdd(@RequestParam("username") String username,
                            @RequestParam("phone") String phone,
                            @RequestParam("email") String email,
                            @RequestParam("pass") String pass,
                            @RequestParam("repass") String repass,
                            @RequestParam("role") String role) {
        Result<User> result = new Result<>();
        if (userService.getUserByUsername(username) != null) {
            result.setStatus(Constants.HTTP_STATUS_ERROR);
            result.setMsg("用户已存在");
            result.setData(null);
        } else {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(pass);
            user.setPhone(phone);
            user.setRole("旅客".equals(role) ? 1 : 2);
            user.setCreateTime(new Timestamp(System.currentTimeMillis()));
            userService.addUser(user);
            result.setStatus(Constants.HTTP_STATUS_OK);
            result.setMsg("用户添加成功");
            result.setData(user);
        }
        return result;
    }

    @GetMapping("admin-room-list")
    public String roomList(Model model) {
        List<RoomDetail> roomDetailList = roomDetailService.getRoomDetail();
        model.addAttribute("roomDetailList", roomDetailList);
        model.addAttribute("roomDetailNumber", roomDetailList.size());
        return "admin-room-list";
    }

    @GetMapping("admin-room-edit")
    public String roomEdit(@RequestParam("id") Integer id, Model model) {
        RoomDetail roomDetail = roomDetailService.getRoomDetailById(id);
        model.addAttribute("roomDetail", roomDetail);
        return "admin-room-edit";
    }

    @PostMapping("admin-room-edit")
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
//                picture.transferTo(new File(pictureUploadProjectPath + fileName));
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

    @PostMapping("admin-room-del")
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

    @GetMapping("admin-room-add")
    public String roomAdd() {
        return "admin-room-add";
    }

    @PostMapping("admin-room-add")
    @ResponseBody
    public Result<RoomDetail> doRoomAdd(@RequestParam("ownerId") Integer ownerId,
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
        roomDetail.setOwnerId(ownerId);
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
}
