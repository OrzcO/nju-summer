package nju.software.web.controller;


import nju.software.data.dataobject.User;
import nju.software.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @RequestMapping("/toLogin.do")
    public String toLogin() {
//        System.out.println("to login.do");
        return "login";
    }

    @ResponseBody
    @RequestMapping("/login.do")
    public int login(HttpServletRequest httpServletRequest) {

        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");


        User user = userService.login(username , password);

        System.out.println("login action - username : " + username + " - " + "password : " + password + " - " + user==null);

        if (user != null) {
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("user" , user);
            return 1;
        }

        return 0;

    }

    @ResponseBody
    @RequestMapping("/getSession.do")
    public Map<String , String> getSession(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();

        User user = (User)session.getAttribute("user");

        if (user == null) {
            return null;
        }

        Map<String , String> map = new HashMap();
        map.put("id" , String.valueOf(user.getId()));
        map.put("name" , user.getName());
        map.put("username" , user.getUsername());
        map.put("url", user.getUrl());

        return map;

    }

    @ResponseBody
    @RequestMapping("/logout.do")
    public void logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        session.invalidate();
    }












    @RequestMapping("/toTask.do")
    public String toTask() {
//        System.out.println("to toTask.do");
        return "task";
    }

    @RequestMapping("/toClient.do")
    public String toClient() {
//        System.out.println("to toClientManagement.do");
        return "client";
    }

    @RequestMapping("/toTest.do")
    public String toTest() {
//        System.out.println("to toTest.do");
        return "test";
    }

    @RequestMapping("/toUserInfo.do")
    public String toUserInfo() {
//        System.out.println("to toUserInfo.do");
        return "userInfo";
    }

    @RequestMapping("/toMain.do")
    public String toMain() {
//        System.out.println("to toUserManagement.do");
        return "main";
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
//        System.out.println("getUserList.do");

        return userService.getUserList();

//        List<User> list = new ArrayList<>();
//        list.add(userService.selectByPrimaryKey(1));
//        return list;

    }
}
