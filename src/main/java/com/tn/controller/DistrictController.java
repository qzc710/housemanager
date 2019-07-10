package com.tn.controller;

import com.github.pagehelper.PageInfo;
import com.tn.entity.District;
import com.tn.service.DistrictService;
import com.tn.utils.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    //查询所有街道信息
    @RequestMapping("getDistrictByPage")
    @ResponseBody
    public Map<String,Object> getDistrictByPage(String name, PageParam pageParam){

        PageInfo<District> pageInfo = districtService.getDistrictByPage(name,pageParam);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    //添加
    @RequestMapping("addDistrict")
    @ResponseBody
    public String addDistrict(District district){

        boolean result = districtService.addDistrict(district);

        return " {\"result\":"+result+"}";
    }

    //修改
    @RequestMapping("updateDistrict")
    @ResponseBody
    public String updateDistrict(District district){

        boolean result = districtService.updateDistrict(district);

        return " {\"result\":"+result+"}";
    }

    //通过id删除区域
    @RequestMapping("deleteDistrict")
    @ResponseBody
    public String deleteDistrict(Integer id){
       try {
           districtService.deleteDistrict(id);
           return " {\"result\":"+true+"}" ;
       }catch (Exception e){
           System.out.println("出现错误,事务回滚!");
       }
        return " {\"result\":"+false+"}";
    }
    //通过id删除多条区域
    @RequestMapping("deleteDistrictList")
    @ResponseBody
    public String deleteDistrictList(Integer[] ids){
        try {
            districtService.deleteDistrictList(ids);
            return " {\"result\":"+true+"}" ;
        }catch (Exception e){
            System.out.println("出现错误,事务回滚!");
        }
        return " {\"result\":"+false+"}";
    }

    //通过id得到区域
    @RequestMapping("getDistrictById")
    @ResponseBody
    public District getDistrictById(Integer id){

        District district= districtService.getDistrictById(id);

        return district;
    }

    //得到所有的区域
    @RequestMapping("getDistrictList")
    @ResponseBody
    public List<District> getDistrictList(){

        List<District> districtList = districtService.getDistrictList();

        return districtList;
    }
}
