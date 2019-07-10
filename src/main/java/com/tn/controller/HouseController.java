package com.tn.controller;

import com.github.pagehelper.PageInfo;
import com.tn.entity.House;
import com.tn.service.HouseService;
import com.tn.utils.CheckParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class HouseController {

    @Autowired
    private HouseService houseService;

    //通过通过审核的用户
    @RequestMapping("getHouseByPass")
    @ResponseBody
    public Map<String,Object> getHouseByPass(CheckParam checkParam){
        PageInfo<House> pageInfo = houseService.getHouseIsPass(1,checkParam);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    //没有通过审核的用户
    @RequestMapping("getHouseByNopass")
    @ResponseBody
    public Map<String,Object> getHouseByNopass(CheckParam checkParam){

        //0就是没有同审核
        PageInfo<House> pageInfo = houseService.getHouseIsPass(0,checkParam);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }


    //审核
    @RequestMapping("checkHouse")
    @ResponseBody
    public String checkHouse(String id){

        boolean result = houseService.isPassCheck(1,id);
        return " {\"result\":"+result+"}";
    }

    //重新审核
    @RequestMapping("reCheckHouse")
    @ResponseBody
    public String reCheckHouse(String id){
        boolean result = houseService.isPassCheck(0,id);
        return " {\"result\":"+result+"}";
    }
    //审核多个
    @RequestMapping("checkMoreHouse")
    @ResponseBody
    public String checkMoreHouse(String [] ids){
        boolean result = houseService.isPassMoreCheck(1,ids);
        return " {\"result\":"+result+"}";
    }

    //重新审核多个
    @RequestMapping("recheckMoreHouse")
    @ResponseBody
    public String recheckMoreHouse(String [] ids){
        boolean result = houseService.isPassMoreCheck(0,ids);
        return " {\"result\":"+result+"}";
    }

}
