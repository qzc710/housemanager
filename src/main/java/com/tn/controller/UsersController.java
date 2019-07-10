package com.tn.controller;

import com.github.pagehelper.PageInfo;
import com.tn.entity.Users;
import com.tn.service.UsersService;
import com.tn.utils.UsersParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class UsersController {
    @Autowired
    private UsersService usersService;

    //得到用户所有信息
    @RequestMapping("getUsersByPage")
    @ResponseBody
    public Map<String,Object> getUsersByPage(UsersParam usersParam){
        PageInfo<Users> pageInfo = usersService.getUsersByPage(usersParam);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    //添加用户
    @RequestMapping("addUsers")
    @ResponseBody
    public String addUsers(Users users){
        boolean result = usersService.addUsers(users);

        return "{\"result\":"+result+"}";
    }
    //删除单个用户
    @RequestMapping("deleteUsersList")
    @ResponseBody
    public String deleteUsersList(Integer[] ids){

        boolean result = usersService.deleteUsersList(ids);

        return "{\"result\":"+result+"}";
    }
    //删除多个用户
    @RequestMapping("deleteUsers")
    @ResponseBody
    public String deleteUser(Integer id){

        boolean result = usersService.deleteUsers(id);

        return "{\"result\":"+result+"}";
    }
    //修改用户
    @RequestMapping("updateUsers")
    @ResponseBody
    public String updateUsers(Users users){

        boolean result = usersService.updateUsers(users);

        return "{\"result\":"+result+"}";
    }

    //得到用户
    @RequestMapping("getUsersById")
    @ResponseBody
    public Users getUsersById(Integer id){

        Users users = usersService.getUsersById(id);
        return users;
    }

}
