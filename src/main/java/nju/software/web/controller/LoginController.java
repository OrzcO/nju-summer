package nju.software.web.controller;


import nju.software.data.dataobject.User;
import nju.software.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @RequestMapping("/toLogin.do")
    public String toLogin() {
        System.out.println("to login.do");
        return "login";
    }

    @RequestMapping("/toTask.do")
    public String toTask() {
        System.out.println("to toTask.do");
        return "task";
    }

    @RequestMapping("/toClientManagement.do")
    public String toClientManagement() {
        System.out.println("to toClientManagement.do");
        return "clientManagement";
    }

    @RequestMapping("/toTest.do")
    public String toTest() {
        System.out.println("to toTest.do");
        return "test";
    }

    @RequestMapping("/toUserInfo.do")
    public String toUserInfo() {
        System.out.println("to toUserInfo.do");
        return "userInfo";
    }

    @RequestMapping("/toUserManagement.do")
    public String toUserManagement() {
        System.out.println("to toUserManagement.do");
        return "userManagement";
    }



    @RequestMapping("/login.do")
    public void login(HttpServletRequest httpServletRequest) {
        System.out.println("login action");

        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        System.out.println("username : " + username + " - " + "password : " + password);


    }

    @ResponseBody
    @RequestMapping("/testAjax.do")
    public int testAjax() {
        System.out.println("/testAjax.do");
        return 1;
    }


    @RequestMapping("/getUserList.do")
    @ResponseBody
    public List<User> getUserList() {
        System.out.println("getUserList.do");

        return userService.getUserList();

//        List<User> list = new ArrayList<>();
//        list.add(userService.selectByPrimaryKey(1));
//        return list;

    }
}
