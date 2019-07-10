package com.tn.controller.page;

import com.tn.entity.Users;
import com.tn.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller("usersController1")
@RequestMapping("/page/")
public class UsersController {
    @Autowired
    private UsersService usersService;


    //通过用户名查询用户
    @RequestMapping(value = "checkUserName")
    @ResponseBody
    public String checkUserName(String name){
        boolean check = usersService.checkUserName(name);
        System.out.println(1);
        return "{\"result\":"+check+"}";
    }

    //添加用户
    @RequestMapping("regs")
    public String regs(Users users, Model model){
        boolean result = usersService.addUsers(users);
        if(result){
            return "redirect:login.jsp";
        }else {
            return "redirect:regs.jsp";
        }
    }

    //登录用户
    @RequestMapping("login")
    public String login(String name, String password, Model model, HttpSession session){

        Users users = usersService.loginUsers(name, password);
        if(users==null){
            model.addAttribute("loginInfo","用户名或密码错误!");
            return "login";
        }else {
            session.setAttribute("users",users);
            session.setMaxInactiveInterval(600);
            return "redirect:getHouseByUid";
        }
    }
}

