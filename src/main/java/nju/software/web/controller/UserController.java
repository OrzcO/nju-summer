package nju.software.web.controller;

import nju.software.data.dataobject.User;
import nju.software.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {
    @Resource
    private UserService userService;


    @ResponseBody
    @RequestMapping("/getUserList.do")
    public List<User> getUserList() {
        List<User> list = userService.getUserList();

        System.out.println("/getUserList.do : " + list.size() );

        return list;
    }

    @ResponseBody
    @RequestMapping("/upload.do")
    public int upload(@RequestParam("file") MultipartFile file , HttpServletRequest httpServletRequest) throws IOException {
        String basePath = "F:\\upload";
        String path = "";
        String filename = "";

        System.out.println("getOriginalFilename : " + file.getOriginalFilename());

        int id = 0;
        if (httpServletRequest.getParameter("id") != null && !httpServletRequest.getParameter("id").equals("")) {
            id = Integer.parseInt(httpServletRequest.getParameter("id"));
        }

        filename = "user-" + id  +  file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        path = basePath + File.separator + filename;

        System.out.println("filename : " + filename);
        System.out.println("path : " + path);
        File FILE = new File(path);

        file.transferTo(FILE);

        userService.updateUserUrl(id , path);

        return 1;

    }


    @ResponseBody
    @RequestMapping("/reset.do")
    public int reset(HttpServletRequest httpServletRequest) {
        int id = Integer.parseInt(httpServletRequest.getParameter("id"));
        String password = httpServletRequest.getParameter("password");
        String email = httpServletRequest.getParameter("email");
        String phone = httpServletRequest.getParameter("phone");


        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);

        return userService.updateUser(user);
    }


}
