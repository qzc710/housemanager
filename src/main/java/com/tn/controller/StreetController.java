package com.tn.controller;

import com.github.pagehelper.PageInfo;
import com.tn.entity.Street;
import com.tn.service.StreetService;
import com.tn.utils.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class StreetController {

    @Autowired
    private StreetService streetService;
   @RequestMapping("getStreetByPage")
   @ResponseBody
    public Map<String,Object> getStreetByPage(String name,PageParam pageParam){
       PageInfo<Street> pageInfo = streetService.getStreetByPage(name,pageParam);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
   }

    @RequestMapping("getStreetByDid")
    @ResponseBody
    public Map<String,Object> getStreetByDid(Integer id,PageParam pageParam){

        PageInfo<Street> pageInfo = streetService.getStreetByDid(id,pageParam);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("getStreetById")
    @ResponseBody
    public Street getStreetById(Integer id){

        Street street= streetService.getStreetById(id);

        return  street;
    }

    @RequestMapping("addStreet")
    @ResponseBody
    public String getStreetByDid(Street street){

        boolean result = streetService.addStreet(street);

        return " {\"result\":"+result+"}";
    }
    @RequestMapping("updateStreet")
    @ResponseBody
    public String updateStreet(Street street){

        boolean result = streetService.updateStreet(street);

        return " {\"result\":"+result+"}";
    }
    @RequestMapping("deleteStreet")
    @ResponseBody
    public String deleteStreet(Integer id){

        boolean result = streetService.deleteStreetById(id);

        return " {\"result\":"+result+"}";
    }

    @RequestMapping("deleteStreetList")
    @ResponseBody
    public String deleteStreetList(Integer[] ids){

        boolean result = streetService.deleteStreetList(ids);

        return " {\"result\":"+result+"}";
    }
}
